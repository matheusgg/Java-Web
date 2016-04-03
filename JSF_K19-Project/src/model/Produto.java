package model;

public class Produto {
	private String nome;
	private double preco;
	private boolean detalhar;

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
	 * @return the preco
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * @param preco
	 *            the preco to set
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}

	/**
	 * @return the detalhar
	 */
	public boolean isDetalhar() {
		return detalhar;
	}

	/**
	 * @param detalhar
	 *            the detalhar to set
	 */
	public void setDetalhar(boolean detalhar) {
		this.detalhar = detalhar;
	}

}
