package phaselistener;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import bean.AppBean;

public class CustomPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2627609645309466833L;

	/**
	 * E possivel injetar instancias em varios artefatos do JSF, inclusive em
	 * PhaseListeners.
	 */
	// @Inject
	private AppBean appBean;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext.getCurrentInstance().getExternalContext().log(event.getPhaseId().getName());
		try {
			System.out.println(this.getAppBean());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		FacesContext.getCurrentInstance().getExternalContext().log(event.getPhaseId().getName());
		try {
			System.out.println(this.getAppBean());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

	/**
	 * @return the appBean
	 */
	public AppBean getAppBean() {
		return appBean;
	}

	/**
	 * @param appBean
	 *            the appBean to set
	 */
	public void setAppBean(AppBean appBean) {
		this.appBean = appBean;
	}

}
