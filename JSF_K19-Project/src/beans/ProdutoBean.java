package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Produto;

@ManagedBean
@SessionScoped
public class ProdutoBean {
	private List<Produto> produtos;
	private String configuracao;

	public ProdutoBean() {
		this.configuracao = "Simples";
		this.produtos = new ArrayList<Produto>();
		for (int i = 0; i < 3; i++) {
			Produto p = new Produto();
			p.setNome("note" + i);
			p.setPreco((i + 1) * 1000);
			this.produtos.add(p);
		}
	}

	public String definePreferencia() {
		return "index".concat(this.configuracao);
	}

	public String sorteiaNavegacao() {
		String retorno = "";
		if (Math.random() < 0.5) {
			retorno = "page1";
		} else {
			retorno = "page2";
		}
		return retorno;
	}

	/**
	 * @return the produtos
	 */
	public List<Produto> getProdutos() {
		return produtos;
	}

	/**
	 * @param produtos
	 *            the produtos to set
	 */
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	/**
	 * @return the configuracao
	 */
	public String getConfiguracao() {
		return configuracao;
	}

	/**
	 * @param configuracao
	 *            the configuracao to set
	 */
	public void setConfiguracao(String configuracao) {
		this.configuracao = configuracao;
	}

}
