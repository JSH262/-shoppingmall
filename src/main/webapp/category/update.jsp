<%@page import="com.tjoeun.shoppingmall.vo.CategoryVO"%>
<%@page import="com.tjoeun.shoppingmall.service.CategoryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="vo" class="com.tjoeun.shoppingmall.vo.CategoryVO">
	<jsp:setProperty property="*" name="vo"/>
</jsp:useBean>
${vo}

<%
	CategoryService service = CategoryService.getInstance();

	CategoryVO original = service.selectByid(vo.getId());
	
	service.update(vo);
	
	out.println("<script>");
	out.println("alert('" + original.getName() + " 카테고리를 " + vo.getName() + 
		" 카테고리로 수정완료!!')");
	out.println("location.href='list.jsp'");
	out.println("</script>");
%>

</body>
</html>