package br.com.model;

public class Candidato {
	private String nome;
	private int idade;
	private int situacao;

	public Candidato(String nome, int idade, int situacao) {
		this.nome = nome;
		this.idade = idade;
		this.situacao = situacao;
	}

	public String toString() {
		return this.nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

}
