package model;

import java.util.List;

import br.com.master.treeView.annotations.TreeViewElement;
import br.com.master.treeView.annotations.TreeViewNode;

public class Produto {

	@TreeViewElement(useCheckbox = true)
	private String nome;

	@TreeViewNode
	private List<Produto> produtos;

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

}
