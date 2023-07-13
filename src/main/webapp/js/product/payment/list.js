

$(() => {

	const CONTEXT_PATH = $("#contextPath").val();
	let itemNode = $("tr[name=tableNode]").clone();
	$("#list").empty();

	let data = {
		"currentPage": 1,
		"pageSize": 15
	};
	

	Ajax(`${CONTEXT_PATH}/product/payment/list`, "POST", JSON.stringify(data), 
		function(resp)
		{
			let list = resp.result.list;

			for(let i = 0; i<list.length; i++)
			{
				let node = itemNode.clone();
				let item = list[i];
				let img = $("<img>");
				
				img.attr('src', `${CONTEXT_PATH}/image/${item.productThumbnail}`);
				node.find('td[name=id]').text(item.id);
				node.find('td[name=thumbnail]').append(img);
				node.find('td[name=productName]').text(item.productName);
				node.find('td[name=productAmount]').text(item.fmtProductAmount);
				node.find('td[name=productPrice]').text(item.fmtProductPrice);
				node.find('td[name=productDelivery]').text(item.fmtProductDeliveryPrice);				
				node.find('td[name=totalProductPrice]').text(item.fmtTotalProductPrice);
				
				$("#list").append(node);
			}
			
		},
		function(err)
		{
			console.error(err);
		}
	);	

	
});