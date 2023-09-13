<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<%@ include file="/WEB-INF/component/header/common.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/list/buyer.js"></script>		
		<title>Insert title here</title>
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
			
			div[name=companyName] {
				font-size: 15px;
				font-weight: bold;
			}
			img[name=thumnail]:hover {
				cursor: pointer;
				box-shadow: 11px 14px 20px 13px rgba(0,0,0, .2);
			}
			
			<%--
			
			span[name=score], span[name=review], span[name=review-bookmark-bar], span[name=bookmark], span[name=bookmarkCnt] {
				opacity: 0.0;
			}
			
			--%>
			
			span[name=review-bookmark-bar], span[name=bookmark], span[name=bookmarkCnt] {
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
		</style>
	</head>
	<body>
		<%@ include file="/WEB-INF/component/header.jsp" %>
	
		
		<%--
		<h1 style="text-align:center;">구매자 페이지</h1>
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-sm-6">
					<div class="input-group">
						<%--
						<select class="form-select" id="searchCategoryId" aria-label="">
						    <option selected>선택</option>
						    <option value="1">의류</option>
							<option value="2">과일</option>
					  	</select>
	 				--%>
	 				<%--
						<input type="text" id="searchProductName" class="form-control" aria-label="Text input with dropdown button">
						<button type="button" class="btn btn-outline-info" id="searchProduct" name="searchProduct">🔍</button>
					</div>
				</div>
			</div>
		</div>
		 --%>
		<%-- 참고 사이트: https://www.11st.co.kr/category/DisplayCategory.tmall?method=getDisplayCategory2Depth&dispCtgrNo=1001473 --%>
		<div id="list">
			<div name="product">
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
								<span name="discount">10%</span>
							</div>
							<div class="product-item">&nbsp;</div>
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
	<div style="display:flex; justify-content: center;">
		<ul id="pagination" class="pagination">
		</ul>
	</div>

	<input type="hidden" id="searchProductName" name="searchProductName" value="${productName }" /> 
		<input type="hidden" id="productCategoryId" name="productCategoryId" value="${productCategoryId }" /> 
		<input type="hidden" id="contextPath" name="contextPath" value="${pageContext.request.contextPath }" />
		<input type="hidden" id="currentPage" name="currentPage" value="${currentPage }" />
		<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
		
		<%@ include file="/WEB-INF/component/footer.jsp" %>
	</body>
</html>	
