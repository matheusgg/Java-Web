package br.com.ok.security;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.ok.exception.base.OKException;
import br.com.ok.model.Usuario;

/**
 * The Class OKSecurityContext.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@Component("okSecurityContext")
@Named("okSecurityContext")
@SessionScoped
public class OKSecurityContext implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3854137367388905673L;

	/**
	 * Gets the logged user.
	 *
	 * @return the logged user
	 */
	public Usuario getLoggedUser() {
		Authentication authentication = this.getAuthentication();
		if (authentication instanceof UsernamePasswordAuthenticationToken) {
			return (Usuario) authentication.getCredentials();
		} else {
			return null;
		}
	}

	/**
	 * Sets the logged user.
	 *
	 * @param usuario
	 *            the new logged user
	 */
	public void setLoggedUser(Usuario usuario) {
		if (this.isUserLoggedIn()) {
			if (usuario == null || !this.getLoggedUser().getId().equals(usuario.getId())) {
				throw new OKException("{msg.erro.definir.usuario.logado}");
			}
			Authentication auth = new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario, Arrays.asList(usuario.getPerfil()));
			this.getSecurityContext().setAuthentication(auth);
		}
	}

	/**
	 * Checks if is user logged in.
	 *
	 * @return true, if is user logged in
	 */
	public boolean isUserLoggedIn() {
		return this.getLoggedUser() != null;
	}

	/**
	 * Gets the authorities.
	 *
	 * @return the authorities
	 */
	public List<? extends GrantedAuthority> getAuthorities() {
		return (List<? extends GrantedAuthority>) this.getAuthentication().getAuthorities();
	}

	/**
	 * Checks if is user in role.
	 *
	 * @param role
	 *            the role
	 * @return true, if is user in role
	 */
	public boolean isUserInRole(String role) {
		return Faces.isUserInRole(role);
	}

	/**
	 * Gets the authentication.
	 *
	 * @return the authentication
	 */
	public Authentication getAuthentication() {
		return this.getSecurityContext().getAuthentication();
	}

	/**
	 * Gets the security context.
	 *
	 * @return the security context
	 */
	private SecurityContext getSecurityContext() {
		return SecurityContextHolder.getContext();
	}

}
