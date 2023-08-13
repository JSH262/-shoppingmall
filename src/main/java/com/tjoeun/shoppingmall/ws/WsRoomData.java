package com.tjoeun.shoppingmall.ws;

import java.io.IOException;
import java.util.ArrayList;

import com.tjoeun.shoppingmall.vo.UsersVO;

public class WsRoomData 
{
	private String roomId;
	private ArrayList<WsInfoData> users;
	
	
	
	public WsRoomData(String roomId)
	{
		this.roomId = roomId;
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
	
	public boolean isEntryRoom(WsInfoData user)
	{
		boolean retval = false;
		
		synchronized (this.users) 
		{
			UsersVO userInfo = user.getUserInfo();
			
			for(int i = 0; i<this.users.size(); i++)
			{
				WsInfoData tmp = this.users.get(i);
				
				if(userInfo.getId().equals(tmp.getUserInfo().getId()))
				{
					retval = true;
					break;
				}
			}
		}
		
		return retval;
	}
	
	public ArrayList<WsInfoData> getUsers()
	{
		ArrayList<WsInfoData> retval = null;
		
		synchronized (this.users) 
		{
			retval = (ArrayList<WsInfoData>)this.users.clone();	
		}
		
		return retval;
	}


	public void sendMessage(String id, String msg) throws IOException 
	{
		synchronized (this.users) 
		{
			for(int i = 0; i<this.users.size(); i++)
			{
				try
				{
					WsInfoData wsInfo = this.users.get(i);				
					
					if(id != null && id.equals(wsInfo.getUserInfo().getId()))
					{
						// 자신한테는 보내지 않는다.
					}
					else
					{
						wsInfo.getSocket().getBasicRemote().sendText(msg);
					}
				}
				catch(IOException exp)
				{
					exp.printStackTrace();
				}
			}
		}
		
	}
	
	
}
