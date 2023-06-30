

$(() => {
	const contextPath = $("#form").attr('action');
	
	$("#formSubmit").bind('click', function() {
		let url = contextPath + "/product/insertOK";
		let data = JSON.stringify({
			"name": "홍길동"
		});
		
		Ajax(url, "POST", data, 
			function(resp)
			{
				console.log(resp);
			}, 
			function()
			{
				
			});
	});
	
	
});