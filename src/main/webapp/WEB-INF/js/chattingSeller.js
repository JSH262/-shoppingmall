$(() => {

	let CONTEXT_PATH = getContextPath();
	let wSocket = null;
	const ID = document.id;
	let currRoomId = null;
	
	const INIT = "1";
	const SENDER = "2";
	const CREATE_ROOM = "3";
	const ENTRY_ROOM = "4";
	
	const INIT_SUCCESS = "1";
	const SENDER_SUCCESS = "2";
	const ENTRY_ROOM_SUCCESS = "3";
	const CREATE_ROOM_SUCCESS = "4";	
	const CLOSE_USER = "5";
	
	
	let chatUserNode = $(`
		<div class="list-group-item list-group-item-action border-0 m-2 position-relative" name="chatUser">
			<div class="d-flex align-items-start">
				<div class="me-2" name="chatUserAvatar">
					<img class="rounded-circle" width="40" height="40">
				</div>
				<div class="flex-grow-1 ml-3">
					<div name="chatUserId">					
					</div>
					<div class="small">
						<span class="fas fa-circle chat-online invisible" name="chatUserOnline">접속중</span>
						<span class="fas fa-circle chat-offline invisible" name="chatUserOffline">접속종료</span>
					</div>
				</div>
				<span class="position-absolute top-0 start-0 translate-middle badge border border-light rounded-circle bg-danger p-1 invisible" name="chatUserAlert">
					<span class="visually-hidden">New alerts</span>
				</span>
			</div>
		</div>`);
	let chatMeNode =  $(`
		<div class="chat-message-right pb-4" name="chatMsg">
			<div class="chat-avatar-right" name="chatUserAvatar">
				<img src="" class="rounded-circle mr-1" alt="Chris Wood" width="40" height="40">
				<div class="text-muted small text-nowrap mt-2">
					<div name="chatMsgDate">
					</div>
					<div name="chatMsgTime">
					</div>
				</div>
			</div>
			<div class="flex-shrink-1 bg-light rounded py-2 px-3 mr-3">
				<div class="fw-bold mb-1" name="chatId"></div>
				<div name="chatMsg">
				</div>
			</div>
		</div>`);
	let chatOrtherNode = $(`
		<div class="chat-message-left pb-4" name="chatMsg">
			<div class="chat-avatar-left" name="chatUserAvatar">
				<img src="" class="rounded-circle mr-1" alt="Sharon Lessman" width="40" height="40">
				<div class="text-muted small text-nowrap mt-2" name="chatMsgTime"></div>
			</div>
			<div class="flex-shrink-1 bg-light rounded py-2 px-3 ml-3">
				<div class="fw-bold mb-1" name="chatId"></div>
				<div name="chatMsg"></div>
			</div>
		</div>`);
	let roomList = {};
	let chatMsgGup = $("#chatMsgGup");
	let chatUserSearchGup = $("#chatUserSearchGup");
	
	try
	{
		
		//*
		let url = "ws://" + location.host + CONTEXT_PATH + '/chatting';

		wSocket = new WebSocket(url);
		
		// 웹소켓 서버에 연결됐을 때 실행
		wSocket.onopen = function(event) 
		{
		    console.log("웹소켓 서버에 연결되었습니다.");
		    
			let connectData = {
				"code": INIT,
				"id": ID
			};
			
			wSocket.send(JSON.stringify(connectData));
		};
	
		// 웹소켓이 닫혔을 때(서버와의 연결이 끊겼을 때) 실행
		wSocket.onclose = function(event) 
		{
			console.log("웹소켓 서버가 종료되었습니다.");
		};
	
		// 에러 발생 시 실행
		wSocket.onerror = function(event) {
			console.log("채팅 중 에러가 발생하였습니다.", event.data);
		}; 
	
		// 메시지를 받았을 때 실행
		wSocket.onmessage = function(event) { 
		    var data = JSON.parse(event.data);
		    
		    if(data.code <= 0)
	    	{
		    	console.error(data);
	    	}
		    else if(data.code == SENDER_SUCCESS)
		    {
		    	console.log(data);
		    	/*
		    	 	// 대화를 보낸 사용자
					sendMsg.put("id", id);
					
					// 대화 내용
					sendMsg.put("msg", msg);
					
					// 대화를 보낸 방의 아이디
					sendMsg.put("roomId", roomId);
					
					// 받은 데이터 결과
					sendMsg.put("code", ResponseCode.SENDER_SUCCESS);
		    	 */
		    	let senderId = data.id;
		    	let roomId = data.roomId;
			    let msg = data.msg;			    
				let now = new Date();
				let strTime = now.getHours() + ":" + now.getMinutes();
				//let strDate = now.getFullYear() + "/" + now.getMonth() + "/" + now.getDate();
			
			    
			    
			    
			    let oNode = chatOrtherNode.clone();

				oNode.find('div[name=chatUserAvatar] > img').remove();				
				oNode.find('div[name=chatUserAvatar]').prepend($('<i class="bi bi-person-fill"></i>'));				    
			    oNode.find('div[name=chatId]').text(senderId);
			    oNode.find('div[name=chatMsg]').text(msg);
			    oNode.find('div[name=chatMsgTime]').text(strTime);
				//oNode.find('div[name=chatMsgDate]').text(strDate);
			    			    
			    //선택이 되어 있을 때만 추가한다.
			    if(currRoomId == roomId)
			    {
			    	chatMsgGup.before(oNode);
					$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
			    }
			    
			    if(roomId && !roomList[roomId])
			    {
			    	roomList[roomId] = {
		    			chatList: []
			    	};
			    }
			    
		    	roomList[roomId].chatList.push(oNode);
		    	roomList[roomId].readerId = senderId;
		    	
		    	$("div[name=chatUser]").remove();
		    	
		    	// 지금까지 생성되 모든 방을 순회하면서 유저 목록을 새롭게 만들고 click 이벤트를 부여한다.
		    	for(let key in roomList)
		    	{
		    		let tmpUser = chatUserNode.clone();
		    		
		    		tmpUser.find('div[name=chatUserAvatar] > img').remove();
		    		tmpUser.find('div[name=chatUserAvatar]').append($('<i class="bi bi-person-bounding-box"></i>'));
		    		tmpUser.find('div[name=chatUserId]').text(senderId);
		    		tmpUser.find('div[name=chatUserOnline]').removeClass('invisible');
		    		
		    		tmpUser.bind('click', function() {
		    			currRoomId = key;
		    			
		    			$("#chatList > div[name=chatMsg]").remove();
		    			
		    			// 화면에 뿌리기
		    			for(let i = 0; i<roomList[currRoomId].chatList.length; i++)
		    			{
		    				let chatData = roomList[currRoomId].chatList[i];
		    				

					    	chatMsgGup.before(chatData);
		    			}
		    		});
		    		
		    		chatUserSearchGup.after(tmpUser);
		    	}
		    	
		    	/*  
			    
			    let isChatUser = false;
			    let chatUserList = $("div[name=chatUser]");
			    for(let i = 0; i<chatUserList.length; i++)
		    	{
			    	let chatUser = chatUserList[i];
			    			    	
			    	if(chatUser.data('roomId') == roomId && chatUser.data('senderId') == senderId)
		    		{
			    		isChatUser = true;
			    		break;
		    		}
		    	}
			    
			    
			    if(isChatUser == false)
				{
			    	
				}
			    //*/
			    
		    }
		    else if(data.code = INIT_SUCCESS)
	    	{
		    	
	    	}
		    else if(data.code = ENTRY_ROOM_SUCCESS)
	    	{
		    	
	    	} 		    
		    else if(data.code = CREATE_ROOM_SUCCESS)
	    	{
		    	
	    	}
		    else if(data.code = CLOSE_USER)
		    {
		    	//var closeId = data.id;
		    	//왼쪽 사용자 목록에서 offline으로 변경한다.
		    	console.log("사용자가 접속을 종료함: ", data);
		    	
		    }
		    else
		    {
		    	console.error(data);
		    }
		};
		//*/
		
		let sendMessage = function()
		{
			let msg = chatMsgGup.find('input').val();
			if(msg)
			{
				chatMsgGup.find('input').val('');
				let mNode = chatMeNode.clone();
				let now = new Date();
				let strTime = now.getHours() + ":" + now.getMinutes();
				//let strDate = now.getFullYear() + "/" + now.getMonth() + "/" + now.getDate();
												
				mNode.find('div[name=chatUserAvatar] > img').remove();				
				mNode.find('div[name=chatUserAvatar]').prepend($('<i class="bi bi-person-fill"></i>'));				
				mNode.find('div[name=chatMsgTime]').text(strTime);
				//mNode.find('div[name=chatMsgDate]').text(strDate);
				mNode.find('div[name=chatMsg]').text(msg);
				mNode.find('div[name=chatId]').text('나');
				
				chatMsgGup.before(mNode);
				
				
				
			    if(currRoomId && !roomList[currRoomId])
			    {
			    	roomList[currRoomId] = {
			    		chatList: []
			    	};
			    }
			    
	    		roomList[currRoomId].chatList.push(mNode);
		    	
				// msg 전송
				let sendData = {
					code: SENDER,
					roomId: currRoomId,
					id: ID,
					msg: msg
				};
				
				wSocket.send(JSON.stringify(sendData));
				$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
			}
			else
			{
				
			}
			
		};
		
		chatMsgGup.find('button').bind('click', function() {
			//메시지 전송
			sendMessage();
		});
	
		chatMsgGup.find('input').bind('keyup', function(e) {		
			if(e.keyCode == 13)
			{
				//메시지 전송
				sendMessage();
			}
		});
	}
	catch(exp)
	{
		console.error(exp);
	}
});	
