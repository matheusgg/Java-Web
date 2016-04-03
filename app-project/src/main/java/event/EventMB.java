package event;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.PhaseId;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@Named
@ViewScoped
public class EventMB implements Serializable {

	private static final long serialVersionUID = 6936727384103501631L;

	public void testQueue(ActionEvent event) {
		CustomBroadcaster customBroadcaster = new CustomBroadcaster();
		CustomEvent customEvent = new CustomEvent(customBroadcaster, "#{eventMB.showMsg('Mensagem de Teste')}", PhaseId.INVOKE_APPLICATION);
		customBroadcaster.queueEvent(customEvent);
	}

	public void showMsg(String msg) {
		Messages.addInfo(null, msg + " " + Faces.getCurrentPhaseId());
	}

	public void testPreRenderResponseEvent(ComponentSystemEvent event) {
		Messages.addInfo(null, "Test");
	}
}
