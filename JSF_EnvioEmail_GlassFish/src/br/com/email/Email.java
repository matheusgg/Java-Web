package br.com.email;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.mail.MultiPartEmail;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class Email implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3832434999047268792L;

	private String de;
	private String para;
	private String assunto;
	private String mensagem;
	private String nomeAnexo;
	private UploadedFile arquivoAnexo;
	private static final String CAMINHO_PADRAO;

	public Email() {		
		this.nomeAnexo = "";
	}
	
	static{
		CAMINHO_PADRAO = "C:\\Users\\Matheus\\Downloads\\";
	}

	public void enviaEmail() {
		FacesMessage msg = null;
		FacesContext context = FacesContext.getCurrentInstance();
		
		try{
			Context envContext = new InitialContext();
			Session sessao = (Session) envContext.lookup("mail/Session");
			Properties config = sessao.getProperties();
			
			MultiPartEmail email = new MultiPartEmail();
			email.setDebug(true);
			email.setAuthentication(config.getProperty("mail.user"), config.getProperty("mail.smtp.password"));
			email.setHostName(config.getProperty("mail.host"));
			email.setSmtpPort(Integer.parseInt(config.getProperty("mail.smtp.port")));
			
			
			email.setTLS(true);
			
			email.setFrom(this.de);
			email.addTo(this.para);
			email.setSubject(this.assunto);
			
			MimeBodyPart corpo = new MimeBodyPart();
			corpo.setContent(this.mensagem, "text/html");
			
			MimeMultipart partesEmail = new MimeMultipart();
			partesEmail.addBodyPart(corpo);
			
			if (this.nomeAnexo.trim().length() > 0) {
				partesEmail.addBodyPart(this.anexaArquivo());
			}
			
			email.setContent(partesEmail);
			email.send();
			
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "A mensagem foi enviada com sucesso!", "");
			this.limpaDados();
			
		}catch(Exception ex){
			ex.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
		}

		context.addMessage("", msg);
	}

	public void uploadFile(FileUploadEvent event) {
		this.arquivoAnexo = event.getFile();
		this.nomeAnexo = this.arquivoAnexo.getFileName();
		try {
			this.salvaArquivoTemporario();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private MimeBodyPart anexaArquivo() throws MessagingException{
		MimeBodyPart anexo = new MimeBodyPart();		
		FileDataSource arquivo = new FileDataSource(new File(CAMINHO_PADRAO + this.nomeAnexo));			
		anexo.setDataHandler(new DataHandler(arquivo));
		anexo.setFileName(arquivo.getName());
		
		return anexo;
	}
	
	private void limpaDados(){
		this.de = "";
		this.assunto = "";
		this.mensagem = "";
		this.nomeAnexo = "";
		this.para = "";
		this.arquivoAnexo = null;
	}
	
	public void removeAnexo(AjaxBehaviorEvent event){
		this.arquivoAnexo = new DefaultUploadedFile();
		this.nomeAnexo = "";
	}

	private void salvaArquivoTemporario() throws IOException {
		File caminhoArquivoTemp = null;

		if (this.arquivoAnexo != null && this.nomeAnexo.trim().length() > 0) {
			caminhoArquivoTemp = new File(CAMINHO_PADRAO + this.nomeAnexo);
			InputStream arquivoOrigem = this.arquivoAnexo.getInputstream();
			OutputStream arquivoTemp = new FileOutputStream(caminhoArquivoTemp);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = arquivoOrigem.read(buffer)) > 0) {
				arquivoTemp.write(buffer, 0, len);
			}
			arquivoTemp.close();
			arquivoOrigem.close();
		}
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

	public String getNomeAnexo() {
		return nomeAnexo;
	}

	public void setNomeAnexo(String nomeAnexo) {
		this.nomeAnexo = nomeAnexo;
	}

	public UploadedFile getArquivoAnexo() {
		return arquivoAnexo;
	}

	public void setArquivoAnexo(UploadedFile arquivoAnexo) {
		this.arquivoAnexo = arquivoAnexo;
	}

}
