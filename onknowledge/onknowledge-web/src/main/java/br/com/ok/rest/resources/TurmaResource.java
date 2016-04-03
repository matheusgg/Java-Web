package br.com.ok.rest.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.ok.facade.TurmaFacade;
import br.com.ok.util.constants.OKConstants;

/**
 * The Class TurmaResource.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@Path("turma")
public class TurmaResource {

	/** The turma facade. */
	@Inject
	private TurmaFacade turmaFacade;

	/**
	 * Lista codigos turmas.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the list
	 */
	@GET
	@Path("listaCodigosTurmas")
	@Produces(MediaType.APPLICATION_JSON + OKConstants.UTF_8_CHARSET)
	public List<String> listaCodigosTurmas(@QueryParam("q") String codigo) {
		return this.turmaFacade.listaCodigosTurmas(codigo);
	}
}
