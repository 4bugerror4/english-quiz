function useTranslate() {
	
	let data = {
		source: $('#korText').val(),
		sourceAndTargetNumber: $('#sourceAndTargetNumber').val()
	};
	
	console.log(data);
	
	$.ajax({
		type: 'POST',
		url: 'api/translate',
		data: JSON.stringify(data),
		contentType: 'application/json; charset=utf-8;',
		dataType: 'JSON',
		
		success: function(res) {
			console.log(res);
			const text = res.data.message.result.translatedText;
			$('#engText').val(text);
		},
		
		error: function(e) {
			console.log(e);
			location.reload();
		}
	});
}

function resetTranslate() {
	$('#korText').val('');
	$('#engText').val('');
}