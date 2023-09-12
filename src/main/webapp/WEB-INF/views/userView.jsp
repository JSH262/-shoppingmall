<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 정보</title>
</head>
<body>

<form action="updateId" method="post">
	<table width="600" align="center" border="1" cellpadding="5" cellspacing="0">
		<tr>
			<th colspan="4">유저 아이디</th>
		</tr>
		<tr>
			<th width="170">ID</th>
			<th width="160">이름</th>
			<th width="150">가입날짜</th>
			<th width="120">업체아이디</th>
		</tr>
		<tr>
			<td align="center">${vo.id}</td>
			<td align="center">
				<c:set var="name" value="${fn:replace(vo.name, '<', '&lt;')}"/>
				<c:set var="name" value="${fn:replace(name, '>', '&gt;')}"/>
				<input type="text" name="name" value="${name}" />
			</td>
			<td align="center">
				${vo.createDate}
				<%-- <fmt:formatDate value="${vo.createDate}" pattern="yyyy.MM.dd(E)"/>  --%>
			</td>
			<td align="center">
			
			<c:choose>
	         <c:when test ="${vo.type == '1'}">	
	         </c:when>
	         <c:when test = "${vo.type == '2'}">
	            <input type="text" name="companyId" value="${vo.companyId}" />
	         </c:when>
	         <c:otherwise>
	         </c:otherwise>
	      </c:choose>
				
			</td>
		</tr>
		<tr>
			<th width="100">분류</th>
			<th width="200">Email</th>
			<th width="200">핸드폰번호</th>
			<th width="100">사용여부</th>
		</tr>
		<tr>
			<td align="center">${vo.type}</td>
			<td align="center">
				<c:set var="email" value="${fn:replace(vo.email, '<', '&lt;')}"/>
				<c:set var="email" value="${fn:replace(email, '>', '&gt;')}"/>
				<input type="text" name="email" value="${email}" />
			</td>
			<td align="center">
				<c:set var="phone" value="${fn:replace(vo.phone, '<', '&lt;')}"/>
				<c:set var="phone" value="${fn:replace(phone, '>', '&gt;')}"/>
				<input type="text" name="phone" value="${phone}" />
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
				<input type="submit" value="수정하기"/>
					<c:choose>
			         <c:when test ="${vo.useYn == 'N'}">
			            <input type="button" value="복구하기" 
							onclick="location.href='restoreId?id=${vo.id}&currentPage=${currentPage}'"/>
			         </c:when>
			         <c:when test = "${vo.useYn == 'Y'}">
			            <input type="button" value="삭제하기" 
							onclick="location.href='deleteId?id=${vo.id}&currentPage=${currentPage}'"/>
			         </c:when>
			         <c:otherwise>
			            ${vo.useYn}
			         </c:otherwise>
			      </c:choose>
				<input type="button" value="돌아가기" 
					onclick="location.href='adminPage?currentPage=${currentPage}'"/>
			</td>
		</tr>
	</table>
 	<input type="hidden" name="useYn" value="${vo.useYn}"/> 
	<input type="hidden" name="id" value="${vo.id}"/>
	<input type="hidden" name="currentPage" value="${currentPage}"/>
				
</form>

</body>
</html>




