function passwordCheck() {
	var password = $('#password').val();
	var userId = $('#userId').val();
	$.ajax({
		type: 'POST',
		url: './passwordCheckF',
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
					$('#messageContent').html('값을 입력해주세요');
					$('#errorMessage').html('값을 입력하고 버튼을 눌러주세요.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
				case '2':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('인증 실패.');
					$('#errorMessage').html('비밀번호가 일치하지 않습니다.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
			}
			$('#messageModal').modal('show');
		},
		error: e => console.log('요청 실패:', e.status)
	});
}




