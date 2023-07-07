function isValidUserPassword() {
  let userPassword1 = $('#userPassword1').val();
  let passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*_])[a-zA-Z\d!@#$%^&*_]{8,30}$/;

  if (userPassword1 === '') {
    $('#passwordCheckMessage').html('비밀번호를 입력하세요.\n생성규칙: 대문자, 소문자, 특수문자(!@#$%^&*_), 숫자 포함, 8자 이상 30자 이하');
  } else if (!passwordRegex.test(userPassword1)) {
    $('#passwordCheckMessage').html('비밀번호 생성 규칙 (대문자, 소문자, 특수문자(!@#$%^&*_), 숫자 포함, 8자 이상 30자 이하)을 충족하지 못합니다!');
  } else {
    $('#passwordCheckMessage').html('사용 가능한 비밀번호입니다!');
  }
}


//	비밀번호가 일치하는가 확인하는 함수
function passwordCheckFunction() {
	let userPassword1 = $('#userPassword1').val();
	let userPassword2 = $('#userPassword2').val();
	
	if (userPassword1 != userPassword2) {
		$('#passwordCheckMessage').html('비밀번호가 일치하지 않습니다.');
	} else {
		$('#passwordCheckMessage').html('확인 완료');
	}
}


//	회원 가입을 실행하는 함수
function userReister() {
//	ajax를 이용해서 index.jsp에서 테이블에 저장할 데이터를 얻어온다.
	//let userType = $('input[name=userType]:checked').val();
	let userId = $('#userId').val();
	let userPassword1 = $('#userPassword1').val();
	let userPassword2 = $('#userPassword2').val();
	let userEmail = $('#userEmail').val();
	let userPhone = $('#userPhone').val();
	let userType = $('#userType').val();
	let userName = $('#userName').val();
	let companyId = $('#companyId').val();

//	jQuery ajax
	$.ajax({
		type: 'POST', // 요청 방식
		url: './UserJoin', // 요청할 서블릿
		data: { // 서블릿으로 전송할 데이터
			// 변수명: 값
			userType: userType,
			userId: userId,
			userPassword1: userPassword1,
			userPassword2: userPassword2,
			userName: userName,
			userEmail: userEmail,
			userPhone: userPhone,
			companyId : companyId
		},
		// ajax 요청이 성공하면 실행할 콜백 함수
		// ajax 요청이 성공하면 response.getWriter().write(문자열)의 문자열이 콜백 함수의 인수로 넘어온다.
		success: res => {
			// 서블릿 응답에 따른 메시지를 출력한다.
			switch (res) {
				case '1':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('모든 내용을 입력하세요.');
					$('#errorMessage').html('에러: 모든 내용을 입력하세요.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
				case '2':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('아이디가 형식에 맞지 않습니다.');
					$('#errorMessage').html('에러: 아이디가 형식에 맞지 않습니다.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
				case '3':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('비밀번호가 일치하지 않습니다.');
					$('#errorMessage').html('에러: 비밀번호가 일치하지 않습니다.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
				case '4':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('비밀번호가 형식에 맞지 않습니다.');
					$('#errorMessage').html('에러: 비밀번호가 형식에 맞지 않습니다.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
				case '5':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('이름이 형식에 맞지 않습니다.');
					$('#errorMessage').html('에러: 이름이 형식에 맞지 않습니다.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
				case '6':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('이메일이 형식에 맞지 않습니다.');
					$('#errorMessage').html('에러: 이메일이 형식에 맞지 않습니다.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
				case '7':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('전화번호가 형식에 맞지 않습니다.');
					$('#errorMessage').html('에러: 전화번호가 형식에 맞지 않습니다.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
				case '8':
					$('#messageType').html('성공 메시지');
					$('#messageContent').html('회원 가입에 성공했습니다.');
					$('#errorMessage').html('회원 가입에 성공했습니다.');
					$('#messageCheck').attr('class', 'modal-content panel-success');
					break;
				case '9':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('이미 존재하는 회원입니다.');
					$('#errorMessage').html('에러: 이미 존재하는 회원입니다.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
			}
			// 모달 창을 띄운다.
			$('#messageModal').modal('show');
		},
		// ajax 요청이 실패하면 실행할 콜백 함수
		// ajax 요청이 실패하면 에러 정보가 콜백 함수의 인수로 넘어온다.
		error: e => console.log('요청 실패:', e.status)
	});
}

//	아이디 중복 검사를 실행하는 함수
function registerCheckFunction() {
//	ajax를 이용해서 중복 검사할 아이디를 얻어온다.
	let userId = $('#userId').val();
	console.log(userId);

//	jQuery ajax
	$.ajax({
		type: 'POST',
		url: './userIdCheck',
		data: {
			userId: userId
		},
		success: res => {
			// console.log("요청 성공:", res);
			switch (res) {
				case '0':
					$('#messageType').html('성공 메시지');
					$('#messageContent').html('사용 가능한 아이디입니다.');
					$('#errorMessage').html('사용 가능한 아이디입니다.');
					$('#messageCheck').attr('class', 'modal-content panel-success');
					break;
				case '1':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('사용중인 아이디 입니다.');
					$('#errorMessage').html('에러: 사용중인 아이디 입니다.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					$('#userId').val('');
					break;
				case '2':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('spl 실행 오류입니다.');
					$('#errorMessage').html('에러: spl 실행중 오류가 발생했습니다. 관리자에게 문의하세요.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					$('#userId').val('');
					break;
				case '3':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('아이디를 입력하고 중복 체크 버튼을 누르세요.');
					$('#errorMessage').html('에러: 아이디를 입력하고 중복 체크 버튼을 누르세요.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					$('#userId').val('');
					break;
				
				
			}
			$('#messageModal').modal('show');
		},
		error: e => console.log('요청 실패:', e.status)
	});
}

// 로그인 함수
function UserLogin() {
	var userId = $('#userId').val();
	var userPassword = $('#userPassword').val();
	$.ajax({
		type: 'POST',
		url: './UserLogin',
		data: {
		userId: userId,
		userPassword: userPassword
	},
	success: function(res) {
		switch (res) {
				case '0':
					$('#messageType').html('성공 메시지');
					$('#messageContent').html('로그인 성공.');
					$('#errorMessage').html('로그인에 성공했습니다.');
					$('#messageCheck').attr('class', 'modal-content panel-success');
					break;
				case '1':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('아이디 또는 비밀번호를 잘못 입력했습니다.');
					$('#errorMessage').html('입력하신 내용을 다시 확인해주세요.');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					$('#userId').val('');
					break;
			}
		},
		error: e => console.log('요청 실패:', e.status)
	});
}


//회사 정보 입력
function companyInsert() {
	let name = $('#name').val();
	let id = $('#id').val();
	let address1 = $('#address1').val();
	let address2 = $('#address2').val();
	let stamp = $('#stamp').val();
	let userType = $('#userType').val();

	$.ajax({
		type: 'POST', // 요청 방식
		url: './CompanyInsert', // 요청할 서블릿
		data: { // 서블릿으로 전송할 데이터
			Name: name,
			Id: id,
			Address1: address1,
			Address2: address2,
			Stamp: stamp,
		},
		success: res => {
			switch (res) {
				case '1':
					alert('회사정보 입력 성공.');
					location.href = 'join.jsp?userType=' + userType + '&companyId=' + id;
					break;
				case '2':
					alert('sql 실행 오류입니다. 관리자에게 문의하세요.');
					break;
				case '3':
					alert('모든 값을 입력해주세요.');
					break;
			}
		},
		error: e => {
            console.log('요청 실패:', e.status);
        }
	});
}
















