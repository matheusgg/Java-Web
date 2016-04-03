/*
 * Definindo uma mensagem padrão de validação no objetos de mensagens padrão do PrimeFaces
 */
PrimeFaces.locales['en_US'].messages['cpf_invalido'] = '\'{0}\' não é um CPF válido!';

/*
 * Registrando o client validator 'csv.CustomCPFClientValidator' no PrimeFaces
 */
PrimeFaces.validator['csv.CustomCPFClientValidator'] = {
	validate : function(element, value) {
		if (value !== null && value !== "") {

			// ValidationContext contém funções utilitárias para recuperar
			// mensagens
			var vc = PrimeFaces.util.ValidationContext;

			// Se o CPF não for válido
			if (!validarCPF(value)) {

				/*
				 * Recupera a mensagem definida na anotação e inserida no mapa
				 * de data-atributos no método getMetadata do
				 * CustomCPFClientValidator
				 */
				var msg = element.data('p-cpf-msg');

				// Se a mensagem estiver vazia, recupera a mensagem padrão
				if (msg) {
					msg = {
						summary : msg,
						detail : msg
					}
				} else {
					msg = vc.getMessage('cpf_invalido', value);
				}

				/*
				 * Lança uma exceção com a mensagem de validação para ser
				 * exibida para o usuário
				 */
				throw msg;
			}
		}
	}
};

function validarCPF(cpf) {
	var div1, div2;
	cpf = cpf.replace(/[^\d]+/g, '');
	// Verifica tamanho e se é um cpf padrão
	if (cpf.length !== 11 || isCPFPadrao(cpf)) {
		return false;
	}
	div1 = calcDVs(cpf.substring(0, 9));
	div2 = calcDVs(cpf.substring(0, 9) + div1);
	return (cpf == (cpf.substring(0, 9) + div1 + div2));
}

function isCPFPadrao(cpf) {
	if (cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222"
			|| cpf == "33333333333" || cpf == "44444444444"
			|| cpf == "55555555555" || cpf == "66666666666"
			|| cpf == "77777777777" || cpf == "88888888888"
			|| cpf == "99999999999") {
		return true;
	} else {
		return false;
	}
}

function calcDVs(cpf) {
	var soma, digito, resto;
	var peso = [ 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];
	soma = 0;
	for (indice = cpf.length - 1; indice >= 0; indice--) {
		digito = parseInt(cpf.substring(indice, indice + 1));
		soma += digito * peso[peso.length - cpf.length + indice];
	}
	resto = (soma * 10) % 11;
	return (resto == 10 || resto == 11) ? 0 : resto;
}
