function resultProblem() {
	
	
	let data = {
		id: $('#id').val(),
		answer: $('#answer').val()
	};
	
	$.ajax({
		type: 'POST',
		url: `/api/quiz/result`,
		data: JSON.stringify(data),
		contentType: 'application/json; charset=utf-8',
		dataType: 'JSON',
		
		success: function(res) {
			console.log("결과", res);
			
			if (res.code == 1) {
				alert(res.msg);
				location.reload();
			} else if (res.code == -1) {
				alert(res.msg)
				$('#answer').val('');
			}
		},
		error: function(e) {
			console.log("실패", e);
			alert(JSON.stringify(e.responseJSON), "\t");
		}
	});
	
}