package br.com.maestroautomacoes.portal.view.login.helper;

import java.io.Serializable;

import br.com.maestroautomacoes.portal.security.SecurityManager;

public class LoginViewHelper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1319859234321467469L;
	private SecurityManager securityManager;
	private String email;
	private String senha;

	public void inicializar() {
		this.securityManager = new SecurityManager();
		this.email = "";
		this.senha = "";
	}

	/**
	 * @return the securityManager
	 */
	public SecurityManager getSecurityManager() {
		return securityManager;
	}

	/**
	 * @param securityManager
	 *            the securityManager to set
	 */
	public void setSecurityManager(SecurityManager securityManager) {
		this.securityManager = securityManager;
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

}
