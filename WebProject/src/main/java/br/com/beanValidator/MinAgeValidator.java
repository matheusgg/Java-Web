package br.com.beanValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Esta classe é responsável por realizar a validação do atributo marcado com a
 * anotação MinAge. Nos tipos parametrizados é necessário informar a anotação
 * responsável por este validador e o tipo de dado que se está trabalhando.
 * 
 * @author Matheus
 * 
 */
public class MinAgeValidator implements ConstraintValidator<MinAge, Integer> {
	private int idadeMinima;

	/**
	 * Método utilizado para recuperar os valores da anotação do atributo.
	 */
	@Override
	public void initialize(MinAge minAge) {
		this.idadeMinima = minAge.idadeMinima();
	}

	/**
	 * Método que realizará a validação.
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
