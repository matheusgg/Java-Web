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
			 * Aqui é recuperada a sessão com base nas informações do
			 * Context.xml
			 */
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext
					.lookup("java:comp/env");
			/*
			 * Na utilização de servidores como JBoss e GlassFish, esta linha não é necessária,
			 * pois a configuração é feita diretamente no servidor e o nome do datasource ficaria
			 * apenas 'jdbc/FinanceiroDB'
			 */
			Session session = (Session) envContext.lookup("mail/Session");
			Properties config = session.getProperties();

			/*
			 * A sessão deveria ser setada deste modo no email, porém esta
			 * configuração não funcionou: email.setMailSession(Sessao);
			 */

			/*
			 * Aqui é feita a configuração do email
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
			 * Aqui é feita uma verificação se um email foi informado no momento
			 * do cadastro, caso negativo, o email padrão é buscado do
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
			 * Criação do corpo do email
			 */
			MimeMultipart partesEmail = new MimeMultipart();
			MimeBodyPart corpo = new MimeBodyPart();
			corpo.setContent(mensagem, "text/html");
			partesEmail.addBodyPart(corpo);

			/*
			 * Adição do corpo ao email e envio do mesmo
			 */
			email.setContent(partesEmail);
			email.send();
		} catch (Exception ex) {
			throw new UtilException(ex.getMessage());
		}
	}
}
