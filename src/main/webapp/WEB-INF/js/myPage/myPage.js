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
					$('#messageType').html('성공 메시지');
					$('#messageContent').html('탈퇴완료.');
					$('#errorMessage').html('탈퇴 되었습니다.');
					$('#messageCheck').attr('class', 'modal-content panel-success');
					break;
				case '1':
					$('#messageType').html('에러 메시지');
					$('#messageContent').html('탈퇴 실패.');
					$('#errorMessage').html('탈퇴에 실패하였습니다. 잠시후 다시 시도해주세요');
					$('#messageCheck').attr('class', 'modal-content panel-warning');
					break;
			}
			$('#messageModal').modal('show');
		},
		error: e => console.log('요청 실패:', e.status)
	});
}
