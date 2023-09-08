

const payment = {
	paymentSelectModal: null,
	paymentInputModal: null,
	
	
	openStartPayment: function() {
		payment.paymentSelectModal.toggle();
	},
	
	init: function(callback) {

		try
		{
			const CONTEXT_PATH = getContextPath();
			let data = {
				"status":"pay_after"
			};
			
			Ajax(`${CONTEXT_PATH}/product/payment`, "POST", JSON.stringify(data), 
				function(resp)
				{
					if(resp.code == 0)
					{
						$("div[name=paymentPayProductName]").text(resp.result.productNames);
						$("div[name=paymentPayProductPrice]").text(resp.result.fmtPaymentPrice + "원");
						$("div[name=paymentPayPrice]").text(resp.result.fmtPaymentPrice + "원");
						$("#paymentPrice").text(resp.result.fmtPaymentPrice);
						payment.setPayemtnEvent();
						callback();
					}
					else
						alert(resp.msg);
				},
				function(err)
				{
					console.error(err);
				});	
		}
		catch(exp)
		{
			console.error(exp);
		}
	},
	setPayemtnEvent: function() {
		const CONTEXT_PATH = getContextPath();
		const ID = document.id;
		const PRODUCT_BUY = "02";
		
		

		const isPaymentSelectAllAccepted = () => {
			return $("#paymentPrivateInfouUsedAccepted").is(':checked') &&
				$("#paymentPrivateInfoCommnAccepted").is(':checked');
		};
		const getPaymentSelectCreaditCard = () => {
			return $("input[name=creaditCardCat]:checked").val();
		};
		
		
		payment.paymentSelectModal = new bootstrap.Modal(document.getElementById('paymentSelectModal'));
		payment.paymentInputModal = new bootstrap.Modal(document.getElementById('paymentInputModal'));	
		$("#payment").bind('click', function(){
			paymentSelectModal.toggle();
		});
		
		// 1****************/
		$("#paymentSelectModalNext").bind('click', function() {
			
			const isChecked = isPaymentSelectAllAccepted();
			const creaditCard = getPaymentSelectCreaditCard();
			
			if(isChecked && creaditCard)
			{
				payment.paymentSelectModal.toggle();
				payment.paymentInputModal.toggle();	
			}
			else
			{
				if(!isChecked)
				{
					alert('이용약관에 동의해주세요');
				}
				else if(!creaditCard)
				{
					alert('신용카드를 선택해주세요');
				}
			}
		}); 
		$("#paymentSelectModalClose").bind('click', function() {
			payment.paymentSelectModal.toggle();
		});
		
		$("#paymentInputModalNext").bind('click', function() {
			
			let ccNm = '';
			let ccNm1 = $("#paymentInputCreditCardNm1").val();
			let ccNm2 = $("#paymentInputCreditCardNm2").val();
			let ccNm3 = $("#paymentInputCreditCardNm3").val();
			let ccNm4 = $("#paymentInputCreditCardNm4").val();
			let ccYear = $("#paymentInputCreditCardYear").val();
			let ccMonth = $("#paymentInputCreditCardMonth").val();
			let ccCat = $("input[name=paymentInputCreditCardCat]:checked").val();
			let ccPwd = $("#paymentInputCreditCardPwd").val();
			let persNm = '';
			let persNm1 = $("#paymentInputPersonalNm1").val();
			let persNm2 = $("#paymentInputPersonalNm2").val();
			const isEleFinAccept = $("#paymentInputEleFinAccept").is(':checked');
			const isUniInfoAccept = $("#paymentInputUniPerInfoAccept").is(':checked');
			const isPerInfoCollAccept = $("#paymentInputPerInfoCollAccept").is(':checked');
			const isPerInfoAccept = $("#paymentInputPerInfoAccept").is(':checked');
			
			$("input[name=paymentInputCreditCardNm]").each(function(_, item) {			
				ccNm += item.value;
			});
			
			$("input[name=paymentInputPersonalNm]").each(function(_, item) {
				persNm += item.value;
			});
			
			if(isEleFinAccept && isUniInfoAccept && isPerInfoCollAccept && isPerInfoAccept)
			{
				if(ccNm1 && 
					ccNm2 && 
					ccNm3 && 
					ccNm4 && 
					ccYear && 
					ccMonth && 
					ccPwd && 
					persNm1 && 
					persNm2 && ccCat)
				{
					let data = {
						"status":"pay_before"
					};
				
					try
					{
						Ajax(`${CONTEXT_PATH}/product/payment`, "POST", JSON.stringify(data), 
							function(resp)
							{
								let productList = [];

								for(let i = 0; i<resp.result.list.length; i++)
								{
									const item = resp.result.list[i];
															
									productList.push({
										'productId': item.id,
										'sellerId': item.sellerId
									});
								}
							
								const url = "ws://" + location.host + CONTEXT_PATH + '/alert';
								
								wSocket = new WebSocket(url);
								
								// 웹소켓 서버에 연결됐을 때 실행
								wSocket.onopen = function(event) 
								{
									
									let sendData = {
										'code': PRODUCT_BUY,
										'id':ID,
										'productList': productList
									};
									
									wSocket.send(JSON.stringify(sendData));
									wSocket.close();
									
									alert(resp.msg);					
									location.href = `${CONTEXT_PATH}/product/payment/list`;
								};
							
								wSocket.onclose = function(event) 
								{
									console.log("웹소켓 서버가 종료되었습니다.", event.data);
								};
							
								wSocket.onerror = function(event) {
									console.error(event.data);
								}; 
							
								wSocket.onmessage = function(event) { 
								   
								};
							},
							function(err)
							{
								console.error(err);
							});	
					}	
					catch(exp)
					{
						console.error(exp);
					}
					
					//paymentInputModal.toggle();
				}
				else
				{
					alert('입력하지 않은 내용이 있습니다.');
				}			
			}
			else
			{
				alert('이용약관에 동의해주세요');
			}
			
		});
		$("#paymentInputModalClose").bind('click', function() {
			payment.paymentInputModal.toggle();
		});
		
		// 1****************/
		
		// 2****************/
		const paymentAllAcceptChage = () => {
			const isChecked = $("#paymentPrivateInfouUsedAccepted").is(':checked') &&
				$("#paymentPrivateInfoCommnAccepted").is(':checked');
			$("#paymentAllAccepted").prop('checked', isChecked);
		};
		
		$("#paymentAllAccepted").bind('change', function(){
			const isChecked = $(this).is(':checked');
			
			$("#paymentPrivateInfouUsedAccepted").prop("checked", isChecked);		
			$("#paymentPrivateInfoCommnAccepted").prop("checked", isChecked);
			
		});	
		
		$("#paymentPrivateInfouUsedAccepted").bind('change', function(){
			paymentAllAcceptChage();
		});	
		
		$("#paymentPrivateInfoCommnAccepted").bind('change', function(){
			paymentAllAcceptChage();
		});	
		// 2****************/
		
		
		// 3****************/
		$("#paymentInputAllAccept").bind('change', function() {
			let isChecked = $(this).is(':checked');
			$("#paymentInputEleFinAccept").prop('checked', isChecked);
			$("#paymentInputUniPerInfoAccept").prop('checked', isChecked);
			$("#paymentInputPerInfoCollAccept").prop('checked', isChecked);
			$("#paymentInputPerInfoAccept").prop('checked', isChecked);
		});

		const paymentInputAcceptChange = () => {
			const isChecked = $("#paymentInputEleFinAccept").is(':checked') &&
				$("#paymentInputUniPerInfoAccept").is(':checked') && 
				$("#paymentInputPerInfoCollAccept").is(':checked') &&
				$("#paymentInputPerInfoAccept").is(':checked');
			
			$("#paymentInputAllAccept").prop('checked', isChecked);
		};
		
		
		$("#paymentInputEleFinAccept").bind('change', function() {
			paymentInputAcceptChange();
		});
		
		$("#paymentInputUniPerInfoAccept").bind('change', function() {
			paymentInputAcceptChange();
		});
		
		$("#paymentInputPerInfoCollAccept").bind('change', function() {
			paymentInputAcceptChange();
		});
		
		$("#paymentInputPerInfoAccept").bind('change', function() {
			paymentInputAcceptChange();
		});
		// 3****************/
		
	}
};



 





