package beans;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.sun.faces.component.PassthroughElement;

@Named
@ViewScoped
public class AppBean {

	private String nome;

	public void showName() {
		this.getClass();
	}

	public void testClickDiv() {
		PassthroughElement div = (PassthroughElement) FacesContext.getCurrentInstance().getViewRoot().findComponent("div");
		div.setStyle("border: 2px solid red");
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

}
