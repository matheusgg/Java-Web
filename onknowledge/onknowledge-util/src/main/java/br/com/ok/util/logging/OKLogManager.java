package br.com.ok.util.logging;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

import org.springframework.stereotype.Component;

/**
 * The Class OKLogManager.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@Component
@SessionScoped
public class OKLogManager implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -104700402209547207L;

	/** The logger. */
	private Logger logger;

	/**
	 * Inits the OKLogManager.
	 */
	@PostConstruct
	public void init() {
		this.logger = Logger.getLogger(OKLogManager.class.getName());
	}

	/**
	 * Gets the logger.
	 *
	 * @return the logger
	 */
	public Logger getLogger() {
		return logger;
	}

}
