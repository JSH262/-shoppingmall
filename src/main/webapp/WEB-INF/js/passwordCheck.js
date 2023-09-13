function passwordCheck() {
	var password = $('#password').val();
	var userId = $('#userId').val();
	var action = $('#action').val();
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
			if(action === 'unregister'){
				customAlert.show('탈퇴를 진행하시겠습니까?', null, 2, function(){
					unregister();
				}, function(){
					location.href="/shoppingmall/myPage/";
				});
				}
			else{
				customAlert.show('패스워드 인증에 성공했습니다.', null, 1, function(){
					location.href="/shoppingmall/myPage/" + action;					
				});
			}
			break;
		case '1':
			customAlert.show('값을 입력해주세요.', '값을 입력하고 버튼을 눌러주세요.', 1, function(){
				$('#password').val('');	
			});
			break;
		case '2':
			customAlert.show('인증 실패.', '비밀번호가 일치하지 않습니다.', 1, function(){
				$('#password').val('');	
			});
			break;
		}
		/*switch (res) {
				case '0':
					$('#messageType').html('성공 메시지');
					$('#messageContent').html('비밀번호 인증성공.');
					$('#errorMessage').html('비밀번호 인증에 성공했습니다.');
					$('#messageCheck').attr('class', 'modal-content panel-success');
					$('#success').prop('disabled', false);
					$('#warning').prop('disabled', true);
					break;
				case '1':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('값을 입력해주세요');
					$('#errorMessage').html('값을 입력하고 버튼을 눌러주세요.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					$('#warning').prop('disabled', false);
					$('#success').prop('disabled', true);
					break;
				case '2':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('인증 실패.');
					$('#errorMessage').html('비밀번호가 일치하지 않습니다.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					$('#warning').prop('disabled', false);
					$('#success').prop('disabled', true);
					break;
			}*/
		
		
			$('#messageModal').modal('show');
		},
		error: e => console.log('요청 실패:', e.status)
	});
}

function unregister() {
	var id = $('#userId').val();
	$.ajax({
		type: 'POST',
		url: './unregister',
		data: {
			id: id
		},
		success: function(res) {
			switch (res) {
			case '0':
				customAlert.show('탈퇴 되었습니다.', '감사합니다.', 1, function(){
					location.href="/shoppingmall/logout";
				});
				break;
			case '1':
				customAlert.show('탈퇴에 실패하였습니다.', '잠시후 다시 시도해주세요.', 1, function(){
				});
				break;
			}
		},
		error: e => console.log('요청 실패:', e.status)
	});
}


