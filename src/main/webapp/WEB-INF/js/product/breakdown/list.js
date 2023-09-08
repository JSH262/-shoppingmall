
$(() => {
	const CONTEXT_PATH = $("#contextPath").val();
	let productBody =$("tbody[name=productBody]");
	let productItem = $(`
		<tr name="productItem">
     		<th scope="row">1</th>
     		<td>
     			<img class="img-thumbnail" src="" >
     		</td>
     		<td>
     			이태리 직수입 파시미나 머플러(MADE IN ITALY)
			</td>
     		<td>23/07/23</td>			     		
     		<td>2</td>
     		<td>8</td>
     		<td>서울시 종로구 종로 123길</td>			     		
     		<td>123층 1231호</td>
     		<td>000-0000-0000</td>
     		<td>배송시 연락을 주세요</td>
     		<td>배송중</td>
     		<td>
     			<select>
     			</select>
     			<input type="button" value="변경" />
			</td>
   		</tr>
	`);

	const NORMAL_STATUS_WEIGHT = 0;
	const NORMAL_STATUS = [
		"",
		"결재완료",
		"상품준비",
		"배송중",
		"배송완료",
		"거래완료"];

	const RETURNS_STATUS_WEIGHT = 100;
	const RETURNS_STATUS = [ 
		"",
		"상품반품",
		"회수중",
		"회수완료",
		"상품확인",
		"반품완료",
		"환불완료"	];	
	
	const EXCNAGE_STATUS_WEIGHT= 200;
	const EXCNAGE_STATUS = [
		"",
		"상품교환",
		"회수중",
		"회수완료",
		"상품확인중",
		"상품준비",
		"배송중",
		"배송완료",
		"거래완료"	];






	let createList = function(currentPage, pageSize) {
		
		let data = {
			currentPage: currentPage,
			pageSize: pageSize
		};
		
		Ajax(`${CONTEXT_PATH}/product/breakdown/list`, "POST", JSON.stringify(data), 
			function(resp)
			{				
				if(resp.code == 0)
				{	
					productBody.empty();
					
					let pagination = resp.result.paging;
					let list = resp.result.list;
					for(let i = 0; i<list.length; i++)
					{
						let node = productItem.clone();
						let item = list[i];
						
						node.find("th:eq(0)").text(item.rnum);
						node.find("td:eq(0)").find("img").attr("src", `${CONTEXT_PATH}/image/${item.productThumbnail}`);
						node.find("td:eq(1)").text(item.productName);
						node.find("td:eq(2)").text(item.createDate);
						node.find("td:eq(3)").text(item.productAmount);
						node.find("td:eq(4)").text(item.originalProductAmount);
						node.find("td:eq(5)").text(item.deliveryAddr1);
						node.find("td:eq(6)").text(item.deliveryAddr2);
						node.find("td:eq(7)").text(item.deliveryPhone);
						node.find("td:eq(8)").text(item.deliveryRequestMsg);		
								
						if(item.status >= 1 && item.status <= 5)
						{		
							let statusName = null;
							if(item.status < 0)
							{
								switch(item.status)
								{
								case -1:
									statusName = "주문취소";
									break;
								}
							}
							else
							{
								statusName = NORMAL_STATUS[item.status];
							}
							
							node.find("td:eq(9)").text(statusName);
							
							let selectNode = node.find("td:eq(10)").find('select');
							let selectedBtnNode = node.find("td:eq(10)").find('input');
							if(item.status >= 1 && item.status <= 2)
							{
								for(let z = item.status; z<4; z++)
								{
									selectNode.append(`<option value="${z}">${NORMAL_STATUS[z]}</option>`);	
								}
								
								let isSelectedChangeOnce = false;
								node.find("td:eq(10)").find('select').val(item.status).prop('selected', true);
								
								
								selectedBtnNode.bind('click', function() 
								{
									let selectedValue = parseInt($(this).parent().find('select option:selected').val());							
									if(item.status != selectedValue && selectNode.find('option').length != 1)
									{
										if(isSelectedChangeOnce == false)
										{
											isSelectedChangeOnce = true;
											let statusData = {
												userId: item.userId,
												id: item.id,
												productId: item.productId, 
												status: selectedValue
											};
										
											Ajax(`${CONTEXT_PATH}/product/breakdown/modify`, "POST", JSON.stringify(statusData), 
												function(resp)
												{	
													if(resp.code == 0)
													{
														let selectedWehgit = 1;
														
														//ajax 통신이 성공하면 선택한 상태로 변경한다.		
														node.find("td:eq(9)").text(NORMAL_STATUS[selectedValue]);
																						
														selectNode.find('option').remove();
									
														// 상태 목록에서 배송완료, 거래완료를 제외시킨다.
														if(selectedValue == 1 || selectedValue == 2)
														{
															selectedWehgit = 2;
														}
														
														
														for(let z = selectedValue; z<NORMAL_STATUS.length - selectedWehgit; z++)
														{
															selectNode.append(`<option value="${z}">${NORMAL_STATUS[z]}</option>`);	
														}
																												
														//배송중 이후의 값에 대해서 배경을 기본색으로 변경한다.
														if(selectedValue >= 3 && selectedValue <= 5)
														{
															node.removeClass("bg-success bg-gradient text-white");
														}
														
														if(selectedValue == 3)
														{
															selectNode.attr("disabled", true);
															selectNode.addClass("invisible");
															selectedBtnNode.attr("disabled", true);
															selectedBtnNode.addClass("invisible");
														}
													}
												},
												function(err)
												{
													console.error(err);		
												},
												function()
												{
													isSelectedChangeOnce = false;
												}
											);
										}	
										
									} 
								});
							}
							else
							{
								
								selectNode.attr("disabled", true).addClass("invisible");
								selectedBtnNode.attr("disabled", true).addClass("invisible");
							}
							
							//상품을 배송해야할 때는 배경색을 초록색으로 변경한다.
							if(item.status >= 1 && item.status <= 2)
							{
								node.addClass("bg-success bg-gradient text-white");
							}
						}
							
						
						productBody.append(node);
					}
					
					
					$("#pagination").empty();
					
					//처음으로
					let startNode = null;
					if(pagination.startPage == 1)
					{
						startNode = $(`<li class="page-item disabled"><span class="page-link">처음으로</span></li>`);
					}
					else
					{
						startNode = $(`<li class="page-item"><span class="page-link">처음으로</span></li>`);
						startNode.bind('click', function() {							
							//successSearchData(pagination.startPage - 1, pageSize, $("#searchCategory").val(), $("#searchValue").val());
							createList(1, pageSize);
						});
					}	
								
					$("#pagination").append(startNode);
								
								
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
							
							//successSearchData(pagination.startPage - 1, pageSize, $("#searchCategory").val(), $("#searchValue").val());
							createList(pagination.startPage - 1, pageSize);
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
								//successSearchData(i, pageSize, $("#searchCategory").val(), $("#searchValue").val());
								createList(i, pageSize);
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
							//successSearchData(pagination.endPage + 1, pageSize, $("#searchCategory").val(), $("#searchValue").val());
							createList(pagination.endPage + 1, pageSize)
						});
					}
					else
					{
						nextNode = $(`<li class="page-item disabled"><span class="page-link">다음</span></li>`);
					}
					
					$("#pagination").append(nextNode);
					
					
					//끝으로
					let endNode = null;
					if(currentPage != pagination.totalPage)
					{
						endNode = $(`<li class="page-item"><span class="page-link">끝으로</span></li>`);
						endNode.bind('click', function() {
							//successSearchData(pagination.endPage + 1, pageSize, $("#searchCategory").val(), $("#searchValue").val());
							createList(pagination.totalPage, pageSize)
						});
					}
					else
					{
						endNode = $(`<li class="page-item disabled"><span class="page-link">끝으로</span></li>`);
					}
					
					$("#pagination").append(endNode);
					
				}
				else
					alert(resp.msg);
			},
			function(err)
			{
				console.error(err);
			}
		);
	};
	
	createList(1, 15);
	
});