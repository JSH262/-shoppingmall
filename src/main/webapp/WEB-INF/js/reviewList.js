
$(() => {
	let successSearchData = function() 
	{
				
		Ajax(`${CONTEXT_PATH}/reviewList`, "POST", JSON.stringify(data), 
			function(resp)
			{
				$("#list").empty();
				
				let list = resp.result.list;
							
				for(let i = 0; i<list.length; i++)
				{
					let item = list[i];
					let tr = $('<tr>');
											
					//tr.append(`<td align="center"><a href="${CONTEXT_PATH}/product/detail?id=${item.id}&pageSize=${pageSize}&currentPage=${currentPage}">${item.name}</a></td>`);				
					
					tr.append(`<td align="center">${item.score}</td>`);
					tr.append(`<td align="center">${item.contents}</td>`);
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