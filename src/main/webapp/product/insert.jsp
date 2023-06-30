<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/product.js"></script>

</head>
<body>
	<form id="form" method="POST" action="<%=request.getContextPath() %>">
		<table border="1" align="center" cellpadding="5" cellspacing="0" style="margin-top:50px;">
			<tr>
				<th colspan="2">
					상품 등록하기
				</th>
			</tr>
			<tr>
				<th>
					썸네일
				</th>
				<td>
					<input type="file" name="file" id="file" />
				</td>
			</tr>
			<tr>
				<th>
					상품 이름
				</th>
				<td>
					<input type="text" name="name" id="name" />					
				</td>
			</tr>
			<tr>
				<th>
					상품 가격
				</th>
				<td>
					<input type="number" name="price" id="price" />					
				</td>
			</tr>
			<tr>
				<th>
					판매수량
				</th>
				<td>
					<input type="number" name="amount" id="amount" />					
				</td>
			</tr>
			<tr>
				<th>
					배송비
				</th>
				<td>
					<input type="text" name="deliveryPrice" id="deliveryPrice" />					
				</td>
			</tr>
			<tr>
				<th>
					상품 설명
				</th>
				<td>
					<input type="text" name="contents" id="contents" />					
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" id="formSubmit" value="상품 추가하기" />
				</td>				
			</tr>
		</table>
	</form>
</body>
</html>