package facelets;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class FaceletsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9071518290758549943L;

	private String name;

	public void verifyName() {
		if (this.name.length() < 5) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O nome deve possuir pelo menos 5 letras!"));
			this.name = null;
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
