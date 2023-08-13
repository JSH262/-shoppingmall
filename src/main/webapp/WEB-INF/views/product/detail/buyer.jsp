<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
  
<!DOCTYPE html>
<html>
	<%
	/////////////////////////////////////////////////디테일(구매자 상품)에 장바구니로 이동 버튼
	%>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/detail/buyer.js"></script>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
		
		<style>
			.product-container {
				max-width: 1200px;
				display: grid;
				grid-template-columns: 47% auto 47%;
				grid-template-rows: 100%;
				margin: 0px auto;
			}
			
			.product-info-container {
				display: grid;						
				grid-template-rows: 10% 1% 1fr 1fr 1fr 1fr 10%;
				margin-left: 30px;
			}
			
			.product-image {
				text-align: center;
				justify-content: center;
				display: flex;
				align-items: center;
			}
			.product-cart {
				grid-column: 1/4;
				text-align: center;
				display:flex;
				align-items: center;
				justify-content: center;
									
			}
			.product-contents {
				text-align: center;
				justify-content: center;
				display: flex;
				align-items: center;
				margin-top: 100px;
			}
			
			
			span[name=score], span[name=review] {
				opacity: 0.0;
			}
			
						
			input[name=amount] {
				width: 40px;
				text-align: center;
				-moz-appearance: textfield;
			}
			input[name=amount]::-webkit-outer-spin-button, input[name=amount]::-webkit-inner-spin-button {
    			-webkit-appearance: none;
    			-moz-appearance: none;
    			appearance: none;
			}
					
			div[name=companyName] {
				font-size: 13pt;
				text-align: left;
				align-items: center;
				justify-content: flex-start;						
				display: flex;
			}
			div[name=name] {
				font-size: 28pt;
				align-items: center;
				justify-content: flex-start;						
				display: flex;
			}
			
			span[name=fmtDiscountPrice] {
				font-size: 18pt;
				font-weight: bold;
			}
			
			span[name=fmtPrice] {
				text-decoration: line-through;
			}
			
			span[name=fmtDiscount] {
				color: crimson;
				font-size: 14pt;
			
			}
			span[name=fmtPoint] {
				opacity: 0.0;
				color: skyblue;
			}				
			span[name=fmtPoint]:after {
				color: black;
				content: ' 적립'
			}
			span[name=amountGroup] {
			}

			
		</style>
	</head>
	<body> 
		<div id="alert" style="position: sticky; top:0;">
		</div>

		 <div class="container gap-3 mt-5">
		 	<div class="row justify-content-center">
		 		<div class="col-md-5">
					<img style="max-width:100%" src="${product.thumbnail }" />
		 		</div>
		 		<div class="col-md-2">
		 			&nbsp;
		 		</div>
		 		<div class="col-md-5">
		 			<div class="row">
		 				${product.companyName }
		 			</div>
		 			<div class="row">
		 				<hr />
		 			</div>
		 			<div class="row">
		 				<div class="col-sm-12 fs-1">
		 					<span>${product.name }</span>
		 				</div>
		 			</div>
		 			<div class="row">
		 				<div class="col-sm-12">
							<span name="score">★★★★☆</span>
							<span name="review">(1,000)</span>
						</div>
		 			</div>
		 			<div class="row">
		 				<div class="row">&nbsp;</div>
		 			</div>
		 			<div class="row">
		 				<div class="col-sm-12">
							<span name="fmtDiscountPrice">${product.fmtDiscountPrice}</span>
							<span name="fmtPrice">${product.fmtPrice }</span>
							<span name="fmtDiscount">${product.fmtDiscount }↓</span>
						</div>
					</div>
		 			<div class="row">
		 				<div class="col-sm-12">
							<span name="delivery">${delivery }</span>
							<span name="fmtDeliveryPrice">${product.fmtDeliveryPrice}</span>
						</div>
					</div>
		 			<div class="row">
						<span name="fmtPoint">100P</span>
					</div>
		 		</div>
		 	</div>
		 	<div class="row pt-3 pb-3 bg-light" style="position: sticky; top:60px;">
		 		<div class="col-md-6">
			 		<span class="input-group mb-3 justify-content-end">
						<label class="input-group-text" for="amount">수량</label>
						<input class="form-control" style="max-width: 15%;" type="number" id="amount" name="amount" min="1" value="1" />
						<input class="btn btn-outline-secondary" type="button" id="amountMinus" name="amountMinus" value="-" />
						<input class="btn btn-outline-secondary" type="button" id="amountPlus" name="amountPlus" value="+" />
					</span>
				</div>
				<div class="col-md-1">					
					<span>&nbsp;</span>
				</div>
				<div class="col-md-5">
					<span class="input-group mb-3">
						<input class="btn btn-outline-secondary" type="button" id="return" name="return" value="돌아가기" />
						
						<c:choose>
							<c:when test="${product.amount != 0 }">
								<input class="btn btn-outline-secondary" type="button" id="cart" name="cart" value="장바구니" />
								<input class="btn btn-outline-secondary" type="button" id="buy" name="buy" value="구매하기" />
							</c:when>
							<c:otherwise>
								<input class="btn btn-secondary" type="button" value="품절" disabled />
							</c:otherwise>
						</c:choose>
					</span>
				</div>
		 	</div>
		 	<div class="row d-flex">
		 		<div class="col-md-12 d-flex justify-content-center" style="max-width:100%">
		 			${product.contents }
		 		</div>
		 	</div>
		</div>
		
		
		<div>
			<h1>채팅 테스트</h1>
			<div style="position: fixed; bottom:30px; right: 30px;">
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-info" id="btnStartChatting">
					<i class="bi bi-chat-right-text-fill"></i>
				</button>
			</div>
			
		</div>
			
		
		<input type="hidden" id="sellerId" name="sellerId" value="${product.sellerId }" />
		<input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath() %>" />
		<input type="hidden" id="id" name="id" value="${product.id }" />
		<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
		<input type="hidden" id="currentPage" name="currentPage" value="${currentPage }" />
	</body>
</html>