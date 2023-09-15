function reviewInsert() {
	let userId = $('#userId').val();
	let productId = $('#productId').val();
	let orderId = $('#orderId').val();
	let score = $('#score').val();
	let contents = $('#contents').val();
	$.ajax({
		type: 'POST',
		url: './ReviewInsert',
		data: {
			userId: userId,
			productId: productId,
			orderId: orderId,
			score: score,
			contents: contents
		},
		success: res => {
			switch (res) {
			case '0':
				customAlert.show('리뷰등록에 성공했습니다.', null, 1, function(){
					location.href="/shoppingmall/product/payment/list";	
				});
				break;
			case '1':
				customAlert.show('글자수 부족.', '5글자 이상 입력하세요.', 1, function(){
				});
				break;
			case '2':
				customAlert.show('등록 실패.', '잠시후 다시 실행해주세요.', 1, function(){
				});
				break;
			}
		},
		error: e => console.log('요청 실패:', e.status)
	});
}















