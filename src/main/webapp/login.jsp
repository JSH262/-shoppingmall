<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="icon" href="./images/logo.png"/>
<link rel="stylesheet" href="./css/bootstrap.css"/>
<script type="text/javascript" src="./js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="./js/bootstrap.js"></script>
<script type="text/javascript" src="./js/ajax.js"></script> <!-- ajax 구현 -->
</head>
<body>

<div class="container" style="margin-top: 20px;">
	<form>
		<table class="table table-hover table-bordered" style="border: 1px solid #FF0000;">
			<thead>
				<tr class="success">
					<th colspan="3" style="text-align: center;">
						<h2>로그인</h2>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="danger" style="vertical-align: middle; width: 120px;">아이디</th>
					<td>
						<input id="id" class="form-control" type="text" name="id"
							placeholder="아이디를 입력하세요" autocomplete="off"/>
					</td>
				</tr>
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
						<input class="btn btn-primary" type="button" value="로그인" 
							onclick="UserLogin()"/>
						<input class="btn btn-primary" type="button" value="회원가입" onclick="location.href='joinType.jsp'"/>
						<input class="btn btn-primary" type="button" value="입력된 값 확인" onclick="showInputValues()"/>
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

<script>
	function showInputValues() {
		var userID = document.getElementById("id").value;
		var userPassword = document.getElementById("password").value;
		
		alert("아이디: " + id + "\n비밀번호: " + password);
	}
</script>

</body>
</html>















