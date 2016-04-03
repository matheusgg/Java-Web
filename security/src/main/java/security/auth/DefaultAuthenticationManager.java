package security.auth;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import security.model.Permissao;
import security.model.Usuario;

@Component
public class DefaultAuthenticationManager implements AuthenticationManager {

	// @Autowired
	// private LdapTemplate ldapTemplate;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		// ldapTemplate.authenticate(DistinguishedName.EMPTY_PATH,
		// "(&(objectClass=user)(sAMAccountName=" + auth.getName() + "))",
		// auth.getCredentials()
		// .toString());
		// return auth;
		Permissao permissao = new Permissao();
		permissao.setNome("admin");

		Usuario usuario = new Usuario();
		usuario.setNome("ADMIN");

		return new UsernamePasswordAuthenticationToken(usuario.getNome(), usuario, Arrays.asList(permissao));
	}

}
