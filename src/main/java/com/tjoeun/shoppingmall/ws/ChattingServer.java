package com.tjoeun.shoppingmall.ws;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.*;
import javax.websocket.server.*;





/*
	서버				클라이언트
			<- 
			접속
			
			<-
			접속한 클라이언트의 아이디와 채팅할 아이디를 전송
			
			-> 
			검색해서 접속한 클라이언트의 채팅할 아이디가 있으면 알리기

			<-
			
 
*/



@ServerEndpoint("/chatting")
public class ChattingServer 
{
	private static Map<Session, WsInfoData> clients = Collections.synchronizedMap(new HashMap<Session, WsInfoData>());
	
	@OnOpen // 클라이언트 접속 시 실행
	public void onOpen(Session session) 
	{
		clients.put(session, null);
		System.out.println("웹소켓 연결:" + session.getId());
	}

	@OnMessage // 메시지를 받으면 실행
	public void onMessage(String message, Session session) throws IOException 
	{
		System.out.println("메시지 전송 : " + session.getId() + ":" + message);
		synchronized (clients) 
		{
			for (Session client : clients) 
			{ // 모든 클라이언트에 메시지 전달
				if (!client.equals(session)) 
				{ // 단, 메시지를 보낸 클라이언트는 제외
					client.getBasicRemote().sendText(message);
				}
			}
		}
	}

	@OnClose // 클라이언트와의 연결이 끊기면 실행
	public void onClose(Session session) 
	{
		clients.remove(session);
		System.out.println("웹소켓 종료 : " + session.getId());
	}

	@OnError // 에러 발생 시 실행
	public void onError(Throwable e) 
	{
		System.out.println("에러 발생");
		e.printStackTrace();
	}
}
