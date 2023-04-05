function getProblem() {
	
	let problem;
	
	if (document.getSelection) {
		problem = document.getSelection().toString();
	}
	
	$('input[name=problem]').attr('value',problem);
};

function getAnswer() {
	
	let answer;
	
	if (document.getSelection) {
		answer = document.getSelection().toString();
	}
	
	$('input[name=answer]').attr('value',answer);
};

function getHint() {
	
	let hint;
	
	if (document.getSelection) {
		hint = document.getSelection().toString();
	}
	
	$('input[name=hint]').attr('value',hint);
};

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

// 모달창 값 전달 받기
$(document).ready(function() {
	$('#easyProblemModal').on('show.bs.modal', function(event) {
		let button = $(event.relatedTarget);
		let content = button.data('content'); // th:data-content="|${note.content}|"
		let modal = $(this);
		
		let newText = content.replace(/(<([^>]+)>)/ig,"");
		
		modal.find('.modal-body #content').val(newText);
	});
});