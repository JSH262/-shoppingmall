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
			/*switch (res) {
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
			}*/
		},
		error: e => console.log('요청 실패:', e.status)
	});
}

/*
$(() => {
	let successSearchData = function() 
	{
				
		Ajax(`${CONTEXT_PATH}/reviewList`, "POST", JSON.stringify(data), 
			function(resp)
			{
				$("#list").empty();
				
				let list = resp.result.list;
							
				for(let i = 0; i<list.length; i++)
				{
					let item = list[i];
					let tr = $('<tr>');
											
					//tr.append(`<td align="center"><a href="${CONTEXT_PATH}/product/detail?id=${item.id}&pageSize=${pageSize}&currentPage=${currentPage}">${item.name}</a></td>`);				
					
					tr.append(`<td align="center">${item.score}</td>`);
					tr.append(`<td align="center">${item.contents}</td>`);
					tr.append(`<td align="center">${item.createDate}</td>`);
					
					$("#list").append(tr);
				}
			},
			function(err)
			{
				console.error(err);
			});		
	};

	successSearchData();
});
*/