$(() => {
	
	const alertContentsNode = $(`
	<div class="list-group w-auto" >
		<a href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true" name="realtimeAlertContentsLink">
			<i class="bi bi-info-circle text-primary" style="font-size:2rem;"></i>
			<div class="d-flex gap-2 w-100 justify-content-between">
				<div>
					<h6 class="mb-0" name="realtimeAlertContentsTitle">알림</h6>
					<p class="mb-0 opacity-75" name="realtimeAlertContentsBody"></p>
				</div>
				<small class="opacity-50 text-nowrap" name="realtimeAlertContentsTime"></small>
			</div>
		</a>			
	</div>`);
	let intervalId = null;
	const alertContents = $("#realtimeAlertContents");
	const ID = document.id;
	const CONTEXT_PATH = getContextPath();
	let wSocket = null;
	
	const INIT = "01";
	const PRODUCT_BUY = "02";
	const UPDATE = "03";
	
	const INIT_SUCCESS = "03";
	const PRODUCT_BUY_ALERT = "04";
	
	try
	{
		if(ID)
		{
			const url = "ws://" + location.host + CONTEXT_PATH + '/alert';
			
			wSocket = new WebSocket(url);
			
			// 웹소켓 서버에 연결됐을 때 실행
			wSocket.onopen = function(event) 
			{
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
			    else if(data.code == INIT_SUCCESS)
			    {
			    	if(intervalId)
			    	{
			    		clearInterval(intervalId);
			    		intervalId = null;
			    	}
			    	
			    	intervalId = setInterval(function() 
			    	{
			    		let send = {
			    			'code':UPDATE,
			    			'id':ID
			    		};
			    		wSocket.send(JSON.stringify(send));
						
					}, data.interval);
			    }
			    else if(data.code == PRODUCT_BUY_ALERT)
			    {
			    	let tmpNode = alertContentsNode.clone();
			    	let tmpMsg = `현재 판매중인 "${data.productNames}"을(를) 누군가가 구입했어요`;
			    	
			    	tmpNode.find('p[name=realtimeAlertContentsBody]').text(tmpMsg);
			    	tmpNode.find('a[name=realtimeAlertContentsLink]').attr('href', data.link);
			    	
			    	alertContents.append(tmpNode);
			    	
			    }
			    else
			    {
			    	console.error(data);
			    }
			};
			//*/
		}
	}
	catch(exp)
	{
		console.error(exp);
	}
	
	
});