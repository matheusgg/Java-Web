package br.com.rest.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * Providers sao extensoes, ou seja, plugins que maximizam as possibilidades de
 * trabalho com o JAX RS. É possível criar um provider que servira de filtro
 * para capturar requisicoes e respostas realizadas para os recursos
 * registrados. Esse filtro é registrado em AppConfig.
 * 
 * @author Matheus
 *
 */

/**
 * PreMatching faz com que este filtro seja executado antes da fase de
 * vinculacao de recursos. Deste modo, é possível alterar as informacoes da
 * requisicao, uma vez que isso nao é mais possivel apos esta fase. Com PreMatching 
 * o método filter de ContainerRequestResponse nao é executado.
 * 
 * @author Matheus
 *
 */

/**
 * A fase de vinculacao de recursos é onde acontece a verificacao da mensagem de
 * requisicao. Nela, o fingerprint recebido é validado e comparado a fim de
 * descobrir se algum recurso é compativel com aquela requisicao. Nesta fase nao
 * é possivel mais alterar as informacoes da requisicao.
 * 
 * @author Matheus
 *
 */
@Provider
// @PreMatching
public class CustomFilter implements ContainerRequestFilter, ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		System.out.println(responseContext.getEntity());
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println(requestContext.getMethod());
	}

}
