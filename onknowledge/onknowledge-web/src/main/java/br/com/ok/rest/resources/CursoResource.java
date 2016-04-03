package br.com.ok.rest.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.ok.facade.CursoFacade;
import br.com.ok.util.constants.OKConstants;

/**
 * The Class CursoResource.
 *
 * @author Matheus
 * @version 1.0 - 20/09/2014
 */
@Path("curso")
public class CursoResource {

	/** The curso facade. */
	@Inject
	private CursoFacade cursoFacade;

	/**
	 * Lista cursos.
	 *
	 * @param nome
	 *            the nome
	 * @return the list
	 */
	@GET
	@Path("listaNomesCursos")
	@Produces(MediaType.APPLICATION_JSON + OKConstants.UTF_8_CHARSET)
	public List<String> listaNomesCursos(@QueryParam("q") String nome) {
		return this.cursoFacade.listaNomesCursos(nome);
	}

}
