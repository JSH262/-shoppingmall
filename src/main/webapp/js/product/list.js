
$(() => {
	
	const contextPath = $("#form").attr('action');
	let currentPage = $("#currentPage").val();
	let pageSize = $("#pageSize").val();
	
	
	Ajax(contextPath + `/product/list?currentPage=${currentPage}&pageSize=${pageSize}`, "GET", null, 
		function(resp) 
		{
			let list = resp.result.list;
			let currentPage = resp.result.currentPage;
			let pageSize = resp.result.pageSize;
						
			for(let i = 0; i<list.length; i++)
			{
				let item = list[i];
				let tr = $('<tr>');
								
				/*
					<th>순번</th>
					<th>카테고리</th>
					<th>상품 이름</th>
					<th>남은 수량</th>
					<th>상품 가격</th>
					<th>상품 할인률</th>
					<th>배송비</th>
					<th>상품 등록일</th>
				*/
				
				
				//tr.append(`<td>${item.id}</td>`);				
				tr.append(`<td align="center">${item.rnum}</td>`);
				tr.append(`<td align="center">${item.categoryId}</td>`);
				tr.append(`<td align="center"><a href="${contextPath}/product/detail.jsp?id=${item.id}&pageSize=${pageSize}&currentPage=${currentPage}">${item.name}</a></td>`);				
				tr.append(`<td align="center">${item.amount}</td>`);
				tr.append(`<td align="center">${item.price}</td>`);				
				tr.append(`<td align="center">${item.discount}</td>`);
				tr.append(`<td align="center">${item.deliveryPrice}</td>`);
				tr.append(`<td align="center">${item.createDate}</td>`);
				
				$("#list").append(tr);
			}
		}, 
		function(err) 
		{
			console.error(err);
		});
});