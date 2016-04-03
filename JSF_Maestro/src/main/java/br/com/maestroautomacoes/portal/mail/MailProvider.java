package br.com.maestroautomacoes.portal.mail;

import java.io.File;

import javax.activation.FileDataSource;

import org.apache.commons.mail.HtmlEmail;

public abstract class MailProvider {

	/**
	 * Envia um email no formato HTML com as informaçoes desejadas.
	 * 
	 * @param nomeCliente
	 *            Cliente.
	 * @param destino
	 *            Email de destino.
	 * @param ambiente
	 *            Ambiente.
	 * @param assunto
	 *            Assunto.
	 * @param mensagem
	 *            Msnagem a ser enviada.
	 * @param usarFormatacaoPadrao
	 *            Indica se deve ser utilizado o texto de apresentação padrão.
	 * @param anexo
	 *            Orçamento anexo.
	 * @throws Exception
	 */
	public static void mandaEmailHtml(String nomeCliente, String destino, String ambiente, String assunto, String mensagem, File anexo,
			boolean usarFormatacaoPdrao) throws Exception {
		HtmlEmail email = MailProvider.configuraEmail(new HtmlEmail());
		email.addTo(destino); // email destino

		if (anexo != null) {
			FileDataSource fileDataSource = new FileDataSource(anexo);
			email.attach(fileDataSource, fileDataSource.getName(), fileDataSource.getName());
		}

		StringBuffer html = new StringBuffer();

		html = html.append("<html>").append("<head>").append("<style>");
		// CSS
		html = html.append("html, body{margin: 0px; padding: 0px;}");
		html = html.append(".fundo {position: absolute; top: 0px; left: 0px; z-index: 2;}");
		html = html.append(".texto {position: absolute;  top: 70px; left: 220px;z-index: 5;	width: 450px;height: 300px;}");
		html = html.append(".titulo{font-family: Arial; color: orange; font-size: 20;}");
		html = html.append(".corpo{font-family: Arial; color: gray; font-size: 16; line-height:150%;}");
		html = html.append("</style>").append("</head>");
		// BODY
		html = html.append("<body>");
		html = html.append("<table>").append(
				"<tr><td><img src='http://www.maestroautomacoes.com.br/images/topo.jpg' style='padding: 15px;'></td></tr>");
		html = html.append("<tr><td style='padding-left: 150px; padding-top: 30px; padding-bottom: 30px;'>");
		html = html.append("<span style='font-family: Arial; color: orange; font-size: 20;'>");
		html = html.append("Prezado(a) ").append(nomeCliente);
		html = html.append("</span>");
		html = html.append("<br /><br />		");
		html = html.append("<p style='font-family: Arial; color: gray; font-size: 16; line-height:150%;'>");
		html = html.append(usarFormatacaoPdrao ? "Em breve retornaremos seu contato referente à:" : "");
		html = html.append("<br /><br />		");
		html = html.append(ambiente).append("<br />");
		html = html.append(assunto).append("<br />");
		html = html.append(mensagem);
		html = html.append("<br /><br />");
		html = html.append(usarFormatacaoPdrao ? "Enquanto isso veja nossos vídeos, nosso portfólio, saiba mais sobre a Maestro.<br />" : "");
		html = html.append("Atenciosamente<br />");
		html = html.append("Equipe Maestro Automações<br />");
		html = html.append("</p>");
		html = html.append("</td></tr>");
		html = html.append("<tr><td><img src='http://www.maestroautomacoes.com.br/images/rodape.jpg' style='padding: 15px;'></td></tr>").append(
				"</table>");
		html = html.append("</body>");
		html = html.append("</html>");

		email.setHtmlMsg(html.toString());

		// set the alternative message
		email.setTextMsg("Seu email não tem suporte a mensagens em HTML.");

		// send the email
		email.send();
	}

	/**
	 * Adiciona as configurações necessárias para o envio do email.
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	private static HtmlEmail configuraEmail(HtmlEmail email) throws Exception {
		email.setHostName("smtp.maestroautomacoes.com.br");
		email.setAuthentication("contato@maestroautomacoes.com.br", "contato123");
		email.setSSL(true);
		email.addCc("contato@maestroautomacoes.com.br");// email com cópia
		email.setFrom("contato@maestroautomacoes.com.br"); // email
		email.setSubject("Contato - Maestro Automações");
		return email;
	}

}
