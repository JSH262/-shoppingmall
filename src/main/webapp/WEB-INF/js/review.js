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
			/*
			switch (res) {
				case '1':
					$('#messageType').html('성공 메시지');
					$('#messageContent').html('리뷰등록에 성공했습니다.');
					$('#errorMessage').html('리뷰등록에 성공했습니다.');
					$('#messageCheck').attr('class', 'modal-content panel-success');
					location.href="/shoppingmall/reviewList.do?userId=" + encodeURIComponent(userId);
					break;
				case '2':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('글자수 부족.');
					$('#errorMessage').html('에러: 5글자 이상 입력하세요.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
				case '3':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('등록 실패.');
					$('#errorMessage').html('에러: 잠시후 다시 실행해주세요.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
			}
			*/
		},
		error: e => console.log('요청 실패:', e.status)
	});
}















