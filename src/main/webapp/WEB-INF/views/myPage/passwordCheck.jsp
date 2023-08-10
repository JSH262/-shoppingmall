<%@page import="com.tjoeun.helper.AttributeName"%>
<%@page import="com.tjoeun.shoppingmall.vo.UsersVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>passwordCheck</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="icon" href="<%=request.getContextPath() %>/images/logo.png"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/passwordCheck.js"></script> <!-- ajax 구현 -->
</head>
<body>

<%
	String action = request.getParameter("action");
	UsersVO user = AttributeName.getUserData(request);
	String userId = user.getId();
%>

<div class="container" style="margin-top: 20px;">
	<form>
		<table class="table table-hover table-bordered" style="border: 1px solid #FF0000;">
			<thead>
				<tr class="success">
					<th colspan="3" style="text-align: center;">
						<h2>비밀번호 확인</h2>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="danger" style="vertical-align: middle;">비밀번호</th>
					<td>
						<input id="password" class="form-control" type="password" 
							name="password" placeholder="비밀번호를 입력하세요" 
							autocomplete="off"/>
					</td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: center;">
						<!-- 오류 메시지가 출력될 영역 -->
						<h5 id="errorMessage" style="color: lime; font-weight: bold;">
							${messageType}: ${messageContent}
						</h5>
						<input class="hidden" id="userId" value="<%=userId %>"/>
						<input class="btn btn-primary" type="button" value="확인" onclick="passwordCheck()"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
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
                            onclick="location.href='<%= request.getContextPath() %>/<%=action %>.jsp'">확인</button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

