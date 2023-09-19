<%@page import="com.tjoeun.helper.AttributeName"%>
<%@page import="com.tjoeun.shoppingmall.vo.UsersVO"%>
<%@page import="com.tjoeun.helper.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userUpdate</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<%@ include file="/WEB-INF/component/header/common.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/customAlert.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/myPage/myPage.js"></script> <!-- ajax 구현 -->
<style>
	#form {
          display: inline-block;
          text-align: center;
        }

	
</style>
</head>
<body class="d-flex align-items-center py-4 bg-light" style="background-color: #f8f9fa;">
<%
	UsersVO user = AttributeName.getUserData(request);
	String userId = user.getId();
	String name = user.getName();
	String email = user.getEmail();
	String phone = user.getPhone();
%>

<div class="container">
  <main>
    <div class="py-5 text-center">
    <img class="d-block mx-auto mb-4" src="<%=request.getContextPath() %>/images/cat.jpg" alt="" width="57" height="57">
      <h2>회원정보 수정</h2>
    </div>

		<div class="col-md-12 text-center">
			<h4 class="mb-3">회원정보 입력</h4>
			<form class="text-center">
				<div class="row align-items-center">
					<div class="col-4"></div>
					<div class="col-4">
						<label for="address" class="form-label">이름</label>
						<input id="name" class="form-control" type="text" name="name" value="<%=name %>" />
					</div>
					<div class="col-4"></div>
				</div>
				<div class="row">
					<div class="col-4"></div>
					<div class="col-4">
						<label for="address" class="form-label">이메일</label> 
						<input id="email" class="form-control" type="email" name="email" value="<%=email %>" />
					</div>
					<div class="col-4"></div>
				</div>
				<div class="row">
					<div class="col-4"></div>
					<div class="col-4">
						<label for="address" class="form-label">전화번호</label> 
						<input id="phone" class="form-control" type="text" name="phone" value="<%=phone %>" />
					</div>
					<div class="col-4"></div>
				</div>
				<input type="hidden" id="userId" value="<%=userId %>"> <input class="btn btn-primary" style="margin-top: 15px" type="button" value="적용하기" onclick="userUpdate()" />
			</form>
		</div>
		</main>
</div>

<%@ include file="/WEB-INF/component/customAlert.jsp" %>


</body>
</html>