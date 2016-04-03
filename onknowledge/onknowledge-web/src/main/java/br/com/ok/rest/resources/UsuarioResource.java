package br.com.ok.rest.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.ok.facade.UsuarioFacade;
import br.com.ok.util.constants.OKConstants;

/**
 * The Class UsuarioResource.
 *
 * @author Matheus
 * @version 1.0 - 20/09/2014
 */
@Path("usuario")
public class UsuarioResource {

	/** The usuario facade. */
	@Inject
	private UsuarioFacade usuarioFacade;

	/**
	 * Lista nomes professores.
	 *
	 * @param nome
	 *            the nome
	 * @return the list
	 */
	@GET
	@Path("listaNomesProfessores")
	@Produces(MediaType.APPLICATION_JSON + OKConstants.UTF_8_CHARSET)
	public List<String> listaNomesProfessores(@QueryParam("q") String nome) {
		return this.usuarioFacade.listaNomesProfessores(nome);
	}

}
