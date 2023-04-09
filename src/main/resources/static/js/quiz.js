function resultProblem() {
	
	let data = {
		id: $('#id').val(),
		answer: $('#answer').val()
	};
	
	console.log(data);
	
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

//멀티플 퀴즈 버튼 클릭시 정답 input 에 담기
function multipleQuizResult(answer) {
	
	let data = {
		id: $('#id').val(),
		answer: answer
	}
	
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
				location.reload();
			} else if (res.code == -1) {
				return;
			}
			
		},
		error: function(e) {
			console.log("실패", e);
			alert(JSON.stringify(e.responseJSON), "\t");
			location.reload();
		}
	});
	
}