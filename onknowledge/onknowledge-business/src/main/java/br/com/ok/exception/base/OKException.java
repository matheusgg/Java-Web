package br.com.ok.exception.base;

/**
 * The Class OKException.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
public class OKException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3216713444784472121L;

	/**
	 * Instantiates a new OK exception.
	 */
	public OKException() {
		super();
	}

	/**
	 * Instantiates a new OK exception.
	 *
	 * @param message
	 *            the message
	 */
	public OKException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new OK exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public OKException(String message, Throwable cause) {
		super(message, cause);
	}
}
