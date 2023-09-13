function updateAmount(button) {
	var parentSpan = $(button).closest('.input-group');
    var amount = parentSpan.find('input[name=amount]').val();
    var productId = parentSpan.find('input[name=productId]').val();
    var userId = $('#userId').val();
    
    console.log('userId:', userId);
    console.log('amount:', amount);
    console.log('productId:', productId);
    
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
				customAlert.show('변경된 수량이 적용되었습니다.', null, 1, function(){
					location.href = location.href;
				});
				break;
			case '1':
				customAlert.show('변경된 수량이 적용되지 않았습니다.', '잠시 후 다시 시도해 주십시오.', 1, function(){
				});
				break;
			}
        },
        error: e => console.log('요청 실패:', e.status)
    });
}

function deleteProduct(button) {
	var userId = $('#userId').val();
	var productId = parentSpan.find('input[name=productId]').val();
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
				customAlert.show('삭제 되었습니다.', null, 1, function(){
					location.href = location.href;
				});
				break;
			case '1':
				customAlert.show('삭제에 실패했습니다.', '잠시 후 다시 시도해 주십시오.', 1, function(){
				});
				break;
			}
		},
		error: e => console.log('요청 실패:', e.status)
	});
}
