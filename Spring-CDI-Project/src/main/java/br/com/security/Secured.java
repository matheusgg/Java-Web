package br.com.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;
import javax.enterprise.util.Nonbinding;

/**
 * Esta annotation servirá para anotar os métodos onde o interceptor agirá, ou
 * seja, o interceptor interceptará apenas os métodos anotados com esta
 * anotação, por este motivo é necessário anotá-la com '@InterceptorBinding'
 * 
 * @author Matheus
 * 
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Secured {

	@Nonbinding
	String[] value() default {};

}