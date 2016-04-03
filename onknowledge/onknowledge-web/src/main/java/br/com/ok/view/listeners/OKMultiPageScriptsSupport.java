package br.com.ok.view.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;

import br.com.ok.util.constants.OKConstants;

/**
 * Listener responsável por armazenar e restaurar os scripts que deverão ser
 * executados após um redirecionamento através do mecanismo de redirecionamento
 * do PrettyFaces ou parâmetro faces-redirect=true.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
public class OKMultiPageScriptsSupport implements PhaseListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5184868822926751789L;

	/**
	 * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	@Override
	public void afterPhase(PhaseEvent event) {
		if (PhaseId.INVOKE_APPLICATION.equals(event.getPhaseId())) {
			List<String> scripts = RequestContext.getCurrentInstance().getScriptsToExecute();
			if (scripts != null && !scripts.isEmpty()) {
				Faces.setSessionAttribute(OKConstants.SCRIPTS_TO_EXECUTE_KEY, scripts);
			}
		}
	}

	/**
	 * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 */
	@Override
	public void beforePhase(PhaseEvent event) {
		if (PhaseId.RENDER_RESPONSE.equals(event.getPhaseId()) && !event.getFacesContext().getResponseComplete()) {
			List<String> scripts = Faces.getSessionAttribute(OKConstants.SCRIPTS_TO_EXECUTE_KEY);
			if (scripts != null && !scripts.isEmpty()) {
				scripts = new ArrayList<String>(scripts);
				RequestContext requestContext = RequestContext.getCurrentInstance();
				requestContext.getScriptsToExecute().removeIf(script -> !script.isEmpty());
				requestContext.execute(this.buildScriptsToExecute(scripts));
				Faces.removeSessionAttribute(OKConstants.SCRIPTS_TO_EXECUTE_KEY);
			}
		}
	}

	/**
	 * Builds the scripts to execute.
	 *
	 * @param scripts
	 *            the scripts
	 * @return the string
	 */
	private String buildScriptsToExecute(List<String> scripts) {
		StringBuilder scriptBuilder = new StringBuilder();
		scripts.forEach(script -> scriptBuilder.append(script).append(OKConstants.PONTO_VIRGULA));
		return scriptBuilder.toString();
	}

	/**
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
