<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회사 정보 입력</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="icon" href="./images/logo.png"/>
<%@ include file="/WEB-INF/component/header/common.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/customAlert.js"></script>
<script type="text/javascript" src="./js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="./js/ajax.js"></script> <!-- ajax 구현 -->
</head>
	
<body class="bg-body-tertiary">

<%
    String type = request.getParameter("type");
%>

    <div class="container">
 <main>
    <div class="py-5 text-center">
      <img class="d-block mx-auto mb-4" src="./images/cat.jpg" alt="" width="57" height="57">
      <h2>회사정보 입력</h2>
      <p class="lead"></p>
    </div>

    <div class="row">
    	<div class="col-4"></div>
      <div class="col-4">
        <h2>회사정보 입력</h2>
        <form class="needs-validation" novalidate="">
          <div class="row g-3">

            <div class="col-12">
              <label for="address" class="form-label">이름</label>
              <input id="name" class="form-control" type="text" autocomplete="off"
							placeholder="이름을 입력하세요"/>	
            </div>
            
			<div class="col-12">
              <label for="address" class="form-label">업체번호</label>
              <input id="id" class="form-control" type="text" autocomplete="off"
							placeholder="업체번호를 입력하세요"/>	
            </div>
            
            <div class="col-12">
              <label for="address" class="form-label">주소</label>
              <input id="address1" class="form-control" type="text" autocomplete="off"
							placeholder="주소를 입력하세요"/>
            </div>
            
            <div class="col-12">
              <label for="address" class="form-label">상세주소</label>
              <input id="address2" class="form-control" type="text" autocomplete="off"
							placeholder="상세주소를 입력하세요"/>
            </div>
            
            <div class="col-12">
				<label for="address" class="form-label">우편번호</label>
              	<input id="stamp" class="form-control" type="text" autocomplete="off"
							placeholder="우편번호를 입력하세요"/>
							<input type="hidden" name="userType" id="type" value="<%= type %>">
            </div>
          </div>

          <hr class="my-4">



          	<div class="row">
          <input type="hidden" name="type" value="<%= type %>">
						<input class="btn btn-primary" type="button" value="회사정보 입력완료" 
							onclick="companyInsert()"/>
					
          </div>
        </form>
      </div>
      <div class="col-4"></div>
    </div>
  </main>
</div>
    
</body>
</html>