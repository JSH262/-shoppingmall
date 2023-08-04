<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/product/payment.js"></script>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
		
		<style>
			
			.hide-node {
				display: none;
			}
			#paymentPrice {
				font-size: x-large;
				color: crimson;
			}
			#paymentPrice:after {
				content: '원';
				font-size: medium;
				color: black;
			}
			
		</style>
		
	</head>
	<body>
			
		<div style="width: 90%; margin:0px auto; text-align: center;">
			<div>
				<div>결제할 금액은 총 <span id="paymentPrice"></span>입니다. 결제를 하시려면 결제하기 버튼을 눌러주세요.</div>
				<div>&nbsp;</div>
				<div>
					<input type="button" id="payment" name="payment" value="결제하기" />
				</div>
			</div>
			
			
			<div id="nextButton" class="hide-node">
				<span><input type="button" value="홈으로" onclick="location.href='<%=request.getContextPath() %>/';"></span>
				<span><input type="button" value="상품목록" onclick="location.href='<%=request.getContextPath() %>/product/list';"></span>				
				<span><input type="button" value="결제목록" onclick="location.href='<%=request.getContextPath() %>/product/payment/list';"></span>
			</div>
		</div>
	
	
		<input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath() %>" />
		<input type="hidden" id="currentPage" name="currentPage" value="" />
		<input type="hidden" id="pageSize" name="pageSize" value="" />
		
	</body>
</html>