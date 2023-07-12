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
		
	</head>
	<body>
		<h1>결제목록</h1>
		<table>
			<tr id="tableHeader">
				<th>주문번호</th>
				<th>썸네일</th>				
				<th>상품이름</th>
				<th>상품수량</th>
				<th>상품가격</th>
				<th>총 가격</th>
			</tr>
			<tbody>
				<tr id="tableNode">
					<td name="id"></td>
					<td name="thumbnail"></td>
					<td name="productName"></td>
					<td name="productAmount"></td>
					<td name="productPrice"></td>
					<td name="totalProductPrice"></td>
				</tr>
			</tbody>
		</table>
		
		<input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath() %>" />
	</body>
</html>