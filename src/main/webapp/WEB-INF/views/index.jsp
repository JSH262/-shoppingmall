<%@page import="com.tjoeun.shoppingmall.vo.UsersVO"%>
<%@page import="com.tjoeun.helper.AttributeName"%>
<%@page import="com.tjoeun.helper.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>shoppingmall</title>
	
	<script>
		document.contextPath = '<%=request.getContextPath() %>';
	</script>
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/index.js"></script>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="resources/bootstrap-icons-1.10.5/font/bootstrap-icons.css">
	<style>
		#carousel-body img {
		  	object-fit: none;
		  	height: 300px;
		}
		
		.newProductCursor {
			cursor:pointer;
		}
		.newProductImageDuration {
			transition-duration: .2s;
		}
		
		.newProductImageDuration-hover {
			transform: scale(1.1, 1.1);
		}
		
		.newProductShadow {
			box-shadow: 3px 3px 3px 3px lightgray;
		}
		
	</style>
</head>
<body>
	<div class="p-5">
		<div>
			<h1>많이 팔린 상품</h1>
		</div>
		<div id="carouselLotSell" class="carousel carousel-dark slide" data-bs-ride="carousel">
			
			<%-- 이미지 밑의 선택된 이미지 및 이미지 목록들 --%>
			<div class="carousel-indicators" id="carousel-indicator">
				<%--			
				<button class="active" aria-current="true"  
						type="button" data-bs-target="#carouselLotSell" data-bs-slide-to="0" aria-label="Slide 1"></button>
				<button type="button" data-bs-target="#carouselLotSell" data-bs-slide-to="1" aria-label="Slide 2"></button>
				<button type="button" data-bs-target="#carouselLotSell" data-bs-slide-to="2" aria-label="Slide 3"></button>
				<button type="button" data-bs-target="#carouselLotSell" data-bs-slide-to="3" aria-label="Slide 4"></button>
				 --%>
			</div>
			
			<%-- 이미지들 --%>
			<div class="carousel-inner" id="carousel-body">
				<%--
				<div class="carousel-item active" data-bs-interval="3000">
					<img class="ratio ratio-21x9 mx-auto d-block" src="<%=request.getContextPath() %>/image/11ee340654fc4111b956dd7a96c7332c" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item" data-bs-interval="3000">
					<img class="ratio ratio-21x9 mx-auto d-block" src="<%=request.getContextPath() %>/image/11ee340585123d80b956651ffe5e5aa3" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item" data-bs-interval="3000">
					<img class="ratio ratio-21x9 mx-auto d-block" src="<%=request.getContextPath() %>/image/11ee34057f5e4775b9564175e94239a8" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item" data-bs-interval="3000">
					<img class="ratio ratio-21x9 mx-auto d-block" src="<%=request.getContextPath() %>/image/11ee34057f5e4775b9564175e94239a8" class="d-block w-100" alt="...">
				</div>
				 --%>
			</div>
			
			<%-- 이미지 왼쪽에 있는 이동 버튼 --%>
			<button class="carousel-control-prev" type="button" data-bs-target="#carouselLotSell" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span> 
				<span class="visually-hidden">Previous</span>
			</button>
			
			<%-- 이미지 오른쪽에 있는 이동 버튼 --%>
			<button class="carousel-control-next" type="button" data-bs-target="#carouselLotSell" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>
	
		<hr />
	
		<div>
			<h1>신상품</h1>
		</div>
		<div class="row row-cols-1 row-cols-md-3 g-4" id="newProductsBody">
		
			<%-- 상품 로딩중 표시
			<div class="row" name="newProductLoading">			
				<div class="col-4">
					<div class="card" aria-hidden="true">
						<img src="<%=request.getContextPath() %>/resources/default/gray.png" class="card-img-top img-fluid">
						<div class="card-body">
							<h5 class="card-title placeholder-glow">
								<span class="placeholder col-6"></span>
							</h5>
							<p class="card-text placeholder-glow">
								<span class="placeholder col-7"></span> 
								<span class="placeholder col-4"></span> 
								<span class="placeholder col-4"></span> 
								<span class="placeholder col-6"></span> 
								<span class="placeholder col-8"></span>
							</p>
							<a href="#" tabindex="-1" class="btn btn-primary disabled placeholder col-6"></a>
						</div>
					</div>
				</div>
			</div>
			 --%>
		</div>
	
		<hr />
	
	
		<div>	
		<%
			if(AttributeName.getUserData(request) != null)
			{
		%>
				<input type="button" value="로그아웃" onclick="location.href='<%=request.getContextPath() %>/logout'" />
				<%--
				<input type="button" value="구입할 상품확인" onclick="location.href='<%=request.getContextPath() %>/product/order'" />
				 --%>
				<input type="button" value="상품구입 목록" onclick="location.href='<%=request.getContextPath() %>/product/payment/list'" />
				<input type="button" value="장바구니" onclick="location.href='<%=request.getContextPath() %>/cart/list'" />
				<input type="button" value="상품관리" onclick="location.href='<%=request.getContextPath() %>/product/breakdown/list'" />
		<%
			}
			else
			{
		%>
				<input type="button" value="로그인" onclick="location.href='<%=request.getContextPath() %>/login'" />
		<%
			}
		%>
		
			<input type="button" value="상품보기" onclick="location.href='<%=request.getContextPath() %>/product/list'" />
		</div>
	</div>





	
	<%
		UsersVO userData = AttributeName.getUserData(request);
	
		if(userData != null)
		{
	%>
			<script>
				$(() => {
					
					let CONTEXT_PATH = getContextPath();
					const myModal = new bootstrap.Modal(document.getElementById('chattingModal'));
					let wSocket = null;
					const INIT = "1";
					const SEND = "2";
					const ENTRY = "3";	
					
					
					$("#chattingModal button[name=chattingModalClose]").bind('click', function() {
						<%--
						if(wSocket)
						{
							wSocket.close();
							wSocket = null;
						}
						--%>
						myModal.hide();
					});
					$("#btnStartChatting").bind('click', function() {
						myModal.show();
						<%--
						if(!wSocket)
						{				
							let url = "ws://" + location.host + CONTEXT_PATH + '/chatting';
							
							wSocket = new WebSocket(url);
							
							// 웹소켓 서버에 연결됐을 때 실행
							wSocket.onopen = function(event) 
							{
							    console.log("웹소켓 서버에 연결되었습니다.");
							    
								let connectData = {
									"requestCode": INIT,
									"result": {
										"id": "<%=userData.getId() %>"
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
							    
							    if(data.code == '0')
						    	{
							    	console.log(data.msg);
						    	}
							    else if(data.code == '1')
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
						} 
						--%>
					});
				});		
			</script>
		
			<div style="position: fixed; bottom:30px; right: 30px;">
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-info" id="btnStartChatting">
					<i class="bi bi-chat-right-text-fill"></i>
				</button>
			</div>
			
			<!-- Modal -->
			<div class="modal fade" id="chattingModal" data-bs-backdrop="static" tabindex="-1" aria-labelledby="chattingModal" aria-hidden="true">
				<div class="modal-dialog modal-dialog-scrollable">
					<div class="modal-content">
						<div class="modal-header">
							<h1 class="modal-title fs-5">Modal title</h1>
							<button type="button" class="btn-close" name="chattingModalClose" aria-label="Close"></button>
						</div>						
						<div class="modal-body">
							<span>홍길동</span>
							<span>김유신</span>
							<span>임꺽정</span>
							<span>일지매</span>
							<span>장길산</span>
							<span>홍길동</span>
							<span>김유신</span>
							<span>임꺽정</span>
							<span>일지매</span>
							<span>장길산</span>
							<span>홍길동</span>
							<span>김유신</span>
							<span>임꺽정</span>
							<span>일지매</span>
							<span>장길산</span>
							<span>홍길동</span>
							<span>김유신</span>
							<span>임꺽정</span>
							<span>일지매</span>
							<span>장길산</span>
						</div>
						<div class="modal-body">
							<div class="container-fluid">
								<div class="row">
									<div class="col-3">
										<div>홍길동</div>
										<div>김유신</div>
										<div>임꺽정</div>
										<div>일지매</div>
										<div>장길산</div>
									</div>
									<div class="col-1 d-flex">
										<div class="vr"></div>
									</div>
									
									<div class="col-8">
										<p class="m1-1 bg-warning-subtle text-start">asdfasdfasdf</p>
										<p class="m1-1 bg-secondary-subtle text-end">234234234234324</p>
										<p class="m1-1 bg-warning-subtle text-start">asdfasdfasdf</p>
										<p class="m1-1 bg-secondary-subtle text-end">234234234234324</p>
										<p class="m1-1 bg-warning-subtle text-start">asdfasdfasdf</p>
										<p class="m1-1 bg-secondary-subtle text-end">234234234234324</p>
										<p class="m1-1 bg-warning-subtle text-start">asdfasdfasdf</p>
										<p class="m1-1 bg-secondary-subtle text-end">234234234234324</p>
										<p class="m1-1 bg-warning-subtle text-start">asdfasdfasdf</p>
										<p class="m1-1 bg-secondary-subtle text-end">234234234234324</p>
										<p class="m1-1 bg-warning-subtle text-start">asdfasdfasdf</p>
										<p class="m1-1 bg-secondary-subtle text-end">234234234234324</p>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<input type="text" id="chattingMsg" />
							<button type="button" class="btn btn-secondary" name="chattingModalClose">Close</button>
						</div>
					</div>
				</div>
			</div>
	<%
		}
	%>







</body>
</html>