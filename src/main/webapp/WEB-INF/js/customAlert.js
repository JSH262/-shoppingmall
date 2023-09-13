customAlert = {
	
	alertId: '#customAlert',
	titleId: '#customAlertTitle',
	bodyId: '#customAlertBody',
	btnGupId: '#customAlertBtnGup',
	okId: '#customAlertBtnOk',
	cancelId: '#customAlertBtnCancel',
	customAlertModal: null,
	okContents: null,
	cancelContents: null,

	/**
	 * seconds	단위는 초, seconds초 후에 창이 자동으로 닫힌다.
	 * isShowOk 확인 버튼 위에 숫자를 표시하려면 true, 취소 버튼에 표시하면 false 
	 */
	close: function(seconds, isShowOk) {
		if(seconds)
		{
			let btn = null;		
			let end = seconds * 1000;
			let now = 0;
			let btnNode = $(customAlert.alertId); 
			
			if(isShowOk)
			{
				btn = btnNode.find(customAlert.okId);
				btn.html(`<strong>${customAlert.okContents} (${parseInt(end / 1000)})</strong>`);
			}
			else
			{
				btn = btnNode.find(customAlert.cancelId);
				btn.html(`${customAlert.cancelContents} (${parseInt(end / 1000)})`);
			}
						
			let intervalId = setInterval(function() {
				now += 1000;
				result = parseInt((end - now) / 1000);
				
				if(isShowOk)
				{
					btn.html(`<strong>${customAlert.okContents} (${result})</strong>`);
				}
				else
				{
					btn.html(`${customAlert.cancelContents} (${result})`);
				}
				
				if(end <= now)
				{					
					customAlert.customAlertModal.hide();
					clearInterval(intervalId);
				}
			}, 1000);
		}
		else
		{
			customAlert.customAlertModal.hide();
		}
	},
	
	/**
	 * title			알림 창의 제목
	 * body				알림 창의 내용
	 * btnType			버튼의 종류 => 1: 확인, 2: 확인, 취소
	 * callbackOk		확인 버튼을 눌렀을 경우 호출할 함수
	 * callbackCancel	취소 버튼을 눌렀을 경우 호출할 함수
	 * okContents		확인 버튼에 표시할 내용으로 기본값은 확인
	 * backdrop			
	 * cancelContents	취소 버튼에 표시할 내용으로 기본값은 취소
	 */
	show: function(title, body, btnType, callbackOk, callbackCancel, backdrop, okContents, cancelContents)
	{
		if(!backdrop)
			backdrop = 'static';
		
		customAlert.customAlertModal = new bootstrap.Modal(customAlert.alertId, {'backdrop':backdrop});
		let btnNode = $(customAlert.alertId); 
		let btnOk = btnNode.find(customAlert.okId);
		let btnCancel = btnNode.find(customAlert.cancelId);		
		
		const modelOkEvent = function() {
			btnCancel.off('click');
			if(callbackOk)
				callbackOk();
			customAlert.customAlertModal.hide();
			customAlert.customAlertModal = null;
		};
		
		const modelCancelEvent = function() {
			btnOk.off('click');
			if(callbackCancel)
				callbackCancel();
			customAlert.customAlertModal.hide();
			customAlert.customAlertModal = null;
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

		customAlert.okContents = okContents;
		customAlert.cancelContents = cancelContents;
		
		btnOk.html(`<strong>${okContents}</strong>`);
		btnCancel.html(cancelContents);
		
		
		$(customAlert.bodyId).text(body);
		$(customAlert.titleId).text(title);
		
		customAlert.customAlertModal.show();
		return customAlert;
	}	
};

