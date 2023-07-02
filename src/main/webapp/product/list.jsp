<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/list.js"></script>

<title>Insert title here</title>

<%--
	1. ajax POST 요청: /product/listOK 
	2. ajax POST 받음: jquery로 화면에 렌더링
	3. 끝
 --%>
</head>
<body>
	<form id="form" action="<%=request.getContextPath() %>" >
		<table border="1" cellpadding="5" cellspacing="1">
			<thead>
				<tr>
					<th colspan="8">
						등록한 상품 목록
					</th>
				</tr>				
				<tr>
					<th>순번</th>
					<th>카테고리</th>
					<th>상품 이름</th>
					<th>남은 수량</th>
					<th>상품 가격</th>
					<th>상품 할인률</th>
					<th>배송비</th>
					<th>상품 등록일</th>				
				</tr>
			</thead>
			<tbody id="list">
			</tbody>
			<tfoot>
				<tr>
					<td colspan="8" align="center">
						<span>처음으로</span>
						<span>이전</span>
												
						<span>1</span>
						<span>2</span>
						<span>3</span>
						<span>4</span>
												
						<span>다음</span>
						<span>끝으로</span>
					</td>
					
				</tr>
			</tfoot>			
		</table>
		<input type="hidden" id="currentPage" name="currentPage" value="<%=currentPage %>" />
		<input type="hidden" id="pageSize" name="pageSize" value="<%=pageSize %>" />
	</form>
</body>
</html>