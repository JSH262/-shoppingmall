<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="icon" href="./images/logo.png"/>
<link rel="stylesheet" href="./css/bootstrap.css"/>
<script type="text/javascript" src="./js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="./js/bootstrap.js"></script>
<script type="text/javascript" src="./js/ajax.js"></script> <!-- ajax 구현 -->
</head>
<body>

<%
  String userType = request.getParameter("userType");
  String companyId = ""; // 변수 선언
  if (userType.equals("2")) {
	  companyId = request.getParameter("companyId"); // 초기화
  }
%>


<div class="container" style="margin-top: 20px;">
	<form>
		<table class="table table-hover table-bordered" style="border: 1px solid #FF0000;">
			<thead>
				<tr class="success">
					<th colspan="3" style="text-align: center;">
						<h2>회원 가입</h2>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="danger" style="vertical-align: middle; width: 120px;">아이디</th>
					<td>
						<input id="userId" class="form-control" type="text" name="userId"
							placeholder="아이디를 입력하세요" autocomplete="off"/>
					</td>
					<td class="info" style="vertical-align: middle; width: 120px; text-align: center;">
						<button class="btn btn-primary" type="button" onclick="registerCheckFunction()">
							중복검사
						</button>
					</td>
				</tr>
				<tr>
					<th class="danger" style="vertical-align: middle;">비밀번호</th>
					<td colspan="2">
						<input id="userPassword1" class="form-control" type="password" 
							name="userPassword1" placeholder="비밀번호를 입력하세요" 
							autocomplete="off" onkeyup="isValidUserPassword()"/>
					</td>
				</tr>
				<tr>
					<th class="danger" style="vertical-align: middle;">비밀번호 확인</th>
					<td colspan="2">
						<input id="userPassword2" class="form-control" type="password" 
							name="userPassword2" placeholder="비밀번호를 입력하세요" 
							autocomplete="off" onkeyup="passwordCheckFunction()"/>
					</td>
				</tr>
				<tr>
					<th class="danger" style="vertical-align: middle;">이름</th>
					<td colspan="2">
						<input id="userName" class="form-control" type="text" name="userName" 
							placeholder="이름을 입력하세요" autocomplete="off"/>
					</td>
				</tr>
				<tr>
					<th class="danger" style="vertical-align: middle;">이메일</th>
					<td colspan="2">
						<input id="userEmail" class="form-control" type="email" name="userEmail" 
							placeholder="이메일을 입력하세요" autocomplete="off"/>
					</td>
				</tr>
				<tr>
					<th class="danger" style="vertical-align: middle;">전화번호</th>
					<td colspan="2">
						<input id="userPhone" class="form-control" type="text" name="userPhone" 
							placeholder="전화번호를 '-' 없이 입력하세요" autocomplete="off"/>
						<input type="hidden" name="userType" id="userType" value="<%= userType %>">
						<input type="hidden" name="companyId" id="companyId" value="<%= companyId %>"/>
					</td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: center;">
						<!-- 비밀번호 일치 검사 결과 메시지가 출력될 영역 -->
						<h5 id="passwordCheckMessage" style="color: red; font-weight: bold;"></h5>
						<!-- 아이디 중복 검사 결과 메시지가 출력될 영역 -->
						<h5 id="idCheckMessage" style="color: red; font-weight: bold;"></h5>
						<!-- 오류 메시지가 출력될 영역 -->
						<h5 id="errorMessage" style="color: lime; font-weight: bold;">
							${messageType}: ${messageContent}
						</h5>
						<input class="btn btn-primary" type="button" value="회원가입" 
							onclick="userReister()"/>
						<input class="btn btn-primary" type="reset" value="다시쓰기"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>


<div id="messageModal" class="modal fade" role="dialog" aria-hidden="true">
	<div class="vertical-alignment-helper">
		<div class="modal-dialog vertical-align-center">
			<!-- 모달 창의 종류(색상)를 설정한다. -->
			<!-- messageCheck라는 id를 추가하고 class를 제거한다. -->
			<div id="messageCheck">
				<div class="modal-header panel-heading">
					<button class="close" type="button" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
					<!-- messageType이라는 id를 추가한다. -->
					<h4 id="messageType" class="modal-title">
						<%-- ${messageType} --%>
					</h4>
				</div>
				<!-- messageContent라는 id를 추가한다. -->
				<div id="messageContent" class="modal-body">
					<%-- ${messageContent} --%>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" type="button" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>















