<%@page import="java.util.ArrayList"%>
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

<%
	CategoryService service = CategoryService.getInstance();
	CategoryVO original = service.selectByid(vo.getId());
	
	ArrayList<CategoryVO> deleteList = service.deleteList(vo);
	for (int i=0; i<deleteList.size(); i++) {
		out.println(deleteList.get(i) + "<br/>");
		service.delete(deleteList.get(i).getId());
		try {
			if (deleteList.get(i).getSeq() + 1 != deleteList.get(i + 1).getSeq()) {
				break;
			}
		} catch (IndexOutOfBoundsException e) {
			
		}
	}
	
	service.reSeq(original.getGup());
	
	out.println("<script>");
	out.println("alert('" + original.getName() + " 카테고리와 모든 서브 카테고리 삭제완료!!')");
	out.println("location.href='list.jsp'");
	out.println("</script>");
%>

</body>
</html>