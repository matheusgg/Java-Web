package br.com.interceptor;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.model.Usuario;

@Access
@Interceptor
public class Interceptadora implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2835209170196366361L;

	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {
		Object obj = null;
		boolean temAcesso = false;
		Method m = ctx.getMethod();
		Access a = m.getAnnotation(Access.class);

		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		label: for (SimpleGrantedAuthority permissaoUsuario : usuario.getPermissoes()) {
			for (String permissaoMetodo : a.value()) {
				if (permissaoUsuario.getAuthority().equals(permissaoMetodo)) {
					temAcesso = true;
					break label;
				}
			}
		}

		if (temAcesso) {
			obj = ctx.proceed();
		}
		return obj;
	}
}
