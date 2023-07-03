
$(() => {
	
	const contextPath = $("#form").attr('action');
	let currentPage = $("#currentPage").val();
	let pageSize = $("#pageSize").val();
	
	
	Ajax(contextPath + `/product/list?currentPage=${currentPage}&pageSize=${pageSize}`, "GET", null, 
		function(resp) 
		{
			let list = resp.result.list;
			let currentPage = resp.result.paging.currentPage;
			let pageSize = resp.result.paging.pageSize;
						
						
			console.log(resp);
						
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
				tr.append(`<td align="center">${item.fmtAmount}</td>`);
				tr.append(`<td align="center">${item.fmtPrice}</td>`);
				tr.append(`<td align="center">${item.fmtDiscount}</td>`);
				tr.append(`<td align="center">${item.fmtDeliveryPrice}</td>`);
				tr.append(`<td align="center">${item.createDate}</td>`);
				
				$("#list").append(tr);
			}
			
			/*
				페이지 순번 추가
			*/
			let pagination = resp.result.paging;		
						
			//이전
			let prevNode = null;
			if(pagination.startPage == 1)
			{
				prevNode = $(`<span class="page-number page-number-current">이전</span>`);
			}
			else
			{
				prevNode = $(`<span class="page-number">이전</span>`);
				prevNode.bind('click', function() {
					location.href = `${contextPath}/product/list.jsp?currentPage=${pagination.startPage - 1}&pageSize=${pageSize}`;
				});
			}
			$("#pagination").append(prevNode);
			
			//1 ~ 10	
			for(let i = pagination.startPage; i<=pagination.endPage; i++)
			{
				let node = null;
				
				if(i == currentPage)
				{
					node = $(`<span class="page-number page-number-current">${i}</span>`);	
				}
				else
				{
					node = $(`<span class="page-number">${i}</span>`);		
					node.bind('click', function() {
						location.href = `${contextPath}/product/list.jsp?currentPage=${i}&pageSize=${pageSize}`;
					});	
				}
				
				$("#pagination").append(node);
			}						
			
			//다음
			let nextNode = null;
			if(pagination.endPage < pagination.totalPage)
			{
				nextNode = $(`<span class="page-number">다음</span>`);
				nextNode.bind('click', function() {
					location.href = `${contextPath}/product/list.jsp?currentPage=${pagination.endPage + 1}&pageSize=${pageSize}`;
				});
			}
			else
			{
				nextNode = $(`<span class="page-number page-number-current">다음</span>`);
			}
			$("#pagination").append(nextNode);
		}, 
		function(err) 
		{
			console.error(err);
		});
});