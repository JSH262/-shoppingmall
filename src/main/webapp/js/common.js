

function Ajax(url, method, data, success, fail)
{
	$.ajax({
		type: method, //요청방식
		url: url, //요청할 서블릿
		data: data,
		success: success,
		error: fail
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

function AjaxForm(url, method, data, success, fail)
{
	$.ajax({
		type: method, //요청방식
		url: url, //요청할 서블릿
		data: data,
		contentType : false,
		processData : false,
		success: success,
		error: fail
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