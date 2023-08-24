

$(() => {
	const CONTEXT_PATH = $("#contextPath").val();
	
	let data = {
		"status":"pay_after"
	};
	
	Ajax(`${CONTEXT_PATH}/product/payment`, "POST", JSON.stringify(data), 
		function(resp)
		{
			if(resp.code == 0)
				$("#paymentPrice").text(resp.result.fmtPaymentPrice);
			else
				alert(resp.msg);
		},
		function(err)
		{
			console.error(err);
		}
	);	
	
	
	
	$("#payment").bind('click', function() {
		let data = {
			"status":"pay_before"
		};
	
		Ajax(`${CONTEXT_PATH}/product/payment`, "POST", JSON.stringify(data), 
			function(resp)
			{
				alert(resp.msg);
				
				location.href = `${CONTEXT_PATH}/product/payment/list`;
			},
			function(err)
			{
				console.error(err);
			}
		);	
			
	});
	
	const paymentSelectModal = new bootstrap.Modal(document.getElementById('paymentSelectModal'));
	const paymentInputModal = new bootstrap.Modal(document.getElementById('paymentInputModal'));
	const paymentPayModal = new bootstrap.Modal(document.getElementById('paymentPayModal'));
	
	$("#paymentSelectModalNext").bind('click', function() {
		paymentSelectModal.toggle();
		paymentInputModal.toggle();
	}); 
	$("#paymentSelectModalClose").bind('click', function() {
		paymentSelectModal.toggle();
	});
	
	$("#paymentInputModalNext").bind('click', function() {
		paymentInputModal.toggle();
		paymentPayModal.toggle();
	});
	$("#paymentInputModalClose").bind('click', function() {
		paymentInputModal.toggle();
	});
	
	$("#paymentPayModalClose").bind('click', function() {
		paymentPayModal.toggle();
	});
	
	$("#paymentAllAccepted").bind('change', function(){
		const isChecked = $(this).is(':checked');
		
		console.log(isChecked);
		$("#paymentPrivateInfouUsedAccepted").prop("checked", isChecked);		
		$("#paymentPrivateInfoCommnAccepted").prop("checked", isChecked);
		
	});	
	
	$("#paymentPrivateInfouUsedAccepted").bind('change', function(){
		const isChecked1 = $(this).is(':checked');
		const isChecked2 = $("#paymentPrivateInfoCommnAccepted").is(':checked');
		
		console.log(isChecked1 && isChecked2);
		$("#paymentAllAccepted").prop('checked', isChecked1 && isChecked2);
	});	
	
	$("#paymentPrivateInfoCommnAccepted").bind('change', function(){
		const isChecked1 = $(this).is(':checked');
		const isChecked2 = $("#paymentPrivateInfouUsedAccepted").is(':checked');
		
		console.log(isChecked1 && isChecked2);
		$("#paymentAllAccepted").prop('checked', isChecked1 && isChecked2);
	});	
	
	
});