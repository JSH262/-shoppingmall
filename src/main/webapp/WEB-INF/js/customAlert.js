customAlert = {
	
	alertId: '#customAlert',
	titleId: '#customAlertTitle',
	bodyId: '#customAlertBody',
	btnGupId: '#customAlertBtnGup',
	okId: '#customAlertBtnOk',
	cancelId: '#customAlertBtnCancel',

	/**
	 * title			알림 창의 제목
	 * body				알림 창의 내용
	 * btnType			버튼의 종류 => 1: 확인, 2: 확인, 취소
	 * callbackOk		확인 버튼을 눌렀을 경우 호출할 함수
	 * callbackCancel	취소 버튼을 눌렀을 경우 호출할 함수
	 * okContents		확인 버튼에 표시할 내용으로 기본값은 확인
	 * cancelContents	취소 버튼에 표시할 내용으로 기본값은 취소
	 */
	show: function(title, body, btnType, callbackOk, callbackCancel, okContents, cancelContents)
	{
		const customAlertModal = new bootstrap.Modal(customAlert.alertId);
		let btnNode = $(customAlert.alertId); 
		let btnOk = btnNode.find(customAlert.okId);
		let btnCancel = btnNode.find(customAlert.cancelId);		
		
		const modelOkEvent = function() {
			btnCancel.off('click');
			if(callbackOk)
				callbackOk();
			customAlertModal.hide();
		};
		
		const modelCancelEvent = function() {
			btnOk.off('click');
			if(callbackCancel)
				callbackCancel();
			customAlertModal.hide();
		};
		
		if(!btnType)
		{
			btnType = 1
		}
		
		
		switch(btnType)
		{
		default:
		case 1:
		case '1':
			btnOk.one('click', modelOkEvent);			
			btnCancel.css('display', 'none');			
			btnOk.removeClass('col-6 fs-6');
			btnOk.addClass('col-12 fs-12');
			break;
			
		case 2:
		case '2':	
			btnCancel.one('click', modelCancelEvent);
			btnOk.one('click', modelOkEvent);
			btnCancel.css('display', 'block');			
			btnOk.addClass('col-6 fs-6');
			btnOk.removeClass('col-12 fs-12');
			break;
		}
		
		
		if(!body)		
		{
			body = '';
		}
		
		if(!title)
		{
			title = '';
		}
		
		
		if(!okContents)
		{
			okContents = "확인";
		}
		if(!cancelContents)
		{
			cancelContents = '취소';
		}
		
		btnOk.html(`<strong>${okContents}</strong>`);
		btnCancel.html(cancelContents);
		
		
		
		$(customAlert.bodyId).text(body);
		$(customAlert.titleId).text(title);
		
		customAlertModal.show();
	}	
};

