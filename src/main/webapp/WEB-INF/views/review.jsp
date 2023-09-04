<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.tjoeun.shoppingmall.vo.UsersVO"%>
<%@page import="com.tjoeun.helper.AttributeName"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="icon" href="./images/logo.png"/>
<link rel="stylesheet" href="./css/bootstrap.css"/>
<script type="text/javascript" src="./js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="./js/bootstrap.js"></script>
<script type="text/javascript" src="./js/review.js"></script> <!-- ajax 구현 -->
</head>
<%
	UsersVO user = AttributeName.getUserData(request);
	String userId = user.getId();
	String productId = request.getParameter("productId");	
%>

<body>

<div class="container" style="margin-top: 20px;">
	<form>
		<table class="table table-hover table-bordered" style="border: 1px solid #FF0000;">
			<thead>
				<tr class="success">
					<th colspan="3" style="text-align: center;">
						<h2>리뷰작성</h2>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="danger" style="vertical-align: middle; width: 120px;">별점</th>
					<td>
						<select class="form-select" id="score" name="score">
							<option selected="selected" value="1">★</option>
							<option value="2">★★</option>
							<option value="3">★★★</option>
							<option value="4">★★★★</option>
							<option value="5">★★★★★</option>
						</select>
					</td>
				</tr>
				<tr>
					<th class="danger" style="vertical-align: middle;">내용</th>
					<td>
						<textarea id="contents" name="contents" class="form-control" rows="10" placeholder="리뷰를 작성하세요" autocomplete="off"></textarea>
						<input type="hidden" id="userId" name="userId" value="<%=userId %>">
						<input type="hidden" id="productId" name="productId" value="<%=productId %>">
					</td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: center;">
						<!-- 오류 메시지가 출력될 영역 -->
						<h5 id="errorMessage" style="color: lime; font-weight: bold;">
							${messageType}: ${messageContent}
						</h5>
						<input class="btn btn-primary" type="button" value="리뷰등록" 
							onclick="reviewInsert()"/>
						<input class="btn btn-primary" type="button" value="돌아가기" onclick="location.href='<%=request.getContextPath() %>/product/payment/list'"/>
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















