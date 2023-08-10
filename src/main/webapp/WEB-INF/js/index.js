$(() => {
	const CONTEXT_PATH = getContextPath();
	
	let newProductNode = $(`
		<div class="col-4" name="newProductItem">
			<div class="card">
				<img class="card-img-top newProductImageDuration" src="">
				<div class="card-body">
					<h5 class="card-title"></h5>
					<p class="card-text"></p>
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
	
	/*
	//이미지 확대를 위한 이벤트
	let npImg = newProductNode.find('.card > img');
	let npBody = newProductNode;
	npBody.bind('mouseover', function() {
		npImg.addClass('newProductImageDuration-hover newProductShadow ');
		npBody.addClass('newProductCursor');
	});
	npBody.bind('mouseleave', function() {
		npImg.removeClass('newProductImageDuration-hover newProductShadow');
		npBody.removeClass('newProductCursor');
	});
	npBody.bind('click', function() {
		alert('이동');
	});
	
	//이미지
	npImg.attr('src', `${CONTEXT_PATH}/image/11ee340585123d80b956651ffe5e5aa3`);
	
	//제목
	newProductNode.find('.card-title').text('Card title');
	
	//내용
	newProductNode.find('.card-text').text(`Some quick example text to build on the card title and make up the bulk of the card's content.`);
	
	$("#newProductsBody div:eq(0)").append(newProductNode);
	//*/
	
	Ajax(`${CONTEXT_PATH}/index`, "POST", null, 
			function(resp)
			{
				if(resp.code == 0)
				{
					let sellProductList = resp.result.sellList;
					let newProductList = resp.result.newList;

					for(let i = 0; i<sellProductList.length; i++)
					{
						let indicatorTmp = ciNode.clone();
						let bodyTmp = cbNode.clone();
						let item = sellProductList[i];		
						
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
							alert(item[i].id);
						});
						
						$("#carousel-body").append(bodyTmp);
						$("#carousel-indicator").append(indicatorTmp);
					}
					
					let cnt = 0;
					let npTmpRow = $(`<div class="row">`).clone();
					for(let i = 0; i<newProductList.length; i++)
					{
						let item = newProductList[i];
						let npTmp = newProductNode.clone();
							
						//id, name, discount, price, thumbnail
						npTmp.find('img').attr('src', `${CONTEXT_PATH}/image/${item.thumbnail}`);
						npTmp.find('.card-title').text(item.name);
						npTmp.find('.card-text').html(`<span>${item.discount}</span> ${item.price}`);
						npTmp.bind('click', function() {
							alert(item.id);
						});
						
						npTmpRow.append(npTmp);
						$("#newProductsBody").append(npTmpRow);

						cnt += 1;
						if(cnt == 3)
						{
							npTmpRow = $(`<div class="row">`).clone();
							cnt = 0;
						}
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