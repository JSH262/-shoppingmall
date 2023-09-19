<%@page import="com.tjoeun.shoppingmall.vo.UsersVO"%>
<%@page import="com.tjoeun.helper.AttributeName"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UsersVO user = AttributeName.getUserData(request);
	if(user == null)
	{
		response.sendRedirect(request.getContextPath() + "/");
	}		
%>

    
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>주문</title>
		<script>
			document.id="<%=AttributeName.getUserData(request).getId() %>";
			document.contextPath = "<%=request.getContextPath() %>";
		</script>
		<%@ include file="/WEB-INF/component/header/common.jsp" %>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script src="<%=request.getContextPath() %>/js/customAlert.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/order.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/payment.js"></script>
		
		<style type="text/css">
			.product-container {
				display:grid;
				grid-template-columns: auto 1% 45% 1% 20% 1% 15%;
				grid-template-rows: 1fr;
				
			}				
			.product-item {
				font-size: 12px;
				padding: 10px;
			}				
			.product-name-container {
				display:grid;
				grid-template-rows: auto auto auto auto auto;
			}
			.product-price-container {
				display:grid;
				grid-template-rows: auto auto auto auto auto;
				height: 100%;
			}
			.product-space {
				height: 30px;
			}
			.v-line{
				border-left: thin solid lightgray;
				height:100%;
				left: 1px;
			}
			.h-line {
				border-top: thin solid gray;
			}
			
			span[name=price] {
				text-decoration: line-through;
				opacity: 0.0;
			}
			span[name=price]: before {
				color:black;
				font-size: medium;
				content: '원';
			}
			span[name=discount] {
				color: scrim;
				font-weight: bold;
			}
			span[name=discount]:after {
				content: '↓'
			}
			
			div[name=name] {
				font-size: 18px;
			}
			
			div[name=name]:hover {
				text-decoration: underline;
				cursor: pointer;
			}
			
			div[name=discountPrice] {
				font-size: 15px;
				font-weight: bold;
			}
			div[name=discountPrice]: before {
				color:black;
				font-size: medium;
				content: '원';
			}
			
			div[name=companyName] {
				font-size: 15px;
				font-weight: bold;
			}
			img[name=thumnail]:hover {
				cursor: pointer;
				box-shadow: 11px 14px 20px 13px rgba(0,0,0, .2);
			}
			
			span[name=score], span[name=review], span[name=review-bookmark-bar], span[name=bookmark], span[name=bookmarkCnt] {
				opacity: 0.0;
			}

			.page-number {
				border: 1px solid black;
				margin: 5px;		
				padding: 5px;
				width: 20px;
				display: inline-block;
				width: 5%;
			}
			.page-number:hover
			{
				color: white;
				background-color: skyblue;
				cursor: pointer;
			}
			
			.page-number-current {
				background-color: cyan;
				color: black;
			}
			.page-number-current:hover {
				cursor: default;
			}
			
			#pagination {
				text-align: center;
			}
								
			.product-sold-out {
				opacity: 0.3;
			}
			
			#totalPrice {
				font-weight: bold;
			}			
			#totalPrice:after {
				font-size:medium;
				font-weight: normal;
				content: '원'
			}
			
			#totalDeliveryPrice {
				font-weight: bold;
			}
			#totalDeliveryPrice:after {
				font-size:medium;
				font-weight: normal;
				content: '원'
			}
			#totalDiscount {
				font-weight: bold;
			}
			#totalDiscount:after {
				font-size:medium;
				font-weight: normal;
				content: '원'
			}
			#totalDiscountPrice {
				font-size:x-large;
				color: crimson;
				font-weight: bold;
			}
			#totalDiscountPrice:after {
				font-size:medium;
				color: black;
				font-weight: normal;
				content: '원'
			}
			#result {
				text-align: center;
			}
			
			div[name=product] {
				position: relative;
			}
			.overlayContents {
				position: absolute;
			    background-color: rgba(0,0,0,0.1);
		    	width: 100%;
		    	height: 100%;
		   	 	font-size: xx-large;
		   	 	text-align: center;
		   	 	justify-content: center;
		    	align-items: center;
		    	display: flex;
			}
			.hide {
				display:none;
			}
			.hide-opacity {
				opacity: 0.0;
				cursor: default;
			}
			select[name=request], input[name=requestMessage] {
				width: 40%;
			}
		</style>
	</head>
	<body>
		
		<%@ include file="/WEB-INF/component/header.jsp" %>
	
		<div class="mx-auto ms-5 me-5 mt-5">
			<div class="container">
				<div class="row">
					<div class="col fs-1">배송정보</div>
					<span class="col align-self-center">
						<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addrListModal">배송지 목록</button>
					</span>
				</div>
				<hr />
				<div class="row" id="deliveryInfoEmpty">
					<div class="fs-3">배송지 목록에서 배송지 정보를 선택해주세요</div>
				</div>
				<div class="row mb-1 opacity-0" name="deliveryInfo">
					<label name="mainAddrName">홍길동</label>
				</div>
				<div class="row mb-1 opacity-0" name="deliveryInfo">
					<div col="col">
						<label name="mainAddrAddr">서울 종로구 우정국로2길 21 9층</label>
						<input type="hidden" name="mainAddrId">
					</div>
				</div>
				<div class="row mb-1 opacity-0" name="deliveryInfo">
					<label name="mainAddrPhone">02-766-8367</label>
				</div>
				<div class="row mb-1 opacity-0" name="deliveryInfo">
					<div class="col-6">
						<select name="mainAddrRequest" disabled="disabled" style="width:100%">
							<option value="1">배송시 요청사항 선택하기</option>
							<option value="2">직접 수령하겠습니다</option>
							<option value="3">문 앞에 놓아주세요</option>
							<option value="4">경비실에 맡겨주세요</option>
							<option value="5">배송 전 휴대폰으로 연락주세요</option>
							<option value="6">파손위험이 있는 상품이니 조심히 다뤄주세요</option>						
							<option value="999">직접입력</option>
						</select>
					</div>
				</div>
				
				<%-- name="mainAddrRequest"에서 직접입력 선택시 아래 div 보이기 --%>
				<div class="row invisible" name="mainAddrReqBody">
					<div class="col-6">
						<input type="text" style="width:100%" name="mainAddrReqMsg" readonly="readonly" placeholder="수령방법을 입력해주세요 (최대 50자)" />
					</div>
					<div class="col-6">
						<span name="mainAddrCurrMsg">0</span><span>/</span><span name="mainAddrMaxMsg">50</span>
					</div>
				</div>
			</div>
			
			<div class="m-5 ">&nbsp;</div>
			
			<div>
				<div class="fs-1">주문상품</div>
				<hr />
				<div id="list">
					<div name="product">
						<div class="hide overlayContents" name="overlayContents"></div>
						<div name="productBody" class="">
							<hr class="h-line" />
							<div class="product-container">
								<div class="product-item">
									<img name="thumnail" src="<%=request.getContextPath() %>/resources/default/noimg.png" style="max-width: 100%;" />
								</div>
								<div class="v-line"></div>
								<div class="product-item">
									<div class="product-name-container">
										<div class="product-item" name="name">식기세척기</div>
										<div class="product-item">&nbsp;</div>
										<div class="product-item">&nbsp;</div>
										<div class="product-item">&nbsp;</div>
										<div class="product-item">
											<span name="score">★★★★☆</span>  
											<span name="review">1,000건</span>
											<span name="review-bookmark-bar">&nbsp;｜&nbsp;</span>
											<span>
												<span name="bookmark">♡</span>										
												<span name="bookmarkCnt">1,200건</span>
											</span>
										</div>
									</div>
								</div>
								<div class="v-line"></div>
								<div class="product-item">
									<div class="product-price-container">
										<div class="product-item" id="discountPrice" name="discountPrice">810,000원</div>
										<div class="product-item">
											
											<span name="price">900,000원</span>
											<%--
											<span name="discount">10%</span>
											 --%>
										</div>
										<div class="product-item">
											<span>수량</span>&nbsp;<span name="amount">1개</span>
										</div>
										<div class="product-item">&nbsp;</div>
										<div class="product-item">
											<span name="delivery">배송비</span>&nbsp;<span name="deliveryPrice">3,000원</span>
										</div>
									</div>
								</div>
								<div class="v-line"></div>
								<div class="product-item" name="companyName">LG전용 매장</div>
							</div>
							<hr class="h-line" />
						</div>
					</div>
				</div>	
			</div>
			
		</div>
		<div id="result">
			<span>상품금액 <span id="totalPrice"></span></span>			
			<span> + 배송비 <span id="totalDeliveryPrice"></span></span>	
			<span> - 할인금액 <span id="totalDiscount"></span></span>
			<span> = <span id="totalDiscountPrice"></span></span>
			<div>&nbsp;</div>
			<div><input type="button" id="pay" value="결제하기" /></div>
		</div>
		 
		 <%-- 배송지 목록 모달 --%>
		<div class="modal fade" id="addrListModal" tabindex="-1" aria-labelledby="AddrListModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-scrollable modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5">배송지 목록</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>					
					<div class="modal-body bg-light bg-gradient">					
			        	<div class="container">
			        		<div class="row">
				        		<div class="col align-self-center">
									<button type="button" style="width:100%;" class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#addrAddModal">배송지 추가하기</button>
								</div>
							</div>
						</div>
						
						<div name="addrListBody">
				        	<div class="container mt-3 mb-3 pt-3 pb-3 bg-opacity-10 border border-info rounded" name="addrListContents">
				        		<div class="row mb-3 fs-4">
				        			<span name="addrListName">사무실</span>
				        			<span class="invisible" name="addrListId"></span>
				        		</div>	        	
								<div class="row">
									<div class="col">
										<span name="addrListRecvName">홍길동</span><span> | </span><span name="addrListPhone">02-766-8367</span>
									</div>
								</div>
								<div class="row">
									<div class="col" name="addrListFullAddr">서울 종로구 우정국로2길 21 9층</div>
								</div>
								<div class="row">
									<div class="col" name="addrListReqMsg">경비실에 맡겨주세요</div>
								</div>
								<div class="row mt-3">
									<div class="col">
										<input name="addrListRemove" class="btn btn-danger w-100" type="button" value="삭제" />
									</div>
									<div class="col">
										<input name="addrListChoose" class="btn btn-outline-primary w-100" type="button" value="선택" />
									</div>
								</div>
							</div>
						</div>
					</div>
		    	</div>
		  	</div>
		</div>
		
		
		
		
		<%-- 배송지 추가하기 모달 --%>
		<div class="modal fade" id="addrAddModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="AddrAddModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-scrollable modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5">배송지 추가</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body" name="addrAddBody">
						<div class="container">
							<div class="row">
								<div>배송지 이름</div>
							</div>
							<div class="row">
								<div class="form-floating">
									<input type="text" class="form-control" id="addrAddName" name="addrAddName" placeholder="사무실">
									<label style="left: auto;" for="addrAddName">배송지</label>
								</div>
							</div>
							<div class="mt-1 mb-1">
								&nbsp;
							</div>
						
							<div class="row">
								<div>받는 분</div>
							</div>
				        	<div class="row">
								<div class="form-floating mb-3">								
									<input type="text" class="form-control" id="addrAddRecvName" name="addrAddRecvName" placeholder="이름">
									<label style="left: auto;" for="addrAddRecvName">이름</label>
								</div>
							</div>
							<div class="row">
								<div class="form-floating mb-3">
								 	<input type="text" class="form-control" id="addrAddPhone" name="addrAddPhone" placeholder="연락처">
									<label style="left: auto;" for="addAddrPhone">연락처</label>
								</div>
							</div>
							<div class="mt-1 mb-1">
								&nbsp;
							</div>
							<div class="row">
								<div>주소</div>
							</div>
							<div class="row">
								<div>
									<input type="button" value="우편번호 찾기" name="addrAddOpenPostcode" />
								</div>
								<div>
									<input class="form-control w-100" type="text" name="addrAddAddr1" readonly="readonly" placeholder="기본주소">
								</div>								
								<div>									
									<input class="form-control w-100" type="text" name="addrAddAddr2" placeholder="상세주소">
								</div>
							</div>
							<div class="mt-1 mb-1">
								&nbsp;
							</div>							
							<div class="row">
								<div>배송 요청사항</div>
							</div>
							<div class="row">
								<div class="col">
									<select name="addrAddRequest" class="w-100">
										<option value="1">배송시 요청사항 선택하기</option>
										<option value="2">직접 수령하겠습니다</option>
										<option value="3">문 앞에 놓아주세요</option>
										<option value="4">경비실에 맡겨주세요</option>
										<option value="5">배송 전 휴대폰으로 연락주세요</option>
										<option value="6">파손위험이 있는 상품이니 조심히 다뤄주세요</option>						
										<option value="999">직접입력</option>
									</select>
								</div>
							</div>		
							<div class="row hide" name="addrAddReq">
								<div class="col-11">
									<input class="w-100" type="text" name="addrAddReqMsg" placeholder="수령방법을 입력해주세요 (최대 50자)" width="30px" />
								</div>
								<div class="col-1">
									<span name="addrAddCurrMsg">0</span><span>/</span><span name="addrAddMaxMsg">50</span>
								</div>
							</div>
						</div>
					</div>
		      		<div class="modal-footer">
		      			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addrListModal" name="addrAddCancel">취소</button>
		        		<button type="button" class="btn btn-primary" name="addrAddSave">저장</button>
		      		</div>
				</div>
		    </div>
		</div>
		
		<%@ include file="/WEB-INF/component/customAlert.jsp" %>
		<%@ include file="/WEB-INF/component/payment.jsp" %>
		<%@ include file="/WEB-INF/component/footer.jsp" %>
	</body>
</html>