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

// modal autofocus
$(document).on('shown.bs.modal', function () {
    $(this).find('[autofocus]').focus();
});