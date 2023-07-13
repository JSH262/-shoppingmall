<%@page import="com.tjoeun.helper.UsersType"%>
<%@page import="com.tjoeun.shoppingmall.vo.UsersVO"%>
<%@page import="com.tjoeun.helper.AttributeName"%>
<%@page import="com.tjoeun.shoppingmall.vo.ProductVO"%>
<%@page import="com.tjoeun.shoppingmall.service.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
  
<!DOCTYPE html>
<html>
	<%
	///////////////////////////////////////////////////검증 추가	
	/////////////////////////////////////////////////디테일(구매자 상품)에 장바구니로 이동 버튼
	
		
		UsersVO user = AttributeName.getUserData(request);
		Integer currentPage = 1;
		Integer pageSize = 15;
	
		try
		{
			pageSize = Integer.parseInt(request.getParameter("pageSize"));			
		}
		catch(NumberFormatException exp)
		{
			
		}
		
		try
		{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		catch(NumberFormatException exp)
		{
			
		}
		
		int id = Integer.parseInt(request.getParameter("id"));
		ProductVO params = new ProductVO();
		
		params.setId(id);
		params.setChoose("detail");
		
		ProductVO vo = ProductService.getInstance().select(params);
		
		if(UsersType.SELLER.equals(user.getType()))
		{
	%>
			<head>
				<meta charset="UTF-8">
				<title>Insert title here</title>
				<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
				<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
				<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/detail/seller.js"></script>
				
				<link href="<%=request.getContextPath() %>/summernote/summernote-lite.min.css" rel="stylesheet">
				<script src="<%=request.getContextPath() %>/summernote/summernote-lite.min.js"></script>
				<script src="<%=request.getContextPath() %>/summernote/lang/summernote-ko-KR.min.js"></script>
				
				<style>
					.text-readonly {
						border: 0px solid white;
						cursor: default;
						font-size: 15px;
						width: 98%;
					}
					
					.text-input {
						border: 1px solid black;
						cursor: text;
						font-size: 15px;
						width: 98%;
					}
					
					.node-hide {
						display:none;
					}
					input[type=text] {
						width: 98%;
					}
					.product-sold-out {
						background-color: crimson;
						color: white;
					}
					
				</style>
			</head>
			<body>

<%-------------------------------------- 판매자 페이지 --%>	
<%
			if(vo != null)
			{
%>


			<%-- 
///////////////////////////////////////////////////////////////////////////////////////////품절표시 
			--%>
				<form id="form" action="<%=request.getContextPath() %>">
					<table border="1" cellpadding="5" cellspacing="1" style="margin: 0px auto;">
						<tr>
							<th>
								상품 이름
							</th>
							<td>
								<input class="text-readonly" type="text" id="name" name="name" value="<%=vo.getName() %>" readonly="readonly" />	
							</td>
						</tr>
					
						<tr>
							<th>
								상품 썸네일
							</th>
							<td colspan="2">
								<%
									if(vo.getThumbnail() != null && vo.getThumbnail().length() > 0)
									{
								%>
										<img width="300px" id="thumbnail" name="thumbnail" src="<%=request.getContextPath() %>/image/<%=vo.getThumbnail() %>" />
								<%
									}
									else
									{
								%>
										<img width="300px" id="thumbnail" name="thumbnail" src="<%=request.getContextPath() %>/resources/default/noimg.png" />
								<%	
									}
								%>
														
								<input class="node-hide" type="file" id="file" name="file" />
							</td>
											
						</tr>
						
						<tr>
							<th>
								상품 카테고리			
								
							</th>
							<td>
								<select name="categoryId" id="categoryId" disabled="disabled">
									<option value="0">선택</option>
									<option value="1">/식품/과일</option>
									<option value="2">/식품/채소</option>
									<option value="3">/식품/곡물</option>
									<option value="4">/식품/견과,건과</option>
									<option value="5">/식품/축산</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<th>
								상품 가격
							</th>
							<td>
								<input class="text-readonly node-hide" type="number" id="price" name="price" value="<%=vo.getPrice() %>" />
								<input class="text-readonly" type="text" id="fmtPrice" name="fmtPrice" value="<%=vo.getFmtPrice() %>" readonly="readonly" />
							</td>
						</tr>
						
						<tr id="discountPriceNode">
							<th>
								할인된 상품 가격
							</th>
							<td>
								<input class="text-readonly" type="text" id="fmtDiscountPrice" name="fmtDiscountPrice" value="<%=vo.getFmtDiscountPrice() %>" readonly="readonly" />
							</td>
						</tr>
						
						
						<tr>
							<th>
								상품 할인률
							</th>
							<td>
								<input class="text-readonly node-hide" type="number" id="discount" name="discount" value="<%=vo.getDiscount() %>" />
								<input class="text-readonly" type="text" id="fmtDiscount" name="fmtDiscount" value="<%=vo.getFmtDiscount() %>" readonly="readonly" />
							</td>
						</tr>
						
						<tr>
							<th id="productAmountHeader">상품 수량</th>
							<td>
								<input class="text-readonly node-hide" type="number" id="amount" name="amount" value="<%=vo.getAmount() %>" />
								<input class="text-readonly" type="text" id="fmtAmount" name="fmtAmount" value="<%=vo.getFmtAmount() %>" readonly="readonly" />
							</td>
						</tr>
					
						<tr>
							<th>상품 배송비</th>
							<td>
								<input class="text-readonly node-hide" type="number" id="deliveryPrice" name="deliveryPrice" value="<%=vo.getDeliveryPrice() %>" />
								<input class="text-readonly" type="text" id="fmtDeliveryPrice" name="fmtDeliveryPrice" value="<%=vo.getFmtDeliveryPrice() %>" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th colspan="2">
								상품 설명
							</th>
						</tr>
						<tr>
							<td colspan="2">
								<div id="contents">
									<%=vo.getContents() %>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="button" id="modify" name="modify" value="수정하기" />
								<input type="button" class="node-hide" id="save" name="save" value="저장히기" />
								<input type="button" class="node-hide" id="cancel" name="cancel" value="취소하기" />
								<input type="button" id="return" name="return" value="돌아가기" />
							</td>
						</tr>
					</table>
				</form>
				<input type="hidden" type="text" id="id" name="id" value="<%=vo.getId() %>" />
				<input type="hidden" id="categoryValue" name="categoryValue" value="<%=vo.getCategoryId() %>" />
				<input type="hidden" id="pageSize" name="pageSize" value="<%=pageSize %>" />
				<input type="hidden" id="currentPage" name="currentPage" value="<%=currentPage %>" />
<%
			}
			else
			{
%>
				<form id="form" action="<%=request.getContextPath() %>">			
					<table border="1" cellpadding="5" cellspacing="1" style="margin: 0px auto;">
						<tr>
							<td>
								상품 정보가 존재하지 않습니다.
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="button" id="return" name="return" value="돌아가기" />
							</td>
						</tr>
					</table>
				</form>			
				<input type="hidden" id="pageSize" name="pageSize" value="<%=pageSize %>" />
				<input type="hidden" id="currentPage" name="currentPage" value="<%=currentPage %>" />
<%			
			}

		}
		else
		{
			
//////////////////////////// 구매자 페이지			
			String name = vo.getName();
			String fmtPrice = vo.getFmtPrice();
			String fmtDiscountPrice = vo.getFmtDiscountPrice();
			String contents = vo.getContents();
			String fmtDiscount = vo.getFmtDiscount();
			String thumnail = request.getContextPath();
			String fmtDeliveryPrice = vo.getFmtDeliveryPrice();
			String companyName = vo.getCompanyName();
			String delivery = "배송비";
			
			if(vo.getThumbnail() != null)
			{
				thumnail += "/image/" + vo.getThumbnail();
			}
			else				
			{
				thumnail += "/resources/default/noimg.png";
			}
			
			if(vo.getDeliveryPrice() == 0)
				delivery = "";
			
			
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
				
				<%--
				<div class="product-container">
					<div class="product-image">
						<img style="max-width:95%" src="<%=thumnail %>" />
					</div>	
					<div>&nbsp;</div>
					<div class="product-info-container">
						<div name="companyName"><%=companyName %></div>
						<div>
							<hr />
						</div>
						<div name="name"><%=name %></div>
						<div>
							<span name="score">★★★★☆</span>
							<span name="review">(1,000)</span>
						</div>
						<div>
							<span name="fmtDiscountPrice"><%=fmtDiscountPrice %></span>
							<span name="fmtPrice"><%=fmtPrice %></span>
							<span name="fmtDiscount"><%=fmtDiscount %>↓</span>
						</div>
						<div>							
							<span name="delivery"><%=delivery %></span>
							<span name="fmtDeliveryPrice"><%=fmtDeliveryPrice %></span>
						</div>
						<div>
							<span name="fmtPoint">100</span>
						</div>
					</div>
					<div class="product-cart">
						<span class="input-group mb-3 justify-content-end">
							<label class="input-group-text" for="amount">수량</label>
							<input class="form-control" style="max-width: 15%;" type="number" id="amount" name="amount" min="1" value="1" />
							<input class="btn btn-outline-secondary" type="button" name="amountMinus" value="-" />
							<input class="btn btn-outline-secondary" type="button" name="amountPlus" value="+" />
						</span>
						<span>&nbsp;</span>
						<span class="input-group mb-3">
							<input class="btn btn-outline-secondary" type="button" name="cart" value="장바구니" />
							<input class="btn btn-outline-secondary" type="button" name="buy" value="구매하기" />
						</span>
					</div>	
				</div>	
				<div class="product-contents" name="contents">
					<%=contents %>
				</div>	
				
				 --%>
				
				 <%--
				 --%>
				 
				 
				<div id="alert" style="position: sticky; top:0;">
				</div>
		
				 <div class="container gap-3 mt-5">
				 	<div class="row justify-content-center">
				 		<div class="col-md-5">
							<img style="max-width:100%" src="<%=thumnail %>" />
				 		</div>
				 		<div class="col-md-2">
				 			&nbsp;
				 		</div>
				 		<div class="col-md-5">
				 			<div class="row">
				 				<%=companyName %>
				 			</div>
				 			<div class="row">
				 				<hr />
				 			</div>
				 			<div class="row">
				 				<div class="col-sm-12 fs-1">
				 					<span><%=name %></span>
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
									<span name="fmtDiscountPrice"><%=fmtDiscountPrice %></span>
									<span name="fmtPrice"><%=fmtPrice %></span>
									<span name="fmtDiscount"><%=fmtDiscount %>↓</span>
								</div>
							</div>
				 			<div class="row">
				 				<div class="col-sm-12">
									<span name="delivery"><%=delivery %></span>
									<span name="fmtDeliveryPrice"><%=fmtDeliveryPrice %></span>
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
							<%
								if(vo.getAmount() != 0)
								{
							%>
									<input class="btn btn-outline-secondary" type="button" id="cart" name="cart" value="장바구니" />
									<input class="btn btn-outline-secondary" type="button" id="buy" name="buy" value="구매하기" />
							<%
								}
								else
								{
							%>
									<input class="btn btn-secondary" type="button" value="품절" disabled />
							<%
								}
							%>
							</span>
						</div>
				 	</div>
				 	<div class="row d-flex">
				 		<div class="col-md-12 d-flex justify-content-center" style="max-width:100%">
				 			<%=contents %>
				 		</div>
				 	</div>
				</div>
				
				<input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath() %>" />
				<input type="hidden" id="id" name="id" value="<%=vo.getId() %>" />
				<input type="hidden" id="pageSize" name="pageSize" value="<%=pageSize %>" />
				<input type="hidden" id="currentPage" name="currentPage" value="<%=currentPage %>" />
			</body>
<%
		}
%>
	
		
		
	</body>
</html>