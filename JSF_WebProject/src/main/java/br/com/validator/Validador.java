package br.com.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("numberValidator")
public class Validador implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		/*
		 * Aqui, os atributos declarados através do componente f:attribute são
		 * recuperados
		 */
		String atributo = String.valueOf(component.getAttributes().get("campoNumero"));
		Integer valorAtributo = Integer.parseInt(atributo);

		String valor = String.valueOf(value);
		FacesMessage msg = null;

		try {
			int numero = Integer.parseInt(valor);
			if (numero >= valorAtributo.intValue()) {
				msg = new FacesMessage("Informe um valor menor que 100.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
		} catch (NumberFormatException e) {
			msg = new FacesMessage("Por favor, informe um valor numérico.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

	}
}
