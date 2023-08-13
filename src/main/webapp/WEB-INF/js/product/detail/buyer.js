
$(() => {	
	const ID = parseInt($("#id").val());
	const CONTEXT_PATH = $("#contextPath").val();
	const CURRENT_PAGE = $("#currentPage").val();
	const PAGE_SIZE = $("#pageSize").val();
	
	const alertTag = $(`
		<div class="alert alert-success alert-dismissible" role="alert">
			<i class="bi bi-check-circle-fill"></i>
			<span name="msg">
				sadfasdfsadf
			</span>
			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>`);
	
	
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
					let node = alertTag.clone();					
					node.find('span[name=msg]').text('장바구니에 상품이 추가되었습니다.');					
					$("#alert").append(node);
					
					setTimeout(function() {
						$("#alert").empty();
					}, 5000);
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
	
	$("#btnStartChatting").bind('click', () => {
		window.open(`${CONTEXT_PATH}/chatting?productId=${ID}&sellerId=${$('#sellerId').val()}`, '채팅', "width=995,height=850,resizable=no");
	});		

});


