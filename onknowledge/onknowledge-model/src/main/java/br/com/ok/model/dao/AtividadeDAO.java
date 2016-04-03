package br.com.ok.model.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import br.com.ok.model.Atividade;
import br.com.ok.model.Disciplina;
import br.com.ok.model.Modulo;
import br.com.ok.model.dao.base.OKGenericDAO;
import br.com.ok.model.enums.TipoAtividade;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.pagination.OKPaginatedList;

/**
 * The Class AtividadeDAO.
 *
 * @author Matheus
 * @version 1.0 - 09/10/2014
 */
public class AtividadeDAO extends OKGenericDAO<Atividade> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6114934684091816027L;

	/**
	 * Find atividades by args.
	 *
	 * @param args
	 *            the args
	 * @param firstResult
	 *            the first result
	 * @param maxResult
	 *            the max result
	 * @return the OK paginated list
	 */
	@SuppressWarnings("unchecked")
	public OKPaginatedList<Atividade> findAtividadesByArgs(Atividade args, int firstResult, int maxResult) {
		String select = "select a from ";
		String selectCount = "select count(a) from ";

		Map<String, Object> params = new HashMap<>();
		StringBuilder queryBuilder = new StringBuilder(select);

		if (TipoAtividade.SIMPLES.equals(args.getTipoAtividade())) {
			queryBuilder.append("AtividadeSimples a ");
		} else {
			queryBuilder.append("AtividadeQuestionario a ");
		}

		this.prepareCommonSearchArgs(args, params, queryBuilder);

		Query searchQuery = super.getEntityManager().createQuery(queryBuilder.toString());
		Query countQuery = super.getEntityManager().createQuery(queryBuilder.toString().replace(select, selectCount));

		params.forEach((key, value) -> {
			searchQuery.setParameter(key, value);
			countQuery.setParameter(key, value);
		});

		searchQuery.setFirstResult(firstResult);
		searchQuery.setMaxResults(firstResult);

		return new OKPaginatedList<Atividade>(searchQuery.getResultList(), (Long) countQuery.getSingleResult());
	}

	/**
	 * Prepare common search args.
	 *
	 * @param args
	 *            the args
	 * @param params
	 *            the params
	 * @param queryBuilder
	 *            the query builder
	 */
	private void prepareCommonSearchArgs(Atividade args, Map<String, Object> params, StringBuilder queryBuilder) {
		Optional<Modulo> moduloOptional = Optional.ofNullable(args.getModulo());
		Optional<Disciplina> disciplinaOptional = Optional.ofNullable(args.getDisciplina());
		Optional<String> professorOptional = Optional.ofNullable(StringUtils.trimToNull(args.getProfessor().getNome()));

		if (moduloOptional.isPresent()) {
			queryBuilder.append("inner join a.modulo m ");
		} else if (disciplinaOptional.isPresent()) {
			queryBuilder.append("inner join a.modulo m inner join m.disciplinas d ");
		}

		if (professorOptional.isPresent()) {
			queryBuilder.append("inner join a.professor p ");
		}

		queryBuilder.append(OKConstants.WHERE).append("1=1");

		Optional.ofNullable(StringUtils.trimToNull(args.getDescricao())).ifPresent(descricao -> {
			params.put("descricao", descricao + OKConstants.PERCENT);
			queryBuilder.append(OKConstants.AND).append("a.descricao like :descricao ");
		});

		moduloOptional.ifPresent(modulo -> {
			params.put("modulo", modulo.getId());
			queryBuilder.append(OKConstants.AND).append("m.id = :modulo");
		});

		if (!moduloOptional.isPresent() && disciplinaOptional.isPresent()) {
			disciplinaOptional.ifPresent(disciplina -> {
				params.put("disciplina", disciplina.getId());
				queryBuilder.append(OKConstants.AND).append("d.id = :disciplina ");
			});
		}

		professorOptional.ifPresent(professor -> {
			params.put("professor", professor + OKConstants.PERCENT);
			queryBuilder.append(OKConstants.AND).append("p.nome like :professor ");
		});

		queryBuilder.append(OKConstants.ORDER_BY).append("a.descricao");
	}

	/**
	 * Find data prazo final by id.
	 *
	 * @param id
	 *            the id
	 * @return the date
	 */
	public Date findDataPrazoFinalById(Integer id) {
		TypedQuery<Date> query = super.getEntityManager().createNamedQuery("Atividade.findDataPrazoFinalById", Date.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	/**
	 * Find by modulo.
	 *
	 * @param idModulo
	 *            the id modulo
	 * @return the list
	 */
	public List<Atividade> findByModulo(Integer idModulo) {
		TypedQuery<Atividade> query = super.getEntityManager().createNamedQuery("Atividade.findByModulo", Atividade.class);
		query.setParameter("id", idModulo);
		return query.getResultList();
	}

	/**
	 * Find questionarios by modulo.
	 *
	 * @param idModulo
	 *            the id modulo
	 * @return the list
	 */
	public List<Atividade> findQuestionariosByModulo(Integer idModulo) {
		TypedQuery<Atividade> query = super.getEntityManager().createNamedQuery("AtividadeQuestionario.findByModulo", Atividade.class);
		query.setParameter("id", idModulo);
		return query.getResultList();
	}

	/**
	 * Find questionarios by modulo and professor.
	 *
	 * @param idModulo
	 *            the id modulo
	 * @param idProfessor
	 *            the id professor
	 * @return the list
	 */
	public List<Atividade> findQuestionariosByModuloAndProfessor(Integer idModulo, Integer idProfessor) {
		TypedQuery<Atividade> query = super.getEntityManager().createNamedQuery("AtividadeQuestionario.findByModuloAndProfessor", Atividade.class);
		query.setParameter("idModulo", idModulo);
		query.setParameter("idProfessor", idProfessor);
		return query.getResultList();
	}

	/**
	 * Count questionarios by modulo.
	 *
	 * @param idModulo
	 *            the id modulo
	 * @return the long
	 */
	public Long countQuestionariosByModulo(Integer idModulo) {
		TypedQuery<Long> query = super.getEntityManager().createNamedQuery("AtividadeQuestionario.countByModulo", Long.class);
		query.setParameter("id", idModulo);
		return query.getSingleResult();
	}

	/**
	 * Count questionarios by modulo and professor.
	 *
	 * @param idModulo
	 *            the id modulo
	 * @param idProfessor
	 *            the id professor
	 * @return the long
	 */
	public Long countQuestionariosByModuloAndProfessor(Integer idModulo, Integer idProfessor) {
		TypedQuery<Long> query = super.getEntityManager().createNamedQuery("AtividadeQuestionario.countByModuloAndProfessor", Long.class);
		query.setParameter("idModulo", idModulo);
		query.setParameter("idProfessor", idProfessor);
		return query.getSingleResult();
	}
}
