/*
 * 
 */
package br.com.ok.util.messages;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import lombok.Getter;

import org.primefaces.context.RequestContext;

import br.com.ok.util.constants.OKConstants;

/**
 * The Class OKMessages.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
public final class OKMessages {

	/**
	 * The Enum Severity.
	 *
	 * @author Matheus
	 * @version 1.0 - 21/09/2014
	 */
	@Getter
	public enum Severity {

		/** The info. */
		INFO(1, FacesMessage.SEVERITY_INFO),
		/** The success. */
		SUCCESS(2, FacesMessage.SEVERITY_INFO),
		/** The warning. */
		WARNING(3, FacesMessage.SEVERITY_WARN),
		/** The error. */
		ERROR(4, FacesMessage.SEVERITY_ERROR);

		/** The level. */
		private int level;
		private javax.faces.application.FacesMessage.Severity facesSeverity;

		private Severity(int level, javax.faces.application.FacesMessage.Severity facesSeverity) {
			this.level = level;
			this.facesSeverity = facesSeverity;
		}
	}

	/**
	 * Instantiates a new OK messages.
	 */
	private OKMessages() {

	}

	/**
	 * Adds the faces message.
	 *
	 * @param message
	 *            the message
	 * @param severity
	 *            the severity
	 * @param clientId
	 *            the client id
	 */
	public static void addFacesMessage(String message, Severity severity, String clientId) {
		FacesContext facesContext = OKMessages.getFacesContext();
		if (facesContext != null) {
			if (message.startsWith(OKConstants.CHAVES_INICIAIS) && message.endsWith(OKConstants.CHAVES_FINAIS)) {
				message = OKMessages.getMessage(message.replaceAll(OKConstants.REGEX_CHAVES, OKConstants.STRING_VAZIA));
			}
			facesContext.addMessage(clientId, new FacesMessage(severity.getFacesSeverity(), message, message));
		}
	}

	/**
	 * Recupera o valor associado a chave informada do arquivo de propriedades
	 * Messages_xx_XX.properties onde xx_XX significa o Locale informado.
	 *
	 * @param messageKey
	 *            the key
	 * @param locale
	 *            the locale
	 * @return the message
	 */
	public static String getMessage(String messageKey, Locale locale) {
		return OKMessages.getResourceBundle(false, locale).getString(messageKey);
	}

	/**
	 * Recupera o valor associado a chave informada do arquivo de propriedades
	 * Messages_xx_XX.properties onde xx_XX significa o Locale corrente.
	 *
	 * @param messageKey
	 *            the key
	 * @return the message
	 */
	public static String getMessage(String messageKey) {
		return OKMessages.getResourceBundle(true, null).getString(messageKey);
	}

	/**
	 * Recupera a mensagem associada com a chave informada do arquivo de
	 * propriedade Messages_xx_XX.properties, onde xx_XX é o Locale
	 * informado. Além disso, formata a mensagem recuperada de acordo com os
	 * argumentos informados.
	 *
	 * @param messageKey
	 *            the key
	 * @param locale
	 *            the locale
	 * @param args
	 *            the args
	 * @return the formatted message
	 */
	public static String getFormattedMessage(String messageKey, Locale locale, Object... args) {
		return MessageFormat.format(OKMessages.getMessage(messageKey, locale), args);
	}

	/**
	 * Recupera a mensagem associada com a chave informada do arquivo de
	 * propriedade Messages_xx_XX.properties, onde xx_XX é o Locale
	 * corrente. Além disso, formata a mensagem recuperada de acordo com os
	 * argumentos informados.
	 *
	 * @param messageKey
	 *            the key
	 * @param args
	 *            the args
	 * @return the formatted message
	 */
	public static String getFormattedMessage(String messageKey, Object... args) {
		return MessageFormat.format(OKMessages.getMessage(messageKey), args);
	}

	/**
	 * Exibe uma caixa de notificação no canto inferior direito da tela de
	 * acordo com os parâmetros informados.
	 *
	 * @param title
	 *            the title
	 * @param message
	 *            the message
	 * @param severity
	 *            the severity
	 */
	public static void showBigNotification(String title, String message, Severity severity) {
		RequestContext.getCurrentInstance().execute(
				OKMessages.prepareCallNotification("showBigNotification", OKMessages.prepareNotificationText(title), OKMessages.prepareNotificationText(message),
						severity));
	}

	/**
	 * Exibe uma caixa de notificação no canto superior direito da tela de
	 * acordo com os parâmetros informados.
	 *
	 * @param title
	 *            the title
	 * @param message
	 *            the message
	 * @param severity
	 *            the severity
	 */
	public static void showBoxNotification(String title, String message, Severity severity) {
		RequestContext.getCurrentInstance().execute(
				OKMessages.prepareCallNotification("showBoxNotification", OKMessages.prepareNotificationText(title), OKMessages.prepareNotificationText(message),
						severity));
	}

	/**
	 * Prepare notification message.
	 *
	 * @param text
	 *            the message
	 * @return the string
	 */
	private static String prepareNotificationText(String text) {
		if (text.startsWith(OKConstants.CHAVES_INICIAIS) && text.endsWith(OKConstants.CHAVES_FINAIS)) {
			text = OKMessages.getMessage(text.replaceAll(OKConstants.REGEX_CHAVES, OKConstants.STRING_VAZIA));
		}
		return text;
	}

	/**
	 * Exibe uma caixa de diálogo de confirmação de acordo com os parâmetros
	 * informados. O array de parâmetros é tratado de forma posicional de acordo
	 * com a função declarada no arquivo onknowledge.js. <br>
	 * <b>params[0]:</b> Título;<br>
	 * <b>params[1]:</b> Mensagem;<br>
	 * <b>params[2]:</b> Ícone;<br>
	 * <b>params[3]:</b> Função de callback executada após da confirmação da
	 * operação;<br>
	 * <b>params[4]:</b> Função de callback executada após o cancelamento da
	 * operação;<br>
	 * <b>params[5]:</b> Texto do botão de confirmação;<br>
	 * <b>params[6]:</b> Texto do botão de cancelamento;<br>
	 *
	 * @param params
	 *            the params
	 */
	@SafeVarargs
	public static void showSmartConfirmationDialog(String... params) {
		StringBuilder builder = new StringBuilder("showSmartConfirmationDialog");
		OKMessages.appendParams(builder, params);
		String call = builder.toString().replace(",)", OKConstants.PARENTESES_FINAL);
		RequestContext.getCurrentInstance().execute(call);
	}

	/**
	 * Append params.
	 *
	 * @param builder
	 *            the builder
	 * @param params
	 *            the params
	 */
	private static void appendParams(StringBuilder builder, String... params) {
		builder.append(OKConstants.PARENTESES_INICIAL);
		for (String param : params) {
			if (param != null) {
				builder.append(OKConstants.ASPAS).append(param);
				builder.append(OKConstants.ASPAS).append(OKConstants.VIRGULA);
			} else {
				builder.append(param).append(OKConstants.VIRGULA);
			}
		}
		builder.append(OKConstants.PARENTESES_FINAL);
	}

	/**
	 * Prepare call notification.
	 *
	 * @param function
	 *            the function
	 * @param title
	 *            the title
	 * @param message
	 *            the message
	 * @param severity
	 *            the severity
	 * @return the string
	 */
	private static String prepareCallNotification(String function, String title, String message, Severity severity) {
		StringBuilder call = new StringBuilder(function);
		call.append(OKConstants.PARENTESES_INICIAL);
		call.append(OKConstants.APOSTROFO).append(title).append(OKConstants.APOSTROFO).append(OKConstants.VIRGULA);
		call.append(OKConstants.APOSTROFO).append(message).append(OKConstants.APOSTROFO).append(OKConstants.VIRGULA);
		return call.append(severity.getLevel()).append(OKConstants.PARENTESES_FINAL).toString();
	}

	/**
	 * Gets the resource bundle.
	 *
	 * @param useFacesContext
	 *            the use faces context
	 * @param locale
	 *            the locale
	 * @return the resource bundle
	 */
	private static ResourceBundle getResourceBundle(boolean useFacesContext, Locale locale) {
		if (useFacesContext) {
			FacesContext facesContext = OKMessages.getFacesContext();
			return facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		}
		return ResourceBundle.getBundle("Messages", locale);
	}

	private static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
}
