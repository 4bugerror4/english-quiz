function addProblem() {
	
	let data = {
		problem: $('#problem').val(),
		answer: $('#answer').val(),
		hint: $('#hint').val()
	};
	
	$.ajax({
		type: 'POST',
		url: `/api/problem/add`,
		data: JSON.stringify(data),
		contentType: 'application/json; charset=utf-8',
		dataType: 'JSON',
		
		success: function(res) {
			console.log("성공", res);
			alert(res.msg);
			location.href=`/`;
		},
		
		error: function(e) {
			console.log(e);
			alert(JSON.stringify(e.responseJSON.data), '\t');
		}
	});
};

function addNote() {
	
	let data = {
		subject: $('#subject').val(),
		title: $('#title').val(),
		content: $('#content').val()
	};
	
	console.log(data);
	
	$.ajax({
		type: 'POST',
		url: `/api/note/add`,
		data: JSON.stringify(data),
		contentType: 'application/json; charset=utf-8',
		dataType: 'JSON',
		
		success: function(res) {
			console.log("성공", res);
			alert(res.msg);
			location.href=`/`;
		},
		
		error: function(e) {
			console.log(e);
			alert(JSON.stringify(e.responseJSON.data), '\t');
		}
	});
};

function csvUpload() {
	
	let form = $('#csvUploadForm')[0];
	let formData  = new FormData(form);
	
	formData.append('file', $('#file'));
	
	$.ajax({
		type: 'POST',
		url: '/api/problem/csv/upload',
		data: formData,
		contentType: false, // 필수 : x-www-form-urlencoded로 파싱되는 것을 방지
		processData: false, // 필수 : contentType을 false로 줬을 때 QueryString 자동 설정됨. 해제
		enctype: 'multipart/form-data; charset=utf-8',
		dataType: 'json',
		
		success: function(res) {
			console.log("결과", res);
			alert(res.msg);
			location.href='/';
		},
		error: function(error) {
			console.log('실패', error);
			alert(JSON.stringify(error.responseJSON.data, '\t'));
		}
	});
 
}

// modal autofocus
$(document).on('shown.bs.modal', function () {
    $(this).find('[autofocus]').focus();
});