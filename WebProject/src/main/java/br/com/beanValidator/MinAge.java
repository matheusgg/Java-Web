package br.com.beanValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Utilizando os recursos da especifica��o BeanValidations, � poss�vel criar
 * validadores personalizados atrav�s de annotations definindo uma classe que
 * executar� a valida��o de um campo. A annotation Constraint � respons�vel por
 * definir qual classe ser� respons�vel pela valida��o.
 * 
 * @author Matheus
 * 
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinAgeValidator.class)
public @interface MinAge {
	int idadeMinima();

	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
