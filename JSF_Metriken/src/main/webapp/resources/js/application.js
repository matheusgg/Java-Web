jQuery(document).ready(function($) {

	madmin.init();

	// make code pretty
	window.prettyPrint && prettyPrint();

	$("#table1").tablesorter({
		widgets : [ 'zebra' ]
	});

	$('a.help-link').pageslide({
		direction : 'left'
	});

});

/** ************** Ajax Loading *********************** */
$('#loadingDiv').hide().ajaxStart(function() {
	$(this).show();
}).ajaxStop(function() {
	$(this).hide();
});

/** *********** Efeito Fade das Páginas **************** */
$(document).ready(function() {
	$('body').animate({
		opacity : '1.0'
	});
});

/** ****** Funções utilizadas pela aplicação ******** */
function showOptionsButtons(source) {
	var node;
	var i;
	node = source.getElementsByTagName('input');
	for (i = 0; i < node.length; i++) {
		var botao = node[i];
		if (botao.className == 'btn') {
			node[i].style.display = 'inline';
		}
	}
}

function hideOptionsButtons(source) {
	var node;
	var i;
	node = source.getElementsByTagName('input');
	for (i = 0; i < node.length; i++) {
		var botao = node[i];
		if (botao.className == 'btn') {
			node[i].style.display = 'none';
		}
	}
}

function resetIncompleteField(src) {
	alert("Teste");
	var value = src.value;
	var length = src.maxLength;
	if (length != value.length) {
		src.value = "";
	}
	return true;
}

function mascaraData(src, e) {
	var tecla = (window.event) ? event.keyCode : e.which;
	if ((tecla > 47 && tecla < 58)) {
		var value = src.value;
		var length = value.length;
		if (length == 2) {
			value += "/";
		} else if (length == 5) {
			value += "/";
		}
		src.value = value;
		return true;
	} else {
		return false;
	}
}


/******************* Modais ********************/
$(function() {
	$('#messagesModal').modal({
		autoOpen: false,
		resizable: false,
	  	closable: false,
	  	maximizable: false,
		draggable: false
	});
 });

/******************* Scroller ********************/
$(function() {
    $('div.nav a').bind('click',function(event){
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollLeft: $($anchor.attr('href')).offset().left
        }, 1000);
        event.preventDefault();
    });
    
});
