
$(() => {
	try
	{
		const CONTEXT_PATH = $("#contextPath").val();
		const CURRENT_PAGE =  parseInt($("#currentPage").val());
		const PAGE_SIZE = parseInt($("#pageSize").val());
		let searCat = $("#searchCategory").val(); 
		let searVal = $("#searchValue").val();
		
		
		const productNode = $("div[name=product]").clone();
		$("div[name=product]").remove();
		
		
		let successSearchData = function(nCurrentPage, nPageSize, searchCategory, searchValue) 
		{
			if(!nCurrentPage)
				nCurrentPage = CURRENT_PAGE;
	
			if(!nPageSize)
				nPageSize = PAGE_SIZE;
			
			let data = {
				searchCategory: searchCategory,
				searchValue: searchValue,
				currentPage: nCurrentPage,
				pageSize: nPageSize	 
			};
	
			Ajax(`${CONTEXT_PATH}/product/list`, "POST", JSON.stringify(data), 
				function(resp)
				{
					$("#pagination").empty();
					$("#list").empty();
					let list = resp.result.list;
					let currentPage = resp.result.paging.currentPage;
					let pageSize = resp.result.paging.pageSize;
				
					/*
					{
					    "rnum": 1,
					    "id": 821,
					    "categoryId": 2,
					    "name": "ㅁㄴㅇㄻㄴㅇㄹ",
					    "amount": 345,
					    "price": 234,
					    "discount": 33,
					    "deliveryPrice": 123123,
					    "thumbnail": "11ee1b13b6095f7b919ee32b767cd56b",
					    "createDate": "2023-07-05(수) 18:04:10 ",
					    "fmtAmount": "345개",
					    "fmtPrice": "234원",
					    "fmtDiscount": "33%",
					    "fmtDeliveryPrice": "123,123원",
					    "fmtDiscountPrice": "157원",
					    "companyName": "LG 대행판매업체"
					}
					*/
									
					for(let i = 0; i<list.length; i++)
					{
						let productItem = productNode.clone();
						let item = list[i];					
						const productDetailMove = function()
						{
							location.href = `${CONTEXT_PATH}/product/detail.jsp?id=${item.id}&currentPage=${currentPage}&pageSize=${pageSize}`;
						}
						
						if(item.thumbnail)
						{
							productItem.find('img[name=thumnail]').attr('src', `${CONTEXT_PATH}/image/${item.thumbnail}`);	
						}
						else
						{
							productItem.find('img[name=thumnail]').attr('src', `${CONTEXT_PATH}/resources/default/noimg.png`);
						}
						
						
						productItem.find('img[name=thumnail]').bind('click', productDetailMove);
						
						productItem.find('div[name=name]').text(item.name);
						productItem.find('div[name=name]').bind('click', productDetailMove);
						
						productItem.find('div[name=discountPrice]').text(item.fmtDiscountPrice);
						productItem.find('span[name=price]').text(item.fmtPrice);
						productItem.find('span[name=discount]').text(item.fmtDiscount);
						
						if(item.deliveryPrice == 0)
						{
							productItem.find('span[name=delivery]').text('');	
						}
						
						productItem.find('span[name=deliveryPrice]').text(item.fmtDeliveryPrice);
						
						if(item.companyName)
							productItem.find('div[name=companyName]').text(item.companyName);
						else
							productItem.find('div[name=companyName]').text('');
						
						$("#list").append(productItem);
					}
							
					//페이지 순번 추가
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
							//location.href = `${CONTEXT_PATH}/product/list.jsp?currentPage=${pagination.startPage - 1}&pageSize=${pageSize}`;
							//, searchCategory, searchValue
							
							successSearchData(pagination.startPage - 1, pageSize, searCat, searVal);
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
								//location.href = `${CONTEXT_PATH}/product/list.jsp?currentPage=${i}&pageSize=${pageSize}`;							
								successSearchData(i, pageSize, searCat, searVal);
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
							//location.href = `${CONTEXT_PATH}/product/list.jsp?currentPage=${pagination.endPage + 1}&pageSize=${pageSize}`;
							successSearchData(pagination.endPage + 1, pageSize, searCat, searVal);
						});
					}
					else
					{
						nextNode = $(`<span class="page-number page-number-current">다음</span>`);
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
		
		
		successSearchData(CURRENT_PAGE, PAGE_SIZE, null, null);
		
		$("#searchProduct").bind('click', function() {
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
				$("#search").trigger('click');
			}
			
		});
		
		
		
	}
	catch(exp)
	{
		console.error(exp);
	}
});




