package encoder;

import java.util.logging.Logger;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import model.Message;

public class MessageEncoder implements Encoder.Text<Message> {

	private static Logger logger = Logger.getLogger(MessageEncoder.class.getName());

	@Override
	public String encode(Message message) throws EncodeException {
		return Json.createObjectBuilder().add("destinatario", message.getDestinatario()).add("mensagem", message.getMensagem()).build().toString();
	}

	@Override
	public void destroy() {
		MessageEncoder.logger.info("MessageEncoder destroy");
	}

	@Override
	public void init(EndpointConfig config) {
		MessageEncoder.logger.info("MessageEncoder init");
	}
}
