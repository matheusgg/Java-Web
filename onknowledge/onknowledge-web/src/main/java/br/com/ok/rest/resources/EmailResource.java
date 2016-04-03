package br.com.ok.rest.resources;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.ok.facade.EmailFacade;
import br.com.ok.model.EmailRecebido;
import br.com.ok.util.constants.OKConstants;

/**
 * The Class EmailResource.
 *
 * @author Matheus
 * @version 1.0 - 04/11/2014
 */
@Path("emails")
public class EmailResource implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1003099086592571197L;

	/** The email facade. */
	@Inject
	private EmailFacade emailFacade;

	/**
	 * Pesquisa novos emails recebidos por destinatario.
	 *
	 * @param idDestinatario
	 *            the id destinatario
	 * @return the list
	 */
	@GET
	@Path("pesquisaNovosEmailsRecebidosPorDestinatario")
	@Produces(MediaType.APPLICATION_JSON + OKConstants.UTF_8_CHARSET)
	public List<EmailRecebido> pesquisaNovosEmailsRecebidosPorDestinatario(@QueryParam("destinatario") Integer idDestinatario) {
		return this.emailFacade.pesquisaNovosEmailsRecebidosPorDestinatario(idDestinatario);
	}

}
