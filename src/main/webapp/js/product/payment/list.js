

$(() => {

	const CONTEXT_PATH = $("#contextPath").val();

	let data = {
		"currentPage": 1,
		"pageSize": 15
	};
	

	Ajax(`${CONTEXT_PATH}/product/payment/list`, "POST", JSON.stringify(data), 
		function(resp)
		{
			console.log(resp);
		},
		function(err)
		{
			console.error(err);
		}
	);	

	
});