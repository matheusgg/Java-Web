package br.com.beanValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Utilizando os recursos da especificação BeanValidations, é possível criar
 * validadores personalizados através de annotations definindo uma classe que
 * executará a validação de um campo. A annotation Constraint é responsável por
 * definir qual classe será responsável pela validação.
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
