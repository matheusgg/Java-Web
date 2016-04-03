package validators;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validadorNascimento")
public class NascimentoValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
		Calendar dataAtual = new GregorianCalendar();
		Calendar dataLimite = new GregorianCalendar();
		dataLimite.set(Calendar.DAY_OF_MONTH, dataAtual.get(Calendar.DAY_OF_MONTH));
		dataLimite.set(Calendar.MONTH, dataAtual.get(Calendar.MONTH));
		dataLimite.set(Calendar.YEAR, dataAtual.get(Calendar.YEAR) - 18);

		Calendar nascimento = new GregorianCalendar();
		nascimento.setTime((Date) value);

		if (nascimento.after(dataLimite)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "O funcionário deve ser maior de 18 anos!", "");
			throw new ValidatorException(msg);
		}

	}

}
