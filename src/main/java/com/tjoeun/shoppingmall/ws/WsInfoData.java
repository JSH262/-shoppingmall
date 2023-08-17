package com.tjoeun.shoppingmall.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.websocket.Session;

import org.json.simple.JSONObject;

import com.tjoeun.shoppingmall.vo.UsersVO;

public class WsInfoData 
{
	//웹 소켓 통신에 사용할 객체
	Session socket;
	
	//사용자의 정보
	UsersVO userInfo;
	
	//방
	Map<String, WsRoomData> rooms = Collections.synchronizedMap(new HashMap<String, WsRoomData>());
	
	
	
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
	
		if(room == null)
		{
			room = new WsRoomData(roomId);
			
			this.rooms.put(roomId, room);	
		}
		
		return room;
	}
	
	public void removeRoom(String roomId)	
	{
		if(this.rooms.get(roomId) != null)
		{
			this.rooms.remove(roomId);
		}
	}
	
	public WsRoomData getRoom(String roomId)
	{
		WsRoomData room = null;
		
		room = this.rooms.get(roomId);
		
		return room;
	}
	
	public boolean isRoom(String roomId)
	{
		return this.getRoom(roomId) != null;
	}

	public void addRoom(WsRoomData room) 
	{
		boolean isRoom = this.isRoom(room.getRoomId());
		
		if(isRoom == false)
			this.rooms.put(room.getRoomId(), room);			
	}

	
	public UsersVO getUserInfo() 
	{
		return this.userInfo;
	}
	public Session getSocket()
	{
		return this.socket;
	}

	public void sendAllRoomMessage(String id, JSONObject msg) 
	{
		Set<String> keys = this.rooms.keySet();
		Iterator<String> iter = keys.iterator();
		
		while(iter.hasNext())
		{
			try 
			{
				WsRoomData room = this.rooms.get(iter.next());
				
				if(msg.get("roomId") == null)
					msg.put("roomId", room.getRoomId());
				
				room.sendMessage(id, msg.toJSONString());
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void leaveRooms(JSONObject sendMsg)
	{
		Set<String> keys = this.rooms.keySet();
		Iterator<String> iter = keys.iterator();
		
		while(iter.hasNext())
		{
			WsRoomData room = this.rooms.get(iter.next());
			
			room.leaveUserRoom(this.userInfo.getId(), sendMsg);				
		}
	}
	
	public void leaveRoom(String roomId)
	{
		Set<String> keys = this.rooms.keySet();
		Iterator<String> iter = keys.iterator();
		
		while(iter.hasNext())
		{
			WsRoomData room = this.rooms.get(iter.next());
			
			if(roomId.equals(room.getRoomId()))
			{
				room.leaveUserRoom(this.userInfo.getId(), null);	
				break;
			}				
		}
	}

}
