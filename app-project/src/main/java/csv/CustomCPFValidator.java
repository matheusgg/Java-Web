package csv;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class CustomCPFValidator implements ConstraintValidator<CPF, String> {

	private CPFValidator wrappedValidator;

	public CustomCPFValidator() {
		this.getClass();
	}

	@Override
	public void initialize(CPF constraintAnnotation) {
		this.wrappedValidator = new CPFValidator();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return this.isCPFValid(value);
	}

	private boolean isCPFValid(String cpf) {
		try {
			this.wrappedValidator.assertValid(cpf);
			return true;
		} catch (InvalidStateException e) {
			return false;
		}
	}

}
