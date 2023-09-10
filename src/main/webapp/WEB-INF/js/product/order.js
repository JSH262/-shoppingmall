
$(() => {
	const CONTEXT_PATH = getContextPath();
	const MANUAL_DELIVERY_MSG_CODE = '999';
	const DEFAULT_DELIVERY_MSG_CODE = '1';
	const productNode = $("div[name=product]").clone();
	const addrListModal = new bootstrap.Modal('#addrListModal');
	const addrAddModal = new bootstrap.Modal('#addrAddModal');
	const searchPostcode = new daum.Postcode({
        oncomplete: function(data) {
            let addr = null;
            
            if (data.userSelectedType === 'R') 
            {
				 // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } 
            else 
            {
				 // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }
            
            $("input[name=addrAddAddr1]").val('(' + data.zonecode + ') ' + addr);
        }
    });
    
    
    const DELIVERY_MSG = [
    	"배송시 요청사항 선택하기",
		"직접 수령하겠습니다",
		"문 앞에 놓아주세요",
		"경비실에 맡겨주세요",
		"배송 전 휴대폰으로 연락주세요",
		"파손위험이 있는 상품이니 조심히 다뤄주세요"];
    
	$("div[name=product]").remove();
	
	

	const paymentSelectModal = new bootstrap.Modal(document.getElementById('paymentSelectModal'));
	let isExecPay = false;
	$("#pay").bind('click', function()
	{
		if(isExecPay)
		{
			
		}
		else
		{
			try
			{
				isExecPay = true;
				// 구매자가 선택한 주소를 서버에게 보낸다.
				let strId = $("input[name=mainAddrId]").val();
				
				if(strId.length > 0)
				{
					let id = parseInt(strId);
					let data = {
						id: id
					};
					
					Ajax(`${CONTEXT_PATH}/destaddr/selected`, "POST", JSON.stringify(data), 
						function(resp)
						{
							if(resp.code == 0)
							{
								// 모달 열기
								payment.init(function(){
									payment.openStartPayment();
								});
								
								
								//location.href=`${CONTEXT_PATH}/product/payment`;
							}
							else
								alert(resp.code + ": " + resp.msg);
						},
						function(err)
						{
							console.error(err);
						},
						function(){
							isExecPay = false;
						});
				}
				else
				{
					isExecPay = false;
					alert('배송지를 선택해주세요');
				}
			}
			catch(exp)
			{
				console.error(exp);
			}
		} 
	});
	
	
	const addrListContentsNode = $("div[name=addrListContents]").clone();
	$("div[name=addrListContents]").remove();
	
	// 배송지 목록 보기
	$("#addrListModal").on('show.bs.modal', function()
	{
		try
		{
			let parent = $("div[name=addrListBody]");
	
			//배송지 목록을 가져온다.	
			Ajax(`${CONTEXT_PATH}/destaddr/list`, "GET", null, 
				function(resp)
				{
					if(resp.code == 0)
					{
						parent.empty();
						
						let list = resp.result.list;
	
						for(let i = 0; i<list.length; i++)
						{
							let data = list[i];
							let item = addrListContentsNode.clone();
							let name = data.addrName;
							let id = data.id;
							let recvName = data.name;
							let phone = data.phone;
							let addr = data.addr1 + ' ' + data.addr2;
							let reqMsg = data.reqMsg;
								
								
							if(!reqMsg)
								reqMsg = '';
								
					
							item.find('span[name=addrListName]').text(name);
							item.find('span[name=addrListId]').text(id);
							item.find('span[name=addrListRecvName]').text(recvName);
							item.find('span[name=addrListPhone]').text(phone);
							item.find('div[name=addrListFullAddr]').text(addr);
							item.find('div[name=addrListReqMsg]').text(reqMsg);
							
							item.find('input[name=addrListRemove]').bind('click', function()
							{
								let sendData = {
									id: id
								};
								
								Ajax(`${CONTEXT_PATH}/destaddr/remove`, "POST", JSON.stringify(sendData),  
									function(resp)
									{
										if(resp.code == 0)
										{
											alert('선택한 배송지가 삭제되었습니다.');
											
											if(list.length - 1 == 0)											
											{
												$("div[name=deliveryInfo]").addClass('opacity-0');
												$("#deliveryInfoEmpty").removeClass("invisible");
											}
											
											item.remove();
										}
										else
										{
											alert(resp.msg);
										}
									},
									function(err)
									{
										console.error(err);
									}
								);
							});
							
							item.find('input[name=addrListChoose]').bind('click', function()
							{
								$("label[name=mainAddrName]").text(recvName);
								$("label[name=mainAddrAddr]").text(addr);
								$("label[name=mainAddrPhone]").text(phone);
				
				
								$("input[name=mainAddrId]").val(id);
				
				
								let selectOptions = $("select[name=mainAddrRequest] option");
								let selectIndex = -1;
				
								for(let z = 0; z<DELIVERY_MSG.length; z++)
								{
									if(DELIVERY_MSG[z] == reqMsg)
									{
										selectIndex = z;
										break;						
									}
								}
								
								if(selectIndex != -1)
								{
									selectOptions.eq(selectIndex).prop('selected', true);
									$("div[name=mainAddrReqBody]").addClass('invisible');
								}
								else
								{					
									if(reqMsg)
									{
										$("select[name=mainAddrRequest]").val(MANUAL_DELIVERY_MSG_CODE).prop('selected', true);
										$("div[name=mainAddrReqBody]").removeClass('invisible');
										$("input[name=mainAddrReqMsg]").val(reqMsg);
										$("span[name=mainAddrCurrMsg]").text(reqMsg.length);
									}
									else
									{
										$("select[name=mainAddrRequest]").val(DEFAULT_DELIVERY_MSG_CODE).prop('selected', true);
										$("div[name=mainAddrReqBody]").addClass('invisible');
										$("input[name=mainAddrReqMsg]").text('');
									}
								}
								
								
								$("div[name=deliveryInfo]").removeClass('opacity-0');
								$("#deliveryInfoEmpty").addClass("invisible");
								addrListModal.hide();
							});
							
							parent.append(item);
						}
					}
					else
						alert(resp.code + ": " + resp.msg);		
				},
				function(err)
				{
					console.error(err);
				}
			);
				
			
		}
		catch(exp)
		{
			console.error(exp);
		}
		
	});
	
	
	let setEventAddrAddModal = function() {

		//주소1을 클릭하면 주소검색 창 띄우기
		$("input[name=addrAddAddr1]").bind('click', function() 
		{
		    searchPostcode.open();
		});
		
		//취소버튼을 누르면 입력한 값들을 초기화한다.
		$("button[name=addrAddCancel]").bind('click', function() 
		{
			let parent = $("div[name=addrAddBody]");			
			let addrAddNameNode = parent.find('input[name=addrAddName]');
			let addrRecvNameNode = parent.find('input[name=addrAddRecvName]');
			let addrPhoneNode = parent.find('input[name=addrAddPhone]');
			let addr1Node = parent.find('input[name=addrAddAddr1]');
			let addr2Node = parent.find('input[name=addrAddAddr2]');
			let addrReqNode = parent.find('select[name=addrAddRequest]');
			let addrReqMsgNode = parent.find('input[name=addrAddReqMsg]');
			
			addrAddNameNode.val('');
			addrRecvNameNode.val('');
			addrPhoneNode.val('');
			addr1Node.val('');
			addr2Node.val('');
			addrReqNode.find('option:eq(0)').prop('selected', true);
			addrReqMsgNode.val('');
		});
		
		//직접입력할 메시지를 보이거나 숨기기
		$("select[name=addrAddRequest]").bind('change', function() 
		{			
			let selectedNode = $("select[name=addrAddRequest] option:selected");
			
			if(selectedNode.val() == MANUAL_DELIVERY_MSG_CODE)
			{
				$("div[name=addrAddReq]").removeClass('hide');
			}
			else
			{
				$("div[name=addrAddReq]").addClass('hide');
			}
		});
		
		//직접입력할 메시지의 길이를 제한하는 이벤트
		$("input[name=addrAddReqMsg]").attr('maxlength', $("span[name=addrAddMaxMsg]").text());
		$("input[name=addrAddReqMsg]").bind('keyup', function() 
		{
			let str = $("input[name=addrAddReqMsg]").val();
			
			$("span[name=addrAddCurrMsg]").text(str.length);
		});
		
		//주소검색 창 띄우기
		$("input[name=addrAddOpenPostcode]").bind('click', function() 
		{
		    searchPostcode.open();
		});
		
		//배송지 저장하기
		$("button[name=addrAddSave]").bind('click', function() {
			
			let parent = $("div[name=addrAddBody]");			
			let addrAddNameNode = parent.find('input[name=addrAddName]');
			let addrRecvNameNode = parent.find('input[name=addrAddRecvName]');
			let addrPhoneNode = parent.find('input[name=addrAddPhone]');
			let addr1Node = parent.find('input[name=addrAddAddr1]');
			let addr2Node = parent.find('input[name=addrAddAddr2]');
			let addrReqNode = parent.find('select[name=addrAddRequest]');
			let addrReqMsgNode = parent.find('input[name=addrAddReqMsg]');
			
			
			let aaName = addrAddNameNode.val();
			let aRecvName = addrRecvNameNode.val();
			let aPhone = addrPhoneNode.val();
			let addr1 = addr1Node.val();
			let addr2 = addr2Node.val();
			let addrReqItem = addrReqNode.find('option:selected');
			let aReqMsg = addrReqItem.text();
			
			if(addrReqItem.val() == MANUAL_DELIVERY_MSG_CODE)
			{
				aReqMsg = addrReqMsgNode.val();
			}
			else if(addrReqItem.val() == DEFAULT_DELIVERY_MSG_CODE)
			{
				aReqMsg = '';
			}
			
			/*
			console.log('------------------------------');
			console.log(aaName);
			console.log(aRecvName);
			console.log(aPhone);
			console.log(addr1);
			console.log(addr2);
			console.log(aReqMsg);
			console.log('------------------------------');
			//*/
			
			let data = {
				addr1: addr1,
				addr2: addr2,
				name: aRecvName,
				phone: aPhone,
				addrName: aaName,
				reqMsg: aReqMsg
			};
			
			Ajax(`${CONTEXT_PATH}/destaddr/insert`, "POST", JSON.stringify(data), 
				function(resp)
				{
					if(resp.code == 0)
					{
						//addrAddModal.hide() 호출하고 저장했던 배송지 정보를 초기화한다.		
						addrAddModal.hide();
						addrListModal.show();
						addrAddNameNode.val('');
						addrRecvNameNode.val('');
						addrPhoneNode.val('');
						addr1Node.val('');
						addr2Node.val('');
						addrReqNode.find('option:eq(0)').prop('selected', true);
						addrReqMsgNode.val('');
					}
					else if(resp.code == 1)
					{
						alert(resp.msg);
					}
					else
						alert(resp.code + ": " + resp.msg);	
				},
				function(err)
				{
					console.error(err);
				}
			);
		});		
	};
	
	let successSearchData = function() 
	{
		Ajax(`${CONTEXT_PATH}/product/order`, "POST", null, 
			function(resp)
			{
				$("#pagination").empty();
				$("#list").empty();
				let list = resp.result.list;
			
				for(let i = 0; i<list.length; i++)
				{
					let productItem = productNode.clone();
					let item = list[i];
					
					if(item.productAmount == 0 || item.amount > item.productAmount)
					{
						let tmp = '?';
						
						if(item.productAmount == 0)
						{
							tmp = "품절";
						}
						else if(item.amount > item.productAmount)
						{
							tmp = "수량부족";
						}
						
						productItem.find('div[name=overlayContents]').removeClass('hide');
						productItem.find("div[name=productBody]").addClass('product-sold-out');						
						productItem.find("div[name=overlayContents]").text(tmp);
					}
																

																
																
																
					const productDetailMove = function()
					{
						location.href = `${CONTEXT_PATH}/product/detail?id=${item.productId}`;
					}
					
					productItem.find('img[name=thumnail]').attr('src', `${CONTEXT_PATH}${item.thumbnail}`);				
					
					productItem.find('img[name=thumnail]').bind('click', productDetailMove);
					
					productItem.find('div[name=name]').text(item.productName);
					productItem.find('div[name=name]').bind('click', productDetailMove);
					
					productItem.find('div[name=discountPrice]').text(item.fmtDiscountPrice);
					productItem.find('span[name=price]').text(item.fmtPrice);
					productItem.find('span[name=amount]').text(item.fmtAmount);
					
					if(item.deliveryPrice == 0)
					{
						productItem.find('span[name=delivery]').text('');	
					}
					
					productItem.find('span[name=deliveryPrice]').text(item.fmtDeliveryPrice);
					
					if(item.companyName)
						productItem.find('div[name=companyName]').text(item.companyName);
					else
						productItem.find('div[name=companyName]').text('');
					
					$("#list").append(productItem);
					
				}
				
				$("#totalPrice").text(resp.result.fmtResultPrice); // 할인전 가격
				$("#totalDiscount").text(resp.result.fmtResultDiscount); // 할인된 가격
				$("#totalDeliveryPrice").text(resp.result.fmtResultDevliveryPrice); //총 배송비
				$("#totalDiscountPrice").text(resp.result.fmtResultDiscountPrice); //할인한 가격
				
				
			},
			function(err)
			{
				console.error(err);
			});		
	};//let successSearchData = function() 
	
	setEventAddrAddModal();
	successSearchData();
});


