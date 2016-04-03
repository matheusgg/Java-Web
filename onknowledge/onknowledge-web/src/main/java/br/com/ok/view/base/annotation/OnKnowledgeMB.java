package br.com.ok.view.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * The Interface OnKnowledgeMB.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@Named
@ViewScoped
@Stereotype
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
public @interface OnKnowledgeMB {

}
