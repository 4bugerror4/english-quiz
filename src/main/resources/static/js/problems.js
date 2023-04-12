function updateProblem() {
	
	let data = {
		id: $('#id').val(),
		problem: $('#problem').val(),
		answer: $('#answer').val(),
		hint: $('#hint').val(),
		category: $('#category').val()
	};
	
	$.ajax({
		type: 'PUT',
		url: 'api/problem/update',
		data: JSON.stringify(data),
		contentType: 'application/json; charset=utf-8',
		dataType: 'JSON',
		
		success: function(res) {
			console.log("결과", res),
			alert(res.msg);
			location.reload();
		},
		
		error: function(e) {
			console.log("결과", e),
			alert(JSON.stringify(e.responseJSON.msg), '\t');
		}
	});
	
}

// 모달창 값 전달 받기
$(document).ready(function() {
	$('#updateProblemModal').on('show.bs.modal', function(event) {
		let button = $(event.relatedTarget);
		let id = button.data('id');
		let problem = button.data('problem');
		let answer = button.data('answer'); // Button th:data-title
		let hint = button.data('hint'); // Button th:data-content
		let category = button.data('category'); // Button th:data-content
		let modal = $(this);
		modal.find('.modal-body #id').val(id);
		modal.find('.modal-body #problem').val(problem);
		modal.find('.modal-body #answer').val(answer);
		modal.find('.modal-body #hint').val(hint);
		modal.find('.modal-body #category').val(category);
	});
});

function deleteProblem(pid) {
	
	let result = confirm("정말로 삭제 하시겠습니까?");
	
	let data = {
		id: pid
	}
	
	if (result == true) {
		
		$.ajax({
			type: 'DELETE',
			url: '/api/problem/delete',
			data: JSON.stringify(data),
			contentType: 'application/json; charset=utf-8',
			dataType: 'JSON',
			
			success: function(res) {
				console.log("결과", res),
				alert(res.msg);
				location.reload();
			},
			
			error: function(e) {
				console.log("결과", e),
				alert(JSON.stringify(e.responseJSON.data), '\t');
			}
		});
		
	}
}