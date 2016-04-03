package br.com.maestroautomacoes.portal.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class ExceptionFactory extends ExceptionHandlerFactory {
	private ExceptionHandlerFactory parent;

	public ExceptionFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		return new ExceptionWrapper(this.parent.getExceptionHandler());
	}
}
