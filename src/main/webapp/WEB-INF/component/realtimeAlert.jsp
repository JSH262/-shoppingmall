<%@page import="com.tjoeun.helper.UsersType"%>
<%@page import="com.tjoeun.helper.AttributeName"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(UsersType.SELLER.equals(AttributeName.getUserType(request)))
	{
%>
<div id="realtimeAlertContents" class="position-absolute top-0 end-0 mt-3 me-3" style="z-index: 10000;">
	
</div>
<%
	}
%>