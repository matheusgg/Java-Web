package br.com.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.model.Usuario;

@Component
public class Provider implements AuthenticationProvider, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6052656848065648336L;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String user = String.valueOf(authentication.getPrincipal());
		String pass = String.valueOf(authentication.getCredentials());

		List<SimpleGrantedAuthority> permissoes = new ArrayList<>();
		if (user.equals("admin") && pass.equals("admin")) {
			permissoes.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			permissoes.add(new SimpleGrantedAuthority("ROLE_USER"));
		} else if (user.equals("user") && pass.equals("user")) {
			permissoes.add(new SimpleGrantedAuthority("ROLE_USER"));
		} else {
			throw new BadCredentialsException("Usuário ou senha inválidos!");
		}

		Usuario usuario = new Usuario(user, pass, permissoes);
		authentication = new UsernamePasswordAuthenticationToken(usuario, user, permissoes);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}

}
