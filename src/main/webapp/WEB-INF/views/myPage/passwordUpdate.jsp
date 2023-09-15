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


    <div class="dropdown position-fixed bottom-0 end-0 mb-3 me-3 bd-mode-toggle">
      <button class="btn btn-bd-primary py-2 dropdown-toggle d-flex align-items-center" id="bd-theme" type="button" aria-expanded="false" data-bs-toggle="dropdown" aria-label="Toggle theme (light)">
        <svg class="bi my-1 theme-icon-active" width="1em" height="1em"><use href="#sun-fill"></use></svg>
        <span class="visually-hidden" id="bd-theme-text">Toggle theme</span>
      </button>
      <ul class="dropdown-menu dropdown-menu-end shadow" aria-labelledby="bd-theme-text">
        <li>
          <button type="button" class="dropdown-item d-flex align-items-center active" data-bs-theme-value="light" aria-pressed="true">
            <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em"><use href="#sun-fill"></use></svg>
            Light
            <svg class="bi ms-auto d-none" width="1em" height="1em"><use href="#check2"></use></svg>
          </button>
        </li>
        <li>
          <button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="dark" aria-pressed="false">
            <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em"><use href="#moon-stars-fill"></use></svg>
            Dark
            <svg class="bi ms-auto d-none" width="1em" height="1em"><use href="#check2"></use></svg>
          </button>
        </li>
        <li>
          <button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="auto" aria-pressed="false">
            <svg class="bi me-2 opacity-50 theme-icon" width="1em" height="1em"><use href="#circle-half"></use></svg>
            Auto
            <svg class="bi ms-auto d-none" width="1em" height="1em"><use href="#check2"></use></svg>
          </button>
        </li>
      </ul>
    </div>

    
<div class="container">
  <main>
    <div class="py-5 text-center">
    <img class="d-block mx-auto mb-4" src="<%=request.getContextPath() %>/images/cat.jpg" alt="" width="72" height="57">
      <h2>비밀번호 변경</h2>
    </div>

      <div class="col-md-7 col-lg-8">
        <h4 class="mb-3">새 비밀번호 입력</h4>
        <form class="needs-validation" novalidate="">
          <div class="row g-3">
            <div class="col-12" style="padding-top: 12px">
              <label for="address" class="form-label">비밀번호</label>
              <input id="password1" class="form-control" type="password" 
							name="password1" placeholder="비밀번호를 입력하세요" 
							autocomplete="off" onkeyup="isValidUserPassword()"/>
            </div>
          </div>
          <div class="row g-3">
            <div class="col-12" style="padding-top: 12px">
              <label for="address" class="form-label">비밀번호 확인</label>
              <input id="password2" class="form-control" type="password" 
							name="password2" placeholder="비밀번호를 입력하세요" 
							autocomplete="off" onkeyup="passwordCheckFunction()"/>
            </div>
          </div>
          <hr class="my-4">

				<h5 id="passwordCheckMessage" style="color: red; font-weight: bold;"></h5>
				<!-- 아이디 중복 검사 결과 메시지가 출력될 영역 -->
				<h5 id="idCheckMessage" style="color: red; font-weight: bold;"></h5>
				<!-- 오류 메시지가 출력될 영역 -->
				<h5 id="errorMessage" style="color: lime; font-weight: bold;">
					${messageType}: ${messageContent}
				</h5>
				<input type="hidden" id="userId" value="<%=userId %>">
				<input class="btn btn-primary" type="button" value="비밀번호 변경" 
							onclick="passwordUpdate()"/>
        </form>
      </div>
  </main>
</div>

<%@ include file="/WEB-INF/component/customAlert.jsp" %>

</body>
</html>