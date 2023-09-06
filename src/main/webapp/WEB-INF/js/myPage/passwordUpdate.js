function passwordUpdate() {
	var id = $('#userId').val();
	var password1 = $('#password1').val();
	var password2 = $('#password2').val();
	console.log("1: ", password1);
	console.log("2: ", password2);
	$.ajax({
		type: 'POST',
		url: './passwordUpdate',
		data: {
		id: id,
		password1: password1,
		password2: password2
	},
	success: function(res) {
		switch (res) {
				case '0':
					$('#messageType').html('성공 메시지');
					$('#messageContent').html('비밀번호 변경성공.');
					$('#errorMessage').html('비밀번호가 변경되었습니다.');
					$('#messageCheck').attr('class', 'modal-content panel-success');
					break;
				case '1':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('비밀번호 불일치.');
					$('#errorMessage').html('두 비밀번호의 값이 다릅니다.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
				case '2':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('값을 입력해주세요');
					$('#errorMessage').html('값을 입력하고 버튼을 눌러주세요.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
				case '3':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('비밀번호를 잘못 입력했습니다.');
					$('#errorMessage').html('입력하신 내용을 다시 확인해주세요.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
				case '4':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('비밀번호규칙 불이행.');
					$('#errorMessage').html('입력하신 내용을 다시 확인해주세요.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
			}
			$('#messageModal').modal('show');
		},
		error: e => console.log('요청 실패:', e.status)
	});
}


function isValidUserPassword() {
	  let password1 = $('#password1').val();
	  let passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*_])[a-zA-Z\d!@#$%^&*_]{8,30}$/;

	  if (password1 === '') {
	    $('#passwordCheckMessage').html('비밀번호를 입력하세요.\n생성규칙: 대문자, 소문자, 특수문자(!@#$%^&*_), 숫자 포함, 8자 이상 30자 이하');
	  } else if (!passwordRegex.test(password1)) {
	    $('#passwordCheckMessage').html('비밀번호 생성 규칙 (대문자, 소문자, 특수문자(!@#$%^&*_), 숫자 포함, 8자 이상 30자 이하)을 충족하지 못합니다!');
	  } else {
	    $('#passwordCheckMessage').html('사용 가능한 비밀번호입니다!');
	  }
	}


//		비밀번호가 일치하는가 확인하는 함수
	function passwordCheckFunction() {
		let password1 = $('#password1').val();
		let password2 = $('#password2').val();
		
		if (password1 != password2) {
			$('#passwordCheckMessage').html('비밀번호가 일치하지 않습니다.');
		} else {
			$('#passwordCheckMessage').html('확인 완료');
		}
	}













