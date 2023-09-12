<%@page import="com.tjoeun.helper.AttributeName"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
<%--
	.chat-messages => 스크롤을 가지고 있음
	
	
	    
--%>
   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
			
		<script>
			document.contextPath = '<%=request.getContextPath() %>';
			document.id = '<%=AttributeName.getUserData(request).getId() %>';
		</script>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="resources/bootstrap-icons-1.10.5/font/bootstrap-icons.css">				
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
		<script type="text/javascript" src="js/chattingSeller.js"></script>

		<style>
			body {
				margin-top: 20px;
			}
			
			.chat-online {
				color: #34ce57
			}
			
			.chat-offline {
				color: #e4606d
			}
			
			.chat-messages {
				display: flex;
				flex-direction: column;
				max-height: 700px;
				overflow-y: scroll;
				overflow-x: hidden;
			}
			
			.chat-message-left, .chat-message-right {
				display: flex;
				flex-shrink: 0
			}
			
			.chat-message-left {
				margin-right: auto
			}
			
			.chat-message-right {
				flex-direction: row-reverse;
				margin-left: auto
			}
			.chat-avatar-right {
				text-align: center;
				padding-left: 5px;
			}
			.chat-avatar-left {
				text-align: center;
				padding-right: 5px;
			}
			
			.py-3 {
				padding-top: 1rem !important;
				padding-bottom: 1rem !important;
			}
			
			.px-4 {
				padding-right: 1.5rem !important;
				padding-left: 1.5rem !important;
			}
			
			.flex-grow-0 {
				flex-grow: 0 !important;
			}
			
			.border-top {
				border-top: 1px solid #dee2e6 !important;
			}
		</style>
	</head>
	<body>
		<main class="content">
			<div class="container p-0">
		
				<h1 class="h3 mb-3">Messages</h1>
		
				<div class="card">
					<div class="row g-0">
						<div id="chatUserList" class="col-12 col-lg-3 col-xl-3 border-right" style="max-height:760px;overflow-y:scroll;overflow-x:hidden;">
		
							<div class="px-4 d-none d-md-block" id="chatUserSearchGup" style="display:none;">
								<div class="d-flex align-items-center">
									<div class="flex-grow-1">
										<input type="text" class="form-control my-3"
											placeholder="찾기...">
									</div>
								</div>
							</div>
		
									
							<hr class="d-block d-lg-none mt-1 mb-0">
						</div>
						<div class="col-12 col-lg-9 col-xl-9">
							<div class="py-2 px-4 border-bottom d-none d-lg-block invisible">
								<div class="d-flex align-items-center py-1">
									<div class="position-relative">
										<img src="https://bootdey.com/img/Content/avatar/avatar3.png"
											class="rounded-circle mr-1" alt="Sharon Lessman" width="40"
											height="40">
									</div>
									<div class="flex-grow-1 ps-3">
										<strong>Sharon Lessman</strong>
										<div class="text-muted small">
											<em>Typing...</em>
										</div>
									</div>
									<div>
										<button class="btn btn-primary btn-lg mr-1 px-3">
											<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
												viewBox="0 0 24 24" fill="none" stroke="currentColor"
												stroke-width="2" stroke-linecap="round"
												stroke-linejoin="round"
												class="feather feather-phone feather-lg">
												<path
													d="M22 16.92v3a2 2 0 0 1-2.18 2 19.79 19.79 0 0 1-8.63-3.07 19.5 19.5 0 0 1-6-6 19.79 19.79 0 0 1-3.07-8.67A2 2 0 0 1 4.11 2h3a2 2 0 0 1 2 1.72 12.84 12.84 0 0 0 .7 2.81 2 2 0 0 1-.45 2.11L8.09 9.91a16 16 0 0 0 6 6l1.27-1.27a2 2 0 0 1 2.11-.45 12.84 12.84 0 0 0 2.81.7A2 2 0 0 1 22 16.92z"></path></svg>
										</button>
										<button
											class="btn btn-info btn-lg mr-1 px-3 d-none d-md-inline-block">
											<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
												viewBox="0 0 24 24" fill="none" stroke="currentColor"
												stroke-width="2" stroke-linecap="round"
												stroke-linejoin="round"
												class="feather feather-video feather-lg">
												<polygon points="23 7 16 12 23 17 23 7"></polygon>
												<rect x="1" y="5" width="15" height="14" rx="2" ry="2"></rect></svg>
										</button>
										<button class="btn btn-light border btn-lg px-3">
											<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
												viewBox="0 0 24 24" fill="none" stroke="currentColor"
												stroke-width="2" stroke-linecap="round"
												stroke-linejoin="round"
												class="feather feather-more-horizontal feather-lg">
												<circle cx="12" cy="12" r="1"></circle>
												<circle cx="19" cy="12" r="1"></circle>
												<circle cx="5" cy="12" r="1"></circle></svg>
										</button>
									</div>
								</div>
							</div>
		
							<div class="position-relative">
								<div id="chatList" class="chat-messages p-4">	
								
								
								
								
								
									
									
									
									
									<div class="flex-grow-0 py-3 px-4 border-top sticky-bottom bg-light invisible" id="chatMsgGup">
										<div class="input-group">
											<input type="text" class="form-control" placeholder="대화를 입력해주세요">
											<button class="btn btn-primary">보내기</button>
										</div>
									</div>
									<div class="flex-grow-0 py-3 px-4 border-top sticky-bottom bg-light text-center" id="chatMsgStart">
										대화를 시작하려면 왼쪽 목록에서 사용자를 선택해주세요
									</div>
									
								</div>
								
							</div>		
						</div>
					</div>
					
					
				</div>
				
		
			</div>
		</main>
	</body>
</html>