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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tjoeun.helper.UsersType;
import com.tjoeun.helper.Util;
import com.tjoeun.shoppingmall.vo.UsersVO;





@ServerEndpoint("/chatting")
public class ChattingServer 
{
	private static Map<Session, WsInfoData> clients = Collections.synchronizedMap(new HashMap<Session, WsInfoData>());
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public static enum RequestCode
	{
		INIT("1", "인증"),
		SENDER("2", "대화 전송"),
		CREATE_ROOM("3", "방 생성"),
		ENTRY_ROOM("4", "방 참여"),
		
		NONE("0", "알수없음");
		
		private String code;
		private String msg;
		
		private RequestCode(String code, String msg)
		{
			this.code = code;
			this.msg = msg;
		}
		
		public String getCode()
		{
			return this.code;
		}
		
		public String getMsg()
		{
			return this.msg;
		}
	}
	
	public static enum ResponseCode
	{
		ERROR("-999", "오류"),
		
		INIT_SUCCESS("1", "초기화 성공"),
		SENDER_SUCCESS("2", "대화 전송 성공"),
		ENTRY_ROOM_SUCCESS("3", "방 참여 성공"),
		CREATE_ROOM_SUCCESS("4", "방 생성 성공"),
		
		CLOSE_USER("5", "유저 접속 종료"),
		
		
		CREATE_ROOM_FAIL_AUTH("-998", "잚못된 사용자로 인한 방 생성 실패"),
		ENTRY_ROOM_FAIL_NOT_FOUND("-997", "방을 찾을 수 없어서 참여를 할 수 없습니다."),
		INIT_FAIL_DATA("-996", "데이터가 존재하지 않음"), 
		INIT_FAIL_AUTH("-995", "잘못된 데이터"), 
		SENDER_FAIL_NOT_ROOM("-994", "방을 찾을 수 없어서 대화를 보낼 수 없습니다."), 
		SENDER_FAIL_AUTH("-993", "잘못된 데이터"),
		
		NONE("0", "알수없음");
			
			
			
		private String code;
		private String msg;
		
		private ResponseCode(String code, String msg)
		{
			this.code = code;
			this.msg = msg;
		}
		
		public String getCode()
		{
			return this.code;
		}
		
		public String getMsg()
		{
			return this.msg;
		}
	}
		
	private JSONParser parser = new JSONParser();	
	
	@OnOpen // 클라이언트 접속 시 실행
	public void onOpen(Session session) 
	{
		clients.put(session, null);
	}

