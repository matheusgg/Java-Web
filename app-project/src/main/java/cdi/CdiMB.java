package cdi;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

@Named
@ViewScoped
public class CdiMB implements Serializable {

	private static final long serialVersionUID = -8220044677002713524L;

	@Inject
	private AppFacade appFacade;

	public void showMessage() {
		Messages.addInfo(null, this.appFacade.constructMessage());
	}

}
