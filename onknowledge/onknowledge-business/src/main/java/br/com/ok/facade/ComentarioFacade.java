package br.com.ok.facade;

import java.util.List;

import javax.inject.Inject;

import br.com.ok.business.ComentarioBean;
import br.com.ok.facade.base.OKGenericFacade;
import br.com.ok.model.Comentario;

/**
 * The Class ComentarioFacade.
 *
 * @author Matheus
 * @version 1.0 - 11/10/2014
 */
public class ComentarioFacade extends OKGenericFacade {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2422155591567746019L;

	/** The comentario bean. */
	@Inject
	private ComentarioBean comentarioBean;

	/**
	 * Pesquisa comentarios por questao.
	 *
	 * @param idQuestao
	 *            the id questao
	 * @return the list
	 */
	public List<Comentario> pesquisaComentariosPorQuestao(Integer idQuestao) {
		return this.comentarioBean.pesquisaComentariosPorQuestao(idQuestao);
	}

	/**
	 * Pesquisa comentarios por atividade.
	 *
	 * @param idAtividade
	 *            the id atividade
	 * @return the list
	 */
	public List<Comentario> pesquisaComentariosPorAtividade(Integer idAtividade) {
		return this.comentarioBean.pesquisaComentariosPorAtividade(idAtividade);
	}

	/**
	 * Salva comentario.
	 *
	 * @param comentario
	 *            the comentario
	 */
	public void salvaComentario(Comentario comentario) {
		this.comentarioBean.salvaComentario(comentario);
	}

	/**
	 * Removes the comentario.
	 *
	 * @param comentario
	 *            the comentario
	 */
	public void removeComentario(Comentario comentario) {
		this.comentarioBean.removeComentario(comentario);
	}

}
