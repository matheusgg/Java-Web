/**
 * OnKnowledge Notification Global Variables
 */
var SEVERITY_INFO = 1;
var SEVERITY_SUCCESS = 2;
var SEVERITY_WARNING = 3;
var SEVERITY_ERROR = 4;

/**
 * Page Setup
 */
$(document).ready(function() {
	initPage();
})

/**
 * Inits the current page
 */
function initPage() {
	pageSetUp();
	adjustLayout();
	autocomplete();
	initQtip2();
	$('.datetimepicker').datetimepicker();
	$(".datepicker").focus(function() {
		removeDatepickerPrevNextTitle();
	});
}

/**
 * Funcao que realiza o logout da aplicacao. Recebe dois parametros que serao
 * utilizados para extraier as informacoes de logout.
 * 
 * @param a
 *            Botao de logout localizado no cabecalho de todas as paginas.
 */
function logout(a) {
	var buttons = '[' + a.data("button-no") + "][" + a.data("button-yes") + "]";
	$.SmartMessageBox({
		title : "<i class='fa fa-sign-out txt-color-orangeDark'></i>"
				+ a.data("logout-msg")
				+ "<span class='txt-color-orangeDark'><strong>"
				+ a.data("logout-msg-usuario") + "</strong></span> ?",
		content : a.data("logout-msg-datail"),
		buttons : buttons
	}, function(ButtonPressed) {
		if (ButtonPressed == a.data("button-yes")) {
			$.post(a.data("logout-action"));
			$.root_.addClass("animated fadeOutUp");
			$.root_.fadeOut(1000, function() {
				document.location.href = a.data("login-page");
			});
		}
	});
}

/**
 * Exibe um smart modal de confirmacao com os parametros informados.
 * 
 * @param title
 *            Titulo que sera exibido no smart modal.
 * @param message
 *            Mensagem que sera exibida.
 * @param icon
 *            Icone exibido ao lado do titulo informado.
 * @param confirmFunction
 *            Funcao que sera executada quando a opcao de confirmacao for
 *            selecionada.
 * @param cancelFunction
 *            Funcao que sera executada quando a opcao de cancelamento for
 *            selecionada.
 * @param labelBtnY
 *            Texto do botao de confirmacao.
 * @param labelBtnN
 *            Texto do botao de cancelamento.
 */
function showSmartConfirmationDialog(title, message, icon, confirmFunction,
		cancelFunction, labelBtnY, labelBtnN) {
	var buttons = "";
	if (labelBtnN != null) {
		buttons = '[' + labelBtnN + ']';
	}
	buttons += '[' + labelBtnY + ']';
	$.SmartMessageBox({
		title : "<i class='fa " + icon
				+ " txt-color-blue' style='margin-right: 10px'></i>" + title,
		content : "<p>" + message + "</p>",
		buttons : buttons
	}, function(ButtonPressed) {
		if (ButtonPressed == labelBtnY) {
			eval(confirmFunction);
		}
		if (ButtonPressed == labelBtnN) {
			eval(cancelFunction);
		}
	});
}

/**
 * Exibe um box de notificacao na parte inferior direita da tela com a mensagem
 * e o titulo informado.
 * 
 * @param title
 * @param message
 * @param severity
 */
function showBigNotification(title, message, severity) {
	animateToTop();
	var configs = prepareBoxConfigs(severity);
	$.bigBox({
		title : title,
		content : "<p>" + message + "</p>",
		color : configs[0],
		timeout : 8000,
		icon : configs[1]
	});
}

/**
 * 
 */
function animateToTop() {
	$("body").animate({
		scrollTop : 0
	}, '500', 'swing');
}

/**
 * Exibe um box de notificacao na parte superior direita da tela com a mensagem
 * e o titulo informado.
 * 
 * @param title
 * @param message
 * @param severity
 */
function showBoxNotification(title, message, severity) {
	if (message.length == 0) {
		title = "<h4 class='custom-box-notification-style'>" + title + "<h4>";
	}
	var configs = prepareBoxConfigs(severity);
	$.smallBox({
		title : title,
		content : "<p>" + message + "</p>",
		color : configs[0],
		timeout : 8000,
		icon : configs[1]
	});
}

