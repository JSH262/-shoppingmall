



$(() => {	
	const ID = $("#id").val();
	const CONTEXT_PATH = $("#contextPath").val();
	
	$("#amountMinus").bind('click', function() {
		let value = parseInt($("#amount").val()) - 1;
		if(value > 0)
			$("#amount").val(value);
	});
	
	$("#amountPlus").bind('click', function() {
		$("#amount").val(parseInt($("#amount").val()) + 1);
	});
	
	$("#cart").bind('click', function() {
				
		let amount = parseInt($("#amount").val());
		
		let data = {
			amount: amount,
			productId: id
		};
		
		Ajax(`${CONTEXT_PATH}/product/cart/insert`, "POST", JSON.stringify(data), 
			function(resp) 
			{
				if(resp.code == 0)
				{
					//정상적으로 추가됨
				}
				else
				{
					alert(resp.msg);
				}
			}, 
			function(err)
			{
				console.error(err);
			});
		
		
		console.log(ID);
		console.log(amount);
	});
	
	$("#buy").bind('click', function() {		
		let amount = parseInt($("#amount").val());
		
		console.log(ID);
		console.log(amount);	
	});
});


