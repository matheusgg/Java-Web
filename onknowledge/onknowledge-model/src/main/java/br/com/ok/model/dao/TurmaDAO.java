package br.com.ok.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import br.com.ok.model.Turma;
import br.com.ok.model.dao.base.OKGenericDAO;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.pagination.OKPaginatedList;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;

/**
 * The Class TurmaDAO.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
public class TurmaDAO extends OKGenericDAO<Turma> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 182398598315887380L;

	/**
	 * Find by codigo and data atual.
	 *
	 * @param codigo
	 *            the codigo
	 * @param dataAtual
	 *            the data atual
	 * @return the turma
	 */
	public Turma findByCodigoAndDataAtual(String codigo, Date dataAtual) {
		TypedQuery<Turma> query = super.getEntityManager().createNamedQuery("Turma.findByCodigoAndDataAtual", Turma.class);
		query.setParameter("codigo", codigo);
		query.setParameter("dataAtual", dataAtual);
		return query.getSingleResult();
	}

	/**
	 * List by codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the list
	 */
	public List<String> listByCodigo(String codigo) {
		TypedQuery<String> query = super.getEntityManager().createNamedQuery("Turma.findCodigosByQuery", String.class);
		query.setParameter("codigo", codigo + OKConstants.PERCENT);
		return query.getResultList();
	}

	/**
	 * Find turmas by args.
	 *
	 * @param args
	 *            the args
	 * @param firstResult
	 *            the first result
	 * @param maxResult
	 *            the max result
	 * @return the OK paginated list
	 */
	public OKPaginatedList<Turma> findTurmasByArgs(Turma args, int firstResult, int maxResult) {
		EasyCriteria<Turma> criteria = EasyCriteriaFactory.createQueryCriteria(super.getEntityManager(), Turma.class);

		Optional.ofNullable(StringUtils.trimToNull(args.getCodigo())).ifPresent(codigo -> {
			criteria.andEquals("codigo", codigo);
		});

		Optional.ofNullable(args.getDataInicio()).ifPresent(dataInicio -> {
			criteria.andGreaterOrEqualTo("dataInicio", dataInicio);
		});

		Optional.ofNullable(args.getDataEncerramento()).ifPresent(dataFinal -> {
			criteria.andLessOrEqualTo("dataEncerramento", dataFinal);
		});

		Optional.ofNullable(args.getCurso()).ifPresent(curso -> {
			criteria.innerJoin("curso").andEquals("curso.id", curso.getId());
		});

		Long count = criteria.count();

		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);

		return new OKPaginatedList<Turma>(criteria.getResultList(), count);
	}

	/**
	 * Count by id and coordenador curso.
	 *
	 * @param idTurma
	 *            the id turma
	 * @param idCoordenador
	 *            the id coordenador
	 * @return the long
	 */
	public Long countByIdAndCoordenadorCurso(Integer idTurma, Integer idCoordenador) {
		TypedQuery<Long> query = super.getEntityManager().createNamedQuery("Turma.countByIdAndCoordenadorCurso", Long.class);
		query.setParameter("idTurma", idTurma);
		query.setParameter("idCoordenador", idCoordenador);
		return query.getSingleResult();
	}

}
