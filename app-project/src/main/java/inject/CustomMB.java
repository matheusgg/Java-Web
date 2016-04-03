package inject;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

@Named
@ApplicationScoped
public class CustomMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -644637350804837293L;

	@Inject
	private SessionMB sessionMB;

	@PostConstruct
	public void init() {
		this.getClass();
	}

	public void showMessage(String msg) {
		Messages.addInfo(null, "Mensagem de Teste");
	}

	public void prepareMessage(ActionEvent event) {
		System.out.println("CustomMB.prepareMessage()");
	}

	/**
	 * @return the sessionMB
	 */
	public SessionMB getSessionMB() {
		return sessionMB;
	}

	/**
	 * @param sessionMB
	 *            the sessionMB to set
	 */
	public void setSessionMB(SessionMB sessionMB) {
		this.sessionMB = sessionMB;
	}

}
