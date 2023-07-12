
$(() => {
	const CONTEXT_PATH = $("#contextPath").val();
	const productNode = $("div[name=product]").clone();
	$("div[name=product]").remove();
	
	
	let successSearchData = function() 
	{
		let data = {
			productId:[
				1,2,160
			]
		};

		Ajax(`${CONTEXT_PATH}/product/order`, "POST", JSON.stringify(data), 
			function(resp)
			{
				$("#pagination").empty();
				$("#list").empty();
				let list = resp.result.list;
			
				for(let i = 0; i<list.length; i++)
				{
					let productItem = productNode.clone();
					let item = list[i];
					
					if(item.amount == 0)
					{
						productItem.addClass('product-sold-out');	
					}
																
					const productDetailMove = function()
					{
						location.href = `${CONTEXT_PATH}/product/detail.jsp?id=${item.id}`;
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
					productItem.find('span[name=amount]').text(item.fmtAmount);
					
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
				
				$("#totalPrice").text(resp.result.fmtResultPrice); // 할인전 가격
				$("#totalDiscount").text(resp.result.fmtResultDiscount); // 할인된 가격
				$("#totalDeliveryPrice").text(resp.result.fmtResultDevliveryPrice); //총 배송비
				$("#totalDiscountPrice").text(resp.result.fmtResultDiscountPrice); //할인한 가격
				
				
			},
			function(err)
			{
				console.error(err);
			});		
	};//let successSearchData = function() 
	
	
	successSearchData();
});


