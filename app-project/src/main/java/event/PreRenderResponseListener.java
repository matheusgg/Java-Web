package event;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

public class PreRenderResponseListener implements PhaseListener {

	private static final long serialVersionUID = -3236872737900392998L;

	@Override
	public void afterPhase(PhaseEvent event) {
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		Faces.getApplication().publishEvent(Faces.getContext(), PreRenderResponseEvent.class, Faces.getViewRoot());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
}
