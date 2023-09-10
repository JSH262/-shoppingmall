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

<%@ include file="/WEB-INF/component/header/common.jsp" %>
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
	<%@ include file="/WEB-INF/component/header.jsp" %>

	<table border="1" cellpadding="5" cellspacing="1" style="width:90%;margin: 0px auto;">
	<thead>
		<tr>
			<th colspan="8">
				리뷰 목록
			</th>
		</tr>
		<tr>
			<th style="width: 50px;">별점</th>
			<th style="width: 500px;">내용</th>
			<th style="width: 100px;">등록일</th>
			<th style="width: 50px;">삭제</th>
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
				<td>
					<%-- <input type="text" id="id" name="id" value="${vo.id}"/> --%>
					<%-- <input type="button" id="button_${vs.index}" name="button_${vs.index}" value="삭제하기"/> --%>
					<input type="button" value="삭제하기" onclick="deleteReview('${vo.id}')" />
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
		<input type="button" onclick="location.href='/shoppingmall/product/payment/list'" value="돌아가기"/>


<div id="messageModal" class="modal fade" role="dialog" aria-hidden="true">
    <div class="vertical-alignment-helper">
        <div class="modal-dialog vertical-align-center">
            <div id="messageCheck">
                <div class="modal-header panel-heading">
                    <button class="close" type="button" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                        <span class="sr-only">Close</span>
                    </button>
                    <h4 id="messageType" class="modal-title">
                        <%-- ${messageType} --%>
                    </h4>
                </div>
                <div id="messageContent" class="modal-body">
                    <%-- ${messageContent} --%>
                </div>
                <div class="modal-footer">
                    	<button id="success" class="btn btn-primary" type="button" data-dismiss="modal" 
                        	    onclick="location.href='<%= request.getContextPath() %>/reviewList.do?userId=<%=userId %>'">확인</button>
                </div>
            </div>
        </div>
    </div>
</div>


	<%@ include file="/WEB-INF/component/footer.jsp" %>

</body>
</html>	
