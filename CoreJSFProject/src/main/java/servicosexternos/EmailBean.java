package servicosexternos;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.MultiPartEmail;

@Named
@ViewScoped
public class EmailBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1023012813873533596L;

	/**
	 * Sessao com os parametros da conta de email configurada no servidor.
	 */
	@Resource(name = "mail/gmail")
	private Session session;

	private String de;
	private String para;
	private String assunto;
	private String mensagem;

	public void sendMail() throws Exception {
		MultiPartEmail email = this.configuraEmail();

		MimeMultipart partesEmail = new MimeMultipart();

		MimeBodyPart corpoEmail = new MimeBodyPart();
		corpoEmail.setContent(this.mensagem, "text/html");
		partesEmail.addBodyPart(corpoEmail);

		email.setSubject(this.assunto);
		email.setFrom(this.de, "Administrador");
		email.addTo(this.para);
		email.addPart(partesEmail);

		email.send();

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "A mensagem foi enviada com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		this.resetFields();
	}

	/**
	 * Configura a conta que sera utilizada para o envio do email.
	 * 
	 * @return
	 */
	private MultiPartEmail configuraEmail() throws Exception {
		MultiPartEmail email = new MultiPartEmail();
		email.setSmtpPort(Integer.parseInt(this.session.getProperty("mail.smtp.port")));
		email.setAuthenticator(new DefaultAuthenticator(this.session.getProperty("mail.user"), this.session.getProperty("mail.password")));
		email.setDebug(Boolean.valueOf(this.session.getProperty("mail.debug")));
		email.setHostName(this.session.getProperty("mail.host"));
		email.setStartTLSEnabled(Boolean.valueOf(this.session.getProperty("mail.smtp.starttls.enable")));
		return email;
	}

	private void resetFields() {
		this.de = "";
		this.para = "";
		this.assunto = "";
		this.mensagem = "";
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
