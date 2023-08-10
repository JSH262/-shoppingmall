function passwordCheck() {
	var password = $('#password').val();
	var userId = $('#userId').val();
	var action = $('#action').val();
	$.ajax({
		type: 'POST',
		url: './PasswordCheck',
		data: {
		userId: userId,
		password: password
	},
	success: function(res) {
		switch (res) {
				case '0':
					$('#messageType').html('성공 메시지');
					$('#messageContent').html('비밀번호 인증성공.');
					$('#errorMessage').html('비밀번호 인증에 성공했습니다.');
					$('#messageCheck').attr('class', 'modal-content panel-success');
					break;
				case '1':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('비밀번호를 입력하세요.');
					$('#errorMessage').html('비밀번호를 입력해주세요.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					$('#passwordCheck').val('');
					break;
				case '2':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('비밀번호를 잘못 입력했습니다.');
					$('#errorMessage').html('입력하신 내용을 다시 확인해주세요.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					$('#passwordCheck').val('');
					break;
			}
		},
		error: e => console.log('요청 실패:', e.status)
	});
}




