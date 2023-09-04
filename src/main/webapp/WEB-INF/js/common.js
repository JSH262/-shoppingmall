

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

$(() => {
	
	const categoryDownNode = $(`
		<li class="list-group-item container">	
			<div class="row">
				<a class="col text-dark text-decoration-none" name="offcanvasCategoryDownData" href="/product/list?productCategoryId="></a>
				<div class="col d-flex justify-content-end" name="offcanvasCategoryDownShow" data-bs-category-id="">
					<i class="bi bi-arrow-bar-right"></i>
				</div>							
			</div>
		</li>
	`);
	const categoryDown2Node = $(`
		<li class="list-group-item">
			<a class="col text-dark text-decoration-none" name="offcanvasCategoryDown2Data" href="/product/list?productCategoryId="></a>
		</li>
		`);
	let catDnList = document.catDownList;
	let catDn2List = document.catDownList2; 
	const CONTEXT_PATH = getContextPath();
	
	try
	{
		const ocCatDn = new bootstrap.Offcanvas('#offcanvasCategoryDown');
		const ocCatDn2 = new bootstrap.Offcanvas('#offcanvasCategoryDown2');
		
		const ocCategory = document.getElementById('offcanvasCategory')
		ocCategory.addEventListener('hide.bs.offcanvas', function() {
			ocCatDn.hide();
			ocCatDn2.hide();
		})
		
		$("#offcanvasCategory").find('div[name=offcanvasCategoryDownShow]').bind('click', function() {
			let catId = parseInt($(this).attr('data-bs-category-id'));
			const catData = catDnList[catId];
			let cdBody = $("ul[name=offcanvasCategoryDownList]");

			cdBody.empty();
			for(let i = 0; i<catData.length; i++)
			{
				let cdNode = categoryDownNode.clone();
				let dataNode = cdNode.find('a[name=offcanvasCategoryDownData]');
								
				cdNode.find('div[name=offcanvasCategoryDownShow]').attr('data-bs-category-id', catData[i].id);
				cdNode.find('div[name=offcanvasCategoryDownShow]').bind('click', function() {					
					let cd2Id = parseInt($(this).attr('data-bs-category-id'));
					let cd2Body = $('ul[name=offcanvasCategoryDown2List]');
					
					cd2Body.empty();
					for(let q = 0; q<catDn2List[cd2Id].length; q++)
					{
						let cd2 = catDn2List[cd2Id][q];
						let cd2Node = categoryDown2Node.clone();
						let cd2a = cd2Node.find('a');

						cd2a.attr('href', CONTEXT_PATH + cd2a.attr('href') + cd2.id);
						cd2a.text(cd2.name);	
						
						cd2Body.append(cd2Node);
					}
					ocCatDn2.show();
				});
				dataNode.attr('href',  CONTEXT_PATH + dataNode.attr('href') + catData[i].id);
				dataNode.text(catData[i].name);
				
				
				cdBody.append(cdNode);
				
			}
			
			ocCatDn.show();
		});
		
		
		
		
	}
	catch(exp)
	{
		console.error(exp);
	}
	
	
	
});

