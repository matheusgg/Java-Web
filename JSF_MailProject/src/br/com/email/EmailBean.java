package br.com.email;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.MultiPartEmail;

import br.com.email.config.EmailConfigs;

@ManagedBean
@SessionScoped
public class EmailBean {
	private String de;
	private String para;
	private String assunto;
	private String mensagem;
	private String caminhoAnexo;

	public void sendMail() throws Exception {
		// Objeto que ser� respos�vel por enviar a mensagem
		MultiPartEmail email = this.configuraEmail();

		// Objeto que armazenar� as se��es (partes) do email
		MimeMultipart partesEmail = new MimeMultipart();

		// Verifica a exist�ncia de anexo. Caso positivo, o arquivo � anexado
		if (this.caminhoAnexo != null && this.caminhoAnexo.length() > 0) {
			// Em uma mensagem do tipo mime, o email � comporto por partes
			MimeBodyPart anexo = new MimeBodyPart();
			FileDataSource arquivo = new FileDataSource(this.caminhoAnexo);
			anexo.setDataHandler(new DataHandler(arquivo));
			anexo.setFileName(arquivo.getName());
			// Adiciona o anexo ao email
			partesEmail.addBodyPart(anexo);
		}

		// Aqui a mensagem � definida no corpo do email
		MimeBodyPart corpoEmail = new MimeBodyPart();
		corpoEmail.setContent(this.mensagem, "text/html");
		partesEmail.addBodyPart(corpoEmail);

		// �ltimos ajustes...
		email.setSubject(this.assunto);
		email.setFrom(this.de, "Administrador");
		email.addTo(this.para);
		email.addPart(partesEmail);

		// Aqui o email � enviado efetivamente
		email.send();

		// Mensagem da perfumaria...
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "A mensagem foi enviada com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		this.limpaCampos();
	}

	/**
	 * Configura a conta que ser� utilizada para o envio do email.
	 * 
	 * @return
	 */
	private MultiPartEmail configuraEmail() {
		MultiPartEmail email = null;
		try {
			email = new MultiPartEmail();
			email.setSmtpPort(Integer.parseInt(EmailConfigs.PORTA_SERVIDOR_SMTP.toString()));
			email.setAuthenticator(new DefaultAuthenticator(EmailConfigs.CONTA_GMAIL.toString(), EmailConfigs.SENHA_GMAIL.toString()));
			email.setDebug(true);
			email.setHostName(EmailConfigs.SERVIDOR_SMTP.toString());
			email.setTLS(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return email;
	}

	/**
	 * Limpa os campos ap�s o envio da mensagem
	 */
	private void limpaCampos() {
		this.de = "";
		this.para = "";
		this.caminhoAnexo = "";
		this.mensagem = "";
		this.assunto = "";
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

	/**
	 * @return the caminhoAnexo
	 */
	public String getCaminhoAnexo() {
		return caminhoAnexo;
	}

	/**
	 * @param caminhoAnexo
	 *            the caminhoAnexo to set
	 */
	public void setCaminhoAnexo(String caminhoAnexo) {
		this.caminhoAnexo = caminhoAnexo;
	}

}
