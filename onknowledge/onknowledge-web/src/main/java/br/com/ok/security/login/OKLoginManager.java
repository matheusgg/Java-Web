package br.com.ok.security.login;

import java.util.Arrays;
import java.util.logging.Level;

import javax.persistence.NoResultException;

import org.omnifaces.config.BeanManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.facade.LoginFacade;
import br.com.ok.model.Usuario;
import br.com.ok.security.OKSecurityContext;
import br.com.ok.util.logging.OKLogManager;
import br.com.ok.util.messages.OKMessages;
import br.com.ok.view.base.OKSessionMB;

/**
 * The Class OKLoginManager.
 *
 * @author Matheus
 * @version 1.0 - 20/09/2014
 */
@Component("okLoginManager")
public class OKLoginManager implements AuthenticationManager {

	/** The Constant OK_SECURITY_LOGIN_URL. */
	private static final String OK_SECURITY_LOGIN_URL = "/ok_security_login";

	/** The usuario facade. */
	@Autowired
	private LoginFacade loginFacade;

	/** The ok security context. */
	@Autowired
	private OKSecurityContext okSecurityContext;

	/** The log manager. */
	@Autowired
	private OKLogManager logManager;

	/**
	 * Authenticate.
	 *
	 * @param authentication
	 *            the authentication
	 * @return the authentication
	 * @throws AuthenticationException
	 *             the authentication exception
	 * @see org.springframework.security.authentication.AuthenticationManager#authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Authentication auth = this.okSecurityContext.getAuthentication();

		if (this.getRequestURL().contains(OKLoginManager.OK_SECURITY_LOGIN_URL) || auth == null) {
			try {
				Usuario usuario = this.loginFacade.login(authentication.getName(), authentication.getCredentials().toString());
				auth = new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario, Arrays.asList(usuario.getPerfil()));
			} catch (Exception e) {
				this.prepareAuthenticationException(e);
			}
		}

		return auth;
	}

	/**
	 * Gets the request url.
	 *
	 * @return the request url
	 */
	private String getRequestURL() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return requestAttributes.getRequest().getRequestURI();
	}

	/**
	 * Prepare authentication exception.
	 *
	 * @param e
	 *            the e
	 * @throws AuthenticationException
	 *             the authentication exception
	 */
	private void prepareAuthenticationException(Exception e) throws AuthenticationException {
		this.logManager.getLogger().log(Level.SEVERE, e.getMessage(), e);
		OKSessionMB okSessionMB = BeanManager.INSTANCE.getReference(OKSessionMB.class);
		if (e instanceof OKBusinessException) {
			throw new DisabledException(OKMessages.getMessage("msg.erro.usuario.inativo", okSessionMB.getCurrentLocale()), e);

		} else if (e instanceof NoResultException) {
			throw new BadCredentialsException(OKMessages.getMessage("msg.erro.credenciais.invalidas", okSessionMB.getCurrentLocale()), e);

		} else {
			throw new OKBusinessException();
		}
	}
}
