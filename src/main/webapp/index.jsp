<%@page import="com.tjoeun.helper.AttributeName"%>
<%@page import="com.tjoeun.helper.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>

	
</head>
<body>
	<h1>Hello world</h1>
	
	
	<div>	
	<%
		if(AttributeName.getUserData(request) != null)
		{
	%>
			<input type="button" value="로그아웃" onclick="location.href='<%=request.getContextPath() %>/logout.jsp'" />
			<%--
			<input type="button" value="구입할 상품확인" onclick="location.href='<%=request.getContextPath() %>/product/order.jsp'" />
			 --%>
			<input type="button" value="상품구입 목록" onclick="location.href='<%=request.getContextPath() %>/product/payment/list.jsp'" />
			<input type="button" value="장바구니" onclick="location.href='<%=request.getContextPath() %>/cart/list.jsp'" />
			<input type="button" value="상품관리" onclick="location.href='<%=request.getContextPath() %>/product/breakdown/list.jsp'" />
	<%
		}
		else
		{
	%>
			<input type="button" value="로그인" onclick="location.href='<%=request.getContextPath() %>/login.jsp'" />
	<%
		}
	%>
	
		<input type="button" value="상품보기" onclick="location.href='<%=request.getContextPath() %>/product/list.jsp'" />
	</div>

</body>
</html>