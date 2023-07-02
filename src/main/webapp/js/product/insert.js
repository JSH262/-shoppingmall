

$(() => {
	const contextPath = $("#form").attr('action');
	const fileUploadUrl = contextPath + '/image/';
	
	$("#contents").summernote({
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
	});
	
	$("#formSubmit").bind('click', function() {
		
		
		//console.log($("#summernote").summernote('code'));
		
		
		let url = contextPath + "/product/insert";		
		let data = new FormData();
		let file = document.getElementById('file');
		let name = $("#name").val();
		let price = $("#price").val();
		let amount = $("#amount").val();
		let deliveryPrice = $("#deliveryPrice").val();
		let categoryId = $("#categoryId").val();
		
		let contents = null;
		
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
		console.log(amount);
		console.log(deliveryPrice);
		console.log(contents);
		console.log(categoryId);
		//*/
		
		data.append('file', file.files[0]);
		data.append('name', name);
		data.append('price', price);
		data.append('amount', amount);
		data.append('deliveryPrice', deliveryPrice);
		data.append('contents', contents);
		data.append('categoryId', categoryId);
		
		
		AjaxForm(url, "POST", data, 
			function(resp)
			{
				console.log(resp);
			}, 
			function()
			{
				
			});
	});
	
	
});
