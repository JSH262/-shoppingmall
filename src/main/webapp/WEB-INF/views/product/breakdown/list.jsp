<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<%@ include file="/WEB-INF/component/header/common.jsp" %>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/breakdown/list.js"></script>
							
	</head>
	<body>
		<%@ include file="/WEB-INF/component/header.jsp" %>
		<div>
			<table class="table table-hover">
				<thead>
			   		<tr align="center">
			   			<th scope="col" colspan="12">
			   				총 판매금액: 100,000원 
			   			</th>
			   		</tr>
			   		<tr>
			     		<th scope="col">순번</th>
			     		<th scope="col" width="10%">썸네일</th>
			     		<th scope="col">상품이름</th>
			     		<th scope="col">구매일자</th>
			     		<th scope="col">구매수량</th>
			     		<th scope="col">남은수량</th>
			     		<th scope="col">기본주소</th>
			     		<th scope="col">상세주소</th>
			     		<th scope="col">연락처</th>
			     		<th scope="col">배송메시지</th>
			     		<th scope="col">상태</th>
			     		<th scope="col">상태 변경</th>			     		
			   		</tr>
			 	</thead>
			 	<tbody class="table-group-divider" name="productBody">
			   		
				</tbody>
			</table>			
		</div>
		
		<div>
			<nav aria-label="Page navigation">
			  	<ul class="pagination justify-content-center" id="pagination">
			  	</ul>
			</nav>
		</div>
		
		<input type="hidden" id="contextPath" value="<%=request.getContextPath() %>" />
		<%@ include file="/WEB-INF/component/footer.jsp" %>
	</body>
</html>