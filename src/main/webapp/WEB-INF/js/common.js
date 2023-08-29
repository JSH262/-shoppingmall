

function Ajax(url, method, data, success, fail, complete)
{
	$.ajax({
		type: method, //요청방식
		url: url, //요청할 서블릿
		data: data,
		success: success,
		error: fail,
		complete: complete,
		headers :{
			"Content-Type": "application/json"
		}
	});
	/*
	let formData = new FormData();
	formData.append("file", file.files[0]);
	formData.append("name", "test1");
	$.ajax({
		data : formData,
		type : "POST",
		url : "./images/",
		contentType : false,
		processData : false,
		success : function(data) {
			console.log(data);
		}
	});
	//*/
}

function AjaxForm(url, method, data, success, fail, complete)
{
	$.ajax({
		type: method, //요청방식
		url: url, //요청할 서블릿
		data: data,
		contentType : false,
		processData : false,
		success: success,
		error: fail,
		complete: complete 
	});
	/*
	let formData = new FormData();
	formData.append("file", file.files[0]);
	formData.append("name", "test1");
	$.ajax({
		data : formData,
		type : "POST",
		url : "./images/",
		contentType : false,
		processData : false,
		success : function(data) {
			console.log(data);
		}
	});
	//*/
}


function getContextPath()
{
	if(document.contextPath)
	{
		return document.contextPath;
	}
	else
	{
		let uri = location.pathname;
		let arr = uri.split('/');

		if(arr.length >= 3)
		{
			return `/${arr[1]}`;
		}
		else
		{
			return '';
		}
	}
}
