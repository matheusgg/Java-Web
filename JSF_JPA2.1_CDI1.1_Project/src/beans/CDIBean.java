package beans;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cdi.Invoke;
import cdi.SubmitEvent;

/**
 * No CDI 1.1, e possivel ignorar classses que serao descobertas apenas
 * especificando a anotacao Vetoed.
 * 
 * @author Matheus
 * 
 */
// @Vetoed
@Named("cdiBean")
@ViewScoped
public class CDIBean {

	@Inject
	private Event<SubmitEvent> events;

	@Invoke
	public void testCDI() {
		this.showMessage("Sucesso!");
	}

	public void testObserver() {
		this.events.fire(new SubmitEvent());
		this.showMessage("Sucesso!");
	}

	/**
	 * Metodo executado sempre que um SubmitEvent for disparado
	 * 
	 * @param submitEvent
	 */
	public void observe(@Observes SubmitEvent submitEvent) {
		this.showMessage("SubmitEvent disparado!");
	}

	private void showMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
	}

}
