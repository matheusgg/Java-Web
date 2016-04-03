package br.com.rest.resources;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Um recurso JAX RS pode ser um NamedBean, um EJB Stateless ou Singleton.
 * 
 * @author Matheus
 *
 */
@Path("/cliente")
@SessionScoped
public class ClienteResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6572977553794228995L;

	/**
	 * Com ResourceContext é possivel recuperar instancias resolvidas de
	 * recursos JAX RS, além de permitir a inicializacao de novos recursos com
	 * valores da requisicao atual.
	 */
	@Context
	private ResourceContext context;

	// @PathParam("param")
	// private String param;

	@GET
	@Path("/nome/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getName(@QueryParam("id") String id) {
		return "Teste";
	}

	/**
	 * Com @Suspended e possivel realizar processamentos assincronos. A
	 * responsta so sera enviada para o cliente quando o metodo resume() de
	 * AsyncResponse for chamado.
	 * 
	 * @param response
	 * @param id
	 * @return
	 */
	@GET
	@Path("/testAsync")
	public void testAsync(@Suspended AsyncResponse response, @QueryParam("id") String id) {
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.resume(id);
	}

	@GET
	@Path("/interceptorTest")
	public String interceptorTest(@QueryParam("id") String id) {
		return id;
	}

}
