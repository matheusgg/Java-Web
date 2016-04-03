package br.com.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;
import javax.enterprise.util.Nonbinding;

/**
 * Esta annotation servir� para anotar os m�todos onde o interceptor agir�, ou
 * seja, o interceptor interceptar� apenas os m�todos anotados com esta
 * anota��o, por este motivo � necess�rio anot�-la com '@InterceptorBinding'
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