package br.com.spring.beans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.spring.model.Usuario;
import br.com.spring.model.dao.UsuarioDAO;

@Named
@SessionScoped
public class AppBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 805124579870967749L;

	@Inject
	private UsuarioDAO usuarioDAO;

	private Locale locale;

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

		if (this.locale == null) {
			this.locale = facesContext.getApplication().getDefaultLocale();
			session.setAttribute("locale", locale);
		}
	}

	public void save() {
		Usuario usuario = new Usuario();
		usuario.setNome("usuario 3");
		this.usuarioDAO.save(usuario);
	}

	public void showCredentials() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário: " + authentication.getName()));
	}

	public void changeLocale(String language, String country) {
		Locale locale = new Locale(language, country);
		Iterator<Locale> locales = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
		while (locales.hasNext()) {
			if (locales.next().equals(locale)) {
				this.locale = locale;
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
				session.setAttribute("locale", this.locale);
				break;
			}
		}
	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}
