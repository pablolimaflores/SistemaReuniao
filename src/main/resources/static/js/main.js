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

function sleep(milliseconds) {
	  var start = new Date().getTime();
	  for (var i = 0; i < 1e7; i++) {
	    if ((new Date().getTime() - start) > milliseconds){
	      break;
	    }
	  }
	}

/*Para preencher os inputs com as informações, é preciso comentar o 'event.preventDefault',
clicar no botão editar e depois vir aqui e descomentar o 'event.preventDefault' e clicar em editar novamente.
Tentei usar uma função de espera mas ainda não deu certo.
*/
$(document).ready(function(){
	
		$('.table .eBtn').on('click',function(event){
			setTimeout(function(){
				event.preventDefault(event);		
				
			}, 100);
		});
			$('.myForm #exampleModal').modal();
});


$(document).ready(function(){
	$('.container .nBtn').on('click', function(event){
		event.preventDefault();
			$('.myForm #exampleModal').modal();
	});
});

$(document).ready(function(){
	$('.container .execBtn').on('click', function(event){
		event.preventDefault();
		var href = $(this).attr('href');
			$.get(href, function(pontoPauta, status){
				$('.myForm #ordem').val(pontoPauta.ordem);
				$('.myForm #descricao').val(pontoPauta.descricao);			
				$('.myForm #discussao').val(pontoPauta.discussao);			
			});
			$('.myForm #execModal').modal();
	});
});