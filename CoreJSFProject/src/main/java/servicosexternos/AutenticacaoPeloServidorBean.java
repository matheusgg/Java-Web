package servicosexternos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AutenticacaoPeloServidorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7537211426214896840L;

	private String remoteUser;

	@PostConstruct
	public void init() {
		this.remoteUser = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
	}

	/**
	 * @return the remoteUser
	 */
	public String getRemoteUser() {
		return remoteUser;
	}

	/**
	 * @param remoteUser
	 *            the remoteUser to set
	 */
	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}

}
