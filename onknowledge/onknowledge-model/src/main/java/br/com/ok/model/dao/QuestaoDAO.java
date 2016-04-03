package br.com.ok.model.dao;

import javax.persistence.TypedQuery;

import br.com.ok.model.Questao;
import br.com.ok.model.dao.base.OKGenericDAO;

/**
 * The Class QuestaoDAO.
 *
 * @author Matheus
 * @version 1.0 - 10/10/2014
 */
public class QuestaoDAO extends OKGenericDAO<Questao> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7866815172489226564L;

	/**
	 * Count by disciplina and aluno.
	 *
	 * @param idDisciplina
	 *            the id disciplina
	 * @param idAluno
	 *            the id aluno
	 * @return the long
	 */
	public Long countByDisciplinaAndAluno(Integer idDisciplina, Integer idAluno) {
		TypedQuery<Long> query = super.getEntityManager().createNamedQuery("Questao.countByDisciplinaAndAluno", Long.class);
		query.setParameter("idDisciplina", idDisciplina);
		query.setParameter("idAluno", idAluno);
		return query.getSingleResult();
	}

	/**
	 * Count by disciplina.
	 *
	 * @param idDisciplina
	 *            the id disciplina
	 * @return the long
	 */
	public Long countByDisciplina(Integer idDisciplina) {
		TypedQuery<Long> query = super.getEntityManager().createNamedQuery("Questao.countByDisciplina", Long.class);
		query.setParameter("idDisciplina", idDisciplina);
		return query.getSingleResult();
	}

}
