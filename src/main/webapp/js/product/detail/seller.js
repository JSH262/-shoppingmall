
function inputTextEnable(item)
{
	item.removeClass('text-readonly');
	item.attr('readonly', false);	
}
function inputTextDisable(item)
{
	item.addClass('text-readonly');
	item.attr('readonly', true);
}
function hideTag(item)
{
	item.addClass('node-hide');
}
function showTag(item)
{
	item.removeClass('node-hide');	
}

function changeAmountHeader(amount)
{
	amount = parseInt(amount);
	
	if(amount == 0)
		$("#productAmountHeader").addClass('product-sold-out');
	else
	{
		$("#productAmountHeader").removeClass('product-sold-out');
	}
}


function changeClassControlBox(cmd)
{
	switch(cmd)
	{
	case 'modify':
		$("#name").removeClass("text-readonly");
		$('#name').attr('readonly', false);			
		$("#thumbnail").addClass('node-hide');
		$("#file").removeClass('node-hide');			
		$("#categoryId").attr('disabled', false);			
		$("#price").removeClass('text-readonly');
		$("#price").removeClass('node-hide');
		$("#fmtPrice").addClass("node-hide");
		$("#discountPriceNode").addClass('node-hide');		
		$("#discount").removeClass('text-readonly');
		$("#discount").removeClass('node-hide');
		$("#fmtDiscount").addClass("node-hide");
		$("#amount").removeClass('text-readonly');
		$("#amount").removeClass('node-hide');
		$("#fmtAmount").addClass("node-hide");			
		$("#deliveryPrice").removeClass('text-readonly');
		$("#deliveryPrice").removeClass('node-hide');
		$("#fmtDeliveryPrice").addClass("node-hide");
		
		break;
		
		
	case 'cancel':
		$("#name").addClass("text-readonly");
		$('#name').attr('readonly', true);			
		$("#thumbnail").removeClass('node-hide');
		$("#file").addClass('node-hide');			
		$("#categoryId").attr('disabled', true);			
		$("#price").addClass('text-readonly');
		$("#price").addClass('node-hide');
		$("#fmtPrice").removeClass("node-hide");
		$("#discountPriceNode").removeClass("node-hide");		
		$("#discount").addClass('text-readonly');
		$("#discount").addClass('node-hide');
		$("#fmtDiscount").removeClass("node-hide");			
		$("#amount").addClass('text-readonly');
		$("#amount").addClass('node-hide');
		$("#fmtAmount").removeClass("node-hide");			
		$("#deliveryPrice").addClass('text-readonly');
		$("#deliveryPrice").addClass('node-hide');
		$("#fmtDeliveryPrice").removeClass("node-hide");
		break;
	}
	
}

