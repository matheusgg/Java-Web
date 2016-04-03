package br.com.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class Cliente implements Serializable {

	private static final long serialVersionUID = -518084418852628830L;

	@NotNull
	private Integer id;

	@NotNull
	private String nome;

	@NotNull
	private Date nascimento;

	public Cliente() {

	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

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
	 * @return the nascimento
	 */
	public Date getNascimento() {
		return nascimento;
	}

	/**
	 * @param nascimento
	 *            the nascimento to set
	 */
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + "]";
	}

}
