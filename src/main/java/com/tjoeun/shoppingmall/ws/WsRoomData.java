package com.tjoeun.shoppingmall.ws;

import java.util.ArrayList;

public class WsRoomData 
{
	String roomId;
	WsInfoData reader;
	ArrayList<WsInfoData> users;
	
	
	
	public WsRoomData(WsInfoData reader, String roomId)
	{
		this.roomId = roomId;
		this.reader = reader;
	}
	
	
	public String getRoomId()
	{
		return this.roomId;
	}
	
	public boolean entryRoom(WsInfoData user)
	{
		boolean retval = false;
		
		synchronized (this.users) 
		{
			retval = this.users.add(user);
		}
		
		return retval;
	}
	
	public WsInfoData getReader()
	{
		return this.reader;
	}
	public ArrayList<WsInfoData> getUsers()
	{
		return this.users;
	}
	
	
}
