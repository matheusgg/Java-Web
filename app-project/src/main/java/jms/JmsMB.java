package jms;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

@Named
@RequestScoped
public class JmsMB implements Serializable {

	private static final long serialVersionUID = -995198066930872033L;

	// @Inject
	// @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
	private JMSContext jmsContext;

	// @Resource(lookup = "java:/jms/queue/DefaultQueue")
	private Queue queue;

	private String message;

	public void sendMessage() {
		JMSProducer producer = this.jmsContext.createProducer();
		producer.send(this.queue, this.message);
		this.message = null;
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
