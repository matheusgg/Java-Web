package financeiro.web;

import java.io.Serializable;

import javax.faces.context.FacesContext;

public class ContextoUtil implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5607919238261179447L;

	public static ContextoBean getContextoBean() {
		return (ContextoBean) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("contextoBean");
	}

}
