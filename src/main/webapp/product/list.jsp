<%@page import="com.tjoeun.shoppingmall.vo.UsersVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>		

<!DOCTYPE html>
<html>
<%

/////////////////////////////////////////////////////////////////////////////////////////////
	//테스트 용
	final String USERS_TYPE_SELLER = "1";
	final String USERS_TYPE_BUYER = "2";
	
	UsersVO vo = new UsersVO();
	vo.setId("asdf1234");
	vo.setType("2");
%>

<%
	int currentPage = 1;
	int pageSize = 15;
	
	try
	{
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	catch(Exception exp)
	{
		
	}
	
	try
	{
		pageSize = Integer.parseInt(request.getParameter("pageSize"));
	}
	catch(Exception exp)
	{
		
	}

	//판매자
	if(vo.getType().equals(USERS_TYPE_SELLER))
	{
%>
		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,initial-scale=1.0" />
			
			<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/list.js"></script>
			
			<title>판매자</title>
			
			
			<style type="text/css">
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
			</style>
		</head>
		<body>
			<table border="1" cellpadding="5" cellspacing="1" style="width:90%;margin: 0px auto;">
				<thead>
					<tr>
						<th colspan="8">
							등록한 상품 목록
						</th>
						
					</tr>
					<tr>
						<td colspan="8" align="right">
							<input type="button" id="registerProduct" value="상품 등록하기" />
						</td>
					</tr>
					<tr>
						<td colspan="8" align="right">
							<select id="searchCategory">
								<option value="name">상품 이름</option>
								<option value="categoryId">상품 종류</option>
							</select>
							<input type="text" id="searchValue" />
							<input type="button" id="searchProduct" value="검색" />
						</td>
					</tr>
					<tr>
						<th>순번</th>
						<th>카테고리</th>
						<th>이름</th>
						<th>남은 수량</th>
						<th>가격</th>
						<th>할인률</th>
						<th>배송비</th>
						<th>등록일</th>
					</tr>
				</thead>
				<tbody id="list">
				</tbody>
				<tfoot>
					<tr>
						<td colspan="8" align="center" id="pagination" height="50px">
							
						</td>
					</tr>
				</tfoot>			
			</table>
				
			<input type="hidden" id="contextPath" name="form" value="<%=request.getContextPath() %>" />
			<input type="hidden" id="currentPage" name="currentPage" value="<%=currentPage %>" />
			<input type="hidden" id="pageSize" name="pageSize" value="<%=pageSize %>" />
		</body>
<%
	}

	//구매자
	else if(vo.getType().equals(USERS_TYPE_BUYER))
	{
%>
		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width,initial-scale=1.0" />		
			<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
			
			<title>Insert title here</title>
			<style type="text/css">
				.product-container {
					display:grid;
					grid-template-columns: 15% 1% 43% 1% 19% 1% 18%;
					grid-template-rows: 1fr;
					
				}
				.product-item {
					font-size: 18px;
					padding: 10px;
				}
				
				.product-name-container {
					display:grid;
					grid-template-rows: auto auto auto auto auto 
				}
				.product-price-container {
					display:grid;
					grid-template-rows: auto auto auto auto auto 
				}
				.product-space {
					height: 30px;
				}
				.v-line{
					border-left: thin solid lightgray;
					height:90%;
					left: 1px;
				}
				.h-line {
					border-top: thin solid lightgray;
				}
			</style>
		</head>
		<body>
			<h1>구매자용 페이지</h1>
			<div id="list">
				<div name="product">
					<hr class="h-line" />
					<div class="product-container">
						<div class="product-item" name="thumnail">사진</div>
						<div class="v-line"></div>
						<div class="product-item">
							<div class="product-name-container">
								<div class="product-item" name="name">이름</div>
								<div class="product-item">&nbsp;</div>
								<div class="product-item">&nbsp;</div>
								<div class="product-item">&nbsp;</div>
								<div class="product-item">
									<span name="score">평점</span>  
									<span name="review">리뷰 개수</span>
									<span>
										<span name="bookmark">찜하기</span>
										<span name="bookmarkCnt">찜하기 개수</span>
									</span>
								</div>
							</div>
						</div>
						<div class="v-line"></div>
						<div class="product-item">
							<div class="product-price-container">
								<div class="product-item" id="discountPrice" name="discountPrice">할인된 가격</div>
								<div class="product-item">
									<del name="price">원래가격</del>
									<span name="discount">할인률</span>
								</div>
								<div class="product-item">&nbsp;</div>
								<div class="product-item">&nbsp;</div>
								<div class="product-item" name="deliveryPrice">배송비</div>
							</div>
						</div>
						<div class="v-line"></div>
						<div class="product-item" name="companyName">업체명</div>
					</div>
					<hr class="h-line" />
				</div>
			</div>
			
			
			<input type="hidden" id="contextPath" name="form" value="<%=request.getContextPath() %>" />
			<input type="hidden" id="currentPage" name="currentPage" value="<%=currentPage %>" />
			<input type="hidden" id="pageSize" name="pageSize" value="<%=pageSize %>" />
		</body>
<%
	}
%>
</html>	
