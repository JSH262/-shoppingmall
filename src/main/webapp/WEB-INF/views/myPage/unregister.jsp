<%@page import="com.tjoeun.helper.AttributeName"%>
<%@page import="com.tjoeun.shoppingmall.vo.UsersVO"%>
<%@page import="com.tjoeun.helper.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>unregister</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/myPage/myPage.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
<%
	UsersVO user = AttributeName.getUserData(request);
	String userId = user.getId();
%>
<div>    
	<input class="hidden" id="userId" value="<%=userId %>">
	<button type="button" class="btn btn-primary" onclick="unregister()">회원 탈퇴</button>
</div>

<div id="messageModal" class="modal fade" role="dialog" aria-hidden="true">
    <div class="vertical-alignment-helper">
        <div class="modal-dialog vertical-align-center">
            <div id="messageCheck">
                <div class="modal-header panel-heading">
                    <button class="close" type="button" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                        <span class="sr-only">Close</span>
                    </button>
                    <h4 id="messageType" class="modal-title">
                        <%-- ${messageType} --%>
                    </h4>
                </div>
                <div id="messageContent" class="modal-body">
                    <%-- ${messageContent} --%>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="button" data-dismiss="modal" 
                            onclick="location.href='<%=request.getContextPath() %>/logout'">확인</button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>