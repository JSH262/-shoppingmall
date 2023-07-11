
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
						
				//할인된 금액
				let totalDiscountedPrice = 0;
				let totalPrice = 0;
				let totalDeliveryPrice = 0;
								
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
					
					
					totalPrice += parseInt(item.amount) * parseInt(item.price);
					totalDiscountedPrice += parseInt(item.amount) * parseInt(item.discountPrice);
					totalDeliveryPrice += parseInt(item.deliveryPrice);
				}
				
				$("#totalPrice").text(totalDiscountedPrice + totalDeliveryPrice);
				$("#totalDiscountPrice").text(totalPrice - totalDiscountedPrice);
				$("#totalDeliveryPrice").text(totalDeliveryPrice);
				
				
			},
			function(err)
			{
				console.error(err);
			});		
	};//let successSearchData = function() 
	
	
	successSearchData();
});


