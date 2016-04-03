package com.livro.capitulo14.commonsmail;

import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.livro.capitulo14.util.ConfiguracoesMail;

public class CommonsMailBean {
	private String de;
	private String para;
	private String destinatariosNormais;
	private String destinatariosOcultos;
	private String assunto;
	private String mensagem;
	private String anexo;

	public String enviarAutenticado() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {

			/*
			 * Cria o email preparado para os anexos
			 */
			MultiPartEmail email = new MultiPartEmail();

			/*
			 * Informações do servidor
			 */
			email.setHostName(ConfiguracoesMail.SERVIDOR_SMTP);
			email.setSmtpPort(ConfiguracoesMail.PORTA_SERVIDOR_SMTP);

			/*
			 * Autenticando no servidor
			 */
			email.setAuthentication(ConfiguracoesMail.CONTA_PADRAO, ConfiguracoesMail.SENHA_CONTA_PADRAO);

			/*
			 * Montando o email
			 */
			email.setFrom(this.de, "Administrador");
			email.addTo(this.para);
			email.setSubject(this.assunto);
			MimeMultipart partesEmail = new MimeMultipart();
			MimeBodyPart corpoEmail = new MimeBodyPart();
			corpoEmail.setContent(this.mensagem, "text/html");
			partesEmail.addBodyPart(corpoEmail);

			List<InternetAddress> normais = this.montaDestinatarios(this.destinatariosNormais);
			if (normais != null) {
				email.setCc(normais);
			}

			List<InternetAddress> ocultos = this.montaDestinatarios(this.destinatariosOcultos);
			if (ocultos != null) {
				email.setBcc(ocultos);
			}

			/*
			 * Cria o anexo
			 */
			if (this.anexo != null && this.anexo.trim().length() > 0) {
				MimeBodyPart anexo = new MimeBodyPart();
				FileDataSource arquivo = new FileDataSource(this.anexo);
				anexo.setDataHandler(new DataHandler(arquivo));
				anexo.setFileName(arquivo.getName());
				// Adiciona o anexo ao email
				partesEmail.addBodyPart(anexo);
			}

			email.setContent(partesEmail);
			email.send();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email enviado com sucesso!", ""));

		} catch (EmailException ex) {
			ex.printStackTrace();
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return "";
	}

	public String enviarSimples() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			SimpleEmail email = new SimpleEmail();

			/*
			 * Informações do servidor
			 */
			email.setHostName(ConfiguracoesMail.SERVIDOR_SMTP);
			email.setSmtpPort(ConfiguracoesMail.PORTA_SERVIDOR_SMTP);

			/*
			 * Montando o email
			 */
			email.setFrom(this.de);
			email.addTo(this.para);
			email.setSubject(this.assunto);
			email.setContent(this.mensagem, "text/html");

			List<InternetAddress> normais = this.montaDestinatarios(this.destinatariosNormais);
			if (normais != null) {
				email.setCc(normais);
			}

			List<InternetAddress> ocultos = this.montaDestinatarios(this.destinatariosOcultos);
			if (ocultos != null) {
				email.setBcc(ocultos);
			}

			email.send();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensage enviada com sucesso!", ""));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	private List<InternetAddress> montaDestinatarios(String destinatarios) throws AddressException {
		List<InternetAddress> emails = null;
		if (destinatarios != null && destinatarios.trim().length() > 0) {
			String[] lista = destinatarios.split(";");
			emails = new ArrayList<InternetAddress>();
			for (int i = 0; i < lista.length; i++) {
				emails.add(new InternetAddress(lista[i]));
			}
		}

		return emails;
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

	public String getDestinatariosNormais() {
		return destinatariosNormais;
	}

	public void setDestinatariosNormais(String destinatariosNormais) {
		this.destinatariosNormais = destinatariosNormais;
	}

	public String getDestinatariosOcultos() {
		return destinatariosOcultos;
	}

	public void setDestinatariosOcultos(String destinatariosOcultos) {
		this.destinatariosOcultos = destinatariosOcultos;
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

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}
}
