<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="java.util.List"%>
<%@page import="com.tjoeun.shoppingmall.service.CartService"%>
<%@page import="com.tjoeun.shoppingmall.vo.CartVO"%>
<%@page import="com.tjoeun.helper.UsersType"%>
<%@page import="com.tjoeun.shoppingmall.vo.UsersVO"%>
<%@page import="com.tjoeun.helper.AttributeName"%>
<%@page import="com.tjoeun.shoppingmall.service.ProductService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<%@ include file="/WEB-INF/component/header/common.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/customAlert.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/cart/cart.js"></script>

<script>
       function productAmount(node, value)
       {
           let item = $(node).siblings('input[name=amount]');    
           let amount = parseInt(item.val()) + value;
            
           item.val(amount);
       }
    </script>
<style>
	.product-container {
		max-width: 1200px;
		display: grid;
		grid-template-columns: 47% auto 47%;
		grid-template-rows: 100%;
		margin: 0px auto;
	}
	
	.product-info-container {
		display: grid;						
		grid-template-rows: 10% 1% 1fr 1fr 1fr 1fr 10%;
		margin-left: 30px;
	}
	
	.product-image {
		text-align: center;
		justify-content: center;
		display: flex;
		align-items: center;
	}
	.product-cart {
		grid-column: 1/4;
		text-align: center;
		display:flex;
		align-items: center;
		justify-content: center;
							
	}
	.product-contents {
		text-align: center;
		justify-content: center;
		display: flex;
		align-items: center;
		margin-top: 100px;
	}
	
	
	span[name=score], span[name=review] {
		opacity: 0.0;
	}
	
				
	input[name=amount] {
		width: 40px;
		text-align: center;
		-moz-appearance: textfield;
	}
	input[name=amount]::-webkit-outer-spin-button, input[name=amount]::-webkit-inner-spin-button {
  			-webkit-appearance: none;
  			-moz-appearance: none;
  			appearance: none;
	}
			
	div[name=companyName] {
		font-size: 13pt;
		text-align: left;
		align-items: center;
		justify-content: flex-start;						
		display: flex;
	}
	div[name=name] {
		font-size: 28pt;
		align-items: center;
		justify-content: flex-start;						
		display: flex;
	}
	
	span[name=fmtDiscountPrice] {
		font-size: 18pt;
		font-weight: bold;
	}
	
	span[name=fmtPrice] {
		text-decoration: line-through;
	}
	
	span[name=fmtDiscount] {
		color: crimson;
		font-size: 14pt;
	
	}
	span[name=fmtPoint] {
		opacity: 0.0;
		color: skyblue;
	}				
	span[name=fmtPoint]:after {
		color: black;
		content: ' 적립'
	}
	span[name=amountGroup] {
	}

	
</style>
</head>

<body>
	<%@ include file="/WEB-INF/component/header.jsp" %>
	<c:forEach var="item" items="${cartList }">
	
		<div class="container gap-3 mt-5">
		    <div class="row justify-content-center">
		        <div class="col-md-5">
		        
		        	<c:choose>
		        		<c:when test="${item.thumbnail != null }">
							<img style="max-width:100%" src="<%=request.getContextPath() %>/image/${item.thumbnail }" />
		        		</c:when>
		        		<c:otherwise>
		            		<img style="max-width:100%" src="<%=request.getContextPath() %>/resources/default/noimg.png" />
		        		</c:otherwise>
		        	</c:choose>
		        </div>
		        <div class="col-md-1">
		            &nbsp;
		        </div>
		        <div class="col-md-5">
		            <div class="row">${item.companyName}</div>
		            <div class="row">
		                <hr />
		            </div>
		            <div class="row">
		                <div class="col-sm-12 fs-1">
		                    <span><${item.productName }</span>
		                </div>
		            </div>
		            <div class="row">
		                <div class="col-sm-12">
		                    <span name="score">★★★★☆</span>
		                    <span name="review">(1,000)</span>
		                </div>
		            </div>
		            <div class="row">
		                <div class="row">&nbsp;</div>
		            </div>
		            <div class="row">
		                <div class="col-sm-12">
		                </div>
		            </div>
		            <div class="row">
		                <div class="col-sm-12">
		                    <span name="delivery"></span>
		                </div>
		            </div>
		            <div class="row">
		                <span name="fmtPoint">100P</span>
		            </div>
		        </div>
		    </div>
		    <div class="row pt-3 pb-3 bg-light" style="position: sticky; top:30px;">
		        <div class="col-md-6">
		            <span class="input-group mb-3 justify-content-end">
		                <label class="input-group-text" for="amount">수량</label>
		                <input class="form-control" style="max-width: 15%;" type="number" id="amount" name="amount" min="1" value="${item.amount}" />
		                <input class="btn btn-outline-secondary" type="button" value="-" onclick="productAmount(this, -1)"/>
		                <input class="btn btn-outline-secondary" type="button" value="+" onclick="productAmount(this, 1)"/>
			            <input type="hidden" id="userId" name="userId" value="${userId }">
			            <input type="hidden" id="productId" name="productId" value="${item.productId }">
			            <input class="btn btn-primary" type="button" value="적용하기" onclick="updateAmount(this)" />
			            <input class="btn btn-primary" type="button" value="삭제하기" onclick="deleteProduct(this)" />
		            </span>
		        </div>
		        <div class="col-md-1">                  
		            <span>&nbsp;</span>
		        </div>
		        <div class="col-md-5">
		            <span class="input-group mb-3">
		            	<c:if test="${item.amount == 0 }">
		            		<input class="btn btn-secondary" type="button" value="품절" disabled />
		            	</c:if>                            
		            </span>
		        </div>
		    </div>
		</div>
	</c:forEach>
	
	<c:if test="${cartCnt == 0}">
		<div class="container gap-3 mt-5">
	    	<div class="row justify-content-center">
	    		<h3>장바구니에 담긴 상품이 없습니다!!!</h3>
	    	</div>
	    		<input class="btn btn-primary" type="button" value="홈으로 이동" onclick="location.href='<%=request.getContextPath() %>/index'" />
		</div>
	</c:if>
	<c:if test="${cartCnt != 0}">
		<div class="container mt-5">
		    <div class="row justify-content-center">
		        <div class="col-md-6" style="margin-bottom: 20px;">
		            <input type="hidden" name="ProductIds" value="${productIds }" />
		            <input class="btn btn-primary" type="button" value="결제하기" onclick="location.href='<%=request.getContextPath() %>/product/order'" />
		        </div>
		    </div>
		</div>
	</c:if>

 <%@ include file="/WEB-INF/component/customAlert.jsp" %>
 

	<%@ include file="/WEB-INF/component/footer.jsp" %>

</body>
</html>