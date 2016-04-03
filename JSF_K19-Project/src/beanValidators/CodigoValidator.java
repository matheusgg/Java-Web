package beanValidators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CodigoBeanValidator.class)
public @interface CodigoValidator {
	String message();

	int max();

	int min();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
