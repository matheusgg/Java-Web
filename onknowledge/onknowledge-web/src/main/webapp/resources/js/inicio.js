$(document).ready(function() {
	$('#disciplinas').nestable();
	$('.dd').nestable('collapseAll');
	initCommentsEditor();
});

function initCommentsEditor(data) {
	if (data != null) {
		initPage();
	}

	var $editor = $('#postComment');
	var language = $editor.data('language');
	var height = $editor.data('height');

	$editor.summernote({
		lang : language,
		height : height,
		focus : false,
		tabsize : 2,
		toolbar : [ [ 'style', [ 'bold', 'italic', 'underline', 'clear' ] ],
				[ 'fontsize', [ 'fontsize' ] ], [ 'color', [ 'color' ] ],
				[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
				[ 'height', [ 'height' ] ], ]
	});
}

function getCommentContent() {
	$("#descricaoComentario").val(preparaConteudoComentario($('#postComment').code()));
}

function preparaConteudoComentario(content){
	var value = content.replace(/&nbsp;/g, "").replace(/<br>/g, "").replace(/\s/g, "").replace(/<p><\/p>/g, "");
	if(value === ""){
		return value;
	}
	return content;
}