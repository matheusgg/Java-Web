package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import model.CPF;

@FacesValidator(value = "validadorCPF")
public class CPFValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
		CPF cpf = (CPF) value;
		int identificacao = cpf.getNumeroIdentificacao();
		int primeiroDigitoVerificador = cpf.getPrimeiroDigitoVerificador();
		int segundoDigitoVerificador = cpf.getSegundoDigitoVerificador();

		if (!this.validaCPF(identificacao, primeiroDigitoVerificador, segundoDigitoVerificador)) {
			String numero = identificacao + "-" + primeiroDigitoVerificador + segundoDigitoVerificador;
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "O número " + numero + " não é um CPF válido!!!", "");
			throw new ValidatorException(msg);
		}
	}

	private boolean validaCPF(int identificacao, int primeiroDigito, int segundoDigito) {
		long digito1 = this.modulo11((long) identificacao);
		long digito2 = this.modulo11((long) identificacao * 10 + primeiroDigito);
		return primeiroDigito == digito1 && segundoDigito == digito2;
	}

	private long modulo11(long numero) {
		long soma = 0;
		long multiplicador = 2;
		while (numero > 0) {
			long digito = numero % 10;
			soma += multiplicador * digito;
			numero /= 10;
			multiplicador++;
		}
		long resto = soma % 11;
		if (resto < 2) {
			return 0;
		} else {
			return 11 - resto;
		}
	}

}
