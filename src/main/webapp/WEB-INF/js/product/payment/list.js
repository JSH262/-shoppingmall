

$(() => {

	const CONTEXT_PATH = $("#contextPath").val();
	let itemNode = $(`
		<tr name="tableNode" align="center">
			<td>					
				<span name="orderDate"></span><span>(<span name="id"></span>)</span>						
			</td>
			<td>
				<div class="container">
					<div class="row">
						<div class="col">
							<div name="thumbnail"></div>		
						</div>
						<div class="col">
							<div name="productName"></div>
						</div>
					</div>
				</div>
			</td>
			<td name="totalProductPrice"></td>
			<td name="productAmount"></td>
			<td name="productPrice"></td>
			<td name="productDelivery"></td>
			<td>
				<div name="status">
				</div>
				<div>
					<input type="button" name="orderCancel" value="주문취소" />
				</div>
				<div>
					<input type="button" name="productExchange" value="상품교환" />
				</div>
				<div>
					<input type="button" name="productReturns" value="상품반품" />
				</div>
				<div>
					<input type="button" name="complete" value="구매확정" />
				</div>
				<div>
					<input type="button" name="review" value="리뷰작성"/>
				</div>							
			</td>
		</tr>
	`);

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
				let img = $("<img class='img-thumbnail w-75'>");
				
				img.attr('src', `${CONTEXT_PATH}/image/${item.productThumbnail}`);
				node.find('span[name=orderDate]').text(item.createDate);
				node.find('span[name=id]').text(item.id);
				node.find('div[name=thumbnail]').append(img);
				node.find('div[name=productName]').text(item.productName);
				node.find('td[name=productAmount]').text(item.fmtProductAmount);
				node.find('td[name=productPrice]').text(item.fmtProductPrice);
				node.find('td[name=productDelivery]').text(item.fmtProductDeliveryPrice);				
				node.find('td[name=totalProductPrice]').text(item.fmtTotalProductPrice);
					
				let btnOrderCancel = node.find('input[name=orderCancel]');
				let btnProductExchange = node.find('input[name=productExchange]');
				let btnProductReturns = node.find('input[name=productReturns]');
				let btnComplete= node.find('input[name=complete]');
				let btnReview = node.find('input[name=review]');
				
				
				let productDeliveryComplete = function()
				{
					//리뷰 작성가능
					btnOrderCancel.addClass("invisible");					
					btnProductExchange.addClass("invisible");
					btnProductReturns.addClass("invisible");
					btnComplete.addClass("invisible");
					btnReview.bind('click', function () {
						console.log(item.productId);
                        const reviewUrl = `${CONTEXT_PATH}/review?productId=${item.productId}&id=${item.id}`;
                        window.location.href = reviewUrl;
                    });
				};
				
				let setStatusName = function(tmpStatusName) {
					let statusName = "?";
					
					if(tmpStatusName)
					{
						statusName = tmpStatusName
					}
					else
					{
						switch(item.status)
						{
						case 1:
							statusName = "결제완료";
							break;
							
						case 2:
							statusName = "상품준비";
							break;
							
						case 3:
							statusName = "배송중";
							break;
							
						case 4:
							statusName = "배송완료";
							break;
							
						case 5:
							statusName = "거래완료";
							break;
							
						case -1:
							statusName = "주문취소";
							break;
						}
					}
					
					node.find('div[name=status]').text(statusName);
				};
				
				if(item.status >= 1 && item.status <= 5)
				{
					let isOnce = false;
					// 거래완료
					if(item.status == 5)
					{
						productDeliveryComplete();
					}		
					
					//결제완료, 상품준비
					else if(item.status <= 2)
					{
						btnOrderCancel.bind('click', function()
						{
							if(isOnce == false)
							{
								isOnce = true;
								let pbmData = {
									id: item.id,
									productId: item.productId,
									status: -1
								};
								
								Ajax(`${CONTEXT_PATH}/product/breakdown/modify`, "POST", JSON.stringify(pbmData), 
									function(resp)
									{
										if(resp.code == 0)
										{
											setStatusName("결제취소");
											alert('주문한 상품 결제취소 완료');
										}
										else
										{
											alert(resp.code + ": " + resp.msg);
										}
									},
									function(err)
									{
										console.error(err);
									}, 
									function()
									{
										isOnce = false;
									});
								
							}
							
						});
						
						btnProductExchange.addClass("invisible");
						btnProductReturns.addClass("invisible");
						btnComplete.addClass("invisible");
						btnReview.addClass("invisible");
					} 
					
					//배송중, 배송완료
					else if(item.status >= 3)
					{
						btnReview.removeClass("invisible");
						btnOrderCancel.addClass("invisible");					
						btnProductExchange.bind('click', function()
						{
							alert('상품교환하기');
						});
						btnProductReturns.bind('click', function()
						{
							alert('상품반품하기');
						});
						btnComplete.bind('click', function()
						{
							
							if(isOnce == false)
							{
								isOnce = true;
								let pbmData = {
									id: item.id,
									productId: item.productId,
									status: 5
								};
								
								Ajax(`${CONTEXT_PATH}/product/breakdown/modify`, "POST", JSON.stringify(pbmData), 
									function(resp)
									{
										if(resp.code == 0)
										{
											productDeliveryComplete();
											setStatusName("거래완료");
											alert('구매결정 완료');
										}
										else
										{
											alert(resp.code + ": " + resp.msg);
										}
									},
									function(err)
									{
										console.error(err);
									}, 
									function()
									{
										isOnce = false;
									});
							}
							
						});
					}
				}
				else
				{
					btnOrderCancel.addClass("invisible");					
					btnProductExchange.addClass("invisible");
					btnProductReturns.addClass("invisible");
					btnComplete.addClass("invisible");
					btnReview.addClass("invisible");
				}
				
				setStatusName();
								
				$("#list").append(node);
			}
			
		},
		function(err)
		{
			console.error(err);
		}
	);	

	
});