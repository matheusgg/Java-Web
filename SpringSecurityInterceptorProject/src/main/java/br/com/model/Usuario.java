package br.com.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String login;
	private String senha;
	private List<SimpleGrantedAuthority> permissoes;

	public Usuario(String login, String senha, List<SimpleGrantedAuthority> permissoes) {
		this.login = login;
		this.senha = senha;
		this.permissoes = permissoes;
	}

	public Usuario() {

	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
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
	 * @return the permissoes
	 */
	public List<SimpleGrantedAuthority> getPermissoes() {
		return permissoes;
	}

	/**
	 * @param permissoes
	 *            the permissoes to set
	 */
	public void setPermissoes(List<SimpleGrantedAuthority> permissoes) {
		this.permissoes = permissoes;
	}

}
