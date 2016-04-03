package br.com.ok.facade;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.com.ok.business.UsuarioBean;
import br.com.ok.facade.base.OKGenericFacade;
import br.com.ok.model.Usuario;

/**
 * The Class LoginFacade.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@Component
public class LoginFacade extends OKGenericFacade {

	private static final long serialVersionUID = -8784305210973510093L;

	/** The usuario bean. */
	@Inject
	private UsuarioBean usuarioBean;

	/**
	 * Login.
	 *
	 * @param login
	 *            the login
	 * @param password
	 *            the password
	 * @return the usuario
	 * @throws Exception
	 *             the exception
	 */
	public Usuario login(String login, String password) throws Exception {
		return this.usuarioBean.login(login, password);
	}

}
