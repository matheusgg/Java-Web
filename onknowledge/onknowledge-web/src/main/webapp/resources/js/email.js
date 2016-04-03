$(document).ready(function() {
	getNewUserMails();
	prepareNewUserMailsSearch();
});

function initNewMailPage(data) {
	initOkEditors(data);
	inicializaDestinatarios();
}

function inicializaDestinatarios() {
	$("#destinatarios").select2();
}

function preparaDestinatariosSelecionados() {
	$("#destinatariosSelecionados").val($("#destinatarios").val());
}

/**
 * @param inputFile
 * @param uploadFunction
 * @param errorMessageSection
 * @returns {Boolean}
 */
function uploadSelectedFiles(inputFile, uploadFunction, errorMessageSection) {
	var files = inputFile.files;
	for (var i = 0; i < files.length; i++) {
		var file = files[i];
		if (file.size > 10485760) { // 10MB
			$(errorMessageSection).text(
					$(inputFile).data("exceeded-max-file-size") + file.name);
			$(inputFile).val("");
			return false;
		}
	}
	uploadFunction();
}

/**
 * 
 */
function prepareNewUserMailsSearch() {
	setInterval(function() {
		getNewUserMails()
	}, 30000);
}

/**
 * 
 */
function getNewUserMails() {
	var url = $("#newMailsSection").data("url");
	$.ajax(url).done(function(data) {
		showNewUserMails(data);
	});
}

/**
 * Atualiza o painel de mensagens recentes com as mensagens da caixa de entrada do usuario logado
 * 
 * @param data
 */
function showNewUserMails(data) {
	var mailPageLocation = $("#newMailsSection").data("mail-page-location");
	var notificationBody = $("#notification-body").empty();
	var qtdMensagensNaoLidas = 0;
	
	// Criando os itens de mensagens
	for(var i = 0; i < data.length; i++){
		var emailRecebido = data[i];
		
		if(!emailRecebido.lido){
			qtdMensagensNaoLidas++;
		}
		
		var li = $("<li class='ok-notification-item' />");
		
		var span = $("<span class='" + (!emailRecebido.lido ? 'unread' : '') + "' />");
		span.appendTo(li);
		
		var profilePicture = emailRecebido.email.usuarioRemetente.profilePicture;
		var profilePictureSrc;
		if(profilePicture != null) {
			profilePictureSrc = profilePicture.encodedData;
		} else {
			if(emailRecebido.email.usuarioRemetente.sexo === "MASCULINO") {
				profilePictureSrc = $("#newMailsSection").data("male-picture");
			} else {
				profilePictureSrc = $("#newMailsSection").data("female-picture");
			}
		}
		var img = $("<img src='" + profilePictureSrc + "' class='air air-top-left margin-top-5' width='40' height='40' />");
		img.appendTo(span);
		
		var a = $("<a href='" + (mailPageLocation + emailRecebido.id) + "' class='msg' />");
		a.appendTo(span);
		
		var from = $("<span class='from' />").text(emailRecebido.email.usuarioRemetente.login);
		from.appendTo(a);
		
		var when = $("<time />").text(moment(emailRecebido.email.dataEnvio).format("DD/MM/YYYY HH:mm"));
		when.appendTo(a);
		
		var subject = $("<span class='subject' />").text(emailRecebido.email.assunto);
		subject.appendTo(a);

		li.appendTo(notificationBody);
	}

	// Atualizando os campos de exibicao de qtd de mensagens nao lidas
	var $qtdMsgs = $(".qtdMsgsNaoLidas");
	if (qtdMensagensNaoLidas > 0) {
		$qtdMsgs.removeClass("ok-hidden");
		$qtdMsgs.text(qtdMensagensNaoLidas)
	} else if (!$qtdMsgs.hasClass('ok-hidden')) {
		$qtdMsgs.addClass("ok-hidden");
	}

	// Atualizando a hora de atualizacao
	var now = moment($.now()).format("DD/MM/YYYY HH:mm:ss");
	$("#lastNewMailsUpdate").text(now);
}
