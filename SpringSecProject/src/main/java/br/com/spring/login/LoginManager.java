package br.com.spring.login;

import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.omnifaces.config.BeanManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import br.com.spring.beans.AppBean;
import br.com.spring.model.Usuario;
import br.com.spring.model.dao.UsuarioDAO;

@Component
public class LoginManager implements AuthenticationManager {

	@Inject
	private UsuarioDAO loginDAO;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			String login = authentication.getName();
			Usuario usuario = this.loginDAO.findUserByLogin(login);
			return new UsernamePasswordAuthenticationToken(usuario, usuario.getNome(), usuario.getPermissoes());
		} catch (NoResultException ex) {
			AppBean appBean = BeanManager.INSTANCE.getReference(AppBean.class);
			ResourceBundle bundle = ResourceBundle.getBundle("br.com.spring.messages.customMessages", appBean.getLocale());
			throw new BadCredentialsException(bundle.getString("AbstractUserDetailsAuthenticationProvider.badCredentials"), ex);
		}
	}

}
