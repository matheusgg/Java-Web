package financeiro.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class MensagemUtil {
	private static final String PACOTE_MENSAGENS_IDIOMAS = "financeiro.idioma.mensagens";

	public static String getMensagem(String propriedade) {
		FacesContext context = FacesContext.getCurrentInstance();
		/*
		 * Como os idiomas suportados pela aplica��o j� foram definidos no
		 * faces-config.xml, esta linha recupera o Bundle do idioma padr�o, onde
		 * � informado o contexto da aplica��o e o nome da vari�vel definida
		 * para recupera��o das mensagens de tradu��o no faces-config, isto �,
		 * 'msg'
		 */
		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, "msg");
		return bundle.getString(propriedade);
	}

	/*
	 * Object... parametros indica que esta m�todo pode receber uma quantidade
	 * indeterminada de par�metros do tipo especificado, neste caso, o tipo
	 * especificado foi Object, sendo assim, � poss�vel passar quantos
	 * par�metros de qualquer tipo forem necess�rios para este m�todo.
	 */
	public static String getMensagem(String propriedade, Object... parametros) {
		String mensagem = MensagemUtil.getMensagem(propriedade);
		MessageFormat formatter = new MessageFormat(mensagem);
		return formatter.format(parametros);
	}

	public static String getMensagem(Locale locale, String propriedade) {
		ResourceBundle bundle = ResourceBundle.getBundle(
				MensagemUtil.PACOTE_MENSAGENS_IDIOMAS, locale);
		return bundle.getString(propriedade);
	}

	public static String getMensagem(Locale locale, String propriedade,
			Object... parametros) {
		String mensagem = MensagemUtil.getMensagem(locale, propriedade);
		MessageFormat formatter = new MessageFormat(mensagem);
		return formatter.format(parametros);
	}
}
