package br.com.mail;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.MultiPartEmail;

import br.com.mail.config.EmailConfig;

@ManagedBean
@ViewScoped
public class MailBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 974644446705945211L;
	private String de;
	private String para;
	private String assunto;
	private String mensagem;

	public void enviaMensagem() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = null;

		MultiPartEmail email = new MultiPartEmail();

		try {
			this.configuraEmail(email);

			// Configuracoes do email
			email.setFrom(this.de, "Matheus");
			email.setSubject(this.assunto);
			email.addTo(this.para);

			// Todas as partes do email
			MimeMultipart partesEmail = new MimeMultipart();

			// Corpo do Email
			MimeBodyPart corpo = new MimeBodyPart();
			corpo.setText(this.mensagem);

			// Adicionando o corpo do email no conteudo
			partesEmail.addBodyPart(corpo);

			// Adicionando as partes da mensagem no email
			email.addPart(partesEmail);

			// Enviando o email
			email.send();
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O Email foi enviado com sucesso!", "");
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ocorreu um erro ao enviar a mensagem, tente novamente.", "");
		} finally {
			context.addMessage(null, msg);
		}

	}

	private void configuraEmail(MultiPartEmail email) {
		email.setSmtpPort(Integer.parseInt(EmailConfig.PORTA_SERVIDOR_SMTP.toString()));
		email.setAuthenticator(new DefaultAuthenticator(EmailConfig.CONTA_GMAIL.toString(), EmailConfig.SENHA_GMAIL.toString()));
		email.setDebug(true);
		email.setHostName(EmailConfig.SERVIDOR_SMTP.toString());
		email.setStartTLSEnabled(true);
	}

	/**
	 * @return the de
	 */
	public String getDe() {
		return de;
	}

	/**
	 * @param de
	 *            the de to set
	 */
	public void setDe(String de) {
		this.de = de;
	}

	/**
	 * @return the para
	 */
	public String getPara() {
		return para;
	}

	/**
	 * @param para
	 *            the para to set
	 */
	public void setPara(String para) {
		this.para = para;
	}

	/**
	 * @return the assunto
	 */
	public String getAssunto() {
		return assunto;
	}

	/**
	 * @param assunto
	 *            the assunto to set
	 */
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem
	 *            the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
