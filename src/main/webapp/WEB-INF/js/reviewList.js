
$(() => {
	
	$("#list").empty();
			
	$("#registerProduct").bind('click', function() {
		location.href = `${CONTEXT_PATH}/product/insert?currentPage=${cPage}&pageSize=${pSize}`;
	});
	
	let successSearchData = function(searchCategory, searchValue) 
	{
		let data = {
			searchCategory: searchCategory,
			searchValue: searchValue
		};
				
		Ajax(`${CONTEXT_PATH}/product/list`, "POST", JSON.stringify(data), 
			function(resp)
			{
				$("#list").empty();
				$("#pagination").empty();
				
				let list = resp.result.list;
							
				for(let i = 0; i<list.length; i++)
				{
					let item = list[i];
					let tr = $('<tr>');
											
					//tr.append(`<td>${item.id}</td>`);
					tr.append(`<td align="center">${item.rnum}</td>`);
					tr.append(`<td align="center">${item.categoryName}</td>`);				
					tr.append(`<td align="center"><a href="${CONTEXT_PATH}/product/detail?id=${item.id}&pageSize=${pageSize}&currentPage=${currentPage}">${item.name}</a></td>`);				
					
					if(item.amount != 0)
						tr.append(`<td align="center">${item.fmtAmount}</td>`);
					else
						tr.append(`<td class="product-sold-out" align="center">${item.fmtAmount}</td>`);
					
					
					tr.append(`<td align="center">${item.fmtPrice}</td>`);
					tr.append(`<td align="center">${item.fmtDiscount}</td>`);
					tr.append(`<td align="center">${item.fmtDeliveryPrice}</td>`);
					tr.append(`<td align="center">${item.createDate}</td>`);
					
					$("#list").append(tr);
				}
			},
			function(err)
			{
				console.error(err);
			});		
	};

	successSearchData();
	
		
});