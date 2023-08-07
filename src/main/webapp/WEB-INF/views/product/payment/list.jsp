<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/payment/list.js"></script>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
		
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
	</body>
</html>