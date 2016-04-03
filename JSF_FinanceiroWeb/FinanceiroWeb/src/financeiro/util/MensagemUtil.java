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
		 * Como os idiomas suportados pela aplicação já foram definidos no
		 * faces-config.xml, esta linha recupera o Bundle do idioma padrão, onde
		 * é informado o contexto da aplicação e o nome da variável definida
		 * para recuperação das mensagens de tradução no faces-config, isto é,
		 * 'msg'
		 */
		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, "msg");
		return bundle.getString(propriedade);
	}

	/*
	 * Object... parametros indica que esta método pode receber uma quantidade
	 * indeterminada de parâmetros do tipo especificado, neste caso, o tipo
	 * especificado foi Object, sendo assim, é possível passar quantos
	 * parâmetros de qualquer tipo forem necessários para este método.
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
