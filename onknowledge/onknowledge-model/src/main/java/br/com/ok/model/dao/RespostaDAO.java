package br.com.ok.model.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.ok.model.Resposta;
import br.com.ok.model.dao.base.OKGenericDAO;

/**
 * The Class RespostaDAO.
 *
 * @author Matheus
 * @version 1.0 - 10/10/2014
 */
public class RespostaDAO extends OKGenericDAO<Resposta> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3955265575396074779L;

	/**
	 * Find by questao and aluno.
	 *
	 * @param idQuestao
	 *            the id questao
	 * @param idAluno
	 *            the id aluno
	 * @return the resposta
	 */
	public Resposta findByQuestaoAndAluno(Integer idQuestao, Integer idAluno) {
		TypedQuery<Resposta> query = super.getEntityManager().createNamedQuery("Resposta.findByQuestaoAndAluno", Resposta.class);
		query.setParameter("idQuestao", idQuestao);
		query.setParameter("idAluno", idAluno);
		return query.getSingleResult();
	}

	/**
	 * Count by questao and aluno.
	 *
	 * @param idQuestao
	 *            the id questao
	 * @param idAluno
	 *            the id aluno
	 * @return the long
	 */
	public Long countByQuestaoAndAluno(Integer idQuestao, Integer idAluno) {
		TypedQuery<Long> query = super.getEntityManager().createNamedQuery("Resposta.countByQuestaoAndAluno", Long.class);
		query.setParameter("idQuestao", idQuestao);
		query.setParameter("idAluno", idAluno);
		return query.getSingleResult();
	}

	/**
	 * Find by questao.
	 *
	 * @param idQuestao
	 *            the id questao
	 * @return the list
	 */
	public List<Resposta> findByQuestao(Integer idQuestao) {
		TypedQuery<Resposta> query = super.getEntityManager().createNamedQuery("Resposta.findByQuestao", Resposta.class);
		query.setParameter("idQuestao", idQuestao);
		return query.getResultList();
	}

}
