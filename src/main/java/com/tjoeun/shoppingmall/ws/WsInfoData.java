package com.tjoeun.shoppingmall.ws;

import javax.websocket.Session;

import com.tjoeun.shoppingmall.vo.UsersVO;

public class WsInfoData 
{
	//웹 소켓 통신에 사용할 객체
	Session socket;
	
	//사용자의 정보
	UsersVO userInfo;	
	
	//대화 중인 상대방의 정보
	WsInfoData personInfo;
	
	
	public WsInfoData() 
	{
	}
	
	
	public WsInfoData(Session socket, UsersVO userInfo) 
	{
		this.socket = socket;
		this.userInfo = userInfo;
	}

	
	
	

	public Session getSocket() {
		return socket;
	}


	public void setSocket(Session socket) {
		this.socket = socket;
	}


	public UsersVO getUserInfo() {
		return userInfo;
	}


	public void setUserInfo(UsersVO userInfo) {
		this.userInfo = userInfo;
	}


	public WsInfoData getPersonInfo() {
		return personInfo;
	}


	public void setPersonInfo(WsInfoData personInfo) {
		this.personInfo = personInfo;
	}
	
}
