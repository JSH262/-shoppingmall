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
						<h6 name="review" class="card-subtitle mb-2 text-muted">
						</h6>
						<p name="price" class="card-text fs-4"></p>
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
	
	
	let createSellProductList = function(list) {
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
	
	let createNewProductList = function(list) {
		if(list && list.length > 0)
		{
			for(let i = 0; i<list.length; i++)
			{
				let item = list[i];
				let npTmp = newProductNode.clone();
				let discountPrice = item.price - (item.discount / 100 * item.price);
								
				//avgReviewScore
				//cntReview
				if(item.cntReview && item.cntReview > 0)
				{
					let reviewNode = npTmp.find("h6[name=review]");
					const cntMaxStar = 5;
					let cntStarFill = parseInt(item.avgReviewScore);
					let cntStarHalf = cntMaxStar - item.avgReviewScore > 0 ? 1: 0;
					let cntStarEmpty = 5 - cntStarFill - cntStarHalf
					
					for(let i = 0; i<cntStarFill; i++)
					{
						reviewNode.append(`<i class="bi bi-star-fill text-warning"></i>`);
					}
					
					for(let i = 0; i<cntStarHalf; i++)
					{
						reviewNode.append(`<i class="bi bi-star-half text-warning"></i>`);
					}
					
					for(let i = 0; i<cntStarEmpty; i++)
					{
						reviewNode.append(`<i class="bi bi-star text-warning"></i>`);
					}
					
					reviewNode.append(`<span class="fs-6"> (${item.cntReview})</span>`);
				}
				
				//id, name, discount, price, thumbnail
				npTmp.find('img').attr('src', `${CONTEXT_PATH}/image/${item.thumbnail}`);
				npTmp.find('.card-title').text(item.name);
				npTmp.find('p[name=price]').html(`<span class="fs-6 text-danger">${item.discount}% </span>${discountPrice.toLocaleString()}<span class="fs-6">원</span>`);
				npTmp.bind('click', function() 
				{
					let parent = $(this);
					$(this).find('div[name=overlayContents]').removeClass('invisible');
					$(this).find('div[name=card-body]').addClass('opacity-25');
					
					window.addEventListener('beforeunload', function(event) {
						
						// 명세에 따라 preventDefault는 호출해야하며, 기본 동작을 방지합니다.
						event.preventDefault();
						
						parent.find('div[name=overlayContents]').addClass('invisible');
						parent.find('div[name=card-body]').removeClass('opacity-25');
					});
					
					location.href= `${CONTEXT_PATH}/product/detail?id=${item.id}`;
				});
				
				$("#newProductsBody").append(npTmp);
			}
		}
	};
	
	
	Ajax(`${CONTEXT_PATH}/index`, "POST", null, 
			function(resp)
			{
				if(resp.code == 0)
				{					
					let sellProductList = resp.result.sellList;
					let newProductList = resp.result.newList;
					let rndList = resp.result.rndList;
					let rndList2 = resp.result.rndList2;

					$("div[name=carousel-loading]").remove();
					
					if(sellProductList && sellProductList.length > 0)
					{
						createSellProductList(sellProductList);
					}
					else
					{
						$("#carouselLotSellTitle").text("오늘의 상품");
						createSellProductList(rndList);
					}
				
					
					
					$("div[name=newProductLoading]").remove();
					if(newProductList && newProductList.length > 0)
					{
						createNewProductList(newProductList)
						
						/*
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
								let parent = $(this);
								$(this).find('div[name=overlayContents]').removeClass('invisible');
								$(this).find('div[name=card-body]').addClass('opacity-25');
								
								window.addEventListener('beforeunload', function(event) {
									
									// 명세에 따라 preventDefault는 호출해야하며, 기본 동작을 방지합니다.
									event.preventDefault();
									
									parent.find('div[name=overlayContents]').addClass('invisible');
									parent.find('div[name=card-body]').removeClass('opacity-25');
								});
								
								location.href= `${CONTEXT_PATH}/product/detail?id=${item.id}`;
							});
							
							$("#newProductsBody").append(npTmp);
						}
						//*/
					}
					else
					{
						createNewProductList(rndList2)
						$("#newProductTitle").text('오늘의 상품');
						//$("#newProductGup").addClass("invisible");
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
	
	
	$("#testCustom1").bind('click', function(){
		customAlert.show("제목 1", "내용 1", 1, function(){
			alert('ok 1');
		},
		function(){
			alert('cancel 1');
		}, "확인 1", "취소 1");
	});
	$("#testCustom2").bind('click', function(){
		customAlert.show("제목 2", "내용 2", 2, function(){
			alert('ok 2');
		},
		function(){
			alert('cancel 2');
		}, "확인 2", "취소 2");
	});
	
});