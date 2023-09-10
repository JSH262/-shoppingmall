<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<%@ include file="/WEB-INF/component/header/common.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/payment/list.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/customAlert.js"></script>
		
		<style>
		  table {
		    width: 90%;
		    border: 1px solid #444444;
		    border-collapse: collapse;
		    text-align: center;
		    margin-bottom: 30px;
		  }
		  th, td {
		    border: 1px solid #444444;
		    padding: 10px;
		  }
		  img {
		  	height: 500px;
		  }
		</style>
		
	</head>
	<body>
		<%@ include file="/WEB-INF/component/header.jsp" %>
	
		<h1 style="text-align: center;margin-top:30px;">결제목록</h1>
		<table border="1" align="center">
			<tr id="tableHeader" align="center">
				<th>주문번호</th>
				<th>주문 상품 정보</th>
				<th>합계</th>
				<th>상품수량</th>
				<th>상품가격</th>
				<th>배송비</th>
				<th>주문상태</th>				
			</tr>
			<tbody id="list">
				
			</tbody>
		</table>
		
		<input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath() %>" />
		
		<%@ include file="/WEB-INF/component/customAlert.jsp" %>
		<%@ include file="/WEB-INF/component/footer.jsp" %>
		
	</body>
</html>