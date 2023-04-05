function resultProblem() {
	
	
	let data = {
		id: $('#id').val(),
		answer: $('#answer').val()
	};
	
	let currentPage = $('#currentPage').text();
	currentPage *= 1;
	
	$.ajax({
		type: 'POST',
		url: `/api/quiz/result`,
		data: JSON.stringify(data),
		contentType: 'application/json; charset=utf-8',
		dataType: 'JSON',
		
		success: function(res) {
			console.log("결과", res);
			alert(res.msg);
			
			if (res.code == 1) {
				currentPage += 1;
				location.href='/quiz?page=' + (currentPage - 1);	
			} else if (res.code == -1) {
				location.reload();
			}
			
			
		},
		error: function(e) {
			console.log("실패", e);
			alert(JSON.stringify(e.responseJSON), "\t");
			location.reload();
		}
	});
	
}