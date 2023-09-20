
$(() => {	
	const ID = parseInt($("#id").val());
	const CONTEXT_PATH = $("#contextPath").val();
	const CURRENT_PAGE = $("#currentPage").val();
	const PAGE_SIZE = $("#pageSize").val();
/*	
	const alertTag = $(`
		<div class="alert alert-success alert-dismissible" role="alert">
			<i class="bi bi-check-circle-fill"></i>
			<span name="msg">
			</span>
			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>`);
*/
	$("#startChattingForm").bind('submit', function(){

		window.open('about:blank', '1:1 대화', "width=995,height=850,resizable=no");
		
		return true;
	});
	
	$("#amountMinus").bind('click', function() {
		let value = parseInt($("#amount").val()) - 1;
		if(value > 0)
			$("#amount").val(value);
	});
	
	$("#amountPlus").bind('click', function() {
		$("#amount").val(parseInt($("#amount").val()) + 1);
	});
	
	$("#cart").bind('click', function() {
		
		$("#cart").attr('disabled', true);
				
		let amount = parseInt($("#amount").val());
		
		let data = {
			amount: amount,
			productId: ID
		};
		
		Ajax(`${CONTEXT_PATH}/cart/insert`, "POST", JSON.stringify(data), 
			function(resp) 
			{
				if(resp.code == 0)
				{
					/*
					let node = alertTag.clone();					
					node.find('span[name=msg]').text('장바구니에 상품이 추가되었습니다.');					
					$("#alert").append(node);
					
					setTimeout(function() {
						$("#alert").empty();
					}, 5000);
					*/

					customAlert.show("장바구니에 상품이 추가되었습니다.", null, 1, null, null, true).close(3, true);
				}
				else
				{
					alert(resp.msg);
				}
				
				$("#cart").attr('disabled', false);
			}, 
			function(err)
			{
				console.error(err);
				$("#cart").attr('disabled', false);
			});
	});
	
	$("#buy").bind('click', function() {		
		let amount = parseInt($("#amount").val());
		
		console.log(ID);
		console.log(amount);	
	});
	
	$("#return").bind('click', function() {
		location.href= `${CONTEXT_PATH}/product/list?currentPage=${CURRENT_PAGE}&pageSize=${PAGE_SIZE}`;		
	});
	
	
	
	
	let reviewNowPage = 1;
	const reviewContents = $(`
		<div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
			<div class="col p-4 d-flex flex-column position-static">
				<strong class="d-inline-block mb-2 text-primary" name="reviewId">아이디</strong>
				<h3 class="mb-0 text-warning" name="reviewScore"></h3>
				<div class="mb-1 text-muted" name="reviewCreateDate">리뷰 작성일</div>
				<p class="card-text mb-auto" name="reviewContents">리뷰 내용</p>
			</div>
			<div class="col-auto d-none d-lg-block opacity-0">
				<svg class="bd-placeholder-img" width="200" height="250" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
					<title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect>
					<text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
			</div>
		</div>
	`);
	let reviewMain = $("#reviewList");
	
	
	const loadReviewList = function(currPage, pId)
	{
		const reviewData = {
			'currentPage': currPage, 
			'id': pId
		};
		
		
		Ajax(`${CONTEXT_PATH}/product/review/list`, "POST", JSON.stringify(reviewData), 
			function(resp) 
			{
				if(resp.code == 0)
				{
					let list = resp.list;
					for (let i = 0; i<list.length; i++)
					{
						let item = list[i];
						let reviewNode = reviewContents.clone();
						let cntStar = item.score;
						let cntEmptyStar = 5 - cntStar;
						let starNode = $('<span></span>');
												
						for (let i = 0; i<cntStar; i++)
						{
							starNode.append(`<i class="bi bi-star-fill"></i>`);
						}
						for (let i = 0; i<cntEmptyStar; i++)
						{
							starNode.append(`<i class="bi bi-star"></i>`);
						}

						reviewNode.find('strong[name=reviewId]').text(item.userId);
						reviewNode.find('h3[name=reviewScore]').append(starNode);
						reviewNode.find('div[name=reviewCreateDate]').text(item.fmtCreateDate);
						reviewNode.find('p[name=reviewContents]').text(item.contents);
						
					
						reviewMain.append(reviewNode);
					}
					
					if(currPage == resp.paging.totalPage)
					{
						$("#reviewMoreList").addClass('invisible');
					}
				}
				else
				{
					alert(resp.msg);
				}
			}, 
			function(err)
			{
				console.error(err);
			});
	};
	
	loadReviewList(reviewNowPage, ID);

	$("#reviewMoreList").bind('click', function() {
		reviewNowPage = reviewNowPage + 1;
		loadReviewList(reviewNowPage, ID);		
	});
	
	$("#showReview").bind('click', function() {
		let nTop = $("#reviewList").offset().top - $("header").height() - $("div[name=productDetailMenu]").height(); 
		
		$(document).scrollTop(nTop);
	});
	
});


