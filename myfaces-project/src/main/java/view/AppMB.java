package view;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@Named
@ViewScoped
public class AppMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5739222190557575825L;

	public void showMessage() {
		Messages.add(FacesMessage.SEVERITY_INFO, null, Faces.getImplInfo());
	}

}
