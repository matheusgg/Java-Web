package beans;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import model.Cliente;
import model.Produto;
import br.com.master.treeView.TreeNode;
import br.com.master.treeView.TreeView;
import br.com.master.treeView.TreeViewBuilder;

@Named
@ViewScoped
public class AppBean {

	private TreeView treeView;

	@PostConstruct
	public void init() {
		if (this.treeView == null) {
			Produto p11 = new Produto();
			p11.setNome("Produto 11");

			Produto p22 = new Produto();
			p22.setNome("Produto 22");

			Produto p1 = new Produto();
			p1.setNome("Produto 1");
			p1.setProdutos(Arrays.asList(p11));

			Produto p2 = new Produto();
			p2.setNome("Produto 2");
			p2.setProdutos(Arrays.asList(p22));

			Cliente c1 = new Cliente();
			c1.setNome("Cliente 1");
			c1.setProdutos(Arrays.asList(p1, p2));

			Cliente c2 = new Cliente();
			c2.setNome("Cliente 2");
			c2.setProdutos(Arrays.asList(p1, p2));

			List<Cliente> clientes = Arrays.asList(c1, c2);

			this.treeView = TreeViewBuilder.buildTreeView(clientes);
			this.treeView.addTreeToView(FacesContext.getCurrentInstance());
		}
	}

	public void test() {
		List<TreeNode> selectedElements = this.treeView.getSelectedElements(FacesContext.getCurrentInstance());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Itens selecionados: " + selectedElements));
		this.treeView.addTreeToView(FacesContext.getCurrentInstance());
	}
}
