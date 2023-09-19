<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<%@ include file="/WEB-INF/component/header/common.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/customAlert.js"></script>
<script type="text/javascript" src="./js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="./js/ajax.js"></script> <!-- ajax 구현 -->
</head>
<body class="d-flex align-items-center py-4 bg-light" style="background-color: #f8f9fa;">

<%
  String type = request.getParameter("type");
  String companyId = ""; // 변수 선언
  if (type.equals("2")) {
	  companyId = request.getParameter("companyId"); // 초기화
  }
%>
    
    <div class="container">
 <main>
    <div class="py-5 text-center">
      <img class="d-block mx-auto mb-4" src="./images/cat.jpg" alt="" width="57" height="57">
      <h2>회원가입</h2>
      <p class="lead"></p>
    </div>

    <div class="row">
    	<div class="col-4"></div>
      <div class="col-4">
        <h4 class="mb-3">사용자 정보 입력</h4>
        <form class="needs-validation" novalidate="">
          <div class="row g-3">
            <div class="col-sm-7">
              <label for="id" class="form-label">ID</label>
              <input id="id" class="form-control" type="text" name="id"
							placeholder="아이디를 입력하세요" autocomplete="off"/>
            </div>

            <div class="col-sm-5">
              <label for="lastName" class="form-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
			  <button class="btn btn-primary w-100" type="button" onclick="registerCheckFunction()">중복검사</button>
			  <%--
              <input type="text" class="form-control" id="lastName" placeholder="" value="" required="">
               --%>
            </div>

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
			  <h5 id="passwordCheckMessage" style="color: red; font-weight: bold;"></h5>
            </div>
            
            <div class="col-12">
              <label for="address" class="form-label">이름</label>
              <input id="name" class="form-control" type="text" name="name" 
							placeholder="이름을 입력하세요" autocomplete="off"/>
            </div>
            
            <div class="col-12">
              <label for="address" class="form-label">이메일</label>
              <input id="email" class="form-control" type="email" name="email" 
							placeholder="이메일을 입력하세요" autocomplete="off"/>
            </div>
            
            <div class="col-12">
				<label for="address" class="form-label">전화번호</label>
              	<input id="phone" class="form-control" type="text" name="phone" 
							placeholder="전화번호를 '-' 없이 입력하세요" autocomplete="off"/>
						<input type="hidden" name="type" id="type" value="<%= type %>">
						<input type="hidden" name="companyId" id="companyId" value="<%= companyId %>"/>
            </div>
          </div>

          <hr class="my-4">



          	<div class="row">
          <input class="btn btn-primary" type="button" value="회원가입" 
					onclick="userReister()"/>
					
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















