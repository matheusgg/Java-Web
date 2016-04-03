package br.com.beanValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Esta classe � respons�vel por realizar a valida��o do atributo marcado com a
 * anota��o MinAge. Nos tipos parametrizados � necess�rio informar a anota��o
 * respons�vel por este validador e o tipo de dado que se est� trabalhando.
 * 
 * @author Matheus
 * 
 */
public class MinAgeValidator implements ConstraintValidator<MinAge, Integer> {
	private int idadeMinima;

	/**
	 * M�todo utilizado para recuperar os valores da anota��o do atributo.
	 */
	@Override
	public void initialize(MinAge minAge) {
		this.idadeMinima = minAge.idadeMinima();
	}

	/**
	 * M�todo que realizar� a valida��o.
	 */
	@Override
	public boolean isValid(Integer idadeField, ConstraintValidatorContext arg1) {
		boolean isValid = false;
		if (idadeField > this.idadeMinima) {
			isValid = true;
		}
		return isValid;
	}
}
