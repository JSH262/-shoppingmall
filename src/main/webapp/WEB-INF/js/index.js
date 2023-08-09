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

	//*
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
	
});