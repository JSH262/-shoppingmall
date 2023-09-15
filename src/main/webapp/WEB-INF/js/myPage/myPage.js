function userUpdate() {
	var id = $('#userId').val();
	var name = $('#name').val();
	var email = $('#email').val();
	var phone = $('#phone').val();
	$.ajax({
		type: 'POST',
		url: './userUpdate',
		data: {
		id: id,
		name: name,
		email: email,
		phone: phone
	},
	success: function(res) {
		switch (res) {
			case '0':
				customAlert.show('수정 되었습니다.', null, 1, function(){
					location.href="/shoppingmall/myPage/";	
				});
				break;
			case '1':
				customAlert.show('수정에 실패하였습니다. 잠시후 다시 시도해주세요.', 1, function(){
				});
				break;
			}
		},
		error: e => console.log('요청 실패:', e.status)
	});
}

