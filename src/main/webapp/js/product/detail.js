
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

function inputShow(enable)
{
	if(enable)
	{
	}
	else
	{
		
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
			
			// 수정하기를 누르면 컨트롤을 활성화시킨다.
			inputTextEnable($("#name"));
			hideTag($("#thumbnail"));	//썸네일은 숨기고 파일 컨트롤을 보여준다.
			showTag($("#file"));
			inputTextEnable($("#price"));
			inputTextEnable($("#discount"));
			inputTextEnable($("#amount"));
			inputTextEnable($("#deliveryPrice"));
			inputTextEnable($("#discount"));
			inputTextEnable($("#amount"));
			inputTextEnable($("#deliveryPrice"));
			$("#categoryId").attr('disabled', false);
			
			// 수정하기를 누르면 읽기전용 컨트롤러를 비활성화(숨김)시킨다.
			inputTextDisable($("fmtPrice"));
			inputTextDisable($("fmtDiscountPrice"));
			inputTextDisable($("fmtDiscount"));
			inputTextDisable($("fmtAmount"));
			inputTextDisable($("fmtDeliveryPrice"));
			
			
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
			
			inputTextDisable($("#name"));
			showTag($("#thumbnail"));
			inputTextDisable($("#price"));
			inputTextDisable($("#discount"));
			inputTextDisable($("#amount"));
			inputTextDisable($("#deliveryPrice"));
			inputTextDisable($("#discount"));
			inputTextDisable($("#amount"));
			inputTextDisable($("#deliveryPrice"));
			$("#categoryId").attr('disabled', true);
			showTag($("#file"));
			
			// 취소하기를 누르면 읽기전용 컨트롤러를 활성화시킨다.
			inputTextEnable($("fmtPrice"));
			inputTextEnable($("fmtDiscountPrice"));
			inputTextEnable($("fmtDiscount"));
			inputTextEnable($("fmtAmount"));
			inputTextEnable($("fmtDeliveryPrice"));
			
			$('#save').addClass('node-hide');
			$('#cancel').addClass('node-hide');
			$('#modify').removeClass('node-hide');
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
					let result = resp.result;
					
					if(result.thumbnail)					
						$("#thumbnail").attr('src', result.thumbnail);
						
						
						
					showTag($("#thumbnail"));
					hideTag($("#file"));
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
					$("fmtPrice").val(result.fmtPrice);
					$("fmtDiscountPrice").val(result.fmtDiscountPrice);
					$("fmtDiscount").val(result.fmtDiscount);
					$("fmtAmount").val(result.fmtAmount);
					$("fmtDeliveryPrice").val(result.fmtDeliveryPrice);
					
					inputTextDisable($("#name"));
					inputTextDisable($("#price"));
					inputTextDisable($("#discount"));
					inputTextDisable($("#amount"));
					inputTextDisable($("#deliveryPrice"));
					inputTextDisable($("#discount"));
					inputTextDisable($("#amount"));
					inputTextDisable($("#deliveryPrice"));
					
					// 저장하기를 누르면 읽기전용 컨트롤러를 활성화시킨다.
					inputTextEnable($("fmtPrice"));
					inputTextEnable($("fmtDiscountPrice"));
					inputTextEnable($("fmtDiscount"));
					inputTextEnable($("fmtAmount"));
					inputTextEnable($("fmtDeliveryPrice"));
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


