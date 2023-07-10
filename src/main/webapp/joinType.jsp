<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>타입선택</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="icon" href="./images/logo.png"/>
<link rel="stylesheet" href="./css/bootstrap.css"/>
<script type="text/javascript" src="./js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="./js/bootstrap.js"></script>
</head>
<body>
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
<script>
	function checkUserType() {
		var type = document.querySelector('input[name="type"]:checked').value;
		// 판매자일경우 회사 정보 기입창으로 넘겨줌
		if (type === "2") {
			location.href = "companyInsert.jsp?type=" + type;
		} else {
			location.href = "join.jsp?type=" + type;	// 이거 받는거 넣어줘야함
		}
	}
</script>
</body>
</html>