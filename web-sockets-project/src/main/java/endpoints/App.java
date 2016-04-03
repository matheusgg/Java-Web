package endpoints;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/MessageHandler/{sala}")
public class App implements Serializable {

	private static final long serialVersionUID = 4667837935386148330L;

	private static final Map<String, List<Session>> SESSIONS = new HashMap<>();

	@OnOpen
	public void open(@PathParam("sala") String sala, Session session) {
		List<Session> sessions = App.SESSIONS.get(sala);
		if (sessions == null) {
			sessions = new ArrayList<>();
			App.SESSIONS.put(sala, sessions);
		}
		sessions.add(session);
	}

	@OnMessage
	public String receiveMessage(@PathParam("sala") String sala, String message, Session session) {
		return message;
	}

	@OnClose
	public void close(@PathParam("sala") String sala, Session session) {
		App.SESSIONS.get(sala).remove(session);
	}

}
