package com.tjoeun.shoppingmall.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tjoeun.shoppingmall.vo.UsersVO;

public class WsRoomData 
{
	private String roomId;
	private List<WsInfoData> users = Collections.synchronizedList(new ArrayList<WsInfoData>());
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	
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
		
		retval = this.users.add(user);
		
		return retval;
	}
	
	public boolean isEntryRoom(WsInfoData user)
	{
		boolean retval = false;
		
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
		
		return retval;
	}
	
	public List<WsInfoData> getUsers()
	{
		List<WsInfoData> retval = null;
		
		retval = this.users;
		
		
		return retval;
	}


	public void sendMessage(String id, String msg) throws IOException 
	{
		for(int i = 0; i<this.users.size(); i++)
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
	}
	
	
	public void leaveUserRoom(String userId)
	{
		int removeIndex = -1;
		
		for(int i = 0; i<this.users.size(); i++)
		{			
			WsInfoData wsInfo = this.users.get(i);				
			
			if(userId != null && userId.equals(wsInfo.getUserInfo().getId()))
			{
				removeIndex = i;
				//this.users.remove(i);
				//break;
			}
			else
			{
				JSONObject sendData = new JSONObject();

				sendData.put("code", ChattingServer.ResponseCode.CLOSE_USER.getCode());
				sendData.put("closeId", userId);
				sendData.put("roomId", this.roomId);
				
				try 
				{
					logger.info("call leaveUserRoom: " + sendData + " >>> " + wsInfo.getSocket().getId() + ", " + wsInfo.getUserInfo().getId());
					
					wsInfo.getSocket().getBasicRemote().sendText(sendData.toJSONString());
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		if(removeIndex != -1)
			this.users.remove(removeIndex);
		
		
	}
	
	
}
