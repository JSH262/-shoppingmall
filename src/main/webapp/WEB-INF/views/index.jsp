<%@page import="com.tjoeun.helper.AttributeName"%>
<%@page import="com.tjoeun.helper.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>shoppingmall</title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	
	<style>
		#mainProducts img {
		  	object-fit: none;
		  	height: 300px;
		}
	</style>
</head>
<body>
	<div>
		<h1>많이 팔린 상품</h1>
	</div>
	<div id="carouselExampleIndicators" class="carousel carousel-dark slide"
		data-bs-ride="true">
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#carouselExampleIndicators"
				data-bs-slide-to="0" class="active" aria-current="true"
				aria-label="Slide 1"></button>
			<button type="button" data-bs-target="#carouselExampleIndicators"
				data-bs-slide-to="1" aria-label="Slide 2"></button>
			<button type="button" data-bs-target="#carouselExampleIndicators"
				data-bs-slide-to="2" aria-label="Slide 3"></button>
		</div>
		<div class="carousel-inner" id="mainProducts">
			<div class="carousel-item active" data-bs-interval="2000">
				<img class="ratio ratio-21x9 mx-auto d-block" src="<%=request.getContextPath() %>/image/11ee340654fc4111b956dd7a96c7332c" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item" data-bs-interval="2000">
				<img class="ratio ratio-21x9 mx-auto d-block" src="<%=request.getContextPath() %>/image/11ee340585123d80b956651ffe5e5aa3" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item" data-bs-interval="2000">
				<img class="ratio ratio-21x9 mx-auto d-block" src="<%=request.getContextPath() %>/image/11ee34057f5e4775b9564175e94239a8" class="d-block w-100" alt="...">
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>

	<hr />

	<div>
		<h1>신상품</h1>
	</div>
	<div class="container" id="newProductsBody">
	
		<%-- 상품 표시 --%>
		<div class="row" name="newProductItem">
			<div class="col-4">
				<div class="card">
					<img
						src="<%=request.getContextPath()%>/image/11ee340654fc4111b956dd7a96c7332c"
						class="card-img-top" alt="...">

					<div class="card-body">
						<h5 class="card-title">Card title</h5>
						<p class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</p>
						<a href="#" class="btn btn-primary">Go somewhere</a>
					</div>
				</div>
			</div>
		</div>
		
		<%-- 상품 로딩 --%>
		<div class="row" id="newProductLoading">			
			<div class="col-4">
				<div class="card" aria-hidden="true">
					<img src="<%=request.getContextPath() %>/resources/default/gray.png" class="card-img-top img-fluid">
					<div class="card-body">
						<h5 class="card-title placeholder-glow">
							<span class="placeholder col-6"></span>
						</h5>
						<p class="card-text placeholder-glow">
							<span class="placeholder col-7"></span> <span
								class="placeholder col-4"></span> <span
								class="placeholder col-4"></span> <span
								class="placeholder col-6"></span> <span
								class="placeholder col-8"></span>
						</p>
						<a href="#" tabindex="-1"
							class="btn btn-primary disabled placeholder col-6"></a>
					</div>
				</div>
			</div>
		</div>
				
	</div>



	<hr />


	<div>	
	<%
		if(AttributeName.getUserData(request) != null)
		{
	%>
			<input type="button" value="로그아웃" onclick="location.href='<%=request.getContextPath() %>/logout'" />
			<%--
			<input type="button" value="구입할 상품확인" onclick="location.href='<%=request.getContextPath() %>/product/order'" />
			 --%>
			<input type="button" value="상품구입 목록" onclick="location.href='<%=request.getContextPath() %>/product/payment/list'" />
			<input type="button" value="장바구니" onclick="location.href='<%=request.getContextPath() %>/cart/list'" />
			<input type="button" value="상품관리" onclick="location.href='<%=request.getContextPath() %>/product/breakdown/list'" />
	<%
		}
		else
		{
	%>
			<input type="button" value="로그인" onclick="location.href='<%=request.getContextPath() %>/login'" />
	<%
		}
	%>
	
		<input type="button" value="상품보기" onclick="location.href='<%=request.getContextPath() %>/product/list'" />
	</div>

</body>
</html>