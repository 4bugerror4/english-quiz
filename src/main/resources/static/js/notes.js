function getProblem(nId) {
	
	let problem;
	
	if (document.getSelection) {
		problem = document.getSelection().toString();
	}
	
	$('input[name=problem'+ nId +']').attr('value',problem);
};

function getAnswer(nId) {
	
	let answer;
	
	if (document.getSelection) {
		answer = document.getSelection().toString();
	}
	
	$('input[name=answer'+ nId +']').attr('value',answer);
};

function getHint(nId) {
	
	let hint;
	
	if (document.getSelection) {
		hint = document.getSelection().toString();
	}
	
	$('input[name=hint'+ nId +']').attr('value',hint);
};

function addProblem(nId) {
	
	let data = {
		problem: $('#problem' + nId).val(),
		answer: $('#answer' + nId).val(),
		hint: $('#hint' + nId).val()
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
			$('#problem' + nId).val('');
			$('#answer' + nId).val('');
			$('#hint' + nId).val('');
		},
		
		error: function(e) {
			console.log(e);
			alert(JSON.stringify(e.responseJSON.data), '\t');
		}
	});
};

function updateNote(nId) {
	
	let data = {
		id: nId,
		subject: $('#subject' + nId).val(),
		title: $('#title' + nId).val(),
		content: $('#content' + nId).val()
	};
	
	console.log(data);
	
	$.ajax({
		type: 'PUT',
		url: `/api/note/update`,
		data: JSON.stringify(data),
		contentType: 'application/json; charset=utf-8',
		dataType: 'JSON',
		
		success: function(res) {
			console.log("결과", res);
			alert(res.msg);
			location.reload();
		},
		
		error: function(e) {
			console.log(e);
			alert(JSON.stringify(e.responseJSON.data), '\t');
		}
	});
	
}

function deleteNote(nId) {
	
	let result = confirm("정말로 삭제 하시겠습니까?");
	
	let data ={
		id: nId
	};
	
	if (result == true) {
		$.ajax({
			type: 'DELETE',
			url: `/api/note/delete`,
			data: JSON.stringify(data),
			contentType: 'application/json; charset=utf-8',
			dataType: 'JSON',
			
			success: function(res) {
				console.log("결과", res);
				alert(res.msg);
				location.reload();
			},
			
			error: function(e) {
				console.log(e);
				alert(JSON.stringify(e.responseJSON.data), '\t');
			}
		});
	}
	
}













