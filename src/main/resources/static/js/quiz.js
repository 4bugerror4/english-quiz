function resultProblem() {
	
	let data = {
		id: $('#id').val(),
		answer: $('#answer').val()
	};
	
	console.log(data);
	
	let totalPage = $('#totalPages').text();
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
				
				if (currentPage > totalPage) {
					alert('모든 정답을 맞추셨습니다. 오늘 하루도 고생하셨습니다.');
					location.href="/"
				}
				
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

function categoryResultProblem() {
	
	let data = {
		id: $('#id').val(),
		answer: $('#answer').val()
	};
	
	console.log(data);
	
	let totalPage = $('#totalPages').text();
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
				if (res.data.category == 'word') {
					location.href='/word/quiz?page=' + (currentPage - 1);	
				} else if (res.data.category == 'sentence') {
					location.href='/sentence/quiz?page=' + (currentPage - 1);	
				} else if (res.data.category == 'expression') {
					location.href='/expression/quiz?page=' + (currentPage - 1);	
				}
				
				if (currentPage > totalPage) {
					alert('모든 정답을 맞추셨습니다. 오늘 하루도 고생하셨습니다.');
					location.href="/"
				}
				
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

var voices = [];
function setVoiceList() {
    voices = window.speechSynthesis.getVoices();
}
setVoiceList();
if (window.speechSynthesis.onvoiceschanged !== undefined) {
    window.speechSynthesis.onvoiceschanged = setVoiceList;
}
function speech(txt) {
    if (!window.speechSynthesis) {
        alert("음성 재생을 지원하지 않는 브라우저입니다. 크롬, 파이어폭스 등의 최신 브라우저를 이용하세요");
        return;
    }
    var lang = 'en-US';
    var utterThis = new SpeechSynthesisUtterance(txt);
    utterThis.onend = function (event) {
        console.log('end');
    };
    utterThis.onerror = function (event) {
        console.log('error', event);
    };
    var voiceFound = false;
    for (var i = 0; i < voices.length; i++) {
        if (voices[i].lang.indexOf(lang) >= 0 || voices[i].lang.indexOf(lang.replace('-', '_')) >= 0) {
            utterThis.voice = voices[i]; voiceFound = true;
        }
    }
    if (!voiceFound) {
        alert('voice not found');
        return;
    }
    utterThis.lang = lang;
    utterThis.pitch = 1;
    utterThis.rate = 1;  //속도 
    window.speechSynthesis.speak(utterThis);
}
function speechBtn(id) {
console.log(id);
    const text = document.getElementById(id).innerText;
    speech(text);
};