/**
 * Prepara as configuracoes de cor e icone dos boxes de notificacoes.
 * 
 * @param title
 * @param message
 * @param severity
 */
function prepareBoxConfigs(severity) {
	var configs = new Array();
	switch (severity) {
	case SEVERITY_INFO: {
		configs[0] = "#296191";
		configs[1] = "fa fa-info-circle fadeInRight animated";
		break;
	}
	case SEVERITY_SUCCESS: {
		configs[0] = "#739E73";
		configs[1] = "fa fa-check fadeInRight animated";
		break;
	}
	case SEVERITY_WARNING: {
		configs[0] = "#C79121";
		configs[1] = "fa fa-warning shake animated";
		break;
	}
	case SEVERITY_ERROR: {
		configs[0] = "#C46A69";
		configs[1] = "fa fa-times shake animated";
		break;
	}
	}
	return configs;
}

/**
 * Realiza um redirecionamento para a URL informada.
 * 
 * @param url
 */
function redirect(url) {
	document.location.href = url;
}

/**
 * Esconde o menu de navegacao a esquerda caso o dispositivo de visualizacao
 * seja um desktop.
 */
function toggleMenu() {
	if ($("body").hasClass("desktop-detected")) {
		$("a[data-action='toggleMenu']")[0].click();
	}
}

/**
 * Ajusta o layout do template de acordo com o dispositivo. Caso a largura do
 * dispositivo seja menor do que 800px, o menu lateral é exibido.
 */
function adjustLayout() {
	window.onresize = adjustMenuLayout;
	adjustMenuLayout();
}

function adjustMenuLayout() {
	var $body = $("body");
	if ($body.width() < 985 && $body.hasClass("menu-on-top")) {
		$body.removeClass("menu-on-top");
		$("nav ul").jarvismenu({
			accordion : !0,
			speed : menu_speed,
			closedSign : '<em class="fa fa-plus-square-o"></em>',
			openedSign : '<em class="fa fa-minus-square-o"></em>'
		})
	} else if ($body.width() >= 985 && !$body.hasClass("menu-on-top")
			&& !$body.hasClass("external-template")) {
		$body.addClass("menu-on-top");
	}
}

/**
 * Remove o titulo (tooltip) dos botoes de navegacao do componente datepicker.
 */
function removeDatepickerPrevNextTitle() {
	$('.ui-datepicker-header > a').removeAttr('title')
}

/**
 * Verifica se o valor do elemento informado possui os caracteres permitidos
 * para utilização no login do usuário. Caso negativo, o campo é resetado.
 * 
 * @param obj
 */
function verifyLoginPermittedCharacters(obj) {
	var $element = $(obj);
	var val = $element.val();
	if (val.match(/[^A-Za-z0-9\.\_]+/g) != null) {
		$element.val('');
	}
}

/**
 * Funcão que permite apenas letras.
 * 
 * @param obj
 */
function alpha(obj, whitespace) {
	var $element = $(obj);
	var val = $element.val();

	if (whitespace) {
		if (val.match(/[^A-Za-z\u00C0-\u00ff\s]+/g)) {
			$element.val('');
		}
	} else {
		if (val.match(/[^A-Za-z\u00C0-\u00ff]+/g) != null) {
			$element.val('');
		}
	}
}

/**
 * Função que permite apenas números.
 * 
 * @param obj
 */
function numeric(obj) {
	var $element = $(obj);
	var val = $element.val();
	if (val.match(/[^0-9]+/g) != null) {
		$element.val('');
	}
}

/**
 * Função que permite apenas letras e números.
 * 
 * @param obj
 */
function alphaNumeric(obj, whitespace) {
	var $element = $(obj);
	var val = $element.val();

	if (whitespace) {
		if (val.match(/[^A-Za-z\u00C0-\u00ff\s0-9]+/g) != null) {
			$element.val('');
		}
	} else {
		if (val.match(/[^A-Za-z\u00C0-\u00ff0-9]+/g) != null) {
			$element.val('');
		}
	}

}

/**
 * @param element
 * @param selectFunction
 */
