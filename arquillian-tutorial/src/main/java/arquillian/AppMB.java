package arquillian;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class AppMB implements Serializable {

	private static final long serialVersionUID = -1265743565277675859L;

	@Inject
	private App1 app1;

	@PostConstruct
	public void showMessage() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(this.app1.createMessage()));
	}

}
