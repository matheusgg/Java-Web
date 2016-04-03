package br.com.ok.security.rest;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import br.com.ok.security.OKSecurityContext;
import br.com.ok.util.logging.OKLogManager;

/**
 * The Class OKRestAuthenticationFilter.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@Component("okRestAuthenticationFilter")
public class OKRestAuthenticationFilter extends BasicAuthenticationFilter {

	/** The ok security context. */
	@Autowired
	private OKSecurityContext okSecurityContext;

	/** The ok log manager. */
	@Autowired
	private OKLogManager okLogManager;

	@Autowired
	public OKRestAuthenticationFilter(@Qualifier("okLoginManager") AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	/**
	 * Intercepta requisicoes REST (/ws/** pattern) e verifica se o usuario esta
	 * autenticado, continuando assim, a cadeia de invocacao. Caso o usuario nao
	 * esteja autenticado, o recurso REST solicitado nao sera invocado e o
	 * codigo de erro 401 (SC_UNAUTHORIZED) sera retornado para o cliente.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Authentication auth = this.okSecurityContext.getAuthentication();
		if (auth == null) {
			this.sendUnauthorizedError((HttpServletResponse) response);
		}
		super.doFilter(request, response, chain);
	}

	/**
	 * Send unauthorized error.
	 *
	 * @param response
	 *            the response
	 */
	private void sendUnauthorizedError(HttpServletResponse response) {
		try {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		} catch (Exception e) {
			this.okLogManager.getLogger().log(Level.SEVERE, e.getMessage(), e);
		}
	}
}
