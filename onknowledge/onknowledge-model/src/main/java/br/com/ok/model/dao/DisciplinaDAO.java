package br.com.ok.model.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import br.com.ok.model.Disciplina;
import br.com.ok.model.dao.base.OKGenericDAO;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.pagination.OKPaginatedList;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

/**
 * The Class DisciplinaDAO.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
public class DisciplinaDAO extends OKGenericDAO<Disciplina> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7078493609336458226L;

	/**
	 * List by nome.
	 *
	 * @param nome
	 *            the nome
	 * @return the list
	 */
	public List<Disciplina> listByNome(String nome) {
		TypedQuery<Disciplina> query = super.getEntityManager().createNamedQuery("Disciplina.listByNome", Disciplina.class);
		query.setParameter("nome", nome + OKConstants.PERCENT);
		return query.getResultList();
	}

	/**
	 * Find disciplinas by args.
	 *
	 * @param args
	 *            the args
	 * @param firstResult
	 *            the first result
	 * @param maxResult
	 *            the max result
	 * @param extraCurricular
	 *            the extra curricular
	 * @return the OK paginated list
	 */
	public OKPaginatedList<Disciplina> findDisciplinasByArgs(Disciplina args, int firstResult, int maxResult, boolean extraCurricular) {
		EasyCriteria<Disciplina> criteria = EasyCriteriaFactory.createQueryCriteria(super.getEntityManager(), Disciplina.class);
		criteria.orderByAsc("nome");

		Optional.ofNullable(StringUtils.trimToNull(args.getCodigo())).ifPresent(codigo -> {
			criteria.andEquals("codigo", codigo);
		});

		Optional.ofNullable(StringUtils.trimToNull(args.getNome())).ifPresent(nome -> {
			criteria.andStringLike("nome", OKConstants.PERCENT + nome + OKConstants.PERCENT);
		});

		Optional.ofNullable(args.getDataInicio()).ifPresent(dataInicio -> {
			criteria.andGreaterOrEqualTo("dataInicio", dataInicio);
		});

		Optional.ofNullable(args.getDataEncerramento()).ifPresent(dataFinal -> {
			criteria.andLessOrEqualTo("dataEncerramento", dataFinal);
		});

		if (extraCurricular) {
			criteria.andCollectionIsEmpty("cursos");
		}

		Long count = criteria.count();

		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);

		return new OKPaginatedList<Disciplina>(criteria.getResultList(), count);
	}

	/**
	 * Find all disciplinas excluding id.
	 *
	 * @param id
	 *            the id
	 * @return the list
	 */
	public List<Disciplina> findAllDisciplinasExcludingId(Integer id) {
		TypedQuery<Disciplina> query = super.getEntityManager().createNamedQuery("Disciplina.findAllDisciplinasExcludingId", Disciplina.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	/**
	 * Find codigos by aluno.
	 *
	 * @param idAluno
	 *            the id aluno
	 * @return the list
	 */
	public List<String> findCodigosByAluno(Integer idAluno) {
		TypedQuery<String> query = super.getEntityManager().createNamedQuery("Disciplina.findCodigosByAluno", String.class);
		query.setParameter("id", idAluno);
		return query.getResultList();
	}

	/**
	 * Find by id professor.
	 *
	 * @param idProfessor
	 *            the id professor
	 * @return the list
	 */
	public List<Disciplina> findByIdProfessor(Integer idProfessor) {
		TypedQuery<Disciplina> query = super.getEntityManager().createNamedQuery("Disciplina.findByIdProfessor", Disciplina.class);
		query.setParameter("id", idProfessor);
		return query.getResultList();
	}

	/**
	 * Find by id aluno.
	 *
	 * @param idAluno
	 *            the id aluno
	 * @return the list
	 */
	public List<Disciplina> findByIdAluno(Integer idAluno) {
		TypedQuery<Disciplina> query = super.getEntityManager().createNamedQuery("Disciplina.findByIdAluno", Disciplina.class);
		query.setParameter("id", idAluno);
		return query.getResultList();
	}

	/**
	 * Find by turma.
	 *
	 * @param idTurma
	 *            the id turma
	 * @return the list
	 */
	public List<Disciplina> findByTurma(Integer idTurma) {
		TypedQuery<Disciplina> query = super.getEntityManager().createNamedQuery("Disciplina.findByTurma", Disciplina.class);
		query.setParameter("id", idTurma);
		return query.getResultList();
	}

	/**
	 * Count by id and professor.
	 *
	 * @param idDisciplina
	 *            the id disciplina
	 * @param idProfessor
	 *            the id professor
	 * @return the long
	 */
	public Long countByIdAndProfessor(Integer idDisciplina, Integer idProfessor) {
		TypedQuery<Long> query = super.getEntityManager().createNamedQuery("Disciplina.countByIdAndProfessor", Long.class);
		query.setParameter("idDisciplina", idDisciplina);
		query.setParameter("idProfessor", idProfessor);
		return query.getSingleResult();
	}

	/**
	 * Count by id and coordenador curso.
	 *
	 * @param idDisciplina
	 *            the id disciplina
	 * @param idCoordenador
	 *            the id coordenador
	 * @return the long
	 */
	public Long countByIdAndCoordenadorCurso(Integer idDisciplina, Integer idCoordenador) {
		TypedQuery<Long> query = super.getEntityManager().createNamedQuery("Disciplina.countByIdAndCoordenadorCurso", Long.class);
		query.setParameter("idDisciplina", idDisciplina);
		query.setParameter("idCoordenador", idCoordenador);
		return query.getSingleResult();
	}
}
