package br.com.ok.exception.base;

import lombok.Getter;

/**
 * The Class OKBusinessException.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
public class OKBusinessException extends OKException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7487312282805555611L;

	/** The client id. */
	@Getter
	private String clientId;

	/**
	 * Instantiates a new ok business exception.
	 */
	public OKBusinessException() {
		super();
	}

	/**
	 * Instantiates a new ok business exception.
	 *
	 * @param message
	 *            the message
	 */
	public OKBusinessException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new OK business exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public OKBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new ok business exception.
	 *
	 * @param message
	 *            the message
	 * @param clientId
	 *            the client id
	 */
	public OKBusinessException(String message, String clientId) {
		super(message);
		this.clientId = clientId;
	}

	/**
	 * Instantiates a new ok business exception.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 * @param clientId
	 *            the client id
	 */
	public OKBusinessException(String message, Throwable cause, String clientId) {
		super(message, cause);
		this.clientId = clientId;
	}

}
