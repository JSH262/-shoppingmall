<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
    
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
	
	<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#paymentSelectModal">
		테스트
	</button>


	<%-- 신용카드 선택 --%>
	<div class="modal fade" id="paymentSelectModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="paymentSelectModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl" style="--bs-modal-width:1000px">
			<div class="modal-content" style="font-size:small;">
			<%--
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="paymentSelectModalLabel">Modal title</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">...</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" data-bs-target="#paymentInputModal">Understood</button>
				</div>
				 --%>
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="paymentSelectModalLabel">Modal title</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="row">
							<div class="col-2">
								<span>신용카드</span>
							</div>
							<div class="col-1 vr p-0 m-0"></div>
							<div class="col m-3">
								<div class="row">
									<div class="col">
										<div class="row">
											<div class="col">
												이용약관
											</div>
											<div class="col form-check">
										  		<input class="form-check-input" type="checkbox" value="" id="paymentAllAccepted">
										  		<label class="form-check-label" for="paymentAllAccepted">전체동의</label>
											</div>
											<hr/>
										</div>
										<div class="row">
											<div class="col">
												전자금융거래 이용약관
											</div>
										</div>
										<div class="row">
											<div class="col">
												<div class="row">										
													<div class="col-8">
														개인정보 수집 및 이용안내
													</div>
													<div class="col form-check">
												  		<input class="form-check-input" type="checkbox" value="" id="paymentPrivateInfouUsedAccepted">
												  		<label class="form-check-label" for="paymentPrivateInfouUsedAccepted">
														    동의
													  	</label>
													</div>
												</div>
											</div>
											<div class="col">
												<div class="row">										
													<div class="col-8">
														개인정보 제공 및 위탁안내
													</div>
													<div class="col form-check">
												  		<input class="form-check-input" type="checkbox" value="" id="paymentPrivateInfoCommnAccepted">
												  		<label class="form-check-label" for="paymentPrivateInfoCommnAccepted">
														    동의
													  	</label>
													</div>
												</div>
											</div>
											<hr/>
										</div>
										
										
										<c:forEach var="item" items="${cardList }">
											<span>
												<input type="radio" class="btn-check" name="creaditCard" id="card${item.id }" autocomplete="off">
												<label class="btn btn-outline-success" for="card${item.id }">${itme.name} }</label>
											</span>
										</c:forEach>
									</div>
								</div>
							</div>
							<div class="col-1 vr p-0 m-0"></div>
							<div class="col-4">
								<div class="row">
									<div class="col-5">상품명</div>
									<div class="col" id="paymentPayProductName">test test</div>
								</div>
								<div class="row">
									<div class="col-5">상품가격</div>
									<div class="col" id="paymentPayProductPrice">101010101원</div>
								</div>
								<div class="row">
									<div class="col-5">결제금액</div>
									<div class="col" id="paymentPayPrice">101010101원</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-bs-target="#paymentInputModal" data-bs-toggle="modal">Understood</button>
				</div>
			</div>
		</div>
	</div>

	<%-- 신용카드 정보 입력 --%>
	<div class="modal fade" id="paymentInputModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="paymentInputModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="paymentInputModalLabel">신용카드 정보 입력</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">...</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" data-bs-target="#paymentPayModal" data-bs-toggle="modal">Understood</button>
				</div>
			</div>
		</div>
	</div>


	<%-- 입력한 정보확인 --%>
	<div class="modal fade" id="paymentPayModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="paymentPayModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="paymentPayModalLabel">최종 확인 및 결제</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">...</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Understood</button>
				</div>
			</div>
		</div>
	</div>






	<div style="width: 90%; margin:0px auto; text-align: center;">
			<div>
				<div>결제할 금액은 총 <span id="paymentPrice"></span>입니다. 결제를 하시려면 결제하기 버튼을 눌러주세요.</div>
				<div>&nbsp;</div>
				<div>
					<input type="button" id="payment" name="payment" value="결제하기" />
					<input type="button" id="paymentCancel" name="paymentCancel" value="결제취소" />
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