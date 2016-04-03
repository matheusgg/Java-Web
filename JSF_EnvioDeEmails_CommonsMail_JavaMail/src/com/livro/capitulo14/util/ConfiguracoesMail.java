package com.livro.capitulo14.util;

public enum ConfiguracoesMail {
	CONFIGURACOES;

	public static final String SERVIDOR_SMTP;
	public static final int PORTA_SERVIDOR_SMTP;
	public static final String CONTA_PADRAO;
	public static final String SENHA_CONTA_PADRAO;

	static {
		SERVIDOR_SMTP = "localhost";
		PORTA_SERVIDOR_SMTP = 25;
		CONTA_PADRAO = "adm@localhost";
		SENHA_CONTA_PADRAO = "123";
	}

}
