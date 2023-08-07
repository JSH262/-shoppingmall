package com.tjoeun.shoppingmall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




/*
	
	
	1. 구성
	https://poiemaweb.com/nodejs-socketio
	
	[1] node.js
	
	//node.js에 접속한 사용자의 정보들
	let userList = [];
	
	//방이름
	rooms['asdf1234'] = {
		users: [],
		roomId: '',
		socket: socket
	};
	
	//방에 접속한 사용자의 정보(socket.id로 처리할 수도 있다)
	rooms['asdf1234'].users['qwer1234'] = {
		lastAccessedTime: 123123123, 
		closeTime: 1231231231,
		maxInactiveInterval: 70,
		userId: "qwer1234",
		roomId: "asdf1234",
		intervalId: null
	};
	
	rooms['asdf1234'].users['qwer1234'].intervalId = setTimeout(function()
	{
		//세션갱신
	},
	
	// 만료되기 3초 전에 setTimeout을 호출한다.
	maxInactiveInterval * 1000 - 3000);
	
	
	
	
	1-1. 인증	
	controller <= node.js	
	node.js는 controller에게 인증을 받는다.
	
	1-2. 기능		
	1-2-1. 클라이언트 접속 순서		
	[1]. 서버에게 자신의 세션 정보 요청(login)
	서버에게 세션 정보를 요청한다. 정보는 아래와 같다.
	lastAccessedTime: session.getLastAccessedTime() => 마지막으로 접속한 시간 
	maxInactiveInterval: session.getMaxInactiveInterval() => 세션유지 시간(초)
	closeTime: session.getLastAccessedTime() + session.getMaxInactiveInterval() * 1000 => 세션이 만료되는 시간
	userId => 접속하려는 사용자의 ID
	roomId => 접속하려는 방의 ID(판매자가 생성한 방의 ID)

	[2]. node.js에 접속한다.
	[1]에서 받은 세션 정보를 node.js에게 전송한다.
	node.js는 받은 데이터를 저장하고 controller에게 정보를 받은 사용자의 상세정보를 요청한다.
	
	사용자가 판매자일 경우 방을 자동으로 생성한다. => roomId 무시
	/login => 판매자일 경우 => io.of('/' + userId). on('connection', function(socket) { ... });
	
	사용자가 구매일 경우 방에 자동으로 접속한다. => roomId 사용(roomId가 없으면 오류)
		
	1-2-2. 클라이언트 접속 순서 정리
	[1] js => controller
	[2] js <= controller
	[3] js => node.js
	[4] node.js => controller
	[5] node.js <= controller	
	[6] 
		 
 	1-2-2. 세션 갱신	 
	[1] node.js는 받은 세션 정보를 setTimeout 도는 setInterval로 시간을 제고 세션 종료가 다가오면 클라이언트(js)에게 세션을 갱신할 수 있도록 통신한다.
	[2] node.js가 보낸 갱신 메시지를 받으면 클라이언트는 controller에게 ajax 통신을 하여 세션을 갱신시킨다.
	 
	 
 	
	
	
	2. controller의 URL	
	/update: 세션을 갱신(client와 통신)
	/user: 유저의 정보를 받는다(node.js와 통신).
	/login: node.js의 인증(node.js와 통신)
	
	
	3. node.js의 URL
	login: 판매자, 구매자가 최초 접속하려는 메시지
	sender: 메시지 전송
	recv: 메시지 받기
	
	4. 주의사항
	io.of: 채널을 만들어 room 개념을 사용할 수 있다. 굳이 채널을 사용해야 하는가? 자료구조를 사용해서 관리를 하면 채널이 없어도 room 개념을 사용할 수 있다.
	room 개념은 n:n 접속이 이루어질 때 사용하면 편하지만 1:1을 구현할 예정인데 굳이 채널을 만들어야 하는가?
	
	




*/
@Controller
public class NodejsController 
{
	private static final long serialVersionUID = 1L;
       
    public NodejsController() {
        super();
    }

    @RequestMapping("/nodejs")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
