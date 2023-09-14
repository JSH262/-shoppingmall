<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>		
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<%@ include file="/WEB-INF/component/header/common.jsp" %>		
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/list/seller.js"></script>
		
		<title>판매자</title>
		
		
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
			
			table {
				max-width:1200px;
				margin: auto;
			}
		</style>
	</head>
	<body>
		<%@ include file="/WEB-INF/component/header.jsp" %>
	
		<%--https://getbootstrap.kr/docs/5.2/content/tables/ --%>
		<table class="table table-striped" <%-- border="1" cellpadding="5" cellspacing="1" style="width:90%;margin: 0px auto;" --%>>
			<thead>
				<%--
				<tr>
					<th colspan="8">
						등록한 상품 목록
					</th>
				</tr>
				 --%>
				<tr>
					<td colspan="10" align="right">
						<input class="btn btn-outline-primary" type="button" id="registerProduct" value="상품 등록하기" />
					</td>
				</tr>
				<tr>
					<td colspan="10" align="right">
						<%--
						<select id="searchCategory">
							<option value="name">상품 이름</option>
							<option value="categoryId">상품 종류</option>
						</select>
						 --%>
						<div class="input-group justify-content-end">
							<input class="form-control" style="max-width:300px;" type="text" id="searchValue" />
							<input class="btn btn-outline-info" type="button" id="searchProduct" value="검색" />
						</div>
					</td>
				</tr>
				<tr>
					<th>순번</th>
					<th>카테고리</th>
					<th>이름</th>
					<th>남은 수량</th>
					<th>가격</th>
					<th>할인률</th>
					<th>배송비</th>
					<th>평균 평점</th>
					<th>리뷰 개수</th>
					<th>등록일</th>
				</tr>
			</thead>
			<tbody id="list">
			</tbody>
			<tfoot>
				<tr>
					<td colspan="10" align="center">
						<ul class="pagination justify-content-center" id="pagination">
			  			</ul>
					</td>
				</tr>
			</tfoot>			
		</table>

		<input type="hidden" id="contextPath" name="contextPath" value="${pageContext.request.contextPath }" />
		<input type="hidden" id="currentPage" name="currentPage" value="${currentPage }" />
		<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
		<%@ include file="/WEB-INF/component/footer.jsp" %>
	</body>
</html>	
