package br.com.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import br.com.rest.filters.annotation.ProdutosBinding;

@Path("produtos")
@ProdutosBinding
public class ProdutosResource {
	
	@Context
	private SecurityContext securityContext;

	@GET
	@Path("find")
	@Produces(MediaType.APPLICATION_JSON)
	public String getId() {
		return "10";
	}

}
