package br.com.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * Com '@Component' o spring security indentifica que este é um spring bean. Com
 * essa anotação é desnecessária a configuração dos beans no
 * applicationContext.xml
 * 
 * @author Matheus
 * 
 */
@Component("manager")
public class AuthenticationBean implements AuthenticationManager {

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String login = String.valueOf(auth.getPrincipal());
		String pass = String.valueOf(auth.getCredentials());

		List<SimpleGrantedAuthority> permissoes = null;
		UsernamePasswordAuthenticationToken user = null;

		if (login.equals("user") && pass.equals("user")) {
			permissoes = new ArrayList<SimpleGrantedAuthority>();
			permissoes.add(new SimpleGrantedAuthority("ROLE_USER"));
			user = new UsernamePasswordAuthenticationToken(login, pass, permissoes);
		} else if (login.equals("admin") && pass.equals("admin")) {
			permissoes = new ArrayList<SimpleGrantedAuthority>();
			permissoes.add(new SimpleGrantedAuthority("ROLE_USER"));
			permissoes.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			user = new UsernamePasswordAuthenticationToken(login, pass, permissoes);
		} else {
			throw new BadCredentialsException("Usuário ou senha incorretos.");
		}

		return user;
	}

}
