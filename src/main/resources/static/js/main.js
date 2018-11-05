/**
 * 
 */

$(document).ready(function(){
	$('.table .delBtn').on('click', function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('#myModal #delRef').attr('href', href);
		$('#myModal').modal();
	});
});

$(document).ready(function(){
	$('.container .nBtn').on('click', function(event){
		event.preventDefault();
		var href = $(this).attr('href');
			$.get(href, function(pontoPauta, status){
				$('.myForm #ordem').val(pontoPauta.ordem);
				$('.myForm #descricao').val(pontoPauta.descricao);
				$('.myForm #tempo').val(pontoPauta.tempo);
				$('.myForm #discussao').val(pontoPauta.discussao);
				$('.myForm #responsavel').val(pontoPauta.responsavel);
				$('.myForm #tipo').val(pontoPauta.tipo);
			});
			$('.myForm #exampleModal').modal();
	});
});
