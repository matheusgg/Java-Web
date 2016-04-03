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
	 * Este método é herdado da superclasse Authenticator e é necessário para
	 * utilizar emails com autenticação via biblioteca JavaMail
	 */
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.usuario, this.senha);
	}

}
