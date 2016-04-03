package br.com.metriken.util.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class ApplicationExceptionFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory parent;

	public ApplicationExceptionFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		return new ApplicationExceptionHandler(this.parent.getExceptionHandler());
	}

}
