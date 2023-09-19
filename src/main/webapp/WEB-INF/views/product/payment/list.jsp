<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>상품</title>
		<%@ include file="/WEB-INF/component/header/common.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/payment/list.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/customAlert.js"></script>
		
		<style>
		  img {
		  	height: 500px;
		  }
		  table {
		  	max-width:1200px;
		  	margin: auto;
		  }
		</style>
		
	</head>
	<body>
		<%@ include file="/WEB-INF/component/header.jsp" %>
	
		<!-- 
		<h1 style="text-align: center;margin-top:30px;">결제목록</h1>
		 -->
		<table class="table table-sm table-hover table-bordered">
			<thead>
				<tr id="tableHeader" >
					<th scope="col">주문번호</th>
					<th scope="col">주문 상품 정보</th>
					<th scope="col">합계</th>
					<th scope="col">상품수량</th>
					<th scope="col">상품가격</th>
					<th scope="col">배송비</th>
					<th scope="col">주문상태</th>				
				</tr>
			</thead>
			<tbody id="list" class="table-group-divider">
				
			</tbody>
		</table>
		
		<input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath() %>" />
		
		<%@ include file="/WEB-INF/component/customAlert.jsp" %>
		<%@ include file="/WEB-INF/component/footer.jsp" %>
		
	</body>
</html>