package br.com.mail.config;

/**
 * Enum com as configura??es da conta que ser? utilizada para o envio das
 * mensagem.
 * 
 * @author Matheus
 * 
 */
public enum EmailConfig {
	SERVIDOR_SMTP("smtp.gmail.com"), PORTA_SERVIDOR_SMTP("587"), CONTA_GMAIL("CONTA GMAIL"), SENHA_GMAIL("SENHA GMAIL");

	private String value;

	private EmailConfig(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value;
	}

}
