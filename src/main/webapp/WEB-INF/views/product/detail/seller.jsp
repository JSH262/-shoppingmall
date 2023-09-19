<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
<!DOCTYPE html>
<html>
	
		<head>
			<meta charset="UTF-8">
			<title>판매자</title>
			
			
			<%@ include file="/WEB-INF/component/header/common.jsp" %>	
			
			<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/detail/seller.js"></script>
			
			<link href="<%=request.getContextPath() %>/summernote/summernote-lite.min.css" rel="stylesheet">
			<script src="<%=request.getContextPath() %>/summernote/summernote-lite.min.js"></script>
			<script src="<%=request.getContextPath() %>/summernote/lang/summernote-ko-KR.min.js"></script>
			
			<style>
				.text-readonly {
					border: 0px solid white;
					cursor: default;
					font-size: 15px;
					width: 98%;
				}
				
				.text-input {
					border: 1px solid black;
					cursor: text;
					font-size: 15px;
					width: 98%;
				}
				
				.node-hide {
					display:none;
				}
				input[type=text] {
					width: 98%;
				}
				.product-sold-out {
					background-color: crimson;
					color: white;
				}
				
			</style>
		</head>
	<body>
	<%@ include file="/WEB-INF/component/header.jsp" %>
	<c:choose>
		<c:when test="${product != null }">
			<form id="form" action="<%=request.getContextPath()%>">
				<table class="table" border="1" cellpadding="5" cellspacing="1" style="margin: 0px auto;">
					<tr>
						<th>상품 이름</th>
						<td>
							<input class="text-readonly" type="text" id="name" name="name" value="${product.name }" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<th>상품 썸네일</th>
						<td colspan="2">
							<img width="300px" id="thumbnail" name="thumbnail" src="${product.thumbnail }" /> 
							<input class="node-hide" type="file" id="file" name="file" />
						</td>

					</tr>
					<tr>
						<th>상품 카테고리</th>
						<td>
							<select name="categoryId" id="categoryId" disabled="disabled">
								<c:forEach var="item" items="${catList }">
									<c:choose>
										<c:when test="${item.id == product.id }">										
											<option value="${item.id }" selected="selected">${item.name }</option>
										</c:when>
										<c:otherwise>
											<option value="${item.id }">${item.name }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</td>
					</tr>

					<tr>
						<th>상품 가격</th>
						<td>
							<input class="text-readonly node-hide" type="number"
								id="price" name="price" value="${product.price }" /> 
							<input class="text-readonly" type="text" id="fmtPrice" name="fmtPrice"
								value="${product.fmtPrice }" readonly="readonly" />
						</td>
					</tr>

					<tr id="discountPriceNode">
						<th>할인된 상품 가격</th>
						<td>
							<input class="text-readonly" type="text"
								id="fmtDiscountPrice" name="fmtDiscountPrice"
								value="${product.fmtDiscountPrice }" readonly="readonly" />
						</td>
					</tr>


					<tr>
						<th>상품 할인률</th>
						<td>
							<input class="text-readonly node-hide" type="number"
								id="discount" name="discount" value="${product.discount }" /> 
							<input class="text-readonly" type="text" id="fmtDiscount"
								name="fmtDiscount" value="${product.fmtDiscount }"
								readonly="readonly" />
						</td>
					</tr>

					<tr>
						<th id="productAmountHeader">상품 수량</th>
						<td>
							<input class="text-readonly node-hide" type="number"
								id="amount" name="amount" value="${product.amount }" />
							 
							<input class="text-readonly" type="text" id="fmtAmount" name="fmtAmount"
								value="${product.fmtAmount }" readonly="readonly" />
						</td>
					</tr>

					<tr>
						<th>상품 배송비</th>
						<td><input class="text-readonly node-hide" type="number"
							id="deliveryPrice" name="deliveryPrice"
							value="${product.deliveryPrice }" /> <input
							class="text-readonly" type="text" id="fmtDeliveryPrice"
							name="fmtDeliveryPrice" value="${product.fmtDeliveryPrice }"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<th colspan="2">상품 설명</th>
					</tr>
					<tr>
						<td colspan="2">
							<div id="contents">${product.contents }</div>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="button"
							id="modify" name="modify" value="수정하기" /> <input type="button"
							class="node-hide" id="save" name="save" value="저장히기" /> 
							<input
								type="button" class="node-hide" id="cancel" name="cancel"
								value="취소하기" /> 
							<input type="button" id="return" name="return"
								value="돌아가기" />
						</td>
					</tr>
				</table>
			</form>
			<input type="hidden" type="text" id="id" name="id"
				value="${product.id}" />
			<input type="hidden" id="categoryValue" name="categoryValue"
				value="${product.categoryId }" />
			<input type="hidden" id="pageSize" name="pageSize"
				value="${pageSize }" />
			<input type="hidden" id="currentPage" name="currentPage"
				value="${currentPage}" />


		</c:when>
		<c:otherwise>
			<form id="form" action="<%=request.getContextPath()%>">
				<table border="1" cellpadding="5" cellspacing="1"
					style="margin: 0px auto;">
					<tr>
						<td>상품 정보가 존재하지 않습니다.</td>
					</tr>
					<tr>
						<td align="center"><input type="button" id="return"
							name="return" value="돌아가기" /></td>
					</tr>
				</table>
			</form>
			<input type="hidden" id="pageSize" name="pageSize"
				value="${pageSize }" />
			<input type="hidden" id="currentPage" name="currentPage"
				value="${currentPage}" />
		</c:otherwise>
	</c:choose>
	<%@ include file="/WEB-INF/component/footer.jsp" %>
</body>
</html>