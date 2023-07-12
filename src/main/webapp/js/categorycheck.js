function mainFormCheck(obj) {
	if (!obj.name.value || obj.name.value.trim().length == 0) {
		alert('메인 카테고리는 반드시 입력해야 합니다.');
		obj.name.value = '';
		obj.name.focus();
		return false;
	}
	return true;
}

function subFormCheck(obj) {
	if (!obj.name.value || obj.name.value.trim().length == 0) {
		alert('서브 카테고리는 반드시 입력해야 합니다.'); 
		obj.name.value = '';
		obj.name.focus();
		return false;
	}
	return true;
}

$(() => {
	$('#form').submit(function (event) {
		let name = $.trim($('#name').val()).length; 
		if (name == 0) {
			alert('메인 카테고리는 반드시 입력해야 합니다.');
			event.preventDefault();
			$('#name').val('');
			$('#name').focus();
		}
	});
 	$('.sub_form').each(function (index, item) {
		 $(item).addClass('sub_form' + index);
	 });
	 
 	$('.sub_name').each(function (index, item) {
		 $(item).addClass('sub_name' + index);
	 });
	
	$('.sub_form').each(function (index, item) {
		$('.sub_form' + index).submit(function (event) {
			let sub_name = $.trim($('.sub_name' + index).val()).length;
			if (sub_name == 0) {
				let categoryName = $.trim($('.sub_form' + index).find('span').text());
				alert(categoryName + ' 서브 카테고리는 반드시 입력해야 합니다.');
				event.preventDefault();
				$('.sub_form' + index)[0].reset();
				$('.sub_name' + index).focus();
			}
		});
	});
	
});

function mySubmitUpdate(obj) {
	if (!obj.name.value || obj.name.value.trim().length == 0) {
		alert('수정할 카테고리를 입력하세요');
		obj.name.value = '';
		obj.name.focus();
	} else {
		obj.action = 'update.jsp';
		obj.submit();
	}
}

function mySubmitRestore(obj) {
	obj.action = 'restore.jsp';
	obj.submit();
}

function mySubmitDelete(obj) {
	obj.action = 'delete.jsp';
	obj.submit();
}








