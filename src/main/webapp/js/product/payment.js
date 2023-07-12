

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
				
				location.href = `${CONTEXT_PATH}/product/payment/list.jsp`;
			},
			function(err)
			{
				console.error(err);
			}
		);	
			
	});
	
	
});