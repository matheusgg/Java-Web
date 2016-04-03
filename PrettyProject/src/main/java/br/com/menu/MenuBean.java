package br.com.menu;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import br.com.ClienteBean;
import br.com.RelatorioBean;

@ManagedBean
@SessionScoped
public class MenuBean {
	private MenuModel menu;

	public MenuBean() {
		this.menu = new DefaultMenuModel();
		this.preparaMenu();
	}

	private void preparaMenu() {
		FacesContext context = FacesContext.getCurrentInstance();
		ELContext elContext = context.getELContext();
		ExpressionFactory ef = context.getApplication().getExpressionFactory();

		Submenu submenuCliente = new Submenu();
		submenuCliente.setLabel("Cliente");

		MenuItem itemPesquisa = new MenuItem();
		itemPesquisa.setValue("Pesquisa de Clientes");
		itemPesquisa.setAjax(false);
		itemPesquisa.setActionExpression(ef.createMethodExpression(elContext, "#{clienteBean.iniciarPagina}", ClienteBean.class, new Class[] {}));

		submenuCliente.getChildren().add(itemPesquisa);

		Submenu submenuRelatorio = new Submenu();
		submenuRelatorio.setLabel("Relatórios");

		MenuItem itemLancamento = new MenuItem();
		itemLancamento.setValue("Lançamentos");
		itemLancamento.setAjax(false);
		itemLancamento.setActionExpression(ef
				.createMethodExpression(elContext, "#{relatorioBean.iniciarPagina}", RelatorioBean.class, new Class[] {}));

		submenuRelatorio.getChildren().add(itemLancamento);

		Submenu submenuLess = new Submenu();
		submenuLess.setLabel("LESS");

		MenuItem itemLess = new MenuItem();
		itemLess.setValue("LESS Example");
		itemLess.setAjax(false);
		itemLess.setOutcome("lessPage");
		
		submenuLess.getChildren().add(itemLess);

		this.menu.addSubmenu(submenuCliente);
		this.menu.addSubmenu(submenuRelatorio);
		this.menu.addSubmenu(submenuLess);
	}

	public MenuModel getMenu() {
		return menu;
	}

	public void setMenu(MenuModel menu) {
		this.menu = menu;
	}

}
