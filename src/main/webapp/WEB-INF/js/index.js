$(() => {
	const CONTEXT_PATH = getContextPath();
	
	let newProductNode = $(`
		<div class="col" name="newProductItem">
			<div class="card">
				<div class="invisible" name="overlayContents">
					<div class="spinner-border" style="width: 5rem; height: 5rem;" role="status">
					  	<span class="visually-hidden">Loading...</span>
					</div>
				</div>
				<div name="card-body">
					<img class="card-img-top newProductImageDuration" src="">
					<div class="card-body">
						<h5 class="card-title"></h5>
						<p class="card-text fs-4"></p>
					</div>
				</div>
			</div>
		</div>
	`);
	
	//#carousel-indicator 안에
	//첫 번째 노드, 두 번째 노드부터 class와 aria-current 속성 제거, data-bs-slide-to="0"와 aria-label="Slide 1"의 값에 규칙이 있다.
	let ciNode = $(`<button type="button" data-bs-target="#carouselLotSell" data-bs-slide-to="0" aria-label="Slide"></button>`);
	
	//#carousel-body 안에
	//첫 번째 노드, 두 번째 노드부터 class의 active 제거
	let cbNode = $(`<div class="carousel-item" data-bs-interval="3000">
						<img class="ratio ratio-21x9 mx-auto d-block" src="" class="d-block w-100">
					</div>`);
	
	
	let createNewProductList = function(list) {
		for(let i = 0; i<list.length; i++)
		{
			let indicatorTmp = ciNode.clone();
			let bodyTmp = cbNode.clone();
			let item = list[i];		
			
			if(i == 0)
			{
				indicatorTmp.addClass('active');
				indicatorTmp.attr('aria-current', "true");
				bodyTmp.addClass('active');
			}		
			
			indicatorTmp.attr("data-bs-slide-to", i);
			indicatorTmp.attr("aria-label", "Slide " + (i + 1));
			
			bodyTmp.find('img').attr('src', `${CONTEXT_PATH}/image/${item.thumbnail}`);
			bodyTmp.bind('click', function() {
				location.href= `${CONTEXT_PATH}/product/detail?id=${item.id}`;
			});
			
			$("#carousel-body").append(bodyTmp);
			$("#carousel-indicator").append(indicatorTmp);
		}
	}
	
	
	Ajax(`${CONTEXT_PATH}/index`, "POST", null, 
			function(resp)
			{
				if(resp.code == 0)
				{					
					let sellProductList = resp.result.sellList;
					let newProductList = resp.result.newList;
					let rndList = resp.result.rndList;

					$("div[name=carousel-loading]").remove();
					
					if(sellProductList.length > 0)
					{
						createNewProductList(sellProductList);
					}
					else
					{
						$("#carouselLotSellTitle").text("오늘의 상품");
						createNewProductList(rndList);
					}
				
					
					$("div[name=newProductLoading]").remove();
					if(newProductList.length > 0)
					{
						for(let i = 0; i<newProductList.length; i++)
						{
							let item = newProductList[i];
							let npTmp = newProductNode.clone();
							let discountPrice = item.price - (item.discount / 100 * item.price);
							
							//id, name, discount, price, thumbnail
							npTmp.find('img').attr('src', `${CONTEXT_PATH}/image/${item.thumbnail}`);
							npTmp.find('.card-title').text(item.name);
							npTmp.find('.card-text').html(`<span class="fs-6 text-danger">${item.discount}% </span>${discountPrice.toLocaleString()}<span class="fs-6">원</span>`);
							npTmp.bind('click', function() 
							{
								$(this).find('div[name=overlayContents]').removeClass('invisible');
								$(this).find('div[name=card-body]').addClass('opacity-25');
								location.href= `${CONTEXT_PATH}/product/detail?id=${item.id}`;
							});
							
							$("#newProductsBody").append(npTmp);
						}
					}
					else
					{
						$("#newProductGup").addClass("invisible");
					}
					
				}
				else
					alert(resp.code + ": " + resp.msg);
			},
			function(err)
			{
				console.error(err);
			}
		);
	
	
});