package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class FlashBean {

	private String senha;
	
	public FlashBean(){
		this.getClass();
	}

	public String goToPage3() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("flashBean", this);
		return "page3?faces-redirect=true";
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha
	 *            the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
