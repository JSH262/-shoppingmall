<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 정보</title>


<%@ include file="/WEB-INF/component/header/common.jsp" %>

	<style>
		table {
			max-width:1200px;
			margin:auto;
		}
	</style>

</head>
<body>

		<%@ include file="/WEB-INF/component/header.jsp" %>
<form action="updateId" method="post">
	<table class="table table-striped table-sm" align="center" border="1" cellpadding="5" cellspacing="0">
		<tr>
			<th class="h2 text-center" colspan="4">USER Info</th>
		</tr>
		<tr>
			<th class="h4 text-center" width="170">ID</th>
			<th class="h4 text-center" width="160">이름</th>
			<th class="h4 text-center" width="150">가입날짜</th>
			<th class="h4 text-center" width="120">업체아이디</th>
		</tr>
		<tr>
			<td align="center">${vo.id}</td>
			<td align="center">
				<c:set var="name" value="${fn:replace(vo.name, '<', '&lt;')}"/>
				<c:set var="name" value="${fn:replace(name, '>', '&gt;')}"/>
				<input class="form-control form-control-dark w-100 rounded-0 border-0" type="text" name="name" value="${name}" />
			</td>
			<td align="center">
				${vo.createDate}
			</td>
			<td align="center">
			
			<c:choose>
	         <c:when test ="${vo.type == '1'}">	
	         </c:when>
	         <c:when test = "${vo.type == '2'}">
	            <input class="form-control form-control-dark w-100 rounded-0 border-0" type="text" name="companyId" value="${vo.companyId}" />
	         </c:when>
	         <c:otherwise>
	         </c:otherwise>
	      </c:choose>
				
			</td>
		</tr>
		<tr>
			<th class="h4 text-center" width="100">분류</th> 
			<th class="h4 text-center" width="200">Email</th>
			<th class="h4 text-center" width="200">핸드폰번호</th>
			<th class="h4 text-center" width="100">사용여부</th>
		</tr>
		<tr>
			<td align="center">
			<c:choose>
		         <c:when test ="${vo.type == '1'}">	
		         	구매자
		         </c:when>
		         <c:when test ="${vo.type == '2'}">
		         	판매자
		         </c:when>
		         <c:otherwise>
		         	관리자
		         </c:otherwise>
	        </c:choose>
	         </td>
			<td align="center">
				<c:set var="email" value="${fn:replace(vo.email, '<', '&lt;')}"/>
				<c:set var="email" value="${fn:replace(email, '>', '&gt;')}"/>
				<input class="form-control form-control-dark w-100 rounded-0 border-0" type="text" name="email" value="${email}" />
			</td>
			<td align="center">
				<c:set var="phone" value="${fn:replace(vo.phone, '<', '&lt;')}"/>
				<c:set var="phone" value="${fn:replace(phone, '>', '&gt;')}"/>
				<input class="form-control form-control-dark w-100 rounded-0 border-0" type="text" name="phone" value="${phone}" />
			</td>
			
			<td align="center">
			<c:choose>
			         <c:when test ="${vo.useYn == 'N'}">
							아이디 삭제 대기중
			         </c:when>
			         <c:when test = "${vo.useYn == 'Y'}">
							 아이디 사용중
			         </c:when>
			         <c:otherwise>
			            ${vo.useYn}
			         </c:otherwise>
			      </c:choose>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<input
					
					class="btn btn-sm btn-outline-secondary"
					type="submit" 
					value="수정하기"/>
					<c:choose>
			         <c:when test ="${vo.useYn == 'N'}">
			            <input 
			            	
			            	class="btn btn-sm btn-outline-secondary"
			            	type="button" 
			            	value="복구하기" 
							onclick="location.href='restoreId?id=${vo.id}&currentPage=${currentPage}'"/>
			         </c:when>
			         <c:when test = "${vo.useYn == 'Y'}">
			            <input 
			            
			            	class="btn btn-sm btn-outline-secondary"
			            	type="button" 
			            	value="삭제하기" 
							onclick="location.href='deleteId?id=${vo.id}&currentPage=${currentPage}'"/>
			         </c:when>
			         <c:otherwise>
			            ${vo.useYn}
			         </c:otherwise>
			      </c:choose>
				<input 
					class="btn btn-sm btn-outline-secondary"
					type="button" value="돌아가기" 
					onclick="location.href='adminPage?currentPage=${currentPage}'"/>
			</td>
		</tr>
	</table>
 	<input type="hidden" name="useYn" value="${vo.useYn}"/> 
	<input type="hidden" name="id" value="${vo.id}"/>
	<input type="hidden" name="currentPage" value="${currentPage}"/>
				
</form>
		<%@ include file="/WEB-INF/component/footer.jsp" %>

</body>
</html>