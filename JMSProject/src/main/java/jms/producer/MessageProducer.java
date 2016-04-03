package jms.producer;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import jms.consumer.MessageConsumer;

/**
 * JMS é uma especificacao e uma API desenvolvida para envio e recebimento de
 * mensagens entre aplicacoes Java EE. Existem varias implementacoes, sendo a
 * Open Java Messeging Service a implementacao de referencia. O JMS trabalha de
 * duas formas, com filas e topicos. Na abordagem de filas, as mensagens sao
 * enviadas para a fila e todos os clientes recebem as mensagens dessa fila. Já
 * na abordagem de topicos, a mensagens sao enviadas para um grupo de mensagens
 * identificado por um assunto (subject) e os clientes consomem essas mensagens
 * desde que estejam registrados no topico. As filas e os topicos sao conhecidos
 * como destinations.
 * 
 * @author Matheus
 * 
 */
@Stateless
public class MessageProducer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2601853510482883286L;

	/*
	 * Com JMSConnectionFactory especifica uma fabrica (Connectionfactory) para
	 * criacao de objetos JMSContext. A fabrica e o nome da ja foram pre
	 * configurados no glassfish
	 */
	@Inject
	@JMSConnectionFactory("jms/__defaultConnectionFactory")
	private JMSContext jmsContext;

	/*
	 * Injeta uma fila para envio das mensagens. Essa fila ja foi previamente
	 * configurada no glassfish com o seguinte nome jndi.
	 */
	@Resource(mappedName = "jms/queueDestination")
	private Queue fila;

	@Resource(mappedName = "jms/topicFactory")
	private TopicConnectionFactory topicConnectionFactory;

	@Resource(mappedName = "jms/topicDestination")
	private Topic topic;

	public void sendMessage(String message) {
		JMSProducer producer = this.jmsContext.createProducer();
		producer.send(this.fila, message);

		try {
			// Criando uma conexao de topico
			TopicConnection topicConnection = this.topicConnectionFactory.createTopicConnection();

			/*
			 * Criando um topicSession informando que o tipo de transacao é
			 * non-trasacted e o modo de notificacao de recebimento é imediato.
			 */
			TopicSession topicSession = (TopicSession) topicConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Criando um publicador de topicos
			TopicPublisher topicPublisher = topicSession.createPublisher(this.topic);

			// Arqui a mensagem é publicada no topico informado
			topicPublisher.publish(topicSession.createTextMessage(message + " 2"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
