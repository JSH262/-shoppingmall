<%@page import="com.tjoeun.helper.AttributeName"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script>
	document.contextPath = '<%=request.getContextPath() %>';
	document.id = '<%=AttributeName.getUserData(request).getId() %>';
</script>
<script type="text/javascript" src="js/realtimeAlert.js"></script>



