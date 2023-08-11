package com.tjoeun.shoppingmall.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.websocket.*;
import javax.websocket.server.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tjoeun.helper.UsersType;
import com.tjoeun.shoppingmall.vo.UsersVO;





@ServerEndpoint("/chatting")
public class ChattingServer 
{
	private static Map<Session, WsInfoData> clients = Collections.synchronizedMap(new HashMap<Session, WsInfoData>());
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static class ServerCode
	{	
		private final static String INIT = "1";
		private final static String SEND = "2";
		private final static String ENTRY = "3";		
	}
	public static class ClientCode
	{
		private final static String SENDER = "1";
		private final static String SUCCESS = "0";
		private final static String ERROR = "-999";
		private final static String DATA_NOT_FOUND = "-998";
		private final static String ROOM_NOT_FOUND = "-997";
		private final static String ROOM_ENTRY_FAIL = "-996";
		private final static String NOT_AUTH = "-995";
		private final static String CREATE_ROOM_FAIL = "-994";		
	}
	
	
	private JSONParser parser = new JSONParser();	
	
	@OnOpen // 클라이언트 접속 시 실행
	public void onOpen(Session session) 
	{
		clients.put(session, null);
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException 
	{		
		JSONObject retval = new JSONObject();
		
		try 
		{
			JSONObject jMsg = (JSONObject)this.parser.parse(message);
			JSONObject result = (JSONObject)jMsg.get("result");
			
			switch(jMsg.get("requestCode").toString())
			{
			case ServerCode.INIT:
				{
					String id = (String)result.get("id");
					UsersVO vo = HttpSessionManagement.getInstance().getUserData(id);
					
					if(vo != null)
					{
						WsInfoData wsInfo = new WsInfoData(session, vo);
						
						clients.put(session, wsInfo);
						
						if(vo.getType() != null && vo.getType().equals(UsersType.SELLER))
						{
							if(wsInfo.createRoom(id))
							{
								// 구매자에게 자신이 접속했다고 알려주기
							}
							else
							{
								retval.put("code", ClientCode.CREATE_ROOM_FAIL);	
								retval.put("msg", "방 생성 실패");
							}
						}		
						
						retval.put("code", ClientCode.SUCCESS);	
						retval.put("msg", "접속 성공");		
					}
					else
					{
						retval.put("code", ClientCode.DATA_NOT_FOUND);
						retval.put("msg", "잘못된 데이터");					
					}		
				}
				break;
			
			case ServerCode.ENTRY:
				{
					String id = (String)result.get("id");	//방에 접속하려는 사용자의 아이디
					String roomId = (String)result.get("roomId");	//방 아이디
					Set<Session> keys = clients.keySet();
					Iterator<Session> iter = keys.iterator();
					String code = ClientCode.ROOM_NOT_FOUND;;
					String msg = "방을 찾을 수 없습니다.";
					WsInfoData user = clients.get(session);	// 방에 접속하려는 사용자의 데이터
					
					// 방에 접속하려는 사용자를 검사한다.
					if(user != null && user.getUserInfo().getId().equals(id))
					{
						//방을 찾는다.
						while(iter.hasNext())
						{
							WsInfoData wsInfo = clients.get(iter.next());
							WsRoomData wsRoom = wsInfo.getRoom();
							
							//방 아이디는 판매자의 아이디로 한다.
							if(wsRoom != null && wsRoom.getRoomId().equals(roomId))
							{
								if(wsRoom.entryRoom(user))
								{
									user.setRoom(wsRoom);									
									code = ClientCode.SUCCESS;
									msg = null;
								}
								else
								{
									code = ClientCode.ROOM_ENTRY_FAIL;
									msg = "방 접속 오류";
								}
								break;
							}
						}
					}
					else
					{
						code = ClientCode.NOT_AUTH;
						msg = "알수없음";
					}
										
					retval.put("code", code);
					retval.put("msg", msg);		
				}
				break;
				
			case ServerCode.SEND:			
				{
					String senderId = (String)result.get("senderId");
					String id = (String)result.get("id");
					WsInfoData wsInfo = clients.get(session);
					
					// 사용자 인증
					if(id.equals(wsInfo.getUserInfo().getId()))
					{
						WsRoomData room = wsInfo.getRoom();
						Iterator<WsInfoData> iter = room.getUsers().iterator();
						
						while(iter.hasNext())
						{
							WsInfoData roomUser = iter.next();
							if(senderId.equals(roomUser.getUserInfo().getId()))
							{
								String msg = (String)result.get("msg");								
								JSONObject senderData = new JSONObject();
								
								// 상대방에게 내용을 전송
								senderData.put("code", ClientCode.SENDER);
								senderData.put("msg", msg);
								senderData.put("senderId", senderId);
								roomUser.sendText(senderData.toJSONString());
								
								// 요청한 사용자에게 메시지가 성공적으로 전송을 됬음을 알림
								retval.put("code", ClientCode.SUCCESS);								
								break;
							}
						}
					}
					else
					{
						retval.put("code", ClientCode.NOT_AUTH);
						retval.put("msg", "알수없음");
					}
				}
				
				break;
			}
		} 
		catch (ParseException e) 
		{
			retval.put("code", "-999");
			retval.put("msg", e.getClass().getName() + ": " + e.getMessage());
		}
		
		session.getBasicRemote().sendText(retval.toJSONString());
	}

	@OnClose // 클라이언트와의 연결이 끊기면 실행
	public void onClose(Session session) 
	{
		WsInfoData wsInfo = clients.get(session);		
		if(wsInfo != null)
		{
			String id = wsInfo.getUserInfo().getId();		
			
			HttpSessionManagement.getInstance().sessionDestroyed(id);
		}
		
		clients.remove(session);
		logger.info("웹소켓 종료 : " + session.getId());
	}

	@OnError // 에러 발생 시 실행
	public void onError(Throwable e) 
	{
		logger.error("에러 발생", e);
	}
}
