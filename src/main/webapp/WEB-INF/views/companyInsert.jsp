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
<body>
	

<%
    String type = request.getParameter("type");
%>


<body class="bg-body-tertiary">
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
    <img class="mb-4" src="./images/logo.png" alt="" width="72" height="57">
    <div class="py-5 text-center">
      <h2>회사정보 입력</h2>
    </div>

      <div class="col-md-7 col-lg-8">
        <h4 class="mb-3">회사 정보 입력란</h4>
        <form class="needs-validation" novalidate="">
          <div class="row g-3">
            <div class="col-12" style="padding-top: 12px">
              <label for="address" class="form-label">이름</label>
              <input id="name" class="form-control" type="text" autocomplete="off"
							placeholder="이름을 입력하세요"/>
            </div>
          </div>
          <div class="row g-3">
            <div class="col-12" style="padding-top: 12px">
              <label for="address" class="form-label">업체번호</label>
              <input id="id" class="form-control" type="text" autocomplete="off"
							placeholder="업체번호를 입력하세요"/>
            </div>
          </div>
          <div class="row g-3">
            <div class="col-12" style="padding-top: 12px">
              <label for="address" class="form-label">주소</label>
              <input id="address1" class="form-control" type="text" autocomplete="off"
							placeholder="주소를 입력하세요"/>
            </div>
          </div>
          <div class="row g-3">
            <div class="col-12" style="padding-top: 12px">
              <label for="address" class="form-label">상세주소</label>
              <input id="address2" class="form-control" type="text" autocomplete="off"
							placeholder="상세주소를 입력하세요"/>
            </div>
          </div>
          <div class="row g-3">
            <div class="col-12" style="padding-top: 12px">
              <label for="address" class="form-label">우편번호</label>
              <input id="stamp" class="form-control" type="text" autocomplete="off"
							placeholder="우편번호를 입력하세요"/>
			  <input type="hidden" name="userType" id="type" value="<%= type %>">
            </div>
          </div>

          <hr class="my-4">

          <input type="hidden" name="type" value="<%= type %>">
						<input class="btn btn-primary" type="button" value="회사정보 입력완료" 
							onclick="companyInsert()"/>
        </form>
      </div>
  </main>
</div>

</body>

<%-- 
<form>
	<div class="container" style="margin-top: 20px;">
		<table class="table">
			<thead>
				<tr class="success">
					<th colspan="2" style="text-align: center; font-size: 25px;">회사정보 입력</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th style="background-color: tomato; vertical-align: middle;">이름</th>
					<td>
						<input id="name" class="form-control" type="text" autocomplete="off"
							placeholder="이름을 입력하세요"/>
					</td>
				</tr>
				<tr>
					<th style="background-color: tomato; vertical-align: middle;">업체번호</th>
					<td>
						<input id="id" class="form-control" type="text" autocomplete="off"
							placeholder="업체번호를 입력하세요"/>
					</td>
				</tr>
				<tr>
					<th style="background-color: tomato; vertical-align: middle;">주소</th>
					<td>
						<input id="address1" class="form-control" type="text" autocomplete="off"
							placeholder="주소를 입력하세요"/>
					</td>
				</tr>
				<tr>
					<th style="background-color: tomato; vertical-align: middle;">상세주소</th>
					<td>
						<input id="address2" class="form-control" type="text" autocomplete="off"
							placeholder="상세주소를 입력하세요"/>
					</td>
				</tr>
				<tr>
					<th style="background-color: tomato; vertical-align: middle;">우편번호</th>
					<td>
						<input id="stamp" class="form-control" type="text" autocomplete="off"
							placeholder="우편번호를 입력하세요"/>
						<input type="hidden" name="userType" id="type" value="<%= type %>">
					</td>
				</tr>
				<tr class="warning">
					<td colspan="2">
						<input type="hidden" name="type" value="<%= type %>">
						<input class="btn btn-primary" type="button" value="회사정보 입력완료" 
							onclick="companyInsert()"/>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</form>
 --%>
</body>
</html>