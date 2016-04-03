package br.com.maestroautomacoes.portal.view.usuario.helper;

import java.io.Serializable;

import br.com.maestroautomacoes.portal.model.usuario.Usuario;

public class UsuarioViewHelper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7675277775941518764L;
	private Usuario usuario;
	private String email;
	private String confirmacaoEmail;

	public void inicializar() {
		this.usuario = new Usuario();
		this.email = "";
		this.confirmacaoEmail = "";
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
	 * @return the confirmacaoEmail
	 */
	public String getConfirmacaoEmail() {
		return confirmacaoEmail;
	}

	/**
	 * @param confirmacaoEmail
	 *            the confirmacaoEmail to set
	 */
	public void setConfirmacaoEmail(String confirmacaoEmail) {
		this.confirmacaoEmail = confirmacaoEmail;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
