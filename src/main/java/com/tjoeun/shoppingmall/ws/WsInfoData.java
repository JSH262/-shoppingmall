package com.tjoeun.shoppingmall.ws;

import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.Session;

import com.tjoeun.shoppingmall.vo.UsersVO;

public class WsInfoData 
{
	//웹 소켓 통신에 사용할 객체
	Session socket;
	
	//사용자의 정보
	UsersVO userInfo;
	
	//방
	WsRoomData room;
	
	
	public WsInfoData() 
	{
	}
	
	public WsInfoData(Session socket) 
	{
		this.socket = socket;
	}
	
	public WsInfoData(Session socket, UsersVO userInfo) 
	{
		this.socket = socket;
		this.userInfo = userInfo;
	}

	public boolean createRoom(String roomId) 
	{
		if(this.room == null && this.userInfo != null)
		{
			this.room = new WsRoomData(this, roomId);
			return true;
		}
		
		return false;	
	}
	public boolean isReader()
	{
		if(room != null)
		{
			String readerId = this.room.reader.userInfo.getId(); 
			String id = this.userInfo.getId();
			
			if(readerId != null && id != null)
			{
				if(id.equals(readerId))
				{
					return true;
				}
			}
		}
		
		return false;
	}
	public void setRoom(WsRoomData room)
	{
		this.room = room;
	}
	public WsRoomData getRoom()
	{
		return this.room;
	}
	public UsersVO getUserInfo() 
	{
		return this.userInfo;
	}
	public void sendText(String text) throws IOException
	{
		this.socket.getBasicRemote().sendText(text);
	}
}
