package br.com.estudos.tabela;

public class Carros {
	private String nome;
	private String preco;

	public Carros(String nome, String preco) {
		this.nome = nome;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String toString() {
		return this.nome;
	}
}
