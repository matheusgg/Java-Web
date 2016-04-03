package server;

import java.io.IOException;
import java.util.logging.Logger;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import model.Message;
import decoder.MessageDecoder;
import encoder.MessageEncoder;

@ServerEndpoint(value = "/chat/{room}/{clientId}", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class MessageEndpoint {

	private static Logger logger = Logger.getLogger(MessageEndpoint.class.getName());

	@OnOpen
	public void openConnection(Session session, @PathParam("room") String room, @PathParam("clientId") String clientId) {
		session.getUserProperties().put("room", room);
		MessageEndpoint.logger.info("Cliente " + clientId + " conectado!");
	}

	@OnMessage
	public void message(Session session, Message message) throws EncodeException, IOException {
		MessageEndpoint.logger.info("Mensagem recebida de " + message.getDestinatario());

		for (Session s : session.getOpenSessions()) {
			if (s.getUserProperties().get("room").equals(session.getUserProperties().get("room"))) {
				s.getBasicRemote().sendObject(message);
			}
		}
	}

	@OnClose
	public void closeConnection(Session session, @PathParam("clientId") String clientId) throws IOException {
		session.close();
		MessageEndpoint.logger.info("Cliente " + clientId + " desconectado!");
	}

}
