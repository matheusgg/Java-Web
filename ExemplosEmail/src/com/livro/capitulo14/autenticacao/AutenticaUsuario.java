package com.livro.capitulo14.autenticacao;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class AutenticaUsuario extends Authenticator {
	private String usuario;
	private String senha;

	public AutenticaUsuario(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	/*
	 * Este m�todo � herdado da superclasse Authenticator e � necess�rio para
	 * utilizar emails com autentica��o via biblioteca JavaMail
	 */
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.usuario, this.senha);
	}

}
