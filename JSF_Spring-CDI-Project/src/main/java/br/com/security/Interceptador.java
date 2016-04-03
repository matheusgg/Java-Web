package br.com.security;

import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Esta será a classe que interceptará as chamadas aos métodos anotados com
 * '@Secured', por isso, ela é anotada com a própria '@Secured', isso significa
 * que esta classe interceptará apenas os métodos anotados com esta anotação.
 * 
 * @author Matheus
 * 
 */
@Secured
@Interceptor
public class Interceptador {

	/**
	 * Este será o método invocado antes da invocação de um método anotado com
	 * '@Secured', ele servirá para verificar reflexivamente se o usuário possui
	 * permissão de acesso ao método.
	 * 
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	@AroundInvoke
	public Object verify(InvocationContext ctx) throws Exception {
		Object permissao = null;
		try {
			Method metodo = ctx.getMethod();
			Secured anotacao = metodo.getAnnotation(Secured.class);
			SecurityContext secContext = SecurityContextHolder.getContext();
			UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) secContext.getAuthentication();
			boolean temPermissao = false;
			forPermissoesAnotacao: for (String permissaoAnotacao : anotacao.value()) {
				for (GrantedAuthority permissaoUsuario : user.getAuthorities()) {
					if (permissaoAnotacao.equals(permissaoUsuario.getAuthority())) {
						temPermissao = true;
						break forPermissoesAnotacao;
					}
				}
			}

			if (temPermissao) {
				permissao = ctx.proceed();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return permissao;
	}

}
