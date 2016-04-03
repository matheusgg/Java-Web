package beanValidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CodigoBeanValidator implements ConstraintValidator<CodigoValidator, Integer> {
	private int min;
	private int max;

	@Override
	public void initialize(CodigoValidator validatorAnnotation) {
		this.min = validatorAnnotation.min();
		this.max = validatorAnnotation.max();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext arg1) {
		boolean retorno = true;
		if (value < this.min || value > this.max) {
			retorno = false;
		}
		return retorno;
	}

}
