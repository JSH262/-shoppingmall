<%@page import="java.util.List"%>
<%@page import="com.tjoeun.shoppingmall.vo.CategoryList"%>
<%@page import="com.tjoeun.shoppingmall.service.CategoryService"%>
<%@page import="java.util.Locale.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	List categoryList = CategoryService.getInstance().selectList();
	request.setAttribute("categoryList", categoryList);
	pageContext.forward("categoryView2.jsp");
%>

</body>
</html>