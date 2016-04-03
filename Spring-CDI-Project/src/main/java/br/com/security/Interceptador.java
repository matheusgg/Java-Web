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
 * Esta ser� a classe que interceptar� as chamadas aos m�todos anotados com
 * '@Secured', por isso, ela � anotada com a pr�pria '@Secured', isso significa
 * que esta classe interceptar� apenas os m�todos anotados com esta anota��o.
 * 
 * @author Matheus
 * 
 */
@Secured
@Interceptor
public class Interceptador {

	/**
	 * Este ser� o m�todo invocado antes da invoca��o de um m�todo anotado com
	 * '@Secured', ele servir� para verificar reflexivamente se o usu�rio possui
	 * permiss�o de acesso ao m�todo.
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
