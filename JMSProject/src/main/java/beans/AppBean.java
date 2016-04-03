package beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jms.producer.MessageProducer;

@Named
@RequestScoped
public class AppBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8745992227354478975L;

	@Inject
	private MessageProducer producer;

	private String message;

	public void sendMessage() {
		this.producer.sendMessage(this.message);
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
