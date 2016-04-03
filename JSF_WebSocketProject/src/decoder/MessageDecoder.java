package decoder;

import java.io.StringReader;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import model.Message;

public class MessageDecoder implements Decoder.Text<Message> {

	private static Logger logger = Logger.getLogger(MessageDecoder.class.getName());

	@Override
	public Message decode(String msg) throws DecodeException {
		StringReader reader = new StringReader(msg);
		Message message = new Message();
		
		try(JsonReader jsonReader = Json.createReader(reader)){
			JsonObject jsonObject = jsonReader.readObject();
			message.setDestinatario(jsonObject.getString("destinatario"));
			message.setMensagem(jsonObject.getString("mensagem"));
		}
		
		return message;
	}

	@Override
	public boolean willDecode(String s) {
		return true;
	}

	@Override
	public void destroy() {
		MessageDecoder.logger.info("MessageDecoder destroy");
	}

	@Override
	public void init(EndpointConfig config) {
		MessageDecoder.logger.info("MessageDecoder init");
	}

}
