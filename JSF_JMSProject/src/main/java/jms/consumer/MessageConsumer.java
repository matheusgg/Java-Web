package jms.consumer;

import java.io.Serializable;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * MDB responsavel por ser notificado e receber as mensagens da fila de
 * mensagens especificadas nas anotacoes ActivationConfigProperty
 * 
 * @author Matheus
 * 
 */

/*
 * Configuracao utilizada para receber mensagens de topicos. Com esses
 * parametros, é feito o registro desse listener no topico.
 */
// @MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName =
// "destinationType", propertyValue = "javax.jms.Topic"),
// @ActivationConfigProperty(propertyName = "destination", propertyValue =
// "TopicDestination"),
// @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue =
// "Auto-acknowledge") })
@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "QueueDestination"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class MessageConsumer implements MessageListener, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4882010964879482221L;

	/**
	 * Metodo chamado quando uma mensagem é enviada para a fila.
	 */
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println(message.getBody(String.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
