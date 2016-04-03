package jms;

import javax.jms.Message;
import javax.jms.MessageListener;

//@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
//		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/DefaultQueue") })
public class MessagesReceiver implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println(message.getBody(String.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
