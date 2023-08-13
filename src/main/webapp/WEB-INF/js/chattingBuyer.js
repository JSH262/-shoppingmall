$(() => {
				
	const SUCCESSS = "0";
	const INIT = "1";
	const SEND = "2";
	const ENTRY = "3";		
	const CLOSE_USER = "4";
	const SENDER = "5";
	const INIT_SUCCESS = "6";
	const ENTRY_SUCCESS = "7";		
	const SENDER_SUCCESS = "8";

	let chatMsgGup = $("#chatMsgGup");
	let CONTEXT_PATH = getContextPath();
	let wSocket = null;
	let chatTitle = $("#chatTitle");
	let productId = document.pId;
	let sellerId = document.sId;
	let userId = document.id;
	let chatMeNode =  $(`
		<div class="chat-message-right pb-4">
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
		<div class="chat-message-left pb-4">
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
		    console.log("웹소켓 서버에 연결되었습니다.");
		    
			let connectData = {
				"requestCode": INIT,
				"result": {
					"id": userId,
					"romdId": roomId
				}
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
		    
		    if(data.code == SUCCESS || data.code == INIT_SUCCESS || data.code == ENTRY_SUCCESS)
	    	{
		    	console.log(data.msg);
	    	}
		    else if(data.code == SENDER_SUCCESS)
		    {
			    var senderId = data.senderId;
			    var msg = data.msg;
			    
			    console.log(senderId, msg);
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
				
				
////////////////////////////////////////////////////////////////////////////////////////////////////				
				chatMsgGup.before(mNode);
				
				// msg 전송
				
				
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
});	