/*
$(() => {
	const CONTEXT_PATH = getContextPath();
	const ID = document.id;
	const PRODUCT_BUY = "02";
	
	let data = {
		"status":"pay_after"
	};
	
	try
	{
		Ajax(`${CONTEXT_PATH}/product/payment`, "POST", JSON.stringify(data), 
			function(resp)
			{
				if(resp.code == 0)
				{
					$("div[name=paymentPayProductName]").text(resp.result.productNames);
					$("div[name=paymentPayProductPrice]").text(resp.result.fmtPaymentPrice + "원");
					$("div[name=paymentPayPrice]").text(resp.result.fmtPaymentPrice + "원");
					$("#paymentPrice").text(resp.result.fmtPaymentPrice);
				}
				else
					alert(resp.msg);
			},
			function(err)
			{
				console.error(err);
			});	
	}
	catch(exp)
	{
		console.error(exp);
	}
		

	
	const isPaymentSelectAllAccepted = () => {
		return $("#paymentPrivateInfouUsedAccepted").is(':checked') &&
			$("#paymentPrivateInfoCommnAccepted").is(':checked');
	};
	const getPaymentSelectCreaditCard = () => {
		return $("input[name=creaditCardCat]:checked").val();
	};
	
	
	const paymentSelectModal = new bootstrap.Modal(document.getElementById('paymentSelectModal'));
	const paymentInputModal = new bootstrap.Modal(document.getElementById('paymentInputModal'));	
	$("#payment").bind('click', function(){
		paymentSelectModal.toggle();
	});
	
	// 1 ---------------------------------------------/
	$("#paymentSelectModalNext").bind('click', function() {
		
		const isChecked = isPaymentSelectAllAccepted();
		const creaditCard = getPaymentSelectCreaditCard();
		
		if(isChecked && creaditCard)
		{
			paymentSelectModal.toggle();
			paymentInputModal.toggle();	
		}
		else
		{
			if(!isChecked)
			{
				alert('이용약관에 동의해주세요');
			}
			else if(!creaditCard)
			{
				alert('신용카드를 선택해주세요');
			}
		}
	}); 
	$("#paymentSelectModalClose").bind('click', function() {
		paymentSelectModal.toggle();
	});
	
	$("#paymentInputModalNext").bind('click', function() {
		
		let ccNm = '';
		let ccNm1 = $("#paymentInputCreditCardNm1").val();
		let ccNm2 = $("#paymentInputCreditCardNm2").val();
		let ccNm3 = $("#paymentInputCreditCardNm3").val();
		let ccNm4 = $("#paymentInputCreditCardNm4").val();
		let ccYear = $("#paymentInputCreditCardYear").val();
		let ccMonth = $("#paymentInputCreditCardMonth").val();
		let ccCat = $("input[name=paymentInputCreditCardCat]:checked").val();
		let ccPwd = $("#paymentInputCreditCardPwd").val();
		let persNm = '';
		let persNm1 = $("#paymentInputPersonalNm1").val();
		let persNm2 = $("#paymentInputPersonalNm2").val();
		const isEleFinAccept = $("#paymentInputEleFinAccept").is(':checked');
		const isUniInfoAccept = $("#paymentInputUniPerInfoAccept").is(':checked');
		const isPerInfoCollAccept = $("#paymentInputPerInfoCollAccept").is(':checked');
		const isPerInfoAccept = $("#paymentInputPerInfoAccept").is(':checked');
		
		$("input[name=paymentInputCreditCardNm]").each(function(_, item) {			
			ccNm += item.value;
		});
		
		$("input[name=paymentInputPersonalNm]").each(function(_, item) {
			persNm += item.value;
		});
		
		if(isEleFinAccept && isUniInfoAccept && isPerInfoCollAccept && isPerInfoAccept)
		{
			if(ccNm1 && 
				ccNm2 && 
				ccNm3 && 
				ccNm4 && 
				ccYear && 
				ccMonth && 
				ccPwd && 
				persNm1 && 
				persNm2 && ccCat)
			{
				let data = {
					"status":"pay_before"
				};
			
				try
				{
					Ajax(`${CONTEXT_PATH}/product/payment`, "POST", JSON.stringify(data), 
						function(resp)
						{
							let productList = [];

							for(let i = 0; i<resp.result.list.length; i++)
							{
								const item = resp.result.list[i];
														
								productList.push({
									'productId': item.id,
									'sellerId': item.sellerId
								});
							}
						
							const url = "ws://" + location.host + CONTEXT_PATH + '/alert';
							
							wSocket = new WebSocket(url);
							
							// 웹소켓 서버에 연결됐을 때 실행
							wSocket.onopen = function(event) 
							{
								
								let sendData = {
									'code': PRODUCT_BUY,
									'id':ID,
									'productList': productList
								};
								
								wSocket.send(JSON.stringify(sendData));
								wSocket.close();
								
								alert(resp.msg);					
								location.href = `${CONTEXT_PATH}/product/payment/list`;
							};
						
							wSocket.onclose = function(event) 
							{
								console.log("웹소켓 서버가 종료되었습니다.", event.data);
							};
						
							wSocket.onerror = function(event) {
								console.error(event.data);
							}; 
						
							wSocket.onmessage = function(event) { 
							   
							};
						},
						function(err)
						{
							console.error(err);
						});	
				}	
				catch(exp)
				{
					console.error(exp);
				}
				
				//paymentInputModal.toggle();
			}
			else
			{
				alert('입력하지 않은 내용이 있습니다.');
			}			
		}
		else
		{
			alert('이용약관에 동의해주세요');
		}
		
	});
	$("#paymentInputModalClose").bind('click', function() {
		paymentInputModal.toggle();
	});
	
	// 1 ---------------------------------------------/
	
	// 2 ---------------------------------------------/
	const paymentAllAcceptChage = () => {
		const isChecked = $("#paymentPrivateInfouUsedAccepted").is(':checked') &&
			$("#paymentPrivateInfoCommnAccepted").is(':checked');
		$("#paymentAllAccepted").prop('checked', isChecked);
	};
	
	$("#paymentAllAccepted").bind('change', function(){
		const isChecked = $(this).is(':checked');
		
		$("#paymentPrivateInfouUsedAccepted").prop("checked", isChecked);		
		$("#paymentPrivateInfoCommnAccepted").prop("checked", isChecked);
		
	});	
	
	$("#paymentPrivateInfouUsedAccepted").bind('change', function(){
		paymentAllAcceptChage();
	});	
	
	$("#paymentPrivateInfoCommnAccepted").bind('change', function(){
		paymentAllAcceptChage();
	});	
	// 2 ---------------------------------------------/
	
	
	// 3 ---------------------------------------------/
	$("#paymentInputAllAccept").bind('change', function() {
		let isChecked = $(this).is(':checked');
		$("#paymentInputEleFinAccept").prop('checked', isChecked);
		$("#paymentInputUniPerInfoAccept").prop('checked', isChecked);
		$("#paymentInputPerInfoCollAccept").prop('checked', isChecked);
		$("#paymentInputPerInfoAccept").prop('checked', isChecked);
	});

	const paymentInputAcceptChange = () => {
		const isChecked = $("#paymentInputEleFinAccept").is(':checked') &&
			$("#paymentInputUniPerInfoAccept").is(':checked') && 
			$("#paymentInputPerInfoCollAccept").is(':checked') &&
			$("#paymentInputPerInfoAccept").is(':checked');
		
		$("#paymentInputAllAccept").prop('checked', isChecked);
	};
	
	
	$("#paymentInputEleFinAccept").bind('change', function() {
		paymentInputAcceptChange();
	});
	
	$("#paymentInputUniPerInfoAccept").bind('change', function() {
		paymentInputAcceptChange();
	});
	
	$("#paymentInputPerInfoCollAccept").bind('change', function() {
		paymentInputAcceptChange();
	});
	
	$("#paymentInputPerInfoAccept").bind('change', function() {
		paymentInputAcceptChange();
	});
	// 3 ---------------------------------------------/
	
	
	
});
//*/