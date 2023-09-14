
$(() => {
	const CONTEXT_PATH = $("#contextPath").val();
	const CURRENT_PAGE =  parseInt($("#currentPage").val());
	const PAGE_SIZE = parseInt($("#pageSize").val());
	
	
	
	$("#list").empty();
	$("#pagination").empty();
			
	$("#registerProduct").bind('click', function() {
		let cPage = $("#currentPage").val();
		let pSize = $("#pageSize").val();
		location.href = `${CONTEXT_PATH}/product/insert?currentPage=${cPage}&pageSize=${pSize}`;
	});
	let searCat = null;
	let searVal = null;
	
	let successSearchData = function(nCurrentPage, nPageSize, searchCategory, searchValue) 
	{
		if(!nCurrentPage)
			nCurrentPage = CURRENT_PAGE;

		if(!nPageSize)
			nPageSize = PAGE_SIZE;
		
		let data = {
			'productCategoryId': searchCategory,
			'productName': searchValue,
			'currentPage': nCurrentPage,
			'pageSize': nPageSize	 
		};
				
		Ajax(`${CONTEXT_PATH}/product/list`, "POST", JSON.stringify(data), 
			function(resp)
			{
				$("#list").empty();
				$("#pagination").empty();
				
				let list = resp.result.list;
				let currentPage = resp.result.paging.currentPage;
				let pageSize = resp.result.paging.pageSize;
							
				for(let i = 0; i<list.length; i++)
				{
					let item = list[i];
					let tr = $('<tr>');
											
					//tr.append(`<td>${item.id}</td>`);
					tr.append(`<td align="left">${item.rnum}</td>`);
					tr.append(`<td align="left">${item.categoryName}</td>`);
					tr.append(`<td align="left"><a href="${CONTEXT_PATH}/product/detail?id=${item.id}&pageSize=${pageSize}&currentPage=${currentPage}">${item.name}</a></td>`);				
					
					if(item.amount != 0)
						tr.append(`<td align="left">${item.fmtAmount}</td>`);
					else
						tr.append(`<td class="product-sold-out" align="left">${item.fmtAmount}</td>`);
					
					tr.append(`<td align="left">${item.fmtPrice}</td>`);
					tr.append(`<td align="left">${item.fmtDiscount}</td>`);
					tr.append(`<td align="left">${item.fmtDeliveryPrice}</td>`);
					
					if(item.cntReview && item.cntReview > 0)
					{
						tr.append(`<td align="left">${item.avgReviewScore.toFixed(2)}</td>`);
						tr.append(`<td align="left">${item.cntReview}</td>`);	
					}
					else
					{
						tr.append(`<td align="left">0</td>`);
						tr.append(`<td align="left">0</td>`);
					}
					
					
					
					tr.append(`<td align="left">${item.createDate}</td>`);
					
					$("#list").append(tr);
				}
				
				
				//페이지 순번 추가
				let pagination = resp.result.paging;		
							
				//이전
				let prevNode = null;
				if(pagination.startPage == 1)
				{
					prevNode = $(`<li class="page-item disabled"><span class="page-link">이전</span></li>`);
				}
				else
				{
					prevNode = $(`<li class="page-item"><span class="page-link">이전</span></li>`);
					prevNode.bind('click', function() {
						successSearchData(pagination.startPage - 1, pageSize, searchCategory, searchValue);
					});
				}
				$("#pagination").append(prevNode);
				
				//1 ~ 10	
				for(let i = pagination.startPage; i<=pagination.endPage; i++)
				{
					let node = null;
					
					if(i == currentPage)
					{
						node = $(`<li class="page-item active"><span class="page-link">${i}</span></li>`);	
					}
					else
					{
						node = $(`<li class="page-item"><span class="page-link">${i}</span></li>`);		
						node.bind('click', function() {
							successSearchData(i, pageSize, searchCategory, searchValue);
						});	
					}
					
					$("#pagination").append(node);
				}						
				
				//다음
				let nextNode = null;
				if(pagination.endPage < pagination.totalPage)
				{
					nextNode = $(`<li class="page-item"><span class="page-link">다음</span></li>`);
					nextNode.bind('click', function() {
						successSearchData(pagination.endPage + 1, pageSize, searchCategory, searchValue);
					});
				}
				else
				{
					nextNode = $(`<li class="page-item disabled"><span class="page-link">다음</span></li>`);
				}
				$("#pagination").append(nextNode);
								
				$("#currentPage").val(currentPage);
				$("#pageSize").val(pageSize);
			},
			function(err)
			{
				console.error(err);
			});		
	};//let successSearchData = function(nCurrentPage, nPageSize, searchCategory, searchValue) 
	
	
	
	successSearchData();
	
	$("#searchProduct").bind('click', function(){
		searVal = $("#searchValue").val();
		if(searVal)
		{
			searCat = $("#searchCategory").val();	
		}
		else
		{
			searCat = null;
			searVal = null;
		}
		
		
		successSearchData(1, PAGE_SIZE, searCat, searVal);
	});	
	
	
	$("#searchValue").bind('keyup', function(e)	{
		
		//Enter
		if(e.keyCode == 13)
		{
			$("#searchProduct").trigger('click');
		}
		
	});
	
		
});