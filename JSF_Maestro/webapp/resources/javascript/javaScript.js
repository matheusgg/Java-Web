/**
 * Classe Com os Java Scripts da Aplicacao
 */
function retirarMensagemDeCampo(campo, mensagem) {
	if (campo.value == mensagem) {
		campo.value = '';
	}
}
function adicionarMensagemDeCampo(campo, mensagem) {
	if (campo.value == '') {
		campo.value = mensagem;
	}
}

/**
 * Função que troca a imagem do tipo do item de acordo com a seleçãod da aba
 * 
 * @returns {Boolean}
 */
function trocaImagemTipos() {
	var tipo = document.getElementById("droppableForm:hidden").value;
	if (tipo == 'TabAmbiente') {
		document.getElementById("imgTipoItemAmbiente").src = "/Maestro/javax.faces.resource/climaAmbiente.png.jsf?ln=images/dock";
		document.getElementById("imgTipoItemAudio").src = "/Maestro/javax.faces.resource/audio_preto_branco.png.jsf?ln=images/dock";
		document.getElementById("imgTipoItemLuz").src = "/Maestro/javax.faces.resource/luzCenas_preto_branco.png.jsf?ln=images/dock";
		document.getElementById("imgTipoItemCinema").src = "/Maestro/javax.faces.resource/videoCinema_preto_branco.png.jsf?ln=images/dock";
	} else if (tipo == 'TabSom') {
		document.getElementById("imgTipoItemAmbiente").src = "/Maestro/javax.faces.resource/climaAmbiente_preto_branco.png.jsf?ln=images/dock";
		document.getElementById("imgTipoItemAudio").src = "/Maestro/javax.faces.resource/audio.png.jsf?ln=images/dock";
		document.getElementById("imgTipoItemLuz").src = "/Maestro/javax.faces.resource/luzCenas_preto_branco.png.jsf?ln=images/dock";
		document.getElementById("imgTipoItemCinema").src = "/Maestro/javax.faces.resource/videoCinema_preto_branco.png.jsf?ln=images/dock";
	} else if (tipo == 'TabSeguranca') {
		document.getElementById("imgTipoItemAmbiente").src = "/Maestro/javax.faces.resource/climaAmbiente_preto_branco.png.jsf?ln=images/dock";
		document.getElementById("imgTipoItemAudio").src = "/Maestro/javax.faces.resource/audio_preto_branco.png.jsf?ln=images/dock";
		document.getElementById("imgTipoItemLuz").src = "/Maestro/javax.faces.resource/luzCenas.png.jsf?ln=images/dock";
		document.getElementById("imgTipoItemCinema").src = "/Maestro/javax.faces.resource/videoCinema_preto_branco.png.jsf?ln=images/dock";
	} else if (tipo == 'TabVideo') {
		document.getElementById("imgTipoItemAmbiente").src = "/Maestro/javax.faces.resource/climaAmbiente_preto_branco.png.jsf?ln=images/dock";
		document.getElementById("imgTipoItemAudio").src = "/Maestro/javax.faces.resource/audio_preto_branco.png.jsf?ln=images/dock";
		document.getElementById("imgTipoItemLuz").src = "/Maestro/javax.faces.resource/luzCenas_preto_branco.png.jsf?ln=images/dock";
		document.getElementById("imgTipoItemCinema").src = "/Maestro/javax.faces.resource/videoCinema.png.jsf?ln=images/dock";
	}
	return true;
}

function mascaraData(objeto, caracter) {
	if (window.event) {
		tecla = event.keyCode; // Captura o codigo ASCII da tecla pressionada
	} else {
		tecla = caracter.which;
	}
	if (((tecla < 48 || tecla > 57) && (tecla < 96 || tecla > 105))) { // Decisao
		// que
		// bloqueia
		// caracteres
		// (Permite
		// somente
		// numeros)
		if ((tecla == 8) || (tecla == 0)) { // Decisao que permite utilizar o
			// backspace e delete.
			return true;
		} else {
			return false;
		}
	} else {
		/* Verifica se a caixa de texto contem 2 caracteres inseridos */
		if (objeto.value.length == 2) {
			/* Verificao da Data (Dia) */
			dia = parseInt(objeto.value);
			if (dia > 0 && dia < 32) {
				objeto.value = objeto.value + "/";
				return true;
			} else {
				alert("DIA INVALIDO! POR FAVOR, DIGITE UM VALOR DE 1 A 31");
				objeto.value = "";
				return false;
			}
		} else if (objeto.value.length == 5) {
			/* Verificao da Data (Mes) */
			mes = parseInt(objeto.value.substr(3, 2));
			if (mes > 0 && mes < 13) {
				objeto.value = objeto.value + "/";
				return true;
			} else {
				alert("MES INVALIDO! POR FAVOR, DIGITE UM VALOR DE 1 A 12.");
				objeto.value = objeto.value.substr(0, 3);
				return false;
			}
		} else if (objeto.value.length == 9) {
			/* Verificacao da Data (Ano) */
			ano = parseInt(objeto.value.substr(6, 3));
			year = parseInt(ano + String.fromCharCode(tecla));
			if (year > 190) {
				return true;
			} else {
				alert("ANO INVALIDO! POR FAVOR, DIGITE UM VALOR ACIMA DE 1900.");
				objeto.value = objeto.value.substr(0, 5);
				return false;
			}
		} else if (objeto.value.length == 10) {
			return false;
		}
	}
}

/** ******************* Máscaras ******************* */

function mascaraCampo(campo, Mascara, campoNumerico) {
	if (campoNumerico) {
		var somenteNumeros = permiteNumeros();
		if (somenteNumeros) {
			formataCampo(campo, Mascara);
			return true;
		} else {
			return false;
		}
	} else {
		formataCampo(campo, Mascara);
		return true;
	}
}

function permiteNumeros() {
	var tecla = window.event.keyCode;
	tecla = String.fromCharCode(tecla);
	if (!((tecla >= "0") && (tecla <= "9"))) {
		return false;
	}
	return true;
}

function resetImcompleteField(src) {
	var length = src.value.length;
	var maxLength = src.maxLength;
	if (length != maxLength) {
		src.value = "";
	}
}

function formataCampo(campo, Mascara) {
	var boleanoMascara;
	exp = /\-|\.|\/|\(|\)| /g;
	campoSoNumeros = campo.value.toString().replace(exp, "");
	var posicaoCampo = 0;
	var NovoValorCampo = "";
	var TamanhoMascara = campoSoNumeros.length;
	var i;
	for (i = 0; i <= TamanhoMascara; i++) {
		boleanoMascara = ((Mascara.charAt(i) == "-")
				|| (Mascara.charAt(i) == ".") || (Mascara.charAt(i) == "/"));
		boleanoMascara = boleanoMascara
				|| ((Mascara.charAt(i) == "(") || (Mascara.charAt(i) == ")") || (Mascara
						.charAt(i) == " "));
		if (boleanoMascara) {
			NovoValorCampo += Mascara.charAt(i);
			TamanhoMascara++;
		} else {
			NovoValorCampo += campoSoNumeros.charAt(posicaoCampo);
			posicaoCampo++;
		}
	}
	campo.value = NovoValorCampo;
}
