function deleteReview(id) {
	$.ajax({
		type: 'POST',
		url: './deleteReview',
		data: {
			id: id
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

