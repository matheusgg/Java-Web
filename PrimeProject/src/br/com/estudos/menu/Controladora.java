package br.com.estudos.menu;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class Controladora {
	public String montaMenu() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);

		sessao.setAttribute("usuario", "analista");
		return "index2";
	}
}
