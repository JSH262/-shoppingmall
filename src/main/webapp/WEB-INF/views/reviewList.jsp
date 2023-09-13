<%@page import="com.tjoeun.helper.AttributeName"%>
<%@page import="com.tjoeun.helper.UsersType"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.tjoeun.shoppingmall.vo.UsersVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%
	UsersVO vo = AttributeName.getUserData(request);
	String userId = vo.getId();
%>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<%@ include file="/WEB-INF/component/header/common.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/customAlert.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/reviewList.js"></script>

<title>My 리뷰</title>

<style type="text/css">
	.page-number {
		border: 1px solid black;
		margin: 5px;		
		padding: 5px;
		width: 20px;
		display: inline-block;
		width: 5%;
	}
	.page-number:hover
	{
		color: white;
		background-color: skyblue;
		cursor: pointer;
	}
	
	.page-number-current {
		background-color: cyan;
		color: black;
	}
	.page-number-current:hover {
		cursor: default;
	}
	.product-sold-out {
		background-color: crimson;
		color: white;
	}
</style>
</head>
<body>
	<table class="table table-bordered table-hover" style="width:70%; margin: 0 auto; margin-top: 150px;">
	<!-- <table class="table-border" border="1" cellpadding="5" cellspacing="1" style="width:90%;margin: 0px auto;"> -->
	<thead style="text-align: center;">
		<tr>
			<th colspan="8">
				리뷰 목록
			</th>
		</tr>
		<tr>
			<th style="width: 50px;">별점</th>
			<th style="width: 500px;">내용</th>
			<th style="width: 100px;">등록일</th>
			<th style="width: 40px;">삭제</th>
		</tr>
	</thead>
	<tbody id="list">
	
		<c:if test="${list.size() == 0}">
			<tr>
				<td colspan="5">
					<marquee>작성한 리뷰가 없습니다.</marquee>
				</td>
			</tr>
		</c:if>
		
		<c:forEach var="vo" items="${list}" varStatus="vs">
			<c:set var="intScore" value="${fn:substringBefore(vo.score, '.')}"/>
			<tr>
				<td align="center">
					<c:forEach begin="1" end="${intScore}">★</c:forEach>
				</td>
				<td align="center">${vo.contents}</td>
				<td align="center">
					<fmt:formatDate value="${vo.createDate}" pattern="yyyy.MM.dd(E)"/>
				</td>
				<td  style="display:block">
					<%-- <input type="text" id="id" name="id" value="${vo.id}"/> --%>
					<%-- <input type="button" id="button_${vs.index}" name="button_${vs.index}" value="삭제하기"/> --%>
					<input  class="btn btn-lg btn-light w-100 mx-0" type="button"  style="text-align:center; display:block; margin: 0 auto;" value="삭제하기" onclick="deleteReview('${vo.id}')" />
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div style="text-align: center; align-content: center; margin-top: 35px; margin-bottom: 10px;">
		<input class="btn btn-primary" type="button" onclick="location.href='/shoppingmall/product/payment/list'" value="결제목록"/>
		<input class="btn btn-primary" type="button" onclick="location.href='/shoppingmall/myPage'" value="My page"/>
</div>

<%@ include file="/WEB-INF/component/customAlert.jsp" %>

</body>
</html>	
