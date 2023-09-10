function updateAmount(button) {
	var parentSpan = $(button).closest('.input-group');
    var amount = parentSpan.find('input[name=amount]').val();
    var productId = parentSpan.find('input[name=productId]').val();
    var userId = $('#userId').val();
    
    $.ajax({
        type: 'POST',
        url: './updateAmount',
        data: {
            userId: userId,
            amount: amount,
            productId: productId
        },
        success: function(res) {
            switch (res) {
                case '0':
                    $('#messageType').html('성공 메시지');
                    $('#messageContent').html('수량변경 성공.');
                    $('#errorMessage').html('변경된 수량이 적용되었습니다.');
                    $('#messageCheck').attr('class', 'modal-content panel-success');
                    break;
                case '1':
                    $('#messageType').html('에러 메시지');
                    $('#messageContent').html('수량적용 실패.');
                    $('#errorMessage').html('변경된 수량이 적용되지 않았습니다. 잠시 후 다시 시도해 주십시오.');
                    $('#messageCheck').attr('class', 'modal-content panel-warning');
                    break;
            }
            $('#messageModal').modal('show');
        },
        error: e => console.log('요청 실패:', e.status)
    });
}

function deleteProduct(event) {
	var userId = $('#userId').val();
	var productId = $('#productId').val();
	$.ajax({
		type: 'POST',
		url: './deleteProduct',
		data: {
			userId: userId,
			productId: productId
		},
		success: function(res) {
			switch (res) {
			case '0':
				$('#messageType').html('성공 메시지');
				$('#messageContent').html('삭제 성공.');
				$('#errorMessage').html('삭제 되었습니다.');
				$('#messageCheck').attr('class', 'modal-content panel-success');
				break;
			case '1':
				$('#messageType').html('에러 메시지');
				$('#messageContent').html('삭제 실패.');
				$('#errorMessage').html('삭제에 실패했습니다. 잠시 후 다시 시도해 주십시오.');
				$('#messageCheck').attr('class', 'modal-content panel-warning');
				break;
			}
			$('#messageModal').modal('show');
		},
		error: e => console.log('요청 실패:', e.status)
	});
}
