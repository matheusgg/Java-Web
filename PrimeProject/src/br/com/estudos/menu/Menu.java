package br.com.estudos.menu;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;

public class Menu {
	private Submenu submenu;

	public Menu() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);

		String perfil = String.valueOf(sessao.getAttribute("usuario"));
		System.out.println(perfil);
		montaMenu();
	}

	private void montaMenu() {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		ExpressionFactory fabricaDeExpressoes = FacesContext
				.getCurrentInstance().getApplication().getExpressionFactory();

		submenu = new Submenu();
		MenuItem item = new MenuItem();
		item.setValue("Candidato");
		item.setStyle("color: black;");
		item.setActionExpression(fabricaDeExpressoes.createMethodExpression(
				context, "#{menuBean.teste}", this.getClass(), new Class[] {}));
		submenu.getChildren().add(item);
	}

	public String teste() {
		System.out.println("Funfo");
		return "tabela";
	}

	public Submenu getSubmenu() {
		return submenu;
	}

	public void setSubmenu(Submenu submenu) {
		this.submenu = submenu;
	}
}
