<%@page import="com.tjoeun.helper.UsersType"%>
<%@page import="com.tjoeun.helper.AttributeName"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
	document.catDownList = ${categoryDownList};
	document.catDownList2 = ${categoryDownList2};
</script>

<header style="z-index: 1000;">
	<nav class="navbar navbar-expand-lg bg-light">
		<div class="container-fluid">
			
			<div class="collapse navbar-collapse">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item dropend">
					<button class="btn nav-link" href="#" data-bs-toggle="offcanvas" data-bs-target="#offcanvasCategory" aria-controls="offcanvasCategory" id="categoryList">
						<i class="bi bi-list" style="font-size:2em;"></i>
					</button>
					<%--
					<ul class="dropdown-menu" id="categoryMenu">
						<c:forEach var="item" items="${categoryList }">
							<a class="dropdown-item" href="<%=request.getContextPath() %>/product/list?productCategoryId=${item.id}" role="button" data-bs-toggle="dropdown" aria-expanded="false">
								${item.name }
							</a>
						</c:forEach>
					</ul>
					 --%>
				</li>
				</ul>
			</div>
			
			<a class="navbar-brand" href="<%=request.getContextPath() %>/index">쇼핑몰</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">	
					<%--				
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"> 메뉴 </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">마이페이지</a></li>
							<li><a class="dropdown-item" href="#">배송조회</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="#">상품 카테고리</a></li>
						</ul>
					</li>
					 --%>
					
<%
			if(AttributeName.getUserData(request) != null)
			{
				String userType = AttributeName.getUserType(request);
				if(userType.equals(UsersType.SELLER))
				{
%>
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath() %>/product/breakdown/list"> 상품관리 </a>						
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath() %>/product/list"> 상품조회 </a>						
					</li>
					
<%
				}
				else if(userType.equals(UsersType.BUYER))
				{

%>
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath()%>/cart/list"> 장바구니 </a>
					</li>	
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath() %>/product/payment/list"> 주문/배송조회 </a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath() %>/logout"> 로그아웃 </a>						
					</li>
<%
				}
			}
			else
			{
%>
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath() %>/login"> 로그인 </a>						
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath() %>/join"> 회원가입 </a>						
					</li>
<%
			}
			
%>



				</ul>
				<form class="d-flex" role="search" style="width: 40%">
					<input class="form-control me-2" type="search" placeholder="검색할 내용을 입력하세요" aria-label="Search">
					<button class="btn btn-outline-success" type="submit">검색</button>
				</form>
			</div>
		</div>
	</nav>
</header>

<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasCategory" aria-labelledby="offcanvasCategory" data-bs-backdrop="true">
	<div class="offcanvas-header">
		<h5 class="offcanvas-title" id="offcanvasCategoryLabel">카테고리</h5>
		<button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
	</div>
	<div class="offcanvas-body">
		<div>
			<ul class="list-group list-group-flush">
				<c:forEach var="item" items="${categoryList }">
					<li class="list-group-item container">
						<div class="row">
							<a class="col text-dark text-decoration-none" href="<%=request.getContextPath() %>/product/list?productCategoryId=${item.id}"> ${item.name } </a>
							<div class="col d-flex justify-content-end" name="offcanvasCategoryDownShow" data-bs-category-id="${item.id}">
								<i class="bi bi-arrow-bar-right"></i>
							</div>							
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>

<div class="offcanvas offcanvas-start" style="margin-left: 350px;width:300px" tabindex="-1" id="offcanvasCategoryDown" data-bs-backdrop="false" aria-labelledby="offcanvasCategoryDown">
	<div class="offcanvas-header">
		<h5 class="offcanvas-title" id="offcanvasCategoryDownLabel"></h5>
		<button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
	</div>
	<div class="offcanvas-body">
		<div>
			<ul class="list-group list-group-flush" name="offcanvasCategoryDownList">
			</ul>
		</div>
	</div>
</div>

<div class="offcanvas offcanvas-start" style="margin-left: 645px;width:300px" tabindex="-1" id="offcanvasCategoryDown2" data-bs-backdrop="false" aria-labelledby="offcanvasCategoryDown2">
	<div class="offcanvas-header">
		<h5 class="offcanvas-title" id="offcanvasCategoryDown2Label"></h5>
		<button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
	</div>
	<div class="offcanvas-body">
		<div>
			<ul class="list-group list-group-flush" name="offcanvasCategoryDown2List">
			</ul>
		</div>
	</div>
</div>
<%--
<div class="container-fluid pb-3">
	<div class="d-grid gap-3"">
		<div>
			<i class="bi bi-person" style="font-size: 2em" title="마이페이지"></i>
			<i>&nbsp;</i>
			<i class="bi bi-cart3" style="font-size: 2em" title="장바구니"></i>
			<i>&nbsp;</i>
			<i class="bi bi-truck" style="font-size: 2em" title="주문현황"></i>
			<i>&nbsp;</i>
			<i class="bi bi-door-open" style="font-size: 2em" title="로그아웃"></i>
		</div>
		<div>
			<i class="bi bi-door-closed" style="font-size: 1.5em" title="로그인"></i>
			<i class="bi bi-people" style="font-size: 1.5em" title="회원가입"></i>
		</div>
	</div>
</div>
 --%>