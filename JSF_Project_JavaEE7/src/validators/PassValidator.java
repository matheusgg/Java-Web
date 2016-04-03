package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("passValidator")
public class PassValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		if (!valor.equals("123")) {
			throw new ValidatorException(new FacesMessage("Erro de validacao! Informe a senha de acesso."));
		}
	}

}
