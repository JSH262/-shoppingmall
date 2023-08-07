<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/insert.js"></script>

<link href="<%=request.getContextPath() %>/summernote/summernote-lite.min.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/summernote/summernote-lite.min.js"></script>
<script src="<%=request.getContextPath() %>/summernote/lang/summernote-ko-KR.min.js"></script>

<style>
	#file, #name, #price, #amount, #deliveryPrice {
		width: 98%;
	}
</style>

</head>
<body>
	<form id="form" method="POST" action="<%=request.getContextPath() %>">
		<table border="1" align="center" cellpadding="5" cellspacing="0" style="margin-top:50px;">
			<tr>
				<th colspan="2">
					<h1>상품 등록하기</h1>
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
					상품 카테고리
				</th>
				<td>	
					<select name="categoryId" id="categoryId">
						<option value="" selected>선택</option>			
						<c:forEach var="item" items="${catList }">
							<option value="${item.id }">${item.name }</option>
						</c:forEach>
					</select>
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
					상품 할인률
				</th>
				<td>
					<input type="number" name="discount" id="discount" />					
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
					<input type="number" name="deliveryPrice" id="deliveryPrice" />					
				</td>
			</tr>
			<tr>
				<th>
					상품 설명
				</th>
				<td>
					<div name="contents" id="contents"></div>
					<%--<textarea name="contents" id="contents"></textarea> --%>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" id="formSubmit" value="상품 추가하기" />
					<input style="display:inline;" type="button" id="preview" value="미리보기" />
					<input style="display:none;" type="button" id="insertView" value="미리보기 해체" />
					<input type="button" id="return" value="돌아가기" />
				</td>				
			</tr>
		</table>
	</form>
	<input type="hidden" id="currentPage" name="currentPage" value="<%=request.getParameter("currentPage") %>">
	<input type="hidden" id="pageSize" name="pageSize" value="<%=request.getParameter("pageSize") %>">
	
</body>
</html>