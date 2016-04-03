package financeiro.web.util;

import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.mail.SimpleEmail;

import financeiro.util.MensagemUtil;
import financeiro.util.UtilException;

public class EmailUtil {
	public void enviarEmail(String de, String para, String assunto,
			String mensagem, Locale locale) throws UtilException {
		try {

			/*
			 * Aqui � recuperada a sess�o com base nas informa��es do
			 * Context.xml
			 */
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext
					.lookup("java:comp/env");
			/*
			 * Na utiliza��o de servidores como JBoss e GlassFish, esta linha n�o � necess�ria,
			 * pois a configura��o � feita diretamente no servidor e o nome do datasource ficaria
			 * apenas 'jdbc/FinanceiroDB'
			 */
			Session session = (Session) envContext.lookup("mail/Session");
			Properties config = session.getProperties();

			/*
			 * A sess�o deveria ser setada deste modo no email, por�m esta
			 * configura��o n�o funcionou: email.setMailSession(Sessao);
			 */

			/*
			 * Aqui � feita a configura��o do email
			 */
			SimpleEmail email = new SimpleEmail();
			email.setDebug(true);
			email.setAuthentication(config.getProperty("mail.smtp.user"),
					config.getProperty("mail.smtp.password"));
			email.setHostName(config.getProperty("mail.smtp.host"));
			email.setSmtpPort(Integer.parseInt(config
					.getProperty("mail.smtp.port")));
			email.setTLS(true);

			/*
			 * Aqui � feita uma verifica��o se um email foi informado no momento
			 * do cadastro, caso negativo, o email padr�o � buscado do
			 * context.xml
			 */
			if (de != null) {
				email.setFrom(de,
						MensagemUtil.getMensagem(locale, "administrador"));
			} else {
				email.setFrom(
						session.getProperties().getProperty("mail.smtp.user"),
						MensagemUtil.getMensagem(locale, "administrador"));
			}

			email.addTo(para);
			email.setSubject(assunto);
			email.setSentDate(new Date());

			/*
			 * Cria��o do corpo do email
			 */
			MimeMultipart partesEmail = new MimeMultipart();
			MimeBodyPart corpo = new MimeBodyPart();
			corpo.setContent(mensagem, "text/html");
			partesEmail.addBodyPart(corpo);

			/*
			 * Adi��o do corpo ao email e envio do mesmo
			 */
			email.setContent(partesEmail);
			email.send();
		} catch (Exception ex) {
			throw new UtilException(ex.getMessage());
		}
	}
}
