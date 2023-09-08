<%@page import="com.tjoeun.helper.AttributeName"%>
<%@page import="com.tjoeun.shoppingmall.vo.UsersVO"%>
<%@page import="com.tjoeun.helper.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	UsersVO vo = AttributeName.getUserData(request);
	String userId = vo.getId();
%>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/passwordCheck.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>

    <div>    
	    <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath() %>/myPage/passwordCheck?action=userUpdate'">회원정보 수정</button>
		<button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath() %>/myPage/passwordCheck?action=passwordUpdate'">비밀번호 변경</button>
		<button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath() %>/myPage/passwordCheck?action=unregister'">회원 탈퇴</button>
		<button type="button" class="btn btn-primary"  onclick="location.href='<%= request.getContextPath() %>/reviewList.do?userId=<%=userId %>'">내가 작성한 리뷰</button>
    </div>
    
<!-- 모달로 구현 시도 -->
<!-- 
    비밀번호 확인 모달
    <div class="modal fade" id="passwordCheckModal" tabindex="-1" role="dialog" aria-labelledby="passwordCheckModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="passwordCheckModalLabel">비밀번호 확인</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="passwordForm">
                        <div class="form-group">
                            <label for="password">비밀번호 입력:</label>
                            <input type="password" class="form-control" id="password" required>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">확인</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                </div>
            </div>
        </div>
    </div>
 -->
</body>
</html>