$(() => {
	try
	{
		const contextPath = $("#form").attr('action');
		const fileUploadUrl = contextPath + "/image/"
		
		
		let oldName = $("#name").val();
		let oldThumbnail = $("#thumbnail").attr('src');
		let oldPrice = $("#price").val();
		let oldDiscount = $("#discount").val();
		let oldAmount = $("#amount").val();
		let oldDeliveryPrice = $("#deliveryPrice").val();			
		let oldContents = $("#contents").html();	
		
		let oldCategoryId = $("#categoryValue").val();
		$("#categoryId").val(oldCategoryId);
		
		changeAmountHeader($("#amount").val());
		
		$("#modify").bind('click', function() {	
			$("#contents").summernote({
				tabsize: 2,
				lang: 'ko-KR',
				//height: 500,
				toolbar: [
		          ['style', ['style']],
		          ['font', ['bold', 'underline', 'clear']],
		          ['color', ['color']],
		          ['para', ['ul', 'ol', 'paragraph']],
		          ['table', ['table']],
		          ['insert', ['link', 'picture', 'video']],
		          ['view', ['fullscreen', 'codeview', 'help']]
		        ],
		        callbacks: {
					onImageUpload: function(files)
					{
						console.log(files.length);
						let data = new FormData();
						
						data.append('file', files[0]);
						
						AjaxForm(fileUploadUrl, "POST", data, 
							function(resp)
							{
								console.log(resp);
								
								if(resp.code == 0)
								{
									const filePath = fileUploadUrl + resp.result[0];
									
									$("#contents").summernote('insertImage', filePath);
								}
								else
								{
									console.error(resp);
								}
								
							}, 
							function(err)
							{
								console.error(err);	
							});
						
						
						//$("#summernote").summernote('insertNode', imgNode);
					}
				}
			});
			
			changeClassControlBox('modify');
			
			$('#save').removeClass('node-hide');
			$('#cancel').removeClass('node-hide');
			$('#modify').addClass('node-hide');
			$("#name").focus();
		});
		
		$("#cancel").bind('click', function() {
			$('#contents').summernote('destroy');
			$("#name").val(oldName);
			$("#thumbnail").attr('src', oldThumbnail);
			$("#price").val(oldPrice);
			$("#discount").val(oldDiscount);
			$("#amount").val(oldAmount);
			$("#deliveryPrice").val(oldDeliveryPrice);			
			$("#contents").html(oldContents);
			$("#categoryId").val(oldCategoryId);
			
			changeClassControlBox('cancel');
			
			$('#save').addClass('node-hide');
			$('#cancel').addClass('node-hide');
			$('#modify').removeClass('node-hide');
			
			changeAmountHeader($("#amount").val());
		});
		
		
		$("#save").bind('click', function() {	
			let url = contextPath + "/product/modify/";
			let id = $("#id").val();
			let name = $("#name").val();
			let file = document.getElementById("file");
			let price = $("#price").val();
			let discount = $("#discount").val();
			let amount = $("#amount").val();
			let deliveryPrice = $("#deliveryPrice").val();			
			let contents = $("#contents").summernote('code');
			let categoryId = $("#categoryId").val();
			let data = new FormData();					
					
			if($("#contents").summernote)
			{
				contents = $("#contents").summernote('code');
			}
			else
			{
				contents = $("#contents").val();
			}
				
			/*
			console.log(name);
			console.log(price);
			console.log(discount);
			console.log(amount);
			console.log(deliveryPrice);
			console.log(contents);
			console.log(categoryId);
			//*/
			data.append('id', id);
			data.append('file', file.files[0]);
			data.append('name', name);
			data.append('price', price);
			data.append('discount', discount);
			data.append('amount', amount);
			data.append('deliveryPrice', deliveryPrice);
			data.append('contents', contents);
			data.append('categoryId', categoryId);
			
			
			AjaxForm(url, "POST", data, 
				function(resp)
				{
					if(resp.code != 0)
					{
						alert(resp.code + ": " + resp.msg);
						return;
					}
					
					let result = resp.result;
										
					if(result.thumbnail)					
						$("#thumbnail").attr('src', result.thumbnail);
						
					$("#categoryId").attr('disabled', true);
		
					$('#save').addClass('node-hide');
					$('#cancel').addClass('node-hide');
					$('#modify').removeClass('node-hide');
					$('#contents').summernote('destroy');
					
					oldName = $("#name").val();
					oldThumbnail = $("#thumbnail").attr('src');
					oldPrice = $("#price").val();
					oldDiscount = $("#discount").val();
					oldAmount = $("#amount").val();
					oldDeliveryPrice = $("#deliveryPrice").val();			
					oldContents = $("#contents").html();	
					
					//
					$("#fmtPrice").val(result.fmtPrice);
					$("#fmtDiscountPrice").val(result.fmtDiscountPrice);
					$("#fmtDiscount").val(result.fmtDiscount);
					$("#fmtAmount").val(result.fmtAmount);
					$("#fmtDeliveryPrice").val(result.fmtDeliveryPrice);
					
					
					changeClassControlBox('cancel');
					changeAmountHeader($("#amount").val());
				}, 
				function()
				{
					
				});
		});
		
		
		$("#return").bind('click', function() {
			
			let currentPage = $("#currentPage").val();
			let pageSize = $("#pageSize").val();
			
			location.href = contextPath + `/product/list.jsp?currentPage=${currentPage}&pageSize=${pageSize}`;
		});
	}
	catch(exp)
	{
		console.error(exp);
	}
});