	static public RequestCode StringToRequestCode(String strCode)
	{
		RequestCode[] arrCode = RequestCode.values();
		for(int i = 0; i<arrCode.length; i++)
		{
			if(arrCode[i].getCode().equals(strCode))
			{
				return arrCode[i];
			}
		}
		
		return null;
	}
	
	
	@OnMessage
	public void onMessage(String message, Session session) throws IOException 
	{		
		JSONObject retval = new JSONObject();
		
		try 
		{
			JSONObject jMsg = (JSONObject)parser.parse(message);
			RequestCode code = StringToRequestCode((String)jMsg.get("code"));
			
			switch(code)
			{
			case ENTRY_ROOM:
				{
					String id = (String)jMsg.get("id");	
					String roomId = (String)jMsg.get("roomId");
					WsInfoData wsInfo = clients.get(session);
										
					if(id != null && wsInfo != null && id.equals(wsInfo.getUserInfo().getId()))
					{										
						WsRoomData room = wsInfo.getRoom(roomId);
						
						if(room != null)
						{
							if(room.isEntryRoom(wsInfo) == false)
							{
								room.entryRoom(wsInfo);	
							}
														
							ArrayList<WsInfoData> entryUsers = room.getUsers();
							JSONArray enterUsers = new JSONArray();
							
							for(int i = 0; i<entryUsers.size(); i++)
							{
								enterUsers.add(								
										entryUsers.get(i).getUserInfo().getId()
								);
							}
							
							retval.put("entryUserList", enterUsers);
							retval.put("code", ResponseCode.ENTRY_ROOM_SUCCESS);
						}
						else
						{
							ResponseCode error = ResponseCode.ENTRY_ROOM_FAIL_NOT_FOUND; 
							retval.put("code", error.getCode());
							retval.put("msg", error.getMsg());	
						}
					}
					else
					{
						ResponseCode error = ResponseCode.CREATE_ROOM_FAIL_AUTH; 
						retval.put("code", error.getCode());
						retval.put("msg", error.getMsg());
					}
				}
				break;
				
				
			case CREATE_ROOM:
				{
					String id = (String)jMsg.get("id");	
					WsInfoData wsInfo = clients.get(session);
					
					if(id != null && wsInfo != null && id.equals(wsInfo.getUserInfo().getId()))
					{
						JSONArray enterUsers = new JSONArray();
						String roomId = Util.UUIDtoString();						
						JSONArray entryUsers = (JSONArray)jMsg.get("entryUsers");
						WsRoomData room = wsInfo.createRoom(roomId);
						
						// 자기자신을 방에 참여시킨다.
						room.entryRoom(wsInfo);
						
						for(int i = 0; i<entryUsers.size(); i++)
						{
							String entryUserId = (String)entryUsers.get(i);							
							Set<Session> keys = clients.keySet();
							Iterator<Session> iter = keys.iterator();
							while(iter.hasNext())
							{
								WsInfoData entryUserData = clients.get(iter.next());
								
								if(entryUserId.equals(entryUserData.getUserInfo().getId()))
								{
									// 방에 참여할 다른 사용자를 방에 참여한다.
									room.entryRoom(entryUserData);
									entryUserData.addRoom(room);
									enterUsers.add(entryUserId);
									break;
								}
							}
						}
						
						retval.put("code", ResponseCode.CREATE_ROOM_SUCCESS);
						retval.put("roomId", roomId);
						retval.put("entryUserList", enterUsers);
					}
					else
					{
						ResponseCode error = ResponseCode.CREATE_ROOM_FAIL_AUTH; 
						retval.put("code", error.getCode());
						retval.put("msg", error.getMsg());
					}
				}
			
				break;
				
			case INIT:
				{
					String id = (String)jMsg.get("id");
					if(id != null)
					{
						UsersVO userInfo = HttpSessionManagement.getInstance().getUserData(id);
						if(userInfo != null)
						{
							WsInfoData wsInfo = new WsInfoData(session, userInfo);
														
							clients.put(session, wsInfo);	
						}
						else
						{
							// 오류: 로그인을 한 사용자가 이님 또는 불법을 시도하려는 사용자
							ResponseCode error = ResponseCode.INIT_FAIL_AUTH; 
							retval.put("code", error.getCode());
							retval.put("msg", error.getMsg());
						}
					}
					else
					{
						// 오류: id가 없음
						ResponseCode error = ResponseCode.INIT_FAIL_DATA; 
						retval.put("code", error.getCode());
						retval.put("msg", error.getMsg());
					}
				}
				break;
				
			case SENDER:
				{
					String id = (String)jMsg.get("id");
					WsInfoData wsInfo = clients.get(session);
					
					if(id != null && wsInfo != null && id.equals(wsInfo.getUserInfo().getId()))
					{
						String roomId = (String)jMsg.get("roomId");
						String msg = (String)jMsg.get("msg");
						WsRoomData room = wsInfo.getRoom(roomId);
						
						if(room != null)
						{
							JSONObject sendMsg = new JSONObject();
							
							sendMsg.put("id", id);
							sendMsg.put("msg", msg);
							sendMsg.put("roomId", roomId);
							sendMsg.put("code", ResponseCode.SENDER_SUCCESS);
							
							room.sendMessage(id, sendMsg.toJSONString());
						}
						else
						{
							// 오류: 방이 없음
							ResponseCode error = ResponseCode.SENDER_FAIL_NOT_ROOM; 
							retval.put("code", error.getCode());
							retval.put("msg", error.getMsg());
						}
					}
					else
					{
						// 오류: id 또는 로그인하지 않음
						ResponseCode error = ResponseCode.SENDER_FAIL_AUTH; 
						retval.put("code", error.getCode());
						retval.put("msg", error.getMsg());
					}
				}
				break;

			case NONE:
			default:
				retval.put("code", ResponseCode.NONE.getCode());
				retval.put("msg", ResponseCode.NONE.getMsg());
				break;
			
			}
			
		} 
		catch (Exception e) 
		{
			retval.put("code", ResponseCode.ERROR);
			retval.put("msg", e.getClass().getName() + ": " + e.getMessage());
		}
		
		session.getBasicRemote().sendText(retval.toJSONString());
	}

	@OnClose
	public void onClose(Session session) 
	{
		WsInfoData wsInfo = clients.get(session);		
		if(wsInfo != null)
		{
			String id = wsInfo.getUserInfo().getId();
			
			try 
			{
				JSONObject senderData = new JSONObject();
				
				senderData.put("closedId", id);
				senderData.put("code", ResponseCode.CLOSE_USER.getCode());
				senderData.put("msg", ResponseCode.CLOSE_USER.getMsg());
				
				//방에 참여한 모든 인원에게 종료한 사용자를 알려주기(전송)
				wsInfo.sendAllRoomMessage(id, senderData.toJSONString());
			}
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
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
