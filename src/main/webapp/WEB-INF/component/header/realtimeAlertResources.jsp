<%@page import="com.tjoeun.helper.UsersType"%>
<%@page import="com.tjoeun.helper.AttributeName"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	if(AttributeName.getUserType(request).equals(UsersType.SELLER))
	{
%>
<script>
	document.contextPath = '<%=request.getContextPath() %>';
	document.id = '<%=AttributeName.getUserData(request).getId() %>';
</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/realtimeAlert.js"></script>
<%
	}
%>
