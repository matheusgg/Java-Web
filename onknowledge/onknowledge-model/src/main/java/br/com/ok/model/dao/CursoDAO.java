package br.com.ok.model.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import br.com.ok.model.Curso;
import br.com.ok.model.dao.base.OKGenericDAO;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.pagination.OKPaginatedList;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

/**
 * The Class CursoDAO.
 *
 * @author Matheus
 * @version 1.0 - 20/09/2014
 */
public class CursoDAO extends OKGenericDAO<Curso> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4348759688902937037L;

	/**
	 * List by nome.
	 *
	 * @param nome
	 *            the nome
	 * @return the list
	 */
	public List<String> listByNome(String nome) {
		TypedQuery<String> query = super.getEntityManager().createNamedQuery("Curso.listByNome", String.class);
		query.setParameter("nome", nome + OKConstants.PERCENT);
		return query.getResultList();
	}

	/**
	 * List all.
	 *
	 * @return the list
	 */
	public List<Curso> listAll() {
		TypedQuery<Curso> query = super.getEntityManager().createNamedQuery("Curso.listAll", Curso.class);
		return query.getResultList();
	}

	/**
	 * Count by name.
	 *
	 * @param nome
	 *            the nome
	 * @return the long
	 */
	public Long countByName(String nome) {
		TypedQuery<Long> query = super.getEntityManager().createNamedQuery("Curso.countByName", Long.class);
		query.setParameter("nome", nome);
		return query.getSingleResult();
	}

	/**
	 * Count by name ignoring id.
	 *
	 * @param nome
	 *            the nome
	 * @param id
	 *            the id
	 * @return the long
	 */
	public Long countByNameIgnoringId(String nome, Integer id) {
		TypedQuery<Long> query = super.getEntityManager().createNamedQuery("Curso.countByNameIgnoringId", Long.class);
		query.setParameter("nome", nome);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	/**
	 * Find cursos by args.
	 *
	 * @param args
	 *            the args
	 * @param firstResult
	 *            the first result
	 * @param maxResult
	 *            the max result
	 * @return the OK paginated list
	 */
	public OKPaginatedList<Curso> findCursosByArgs(Curso args, int firstResult, int maxResult) {
		EasyCriteria<Curso> criteria = EasyCriteriaFactory.createQueryCriteria(super.getEntityManager(), Curso.class);
		criteria.orderByAsc("nome");

		Optional.ofNullable(StringUtils.trimToNull(args.getNome())).ifPresent(nome -> {
			criteria.andStringLike("nome", nome + OKConstants.PERCENT);
		});

		Optional.ofNullable(StringUtils.trimToNull(args.getCoordenador().getNome())).ifPresent(coordenador -> {
			criteria.innerJoin("professor").andEquals("professor.nome", coordenador);
		});

		Long count = criteria.count();
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);

		return new OKPaginatedList<Curso>(criteria.getResultList(), count);
	}

	/**
	 * Count by id and coordenador.
	 *
	 * @param idCoordenador
	 *            the id coordenador
	 * @return the long
	 */
	public Long countByIdAndCoordenador(Integer id, Integer idCoordenador) {
		TypedQuery<Long> query = super.getEntityManager().createNamedQuery("Curso.countByIdAndCoordenador", Long.class);
		query.setParameter("id", id);
		query.setParameter("idCoordenador", idCoordenador);
		return query.getSingleResult();
	}

}
