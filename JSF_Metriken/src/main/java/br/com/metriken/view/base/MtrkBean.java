package br.com.metriken.view.base;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
public class MtrkBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7603914270338084264L;

	/**
	 * Altera o idioma da aplicação de acordo com o idioma selecionado.
	 * 
	 * @param linguagem
	 * @param país
	 * @return
	 */
	public String changeApplicationLanguage(String language, String country) {
		FacesContext.getCurrentInstance().getApplication().setDefaultLocale(new Locale(language, country));
		return "";
	}
}
