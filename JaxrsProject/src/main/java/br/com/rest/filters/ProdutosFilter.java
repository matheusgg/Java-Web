package br.com.rest.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import br.com.rest.filters.annotation.ProdutosBinding;

/**
 * Por padrao, todos as implementacoes Providers de ContainerRequestFilter ou
 * ContainerResponseFilter sao invocadas para todos os recursos solicitados. No
 * JAX RS 2.0, a anotacao NameBinding foi introduzida para resolver este
 * problema. Com ela, é possível executar os filtros de forma seletiva para
 * determinados recursos. Basta criar uma anotacao anotada com NameBinding e
 * aplicar esta anotacao aos filtros e aos recursos que deverao ser
 * interceptados por esses filtros.
 * 
 * @author Matheus
 *
 */
@Provider
@ProdutosBinding
public class ProdutosFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println(requestContext.getUriInfo().getPath() + " invoked");
	}

}
