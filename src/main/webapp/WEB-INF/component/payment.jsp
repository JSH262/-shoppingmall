<%@page import="com.tjoeun.shoppingmall.vo.CategoryVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<%
	CategoryVO[] cardList = {
			new CategoryVO(10000, "현대카드"),
			new CategoryVO(10001, "삼성카드"),
			new CategoryVO(10002, "비씨카드"),
			new CategoryVO(10003, "KB국민"),
			new CategoryVO(10004, "신한카드"),
			new CategoryVO(10005, "롯데카드"),
			new CategoryVO(10006, "NH농협"),
			new CategoryVO(10007, "하나카드"),
			new CategoryVO(10008, "SC제일"),
			new CategoryVO(10009, "우리카드")
	};
%>
	<c:set var="cardList" value="<%=cardList %>" ></c:set>
	
	<%-- 신용카드 선택
	https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=bootpay&logNo=221220922083 
	--%>
	<div class="modal fade" id="paymentSelectModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="paymentSelectModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="--bs-modal-width:1000px">
			<div class="modal-content" style="font-size:small;">
				<div class="modal-header">
					<h1 class="modal-title fs-5 fw-bold text-secondary opacity-50" id="paymentSelectModalLabel">GK 스시니이</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" id="paymentSelectModalClose" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="row">
							<div class="col-2">
								<span>신용카드</span>
							</div>
							<div class="col-1 vr p-0 m-0"></div>
							<div class="col m-1">
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
										
										<div class="row">
											<div class="container">
												<div class="row row-cols-auto justify-content-md-center">
													<c:forEach var="item" items="${cardList }">
														<span class="col-3 d-grid gap-4 p-0 m-1">
															<input type="radio" class="btn-check" name="creaditCardCat" id="card${item.id }" autocomplete="off">
															<label class="btn btn-outline-success btn-sm" for="card${item.id }">${item.name}</label>
														</span>
													</c:forEach>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-1 vr p-0 m-0"></div>
							<div class="col-3">
								<div class="row justify-content-md-center fs-5 fw-bold text-secondary opacity-50">
									GK 스시니이
								</div>
								<div class="row my-3">
									<div class="col-5">상품명</div>
									<div class="col" name="paymentPayProductName">test test</div>
								</div>
								<div class="row">
									<hr class="mx-3" style="width: 95%;" />
								</div>
								<div class="row my-3">
									<div class="col-5">상품가격</div>
									<div class="col" name="paymentPayProductPrice">101010101원</div>
								</div>
								<div class="row">
									<hr class="mx-3" style="width: 95%;" />
								</div>
								<div class="row my-3">
									<div class="col-5">결제금액</div>
									<div class="col" name="paymentPayPrice">101010101원</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="paymentSelectModalNext">다음</button>
				</div>
			</div>
		</div>
	</div>


	<%-- 신용카드 정보 입력
	https://portone.gitbook.io/docs/auth/guide-1/bill/pg 
	--%>
	<div class="modal fade" id="paymentInputModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="paymentInputModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="--bs-modal-width:1000px">
			<div class="modal-content" style="font-size:small;">
				<div class="modal-header">
					<h1 class="modal-title fs-5 fw-bold text-secondary opacity-50" id="paymentInputModalLabel">GK 스시니이</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" id="paymentInputModalClose" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="row">
							<div class="col-2">
								<span>신용카드</span>
							</div>
							<div class="col-1 vr p-0 m-0"></div>
							<div class="col m-1">
								<table class="table">
									<tbody width="100%">
										<tr>
											<th scope="row" width="23%">카드번호</th>
											<td>
												<input class="form-control form-control-sm" id="paymentInputCreditCardNm1" name="paymentInputCreditCardNm" style="width:21%;display:inline;" type="text">
												-
												<input class="form-control form-control-sm" id="paymentInputCreditCardNm2" name="paymentInputCreditCardNm" style="width:21%;display:inline;" type="text">
												-
												<input class="form-control form-control-sm" id="paymentInputCreditCardNm3" name="paymentInputCreditCardNm" style="width:21%;display:inline;" type="text">
												-
												<input  class="form-control form-control-sm" id="paymentInputCreditCardNm4" name="paymentInputCreditCardNm" style="width:21%;display:inline;" type="text">
											</td>
										</tr>
										<tr>
											<th scope="row">유효기간</th>
											<td>
												<input class="form-control form-control-sm" style="width:35px;display:inline;" type="text" id="paymentInputCreditCardYear">년
												<input class="form-control form-control-sm" style="width:35px;display:inline;" type="text" id="paymentInputCreditCardMonth">월
											</td>
										</tr>
										<tr>
											<th scope="row">카드구분</th>
											<td>
												<div class="container">
													<div class="col">
														<div class="row">
															<div class="col-3">
																<div class="form-check"">
																  	<input class="form-check-input" type="radio" name="paymentInputCreditCardCat" value="personal" id="paymentInputCreditCardCat1">
																  	<label class="form-check-label" for="paymentInputCardCat1">
																    	개인
																  	</label>
																</div>
															</div>
															<div class="col">
															  	<div class="form-check">
																  	<input class="form-check-input" type="radio" name="paymentInputCreditCardCat" value="corporation" id="paymentInputCreditCardCat2">
																  	<label class="form-check-label" for="paymentInputCardCat2">
																    	법인
																  	</label>
																</div>
															</div>
														</div>
													</div>
													
												</div>
											</td>
										</tr>
										<tr>
											<th scope="row">비밀번호</th>
											<td>
												<input class="form-control form-control-sm" style="width:35px;display:inline;" type="password" id="paymentInputCreditCardPwd"><span style="font-size: small;">XX (앞2자리)</span>
											</td>
										</tr>
										<tr>
											<th scope="row">주민등록번호</th>
											<td>
												<input class="form-control form-control-sm" style="width:85px;display:inline;" type="password" name="paymentInputPersonalNm" id="paymentInputPersonalNm1">
												-
												<input class="form-control form-control-sm" style="width:85px;display:inline;" type="password" name="paymentInputPersonalNm" id="paymentInputPersonalNm2">
											</td>
										</tr>
									</tbody>
									<tfoot>
										<tr style="font-size:smaller;">
											<td colspan="2" align="left">
												<div class="container">
													<div class="row">
														<div class="col">
															<div class="row">
																<div class="col d-flex justify-content-start align-items-center">
																	<input class="form-check-input " type="checkbox" value="" id="paymentInputAllAccept">
																	&nbsp;
																	<label class="form-check-label fs-6" for="paymentInputAllAccept">
																		전체동의합니다.
																	</label>
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col">
															<div class="row">
																<div class="col p-0 align-items-center">
																	<input class="form-check-input " type="checkbox" value="" id="paymentInputEleFinAccept">
																	<label class="form-check-label" for="paymentInputEleFinAccept">
																		전자금융 이용약관
																	</label>
																</div>																
																<div class="col-3">
																	<button type="button" class="btn btn-secondary" id="paymentInputEleFinAcceptShow" style="--bs-btn-padding-y: .0rem; --bs-btn-padding-x: .0rem; --bs-btn-font-size: .5rem;">보기</button>
																</div>
															</div>
														</div>
														<div class="col">
															<div class="row">
																<div class="col p-0">
																	<input class="form-check-input " type="checkbox" value="" id="paymentInputUniPerInfoAccept">
																	<label class="form-check-label" for="paymentInputUniPerInfoAccept">
																		고유식별정보수집 및 이용약관
																	</label>
																</div>																
																<div class="col-3">
																	<button type="button" class="btn btn-secondary" id="paymentInputUniPerInfoShow" style="--bs-btn-padding-y: .0rem; --bs-btn-padding-x: .0rem; --bs-btn-font-size: .5rem;">보기</button>
																</div>
															</div>
														</div>
													</div>		
													<div class="row">
														<div class="col">
															<div class="row">
																<div class="col p-0">
																	<input class="form-check-input " type="checkbox" value="" id="paymentInputPerInfoCollAccept">
																	<label class="form-check-label" for="paymentInputPerInfoCollAccept">
																		개인정보수집 및 이용안내
																	</label>
																</div>																
																<div class="col-3">
																	<button type="button" class="btn btn-secondary" id="paymentInputPerInfoCollAcceptShow" style="--bs-btn-padding-y: .0rem; --bs-btn-padding-x: .0rem; --bs-btn-font-size: .5rem;">보기</button>
																</div>
															</div>
														</div>
														<div class="col">
															<div class="row">
																<div class="col p-0">
																	<input class="form-check-input " type="checkbox" value="" id="paymentInputPerInfoAccept">
																	<label class="form-check-label" for="paymentInputPerInfoAccept">
																		개인정보제공 및 위탁안내
																	</label>
																</div>																
																<div class="col-3">
																	<button type="button" class="btn btn-secondary" id="paymentInputPerInfoAcceptShow" style="--bs-btn-padding-y: .0rem; --bs-btn-padding-x: .0rem; --bs-btn-font-size: .5rem;">보기</button>
																</div>
															</div>
														</div>
													</div>										
												</div>
											<td>
										</tr>
									</tfoot>
								</table>
							</div>
							<div class="col-1 vr p-0 m-0"></div>
							<div class="col-3">
								<div class="row justify-content-md-center fs-5 fw-bold text-secondary opacity-50">
									GK 스시니이
								</div>
								<div class="row my-3">
									<div class="col-5">상품명</div>
									<div class="col" name="paymentPayProductName">test test</div>
								</div>
								<div class="row">
									<hr class="mx-3" style="width: 95%;" />
								</div>
								<div class="row my-3">
									<div class="col-5">상품가격</div>
									<div class="col" name="paymentPayProductPrice">101010101원</div>
								</div>
								<div class="row">
									<hr class="mx-3" style="width: 95%;" />
								</div>
								<div class="row my-3">
									<div class="col-5">결제금액</div>
									<div class="col" name="paymentPayPrice">101010101원</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="paymentInputModalNext">결제</button>
				</div>
			</div>
		</div>
	</div>
