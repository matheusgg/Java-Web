package br.com.ok.view.base;

import java.util.Locale;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import lombok.Getter;

import org.omnifaces.util.Faces;

/**
 * The Class OKSessionMB.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@Named("okSessionMB")
@SessionScoped
public class OKSessionMB extends OKBaseMB {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1126861286707701107L;

	/** The current locale. */
	@Getter
	private Locale currentLocale;

	/** The current time zone. */
	@Getter
	private TimeZone currentTimeZone;

	/**
	 * Inits the locale.
	 */
	@PostConstruct
	public void init() {
		this.currentLocale = Faces.getDefaultLocale();
		this.currentTimeZone = TimeZone.getDefault();
	}

	public void alteraIdioma(String language, String country) {
		if (language == null || country == null) {
			throw new IllegalArgumentException("Os parâmetros não podem ser nulos!");
		}

		Locale chosenLocale = new Locale(language, country);
		Faces.getSupportedLocales().forEach((locale) -> {
			if (locale.equals(chosenLocale)) {
				this.currentLocale = chosenLocale;
				Faces.setLocale(this.currentLocale);
			}
		});

		Faces.renderResponse();
	}
}
