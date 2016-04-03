package br.com.ok.rest.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.ok.facade.EventoFacade;
import br.com.ok.model.Evento;
import br.com.ok.security.OKSecurityContext;
import br.com.ok.util.constants.OKConstants;

/**
 * The Class EventoResource.
 *
 * @author Adilson
 * @version 1.0 - 31/10/2014
 */
@Path("events")
public class EventoResource {

	/** The security context. */
	@Inject
	private OKSecurityContext securityContext;

	/** The evento facade. */
	@Inject
	private EventoFacade eventoFacade;

	/**
	 * Lista eventos cadastrados pelo usuario.
	 *
	 * @return the list
	 */
	@GET
	@Path("listaEventosCadastradosPeloUsuario")
	@Produces(MediaType.APPLICATION_JSON + OKConstants.UTF_8_CHARSET)
	public List<Evento> listaEventosCadastradosPeloUsuario() {
		return this.eventoFacade.listaEventosCadastradosPeloUsuario(this.securityContext.getLoggedUser().getId());
	}

	/**
	 * Lista eventos usuario.
	 *
	 * @return the list
	 */
	@GET
	@Path("listaEventosUsuario")
	@Produces(MediaType.APPLICATION_JSON + OKConstants.UTF_8_CHARSET)
	public List<Evento> listaEventosUsuario() {
		return this.eventoFacade.listaEventosUsuario(this.securityContext.getLoggedUser());
	}
}
