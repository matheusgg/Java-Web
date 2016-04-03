package br.com.ok.model.dao;

import javax.persistence.TypedQuery;

import br.com.ok.model.CodigoSeguranca;
import br.com.ok.model.dao.base.OKGenericDAO;

/**
 * The Class CodigoSegurancaDAO.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
public class CodigoSegurancaDAO extends OKGenericDAO<CodigoSeguranca> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5594608777519887656L;

	/**
	 * Find by codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the codigo seguranca
	 */
	public CodigoSeguranca findByCodigo(String codigo) {
		TypedQuery<CodigoSeguranca> query = super.getEntityManager().createNamedQuery("CodigoSeguranca.findByCodigo", CodigoSeguranca.class);
		query.setParameter("codigo", codigo);
		return query.getSingleResult();
	}

}
