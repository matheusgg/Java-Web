package br.com.ok.rest.resources;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.ok.facade.DisciplinaFacade;
import br.com.ok.model.Disciplina;
import br.com.ok.util.constants.OKConstants;

/**
 * The Class DisciplinaResource.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@Path("disciplina")
public class DisciplinaResource {

	/** The disciplina facade. */
	@Inject
	private DisciplinaFacade disciplinaFacade;

	/**
	 * Lista nomes disciplinas.
	 *
	 * @param nome
	 *            the nome
	 * @return the list
	 */
	@GET
	@Path("listaNomesDisciplinas")
	@Produces(MediaType.APPLICATION_JSON + OKConstants.UTF_8_CHARSET)
	public List<String> listaNomesDisciplinas(@QueryParam("q") String nome) {
		List<Disciplina> disciplinas = this.disciplinaFacade.listaNomesDisciplinas(nome);
		List<String> nomesDisciplinas = new ArrayList<>();

		for (Disciplina disciplina : disciplinas) {
			nomesDisciplinas.add(disciplina.getCodigo() + OKConstants.WORDS_SEPARATOR + disciplina.getNome());
		}

		return nomesDisciplinas;
	}

}
