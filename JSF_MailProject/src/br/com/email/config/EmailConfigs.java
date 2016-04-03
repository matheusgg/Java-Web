package br.com.email.config;

/**
 * Enum com as configurações da conta que será utilizada para o envio
 * das mensagem.
 * @author Matheus
 *
 */
public enum EmailConfigs {
	SERVIDOR_SMTP("smtp.gmail.com"),
	PORTA_SERVIDOR_SMTP("587"),
	CONTA_GMAIL("email"),
	SENHA_GMAIL("senha");
	
	private String value;
	
	private EmailConfigs(String value) {
		this.value = value;
	}
	
	public String toString(){
		return this.value;
	}

}
