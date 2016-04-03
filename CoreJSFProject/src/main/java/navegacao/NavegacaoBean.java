package navegacao;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.Size;

@Named
@ViewScoped
public class NavegacaoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7452248942600807796L;

	@Size(min = 2, message = "O campo usuario deve possuir no minimo {min} caracteres")
	private String user;

	public String navegate() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("message", "Mensagem inserida dentro do mapa de flash");
		return "navegacao2?faces-redirect=true";
	}

	public void verificaDados() {
		if (this.user.equals("admin")) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("autorizado", true);
		} else {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("autorizado", false);
		}
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

}
