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
<%
	UsersVO user = AttributeName.getUserData(request);
	String currentPage = request.getParameter("currentPage");
	// 카트로 수정
	CartVO params = new CartVO();
	params.setUserId(user.getId());
	List<CartVO> vo = CartService.getInstance().selectList(params);
	int count = CartService.getInstance().count(user.getId());
	for (int i = 0; i < count; i++) {
    Integer amount = vo.get(i).getAmount();
    String sellerId = vo.get(i).getSellerId();
    String thumbnail = vo.get(i).getThumbnail();
    String productName = vo.get(i).getProductName();
    String discountPrice = vo.get(i).getDiscountPrice();
    String companyName = vo.get(i).getCompanyName();
    String delivery = vo.get(i).getDeliveryPrice();
	Integer productId = vo.get(i).getProductId();
    
    if (thumbnail != null) {
        thumbnail += "/image/" + thumbnail;
    } else {
        thumbnail += "/resources/default/noimg.png";
    }

    if (delivery.equals('0')) {
        delivery = "";
    }
%>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/detail/buyer.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

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
<div class="container gap-3 mt-5">
    <div class="row justify-content-center">
    <%-- 
        <div class="col-md-1">
            <!-- 체크박스 추가 -->
            <input type="checkbox" name="selectedProduct" value="<%= productId %>" />
        </div>
         --%>
        <div class="col-md-5">
            <img style="max-width:100%" src="<%= thumbnail %>" />
        </div>
        <div class="col-md-1">
            &nbsp;
        </div>
        <div class="col-md-5">
            <div class="row"><%= companyName %></div>
            <div class="row">
                <hr />
            </div>
            <div class="row">
                <div class="col-sm-12 fs-1">
                    <span><%= productName %></span>
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
                    <span name="delivery"><%= delivery %></span>
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
                <input class="form-control" style="max-width: 15%;" type="number" id="amount" name="amount" min="1" value="1" />
                <input class="btn btn-outline-secondary" type="button" id="amountMinus" name="amountMinus" value="-" />
                <input class="btn btn-outline-secondary" type="button" id="amountPlus" name="amountPlus" value="+" />
            </span>
        </div>
        <div class="col-md-1">                  
            <span>&nbsp;</span>
        </div>
        <div class="col-md-5">
            <span class="input-group mb-3">
                <% if (amount != 0) { %>
                    <input class="btn btn-outline-secondary" type="button" id="buy" name="buy" value="구매하기" />
                <% } else { %>
                    <input class="btn btn-secondary" type="button" value="품절" disabled />
                <% } %>                               
            </span>
        </div>
    </div>
</div>
<!-- 상품 컨테이너 끝 -->
<%
}
%>
<!-- from사용 -->
<%-- 
<%
    StringBuilder productIdsBuilder = new StringBuilder();
    for (int i = 0; i < count; i++) {
        Integer productId = vo.get(i).getProductId();
        productIdsBuilder.append(productId);
        if (i != count - 1) {
            productIdsBuilder.append(",");
        }
    }
    String productIds = productIdsBuilder.toString();
%>
<!-- 버튼 -->
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <form action="order.jsp" method="get">
                <input type="hidden" name="ProductIds" value="<%= productIds %>" />
                <input class="btn btn-primary" type="submit" value="결제하기" />
            </form>
        </div>
    </div>
</div>
 --%>
 
 <!--  onclick 사용 -->
 <%
    // 변수를 선언하여 productid 값을 모두 저장
    StringBuilder productIdsBuilder = new StringBuilder();
    for (int i = 0; i < count; i++) {
        // 이전 코드 내용
        Integer productId = vo.get(i).getProductId();
        productIdsBuilder.append(productId);
        if (i != count - 1) {
            productIdsBuilder.append(",");
        }
    }
    String productIds = productIdsBuilder.toString();
%>
<!-- 선택 상품 결제하기 버튼 -->
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <input type="hidden" name="ProductIds" value="<%= productIds %>" />
            <!-- 주석부분이 실사용(경로) -->
            <%-- <input class="btn btn-primary" type="button" value="결제하기" onclick="location.href='/product/order.jsp?ProductIds=<%= productIds %>'" /> --%>
            <!-- 테스트용 -->
            <input class="btn btn-primary" type="button" value="결제하기" onclick="location.href='orderTest.jsp?ProductIds=<%= productIds %>'" />
        </div>
    </div>
</div>
 

</body>
</html>