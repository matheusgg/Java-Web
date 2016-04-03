package email.externo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.MultiPartEmail;

import email.Autenticador;

public class EmailExterno {
	private static final String CONTA_PADRAO = "";
	private static final String SENHA_CONTA = "";
	private static final String SERVIDOR_SMTP = "smtp.mail.yahoo.com.br";
	private static final int PORTA_SMTP = 25;

	private String de;
	private String para;
	private String assunto;
	private String destinatariosNormais;
	private String destinatariosOcultos;
	private String anexo;
	private String mensagem;

	public void enviaEmail() {
		MultiPartEmail email = null;
		try {
			email = this.preparaEmail();
			email.setFrom(this.de, "Matheus Gomes Góes");
			email.addTo(this.para);
			email.setSubject(this.assunto);
			email.setSentDate(new Date());
			
			MimeMultipart partesEmail = new MimeMultipart();
			
			/*
			 * Corpo do email
			 */			
			MimeBodyPart corpoEmail = new MimeBodyPart();
			corpoEmail.setContent(this.mensagem, "text/html");	
			partesEmail.addBodyPart(corpoEmail);		

			if (this.anexo != null) {
				partesEmail.addBodyPart(this.verificaAnexo());
			}

			if (this.destinatariosNormais != null
					&& this.destinatariosNormais.trim().length() > 0) {
				email.setCc(this.listaDestinatarios(this.destinatariosNormais));
			}

			if (this.destinatariosOcultos != null
					&& this.destinatariosOcultos.trim().length() > 0) {
				email.setBcc(this.listaDestinatarios(this.destinatariosOcultos));
			}
			
			email.setContent(partesEmail);
			email.send();
			System.out.println("Mensagem enviada com sucesso!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private MultiPartEmail preparaEmail() {
		MultiPartEmail email = null;
		try {
			email = new MultiPartEmail();
			email.setDebug(true);
			email.setHostName(EmailExterno.SERVIDOR_SMTP);
			email.setSmtpPort(EmailExterno.PORTA_SMTP);
			/*
			 * Configuração necessária para enviar mensagens a partir do Gmail
			 * email.setTLS(true);
			 */
			email.setAuthenticator(new Autenticador(EmailExterno.CONTA_PADRAO,
					EmailExterno.SENHA_CONTA));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return email;
	}

	private List<InternetAddress> listaDestinatarios(String destinatarios)
			throws AddressException {
		List<InternetAddress> lista = new ArrayList<InternetAddress>();
		String[] enderecos = destinatarios.split(";");
		for (int i = 0; i < enderecos.length; i++) {
			lista.add(new InternetAddress(enderecos[i]));
		}
		return lista;
	}

	private MimeBodyPart verificaAnexo() throws MessagingException{
		/*
		 * Anexo do arquivo
		 */
		MimeBodyPart anexo = new MimeBodyPart();
		FileDataSource arquivo = new FileDataSource(this.anexo);
		anexo.setDataHandler(new DataHandler(arquivo));
		anexo.setFileName(arquivo.getName());
		return anexo;
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

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
