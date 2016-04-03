package br.com.ok.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * A factory for creating OKExceptionHandler objects.
 * 
 * @author Matheus
 */
public class OKExceptionHandlerFactory extends ExceptionHandlerFactory {

	/** The parent. */
	private ExceptionHandlerFactory parent;

	/**
	 * Instantiates a new OK exception handler.
	 *
	 * @param parent
	 *            the parent
	 */
	public OKExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	/**
	 * @see javax.faces.context.ExceptionHandlerFactory#getExceptionHandler()
	 */
	@Override
	public ExceptionHandler getExceptionHandler() {
		return new OKExceptionHandler(this.parent.getExceptionHandler());
	}

}
