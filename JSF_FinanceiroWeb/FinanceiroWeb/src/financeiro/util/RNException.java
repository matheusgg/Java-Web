package financeiro.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class RNException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RNException() {
		super();
	}

	public RNException(String message, Throwable cause) {
		super(message, cause);
	}

	public RNException(String message) {
		super(message);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
	}

	public RNException(Throwable cause) {
		super(cause);
	}

}
