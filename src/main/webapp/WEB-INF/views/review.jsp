<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.tjoeun.shoppingmall.vo.UsersVO"%>
<%@page import="com.tjoeun.helper.AttributeName"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<%@ include file="/WEB-INF/component/header/common.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/customAlert.js"></script>
<script type="text/javascript" src="./js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="./js/review.js"></script> <!-- ajax 구현 -->
</head>
<%
	UsersVO user = AttributeName.getUserData(request);
	String userId = user.getId();
	String productId = request.getParameter("productId");
	String orderId = request.getParameter("id");
%>

<body>

<div class="container" style="margin-top: 20px;">
	<form>
		<table class="table table-border">
			<thead>
				<tr>
					<th colspan="3" style="text-align: center;">
						<h2>리뷰작성</h2>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th style="vertical-align: middle; width: 120px;">별점</th>
					<td>
						<select class="form-select" id="score" name="score" style="width: 130px">
							<option selected="selected" value="1">★</option>
							<option value="2">★★</option>
							<option value="3">★★★</option>
							<option value="4">★★★★</option>
							<option value="5">★★★★★</option>
						</select>
					</td>
				</tr>
				<tr>
					<th style="vertical-align: middle;">내용</th>
					<td>
						<textarea id="contents" name="contents" class="form-control" rows="10" placeholder="리뷰를 작성하세요" autocomplete="off"></textarea>
						<input type="hidden" id="userId" name="userId" value="<%=userId %>">
						<input type="hidden" id="productId" name="productId" value="<%=productId %>">
						<input type="hidden" id="orderId" name="orderId" value="<%=orderId %>">
					</td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: center;">
						<input class="btn btn-primary" type="button" value="리뷰등록" 
							onclick="reviewInsert()"/>
						<input class="btn btn-primary" type="button" value="돌아가기" onclick="location.href='<%=request.getContextPath() %>/product/payment/list'"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>


<%@ include file="/WEB-INF/component/customAlert.jsp" %>


</body>
</html>















