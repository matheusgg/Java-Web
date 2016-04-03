package listeners;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class CustomPhaseListener implements PhaseListener {

	private static final long serialVersionUID = -9077370087684822238L;

	@Override
	public void afterPhase(PhaseEvent event) {
		System.out.println("CustomPhaseListener.afterPhase() " + event.getPhaseId());
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("CustomPhaseListener.beforePhase() " + event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