function autocomplete() {
	var elements = $("[data-autocomplete-source]");
	for (var i = 0; i < elements.length; i++) {
		createAutocomplete($(elements[i]));
	}
}

/**
 * @param element
 */
function createAutocomplete(element) {
	var selectFunction = element.data("autocomplete-select-function");
	var url = element.data("autocomplete-source");
	element
			.autocomplete({
				source : function(request, response) {
					var $iconElement = element.parent().find(
							"i[class*='icon-append']");
					var iconClass;
					if ($iconElement != null) {
						iconClass = $iconElement.attr("class");
						$iconElement.removeClass(iconClass);
					}
					$.ajax({
						url : url + element.val(),
						dataType : "json",
						success : function(data) {
							response(data);
							if ($iconElement != null) {
								$iconElement.addClass(iconClass);
							}
						}
					});
				},
				minLength : 3,
				select : function(event, ui) {
					if (selectFunction != null) {
						eval(selectFunction);
					}
				},
			});
}

/**
 * @param element
 * @param icon
 */
function addIconAppendClasses(element, icon) {
	element.addClass("icon-append").addClass("fa").addClass(icon);
}

/**
 * @param element
 * @param icon
 */
function removeIconAppendClasses(element, icon) {
	element.removeClass("icon-append").removeClass("fa").removeClass(icon);
}

/**
 * Inicializa o tooltip estilizado em toda as paginas
 */
function initQtip2() {
	$("[title]").qtip({
		style : {
			classes : 'qtip-tipsy qtip-shadow'
		},
		position : {
			my : 'top right',
			at : 'bottom left'
		}
	});
}


/**
 * @param data
 */
function initOkEditors(data) {
	initPage();
	destroyOkEditorsInstances(data);

	if (data == null || data.status == "success") {
		CKEDITOR.env.isCompatible = true;
		CKEDITOR.replaceAll(function(textarea, config) {
			var $txt = $(textarea);
			if ($txt.hasClass('ok-editor')) {
				config.language = $txt.data('editor-language');
				config.height = $txt.data('editor-height');
				config.readOnly = $txt.data('editor-readonly');
				config.resize_enabled = $txt.data('editor-resizable');
				config.codeSnippet_theme = 'school_book';
				config.toolbar = [
						{
							name : 'document',
							items : [ 'Source', '-', 'NewPage', 'Preview',
									'Print', '-', 'Templates' ]
						},
						{
							name : 'clipboard',
							items : [ 'Cut', 'Copy', 'Paste', 'PasteText',
									'PasteFromWord', '-', 'Undo', 'Redo' ]
						},
						{
							name : 'editing',
							items : [ 'Find', 'Replace', '-', 'SelectAll', '-',
									'Scayt' ]
						},
						{
							name : 'basicstyles',
							items : [ 'Bold', 'Italic', 'Underline', 'Strike',
									'Subscript', 'Superscript', '-',
									'RemoveFormat' ]
						},
						{
							name : 'paragraph',
							items : [ 'NumberedList', 'BulletedList', '-',
									'Outdent', 'Indent', '-', 'Blockquote',
									'-', 'JustifyLeft', 'JustifyCenter',
									'JustifyRight', 'JustifyBlock', '-',
									'BidiLtr', 'BidiRtl' ]
						},
						{
							name : 'links',
							items : [ 'Link', 'Unlink', 'Anchor' ]
						},
						'/',
						{
							name : 'insert',
							items : [ 'Image', 'CodeSnippet', 'Table',
									'HorizontalRule', 'Smiley', 'SpecialChar',
									'PageBreak' ]
						}, {
							name : 'styles',
							items : [ 'Styles', 'Format', 'Font', 'FontSize' ]
						}, {
							name : 'colors',
							items : [ 'TextColor', 'BGColor' ]
						}, {
							name : 'tools',
							items : [ 'Maximize', 'ShowBlocks' ]
						} ];
				return true;
			}
			return false;
		});
	}
}

/**
 * @param data
 */
function destroyOkEditorsInstances(data) {
	if (data != null && data.status == "begin") {
		for ( var name in CKEDITOR.instances) {
			CKEDITOR.instances[name].destroy();
		}
	}
}