<%@page import="com.tjoeun.helper.AttributeName"%>
<%@page import="com.tjoeun.shoppingmall.vo.UsersVO"%>
<%@page import="com.tjoeun.helper.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>passwordUpdate</title>
<%@ include file="/WEB-INF/component/header/common.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/customAlert.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/myPage/passwordUpdate.js"></script>
</head>

<body class="d-flex align-items-center py-4 bg-light" style="background-color: #f8f9fa;">


<%
	UsersVO	user = AttributeName.getUserData(request);
	String userId = user.getId();
%>

    <div class="container">
 <main>
    <div class="py-5 text-center">
      <img class="d-block mx-auto mb-4" src="<%=request.getContextPath() %>/images/cat.jpg" alt="" width="57" height="57">
      <h2>비밀번호 변경</h2>
      <p class="lead"></p>
    </div>

    <div class="row">
    	<div class="col-4"></div>
      <div class="col-4">
        <h4 class="mb-3">새 비밀번호 입력</h4>
        <form class="needs-validation" novalidate="">
          <div class="row g-3">
            <div class="col-12">
              <label for="address" class="form-label">비밀번호</label>
              <input id="password1" class="form-control" type="password" 
							name="password1" placeholder="비밀번호를 입력하세요" 
							autocomplete="off" onkeyup="isValidUserPassword()"/>		
            </div>
            
			<div class="col-12">
              <label for="address" class="form-label">비밀번호 확인</label>
              <input id="password2" class="form-control" type="password" 
							name="password2" placeholder="비밀번호를 입력하세요" 
							autocomplete="off" onkeyup="passwordCheckFunction()"/>		
            </div>

          <hr class="my-4">
			<h5 id="passwordCheckMessage" style="color: red; font-weight: bold;"></h5>


          	<div class="row">
          <input type="hidden" id="userId" value="<%=userId %>">
				<input class="btn btn-primary" type="button" value="비밀번호 변경" 
							onclick="passwordUpdate()"/>
					
          </div>
        </form>
      </div>
      <div class="col-4"></div>
    </div>
  </main>
</div>

<%@ include file="/WEB-INF/component/customAlert.jsp" %>

</body>
</html>