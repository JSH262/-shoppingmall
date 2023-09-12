<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>타입선택</title>
<link rel="icon" href="./images/logo.png"/>
<%@ include file="/WEB-INF/component/header/common.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/customAlert.js"></script>
<script type="text/javascript" src="./js/jquery-3.7.0.js"></script>
</head>
<body>
<div class="container py-3">
  <main>
    <div class="row row-cols-1 row-cols-md-3 mb-3 text-center" style="text-align: center; max-width: 700px; margin: 0 auto;">
	  <div class="col" style="max-width: 220px; padding: 10px;">
	    <div class="card mb-4 rounded-3 shadow-sm border-primary" style="max-width: 210px;">
	      <div class="card-header py-3 text-bg-primary border-primary">
	        <h4 class="my-0 fw-normal">일반회원 가입</h4>
	      </div>
	      <div class="card-body">
	        <ul class="list-unstyled mt-3 mb-4"></ul>
	        <input type="radio" name="type" value="1" checked="checked"> 일반회원
	      </div>
	    </div>
	  </div>
	  <div class="col" style="max-width: 220px; padding: 10px;">
	    <div class="card mb-4 rounded-3 shadow-sm border-primary" style="max-width: 210px;">
	      <div class="card-header py-3 text-bg-primary border-primary">
	        <h4 class="my-0 fw-normal">판매자 가입</h4>
	      </div>
	      <div class="card-body">
	        <ul class="list-unstyled mt-3 mb-4"></ul>
	        <input type="radio" name="type" value="2"> 판매자
	      </div>
	    </div>
	  </div>
	  <div class="col" style="max-width: 220px; padding: 10px;">
	    <div class="card mb-4 rounded-3 shadow-sm border-primary" style="max-width: 210px;">
	      <div class="card-header py-3 text-bg-primary border-primary">
	        <h4 class="my-0 fw-normal">관리자</h4>
	      </div>
	      <div class="card-body">
	        <ul class="list-unstyled mt-3 mb-4"></ul>
	        <input type="radio" name="type" value="3"> 관리자
	      </div>
	    </div>
	  </div>
	</div>
	<!-- 가운데 정렬 넣어주고싶음 -->
	<button type="button" class="btn btn-primary" style="text-align: center; margin: 0 auto;" onclick="checkUserType()">선택완료</button>
  </main>
</div>
<!--
<form>
	<table class="table table-hover table-bordered" style="border: 1px solid #FF0000;">
		<thead>
			<tr class="success">
				<th colspan="3" style="text-align: center;">
					<h2>타입 선택</h2>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th class="danger" style="vertical-align: middle;">가입자 구분</th>
				<td colspan="2">
					<div class="form-group" style="text-align: center; margin: 0px auto;">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-primary active">
								<input type="radio" name="type" value="1" checked="checked">구매자
							</label>
							<label class="btn btn-primary">
								<input type="radio" name="type" value="2">판매자
							</label>
							<label class="btn btn-primary">
								<input type="radio" name="type" value="3">관리자
							</label>
							<label class="btn btn-primary">
								<input type="radio" name="type" value="4">고객센터
							</label>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="text-align: center;">
					<button type="button" class="btn btn-primary" onclick="checkUserType()">선택완료</button>
				</td>
			</tr>
		</tbody>
	</table>
</form>
-->

<script>
	function checkUserType() {
		var type = document.querySelector('input[name="type"]:checked').value;
		// 판매자일경우 회사 정보 기입창으로 넘겨줌
		if (type === "2") {
			location.href = "companyInsert?type=" + type;
		} else {
			location.href = "join?type=" + type;	// 이거 받는거 넣어줘야함
		}
	}
</script>
</body>
</html>