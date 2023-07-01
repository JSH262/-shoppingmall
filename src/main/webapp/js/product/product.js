

$(() => {
	const contextPath = $("#form").attr('action');
	
	$("#summernote").summernote({
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
				//$("#summernote").summernote('insertNode', imgNode);
			}			
		}
	});
	
	$("#formSubmit").bind('click', function() {
		
		
		//console.log($("#summernote").summernote('code'));
		
		//*
		let url = contextPath + "/product/insertOK";		
		let data = new FormData();
		let file = document.getElementById('file');
		let name = $("#name").val();
		let price = $("#price").val();
		let amount = $("#amount").val();
		let deliveryPrice = $("#deliveryPrice").val();
		let contents = $("#contents").val();
		
		console.log(name);
		console.log(price);
		console.log(amount);
		console.log(deliveryPrice);
		console.log(contents);
		
		data.append('file', file.files[0]);
		data.append('name', name);
		data.append('price', price);
		data.append('amount', amount);
		data.append('deliveryPrice', deliveryPrice);
		data.append('contents', contents);
		
		
		AjaxForm(url, "POST", data, 
			function(resp)
			{
				console.log(resp);
			}, 
			function()
			{
				
			});
		//*/
	});
	
	
});
