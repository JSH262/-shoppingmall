<%@page import="com.tjoeun.shoppingmall.vo.ProductVO"%>
<%@page import="com.tjoeun.shoppingmall.service.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//검증 추가

	int id = Integer.parseInt(request.getParameter("id"));
	String currentPage = request.getParameter("currentPage");
	String pageSize = request.getParameter("pageSize");	
	ProductVO params = new ProductVO();
	
	params.setId(id);
	params.setChoose("detail");
	
	ProductVO vo = ProductService.getInstance().select(params);
	
%>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/detail.js"></script>
		
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
			
		</style>
	</head>
	<body>
	
<%
		if(vo != null)
		{
%>
			<form id="form" action="<%=request.getContextPath() %>">
				<table border="1" cellpadding="5" cellspacing="1" style="margin: 0px auto;">
					<tr>
						<th>
							상품 이름
						</th>
						<td>
							<input class="text-readonly" type="text" id="name" name="name" value="<%=vo.getName() %>" readonly="readonly" />	
						</td>
					</tr>
				
					<tr>
						<th>
							상품 썸네일
						</th>
						<td colspan="2">
							<%
								if(vo.getThumbnail() != null && vo.getThumbnail().length() > 0)
								{
							%>
									<img width="300px" id="thumbnail" name="thumbnail" src="<%=request.getContextPath() %>/image/<%=vo.getThumbnail() %>" />
							<%
								}
								else
								{
							%>
									<img width="300px" id="thumbnail" name="thumbnail" src="<%=request.getContextPath() %>/resources/default/noimg.png" />
							<%	
								}
							%>
													
							<input class="node-hide" type="file" id="file" name="file" />
						</td>
										
					</tr>
					
					<tr>
						<th>
							상품 카테고리			
							
						</th>
						<td>
							<select name="categoryId" id="categoryId" disabled="disabled">
								<option value="0">선택</option>
								<option value="1">/식품/과일</option>
								<option value="2">/식품/채소</option>
								<option value="3">/식품/곡물</option>
								<option value="4">/식품/견과,건과</option>
								<option value="5">/식품/축산</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>
							상품 가격
						</th>
						<td>
							<input class="text-readonly node-hide" type="number" id="price" name="price" value="<%=vo.getPrice() %>" />
							<input class="text-readonly" type="text" id="fmtPrice" name="fmtPrice" value="<%=vo.getFmtPrice() %>" readonly="readonly" />
						</td>
					</tr>
					
					<tr id="discountPriceNode">
						<th>
							할인된 상품 가격
						</th>
						<td>
							<input class="text-readonly" type="text" id="fmtDiscountPrice" name="fmtDiscountPrice" value="<%=vo.getFmtDiscountPrice() %>" readonly="readonly" />
						</td>
					</tr>
					
					
					<tr>
						<th>
							상품 할인률
						</th>
						<td>
							<input class="text-readonly node-hide" type="number" id="discount" name="discount" value="<%=vo.getDiscount() %>" />
							<input class="text-readonly" type="text" id="fmtDiscount" name="fmtDiscount" value="<%=vo.getFmtDiscount() %>" readonly="readonly" />
						</td>
					</tr>
					
					<tr>
						<th>상품 수량</th>
						<td>
							<input class="text-readonly node-hide" type="number" id="amount" name="amount" value="<%=vo.getAmount() %>" />
							<input class="text-readonly" type="text" id="fmtAmount" name="fmtAmount" value="<%=vo.getFmtAmount() %>" readonly="readonly" />
						</td>
					</tr>
				
					<tr>
						<th>상품 배송비</th>
						<td>
							<input class="text-readonly node-hide" type="number" id="deliveryPrice" name="deliveryPrice" value="<%=vo.getDeliveryPrice() %>" />
							<input class="text-readonly" type="text" id="fmtDeliveryPrice" name="fmtDeliveryPrice" value="<%=vo.getFmtDeliveryPrice() %>" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<th colspan="2">
							상품 설명
						</th>
					</tr>
					<tr>
						<td colspan="2">
							<div id="contents">
								<%=vo.getContents() %>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button" id="modify" name="modify" value="수정하기" />
							<input type="button" class="node-hide" id="save" name="save" value="저장히기" />
							<input type="button" class="node-hide" id="cancel" name="cancel" value="취소하기" />
							<input type="button" id="return" name="return" value="돌아가기" />
						</td>
					</tr>
				</table>
			</form>
			<input type="hidden" type="text" id="id" name="id" value="<%=vo.getId() %>" />
			<input type="hidden" id="categoryValue" name="categoryValue" value="<%=vo.getCategoryId() %>" />
			<input type="hidden" id="pageSize" name="pageSize" value="<%=pageSize %>" />
			<input type="hidden" id="currentPage" name="currentPage" value="<%=currentPage %>" />
<%
		}
		else
		{
%>
			<form id="form" action="<%=request.getContextPath() %>">			
				<table border="1" cellpadding="5" cellspacing="1" style="margin: 0px auto;">
					<tr>
						<td>
							상품 정보가 존재하지 않습니다.
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="button" id="return" name="return" value="돌아가기" />
						</td>
					</tr>
				</table>
			</form>			
			<input type="hidden" id="pageSize" name="pageSize" value="<%=pageSize %>" />
			<input type="hidden" id="currentPage" name="currentPage" value="<%=currentPage %>" />
<%
			
		}
%>
	
		
		
	</body>
</html>