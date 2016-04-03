package br.com.beans;

import org.apache.struts.action.ActionForm;

public class UserBean extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3131389900206569166L;

	private String email;
	private String senha;

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
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha
	 *            the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
