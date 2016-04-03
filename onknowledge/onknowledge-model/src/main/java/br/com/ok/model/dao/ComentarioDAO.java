package br.com.ok.model.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.ok.model.Comentario;
import br.com.ok.model.dao.base.OKGenericDAO;

/**
 * The Class ComentarioDAO.
 *
 * @author Matheus
 * @version 1.0 - 11/10/2014
 */
public class ComentarioDAO extends OKGenericDAO<Comentario> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8283053376143280839L;

	/**
	 * Find by questao.
	 *
	 * @param idQuestao
	 *            the id questao
	 * @return the list
	 */
	public List<Comentario> findByQuestao(Integer idQuestao) {
		TypedQuery<Comentario> query = super.getEntityManager().createNamedQuery("Comentario.findByQuestao", Comentario.class);
		query.setParameter("id", idQuestao);
		return query.getResultList();
	}

	/**
	 * Find by atividade.
	 *
	 * @param idQuestao
	 *            the id questao
	 * @return the list
	 */
	public List<Comentario> findByAtividade(Integer idQuestao) {
		TypedQuery<Comentario> query = super.getEntityManager().createNamedQuery("Comentario.findByAtividade", Comentario.class);
		query.setParameter("id", idQuestao);
		return query.getResultList();
	}

}
