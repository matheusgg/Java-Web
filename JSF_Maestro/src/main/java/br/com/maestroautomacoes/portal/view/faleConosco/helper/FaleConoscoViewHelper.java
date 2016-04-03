package br.com.maestroautomacoes.portal.view.faleConosco.helper;

import java.io.Serializable;

public class FaleConoscoViewHelper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7708796448149358484L;
	private String nome;
	private String ambiente;
	private String telefone;
	private String celular;
	private String email;
	private String assuntoSelecionado;
	private String mensagem;
	private boolean modal;

	public void inicializar() {
		setNome("");
		setAmbiente("");
		setTelefone("");
		setCelular("");
		setEmail("");
		setAssuntoSelecionado("");
		setMensagem("");
		setModal(false);
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
	 * @return the ambiente
	 */
	public String getAmbiente() {
		return ambiente;
	}

	/**
	 * @param ambiente
	 *            the ambiente to set
	 */
	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone
	 *            the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * @param celular
	 *            the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the assuntoSelecionado
	 */
	public String getAssuntoSelecionado() {
		return assuntoSelecionado;
	}

	/**
	 * @param assuntoSelecionado
	 *            the assuntoSelecionado to set
	 */
	public void setAssuntoSelecionado(String assuntoSelecionado) {
		this.assuntoSelecionado = assuntoSelecionado;
	}

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem
	 *            the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * @return the modal
	 */
	public boolean isModal() {
		return modal;
	}

	/**
	 * @param modal
	 *            the modal to set
	 */
	public void setModal(boolean modal) {
		this.modal = modal;
	}
}
