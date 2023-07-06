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
			</style>
		</head>
		<body>
			<h1>구매자용 페이지</h1>
			<div style="display:grid;grid-template-columns: 1fr 1fr 1fr;border:1px solid black;">
				<div>
					<img src="<%=request.getContextPath() %>/resources/default/noimg.png" />
				</div>
				<div>
					<span>LG 식기세척기</span>
					<del>999,999,999</del>
					<span>10%</span>
					<span>989,999,999</span>
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
