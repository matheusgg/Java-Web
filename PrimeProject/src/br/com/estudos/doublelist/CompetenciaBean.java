package br.com.estudos.doublelist;

public class CompetenciaBean {
	private int id;
	private String nome;
	private String descricao;

	public CompetenciaBean(String nome) {
		this.nome = nome;
	}

	public CompetenciaBean() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
