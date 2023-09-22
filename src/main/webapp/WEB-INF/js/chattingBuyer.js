$(() => {
	let sellerId = document.sId;
	let userId = document.id;		
	let productId = parseInt(document.pId);
	
	if(userId && productId && sellerId)
	{
		
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
		
		
	
		let currRoomId = null;
		let chatMsgGup = $("#chatMsgGup");
		let CONTEXT_PATH = getContextPath();
		let wSocket = null;
		let chatTitle = $("#chatTitle");
	
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
		
		if(sellerId)
		{
			//*
			let url = "ws://" + location.host + CONTEXT_PATH + '/chatting';
			
			wSocket = new WebSocket(url);
			
			// 웹소켓 서버에 연결됐을 때 실행
			wSocket.onopen = function(event) 
			{
				let connectData = {
					"code": INIT,
					"id": userId
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
			    else if(data.code == INIT_SUCCESS)
			    {
			    	// CREATE_ROOM을 한다.
			    	let sendRoomData = {
						"code": CREATE_ROOM,
						"id": userId,
						entryUsers: [
							sellerId, userId
						]
					};
			    	
					wSocket.send(JSON.stringify(sendRoomData));
			    }
			    else if(data.code == CREATE_ROOM_SUCCESS)
			    {
			    	currRoomId = data.roomId;
			    	let roomUserList = data.entryUserList;
			    	let isSeller = false;
			    	
			    	for(let i = 0; i<roomUserList.length; i++)
			    	{
			    		if(roomUserList[i] == sellerId)
			    		{
			    			isSeller = true;
			    			break;
			    		}
			    	}
			    	
			    	if(isSeller)
			    	{
			    		let sendRoomData = {
							"code": PRODUCT_DATA,
							"id": userId,
							"roomId": currRoomId,
							"productId": productId
			    		};
	
						wSocket.send(JSON.stringify(sendRoomData));
			    	}	
			    	else
			    	{
			    		customAlert.show('판매자가 접속을 하지 않았습니다.', null, 1);
			    	}
			    	
			    	
			    }
			    else if(data.code == CLOSE_USER)
			    {
			    	if(data.closedId == sellerId)
			    	{
			    		customAlert.show('판매자가 대화를 종료했습니다.', null, 1);
			    	}
			    	
			    	console.log(data.closedId, "가 접속을 종료했습니다.");
			    }		    
			    else if(data.code == SENDER_SUCCESS)
			    {
			    	console.log(data);
			    	
				    var senderId = data.id;
				    var msg = data.msg;
				    var roomId = data.roomId;
				    
				    // 현재 생성한 방에서 대화를 하고 있을 때만 처리한다.
				    if(currRoomId == roomId)
		    		{
					    let oNode = chatOrtherNode.clone();
						let now = new Date();
						let strTime = now.getHours() + ":" + now.getMinutes();
						//let strDate = now.getFullYear() + "/" + now.getMonth() + "/" + now.getDate();
						
						oNode.find('div[name=chatUserAvatar] > img').remove();				
						oNode.find('div[name=chatUserAvatar]').prepend($('<i class="bi bi-person-fill"></i>'));				
						oNode.find('div[name=chatMsgTime]').text(strTime);
						//mNode.find('div[name=chatMsgDate]').text(strDate);
						oNode.find('div[name=chatMsg]').text(msg);
						oNode.find('div[name=chatId]').text(senderId);
		
						chatMsgGup.before(oNode);
						
						$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
			    	}
				    else
				    {
				    	console.error(data);
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
					// msg 전송
					let sendData = {
						code: SENDER,
						roomId: currRoomId,
						id: userId,
						msg: msg
					};
	
					console.log(sendData);
					wSocket.send(JSON.stringify(sendData));
				
					
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
	else
	{
		alert('로그인이 필요합니다.');
		
		if(!productId || !sellerId)
			console.error('통신에 필요한 정보가 부족합니다.');
	}
});	