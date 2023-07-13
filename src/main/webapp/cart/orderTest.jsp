<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제</title>
</head>
<body>
<h1>결제창 확인</h1>
<% 
String productIds = request.getParameter("ProductIds");
if (productIds != null && !productIds.isEmpty()) {
	// 컴마 기준으로 나눠서 구분
    String[] productIdArray = productIds.split(",");
    for (String productId : productIdArray) {
        out.println("Product Id: " + productId + "<br>");
    }
} else {
    out.println("상품없음.");
}
%>
</body>
</html>
