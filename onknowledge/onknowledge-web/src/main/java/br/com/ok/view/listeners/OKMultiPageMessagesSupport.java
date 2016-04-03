package br.com.ok.view.listeners;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

import org.omnifaces.util.Faces;

import br.com.ok.util.constants.OKConstants;

import com.ocpsoft.pretty.PrettyContext;
import com.ocpsoft.pretty.faces.config.mapping.UrlMapping;
import com.ocpsoft.pretty.faces.event.MultiPageMessagesSupport;

/**
 * The Class OKMultiPageMessagesSupport.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
public class OKMultiPageMessagesSupport extends MultiPageMessagesSupport {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4828490611234455934L;

	/**
	 * @see com.ocpsoft.pretty.faces.event.MultiPageMessagesSupport#afterPhase(javax.faces.event.PhaseEvent)
	 */
	@Override
	public void afterPhase(PhaseEvent event) {
		super.afterPhase(event);
		/*
		 * Tratamento para remoção da mensagem de login inválido da sessão
		 * inserida pelo SpringSecurity.
		 */
		UrlMapping currentMapping = PrettyContext.getCurrentInstance().getCurrentMapping();
		if (currentMapping != null) {
			String currentViewId = OKConstants.PRETTY_PREFIX + currentMapping.getId();
			if (PhaseId.RENDER_RESPONSE.equals(event.getPhaseId()) && !OKConstants.PRETTY_LOGIN.equals(currentViewId)) {
				Faces.removeSessionAttribute(OKConstants.SPRING_SECURITY_LAST_EXCEPTION_KEY);
			}
		}
	}

}
