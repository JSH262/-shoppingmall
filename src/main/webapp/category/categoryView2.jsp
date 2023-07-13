<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/categorycheck.js" defer="defer"></script>
</head>

<h1>category</h1>

<form id="form" action="insert.jsp" method="post" onsubmit="return mainFormCheck(this)">
	<input id="category" type="text" name="name"/>
	<input id="category2" type="text" name="type"/>
	<input type="submit" value="메인 카테고리"/>
</form>
<hr style="color: red;">

<c:forEach var="vo" items="${categoryList}">
	<c:set var="formName" value="form${vo.id}"/>
	<form action="reply.jsp" method="post" name=${formName} onsubmit="return subFormCheck(this);">
		<input type="text" name="id" value="${vo.id}" size="1"/>
		<input type="text" name="gup" value="${vo.gup}" size="1"/>
		<input type="text" name="lev" value="${vo.lev}" size="1"/>
		<input type="text" name="seq" value="${vo.seq}" size="1"/>
		<input type="text" name="type" value="${vo.type}" size="1"/>
		
		<c:if test="${vo.lev > 0}">
			<c:forEach var="i" begin="1" end="${vo.lev}" step="1">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</c:forEach>
			<img src="./categoryimages/arrow2.png" width="20"/>
		</c:if>
		
		<c:if test="${vo.useYn == 'YES'}">
			삭제된 카테고리 입니다.
		</c:if>
		
		<input type="text" name="name" value="${vo.name}" size="1"/>
		<input type="submit" value="서브 카테고리 만들기"/>
		
			<input type="button" value="삭제" 
				onclick="mySubmitDelete(${formName})"/>
		
		<input type="button" value="수정" 
			onclick="mySubmitUpdate(${formName})"/>
			
	</form>
</c:forEach>
<body>

</body>
</html>


















