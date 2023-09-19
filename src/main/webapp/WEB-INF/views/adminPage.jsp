<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="icon" href="./images/logo.png"/>


<%@ include file="/WEB-INF/component/header/common.jsp" %>


<title>관리자 페이지</title>
<style type="text/css">
	table {
		max-width:1200px;
		margin:auto;
	}
</style>
</head>
<body>

		<%@ include file="/WEB-INF/component/header.jsp" %>
<table class="table table-striped table-sm" width="1000" align="center" border="1" cellpadding="6" cellspacing="0">
	<tr>
		<th class="h2 text-center" colspan="6" align="center">USER List</th>
	</tr>
	<tr>
		<td colspan="6" align="right">
			총 아이디 ${usersList.totalCount}개 (${usersList.currentPage} / ${usersList.totalPage})
		</td>
	</tr>
	<tr>
		<th class= "h6 text-center" style="width: 170px;">ID</th>
		<th class= "h6 text-center" style="width: 110px;">이름</th>
		<th class= "h6 text-center" style="width: 300px;">E-mail</th>
		<th class= "h6 text-center" style="width: 250px;">핸드폰 번호</th>
		<th class= "h6 text-center" style="width: 170px;">회사 ID</th>
		<th class= "h6 " style="width: 50px;">삭제/복구</th>
	</tr>
	
	<c:set var="list" value="${usersList.list}"/>
	<c:if test="${list.size() == 0}">
	<tr>
		<td colspan="5">
			<marquee>아이디가 없습니다.</marquee>
		</td>
	</tr>
	</c:if>

	<c:if test="${list.size() != 0}">
	<c:forEach var="vo" items="${list}">
	<tr id="useID">
		<td align="center">${vo.id}</td>
		<td>
			<c:set var="name" value="${fn:replace(vo.name, '<', '&lt;')}"/>
			<c:set var="name" value="${fn:replace(name, '>', '&gt;')}"/>
			<a class="btn btn-light" href="userView?id=${vo.id}&currentPage=${usersList.currentPage}">
				${vo.name}
			</a>	
								
		</td>
		<td align="center">
			<c:set var="email" value="${fn:replace(vo.email, '<', '&lt;')}"/>
			<c:set var="email" value="${fn:replace(email, '>', '&gt;')}"/>
			${email}
		</td>
		<td align="center">
			${vo.phone}
		</td>
		<td align="center">${vo.companyId}</td>
		<td>
			<c:choose>
	         <c:when test ="${vo.useYn == 'N'}">
	            <input class="btn btn-sm btn-outline-secondary" type="button" value="복구" onclick="location.href='restoreId?id=${vo.id}&currentPage=${usersList.currentPage}'">
	         </c:when>
	         <c:when test = "${vo.useYn == 'Y'}">
	            <input class="btn btn-sm btn-outline-secondary" type="button" value="삭제" onclick="location.href='deleteId?id=${vo.id}&currentPage=${usersList.currentPage}'">
	         </c:when>
	         <c:otherwise>
	            ${vo.useYn}
	         </c:otherwise>
	      </c:choose>		
		</td>
	</tr>
	</c:forEach>
	</c:if>

	<tr>
		<td colspan="5" align="center">
			<c:if test="${usersList.currentPage > 1}">
				<button 
					class="btn btn-sm btn-outline-secondary"
					type="button" 
					title="첫 페이지로 이동합니다." 
					onclick="location.href='?currentPage=1'"
				>처음</button>
			</c:if>
			<c:if test="${usersList.currentPage <= 1}">
				<button 
					class="btn btn-sm btn-outline-secondary"
					type="button" 
					disabled="disabled" 
					title="이미 첫 페이지 입니다."
				>처음</button>
			</c:if>
			
			<c:if test="${usersList.startPage > 1}">
				<button 
					class="btn btn-sm btn-outline-secondary"
					type="button" 
					title="이전 10페이지로 이동합니다." 
					onclick="location.href='?currentPage=${usersList.startPage - 1}'"
				>이전</button>
			</c:if>
			<c:if test="${usersList.startPage <= 1}">
				<button 
					class="btn btn-sm btn-outline-secondary"
					type="button" 
					disabled="disabled" 
					title="이미 첫 10페이지 입니다."
				>이전</button>
			</c:if>
			
			<c:forEach var="i" begin="${usersList.startPage}" end="${usersList.endPage}" step="1">
				<c:if test="${usersList.currentPage == i}">
					<button type='button' disabled='disabled'>${i}</button>
				</c:if>
				<c:if test="${usersList.currentPage != i}">
					<button 
						class="btn btn-sm btn-outline-secondary"
						type='button' 
						title="${i}페이지로 이동합니다."
						onclick="location.href='?currentPage=${i}'"
					>${i}</button>
				</c:if>
			</c:forEach>
			
			<c:if test="${usersList.endPage < usersList.totalPage}">
				<button 
					class="btn btn-sm btn-outline-secondary"
					type="button" 
					title="다음 10페이지로 이동합니다." 
					onclick="location.href='?currentPage=${usersList.endPage + 1}'"
				>다음</button>
			</c:if>
			<c:if test="${usersList.endPage >= usersList.totalPage}">
				<button 
					class="btn btn-sm btn-outline-secondary"
					type="button" 
					disabled="disabled" 
					title="이미 마지막 10페이지 입니다."
				>다음</button>
			</c:if>
			
			<c:if test="${usersList.currentPage < usersList.totalPage}">
			<button
				class="btn btn-sm btn-outline-secondary"
				type="button" 
				title="마지막 페이지로 이동합니다." 
				onclick="location.href='?currentPage=${usersList.totalPage}'"
			>마지막</button>
			</c:if>
			<c:if test="${usersList.currentPage >= usersList.totalPage}">
				<button 
					class="btn btn-sm btn-outline-secondary"
					type="button" 
					disabled="disabled" 
					title="이미 마지막 페이지 입니다."
				>마지막</button>
			</c:if>
		</td>
	</tr>

</table>
		<%@ include file="/WEB-INF/component/footer.jsp" %>
</body>
</html>