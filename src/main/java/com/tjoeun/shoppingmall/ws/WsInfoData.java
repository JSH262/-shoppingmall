package com.tjoeun.shoppingmall.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.Session;

import com.tjoeun.shoppingmall.vo.UsersVO;

public class WsInfoData 
{
	//웹 소켓 통신에 사용할 객체
	Session socket;
	
	//사용자의 정보
	UsersVO userInfo;
	
	//방
	HashMap<String, WsRoomData> rooms = new HashMap<>();
	
	
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

	public WsRoomData createRoom(String roomId) 
	{
		WsRoomData room = this.getRoom(roomId);
	
		synchronized (this.rooms) 
		{
			if(room == null)
			{
				room = new WsRoomData(roomId);
				
				this.rooms.put(roomId, room);	
			}
		}
		
		return room;
	}
	
	public void removeRoom(String roomId)	
	{
		synchronized (this.rooms) 
		{
			if(this.rooms.get(roomId) != null)
			{
				this.rooms.remove(roomId);
			}
		}
	}
	
	public WsRoomData getRoom(String roomId)
	{
		WsRoomData room = null;
		
		synchronized (this.rooms) 
		{
			room = this.rooms.get(roomId);
		}
		
		return room;
	}
	
	public boolean isRoom(String roomId)
	{
		return this.getRoom(roomId) != null;
	}

	public void addRoom(WsRoomData room) 
	{
		boolean isRoom = this.isRoom(room.getRoomId());
		
		synchronized (this.rooms) 
		{
			if(isRoom == false)
				this.rooms.put(room.getRoomId(), room);			
		}
	}

	
	public UsersVO getUserInfo() 
	{
		return this.userInfo;
	}
	public Session getSocket()
	{
		return this.socket;
	}

	public void sendAllRoomMessage(String id, String msg) 
	{
		synchronized (this.rooms) 
		{
			Set<String> keys = this.rooms.keySet();
			Iterator<String> iter = keys.iterator();
			
			while(iter.hasNext())
			{
				try 
				{
					WsRoomData room = this.rooms.get(iter.next());
					
					room.sendMessage(id, msg);
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}		
	}

}
