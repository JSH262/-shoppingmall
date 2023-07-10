

$(() => {
	const contextPath = $("#form").attr('action');
	const fileUploadUrl = contextPath + '/image/';
	const currentPage = $('#currentPage').val();
	const pageSize = $('#pageSize').val();
	const summernoteParams = {
		tabsize:2,
		lang: 'ko-KR',
		height: 500,
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
	}
	
	
	$("#contents").summernote(summernoteParams);
	
	$("#formSubmit").bind('click', function() {
		
		
		//console.log($("#summernote").summernote('code'));
		
		
		let url = contextPath + "/product/insert";		
		let data = new FormData();
		let file = document.getElementById('file');
		let name = $("#name").val();
		let price = $("#price").val();
		let discount = $("#discount").val();
		let amount = $("#amount").val();
		let deliveryPrice = $("#deliveryPrice").val();
		let categoryId = $("#categoryId").val();		
		let contents = null;
		
		if($("#contents").summernote)
		{
			if($(".note-editor.note-frame").length == 1)
			{	
				contents = $("#contents").summernote('code');
			}
			else
			{
				contents = $("#contents").html();	
			}
		}
		else
		{
			contents = $("#contents").html();
		}
				
		/*
		console.log(name);
		console.log(price);
		console.log(amount);
		console.log(deliveryPrice);
		console.log(contents);
		console.log(categoryId);
		//*/
		
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
				if(resp.code == 0)
				{
					location.href = resp.result; 	
				}
				else
				{
					alert(resp.msg);
				}	
			}, 
			function(err)
			{
				console.error(err);
			});
	});
	
	
	$("#return").bind('click', function() {
		location.href = `${contextPath}/product/list.jsp?currentPage=${currentPage}&pageSize=${pageSize}`;
	});
	
	
	$("#preview").bind('click', function() {
		if($("#preview").css("display") != 'none')
		{
			$("#insertView").css('display', 'inline');
			$("#preview").css('display', 'none');
			
			 $("#contents").summernote('destroy');
		}
	});
	
	$("#insertView").bind('click', function() {
		if($("#insertView").css("display") != 'none')
		{
			$("#preview").css('display', 'inline');
			$("#insertView").css('display', 'none');
			
			$("#contents").summernote(summernoteParams);			
		}
	});
	
	
});
