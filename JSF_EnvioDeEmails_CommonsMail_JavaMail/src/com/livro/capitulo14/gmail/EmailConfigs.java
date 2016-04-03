package com.livro.capitulo14.gmail;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.MultiPartEmail;

import com.livro.capitulo14.autenticacao.AutenticaUsuario;

public class EmailConfigs {
	private static final String SERVIDOR_SMTP = "smtp.gmail.com";
	private static final int PORTA_SERVIDOR_SMTP = 587;
	private static final String CONTA_GMAIL = "";
	private static final String SENHA_GMAIL = "";

	private String de;
	private String para;
	private String assunto;
	private String mensagem;

	public String enviaEmail() {
		FacesContext context = FacesContext.getCurrentInstance();
		MultiPartEmail email = this.configuraEmail();

		try {
			if (email != null) {
				email.setFrom(this.de, "Administrador");
				email.addTo(this.para);
				email.setSubject(this.assunto);
				email.setSentDate(new Date());
				email.setContent(this.mensagem, "text/html");
				email.send();
			}

			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email enviado com sucesso!", ""));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	private MultiPartEmail configuraEmail() {
		MultiPartEmail email = null;
		try {
			email = new MultiPartEmail();
			email.setSmtpPort(EmailConfigs.PORTA_SERVIDOR_SMTP);
			email.setAuthenticator(new AutenticaUsuario(EmailConfigs.CONTA_GMAIL, EmailConfigs.SENHA_GMAIL));
			email.setDebug(true);
			email.setHostName(EmailConfigs.SERVIDOR_SMTP);
			email.setTLS(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return email;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}