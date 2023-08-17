$(() => {

	let CONTEXT_PATH = getContextPath();
	let wSocket = null;
	const ID = document.id;
	let currRoomId = null;
	
	const INIT = "1";
	const SENDER = "2";
	const CREATE_ROOM = "3";
	const ENTRY_ROOM = "4";
	const PRODUCT_DATA = "5";
	
	const INIT_SUCCESS = "1";
	const SENDER_SUCCESS = "2";
	const ENTRY_ROOM_SUCCESS = "3";
	const CREATE_ROOM_SUCCESS = "4";	
	const CLOSE_USER = "6";
	const PRODUCT_DATA_SUCCESS = "7";
	
	
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
		    console.log(data);
		    
		    if(data.code <= 0)
	    	{
		    	console.error(data);
	    	}
		    else if(data.code == SENDER_SUCCESS)
		    {
		    	let senderId = data.id;
		    	const roomId = data.roomId;
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
			    
			    if(roomId && !roomList[roomId])
			    {
			    	roomList[roomId] = {
		    			chatList: []
			    	};
			    }
			    
		    	roomList[roomId].chatList.push(oNode);
		    	roomList[roomId].readerId = senderId;

			    if(currRoomId == roomId)
			    {
			    	roomList[roomId].isNewMsg = false; // 새로운 문자열을 받음 여부
			    	
				    //선택(대화 중인 유저)이 되어 있을 때만 대화를 추가한다.
			    	chatMsgGup.before(oNode);
					$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
			    }
			    else
			    {
			    	roomList[roomId].isNewMsg = true; // 새로운 문자열을 받음 여부
			    	/*
			    	//선택(대화 중인 유저)이 되지 않았을 때 왼쪽 유저 목록에서 알림 아이콘을 띄운다.
			    	let chatUsers = $("div[name=chatUser]");
			    	for(let i = 0; i<chatUsers.length; i++)
			    	{
			    		let chatUser = $(chatUsers[i]);
			    		
			    		if(chatUser.attr('chat-room-id') == roomId)
			    		{
			    			chatUser.find('span[name=chatUserAlert]').removeClass('invisible');
			    			break;
			    		}
			    	}
			    	//*/
			    }
		    	
		    	$("div[name=chatUser]").remove();
		    	
		    	// 지금까지 생성되 모든 방을 순회하면서 유저 목록을 새롭게 만들고 click 이벤트를 부여한다.
		    	for(let key in roomList)
		    	{
		    		let tmpUser = chatUserNode.clone();
		    		let tmpSenderId = roomList[key].readerId;
		    		let isNewMsg = roomList[key].isNewMsg;

		    		tmpUser.attr('chat-room-id', key);
		    		tmpUser.find('div[name=chatUserAvatar] > img').remove();
		    		tmpUser.find('div[name=chatUserAvatar]').append($('<i class="bi bi-person-bounding-box"></i>'));
		    		tmpUser.find('div[name=chatUserId]').text(tmpSenderId);
		    		tmpUser.find('span[name=chatUserOnline]').removeClass('invisible');	

		    		if(isNewMsg && key != currRoomId)
		    		{
		    			tmpUser.find('span[name=chatUserAlert]').removeClass('invisible');   
		    		}
		    		else
		    		{
		    			tmpUser.find('span[name=chatUserAlert]').addClass('invisible');
		    		}
		    		
		    		if(currRoomId)
		    		{
		    			if(key != currRoomId)
	    				{
	    					tmpUser.removeClass('bg-secondary bg-opacity-25');
	    				}
		    			else
		    			{
		    				tmpUser.addClass('bg-secondary bg-opacity-25');
		    			}
		    		}		    		
	    			
		    		tmpUser.bind('click', function() {
		    			
		    			// 이전에 선택된 유저(room id)의 html의 class를 수정한다.
		    			if(currRoomId != null)
		    			{		    		
		    				let chatUsers = $("div[name=chatUser]");
		    		    	for(let i = 0; i<chatUsers.length; i++)
		    		    	{
		    		    		let chatUser = $(chatUsers[i]);
		    		    		
		    		    		if(chatUser.attr('chat-room-id') == currRoomId)
		    		    		{
		    		    			chatUser.removeClass('bg-secondary bg-opacity-25');
		    		    			break;
		    		    		}
		    		    	}
		    			}
		    					    			
		    			currRoomId = $(this).attr('chat-room-id');
		    			$(this).find('span[name=chatUserAlert]').addClass('invisible');
		    			
		    			$("#chatList > div[name=chatMsg]").remove();
		    			
		    			// 화면에 뿌리기
		    			for(let i = 0; i<roomList[currRoomId].chatList.length; i++)
		    			{
		    				let chatData = roomList[currRoomId].chatList[i];

					    	chatMsgGup.before(chatData);
		    			}
		    			
		    			$("#chatMsgGup").removeClass('invisible');
		    			$("#chatMsgStart").addClass('invisible');
		    			$(this).addClass('bg-secondary bg-opacity-25');
		    		});
		    				    		
		    		chatUserSearchGup.after(tmpUser);
		    	}
			    

		    }
		    else if(data.code == PRODUCT_DATA_SUCCESS)
		    {
		    	/*				
				retval.put("roomId", roomId);
				retval.put("link", "/product/detail?id=" + productId);
				retval.put("name", productInfo.getName());
				retval.put("code", ResponseCode.PRODUCT_DATA_SUCCESS);	
				*/
		    	
		    	const senderId = data.id;
		    	const roomId = data.roomId
		    	const link = data.link
		    	const productName = data.name;
		    	let now = new Date();
				let strTime = now.getHours() + ":" + now.getMinutes();
				//let strDate = now.getFullYear() + "/" + now.getMonth() + "/" + now.getDate();
			    let oNode = chatOrtherNode.clone();
			    let productLinkItem = $(`<button class="btn btn-outline-primary">${productName}</button>`);
			    let productLink = $(`<div><div>상품정보</div></div>`);
			    
			    productLinkItem.bind('click', function(){
			    	let moveProductUrl = `${CONTEXT_PATH}${link}`;
			    	
			    	window.open(moveProductUrl);
			    });
			    
			    productLink.append(productLinkItem);
			    
				oNode.find('div[name=chatUserAvatar] > img').remove();				
				oNode.find('div[name=chatUserAvatar]').prepend($('<i class="bi bi-person-fill"></i>'));				    
			    oNode.find('div[name=chatId]').text(senderId);
			    oNode.find('div[name=chatMsg]').append(productLink);			    
			    oNode.find('div[name=chatMsgTime]').text(strTime);
				//oNode.find('div[name=chatMsgDate]').text(strDate);

		    	if(roomId && !roomList[roomId])
			    {
			    	roomList[roomId] = {
		    			chatList: []
			    	};
			    }
			    
		    	roomList[roomId].chatList.push(oNode);
		    }
		    else if(data.code == INIT_SUCCESS)
	    	{
		    	
	    	}
		    else if(data.code == ENTRY_ROOM_SUCCESS)
	    	{
		    	
	    	} 		    
		    else if(data.code == CREATE_ROOM_SUCCESS)
	    	{
		    	
	    	}
		    else if(data.code == CLOSE_USER)
		    {
		    	// 대화방에서 나간 사용자 정보를 사용해서 왼쪽 목록에서 변화를 준다.
		    	let chatUsers = $("div[name=chatUser]");
		    	for(let i = 0; i<chatUsers.length; i++)
		    	{
		    		let chatUser = $(chatUsers[i]);
		    		
		    		if(chatUser.attr('chat-room-id') == data.roomId)
		    		{
		    			chatUser.find('span[name=chatUserOnline]').addClass('invisible');
		    			chatUser.find('span[name=chatUserOffline]').removeClass('invisible');
		    			break;
		    		}
		    	}
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
		

		$(window).on("beforeunload", function() {
			if(wSocket)
			{
				wSocket.close();
				wSocket = null;
			}
		});
		$(window).on("unload", function() {
			if(wSocket)
			{
				wSocket.close();
				wSocket = null;
			}
		});
		
	}
	catch(exp)
	{
		console.error(exp);
	}
});	
