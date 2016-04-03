package br.com.ok.model.dao;

import javax.persistence.TypedQuery;

import br.com.ok.model.Perfil;
import br.com.ok.model.dao.base.OKGenericDAO;
import br.com.ok.model.enums.PerfilUsuario;

/**
 * The Class PerfilDAO.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
public class PerfilDAO extends OKGenericDAO<Perfil> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2866170592534918918L;

	/**
	 * Find by descricao.
	 *
	 * @param description
	 *            the description
	 * @return the perfil
	 */
	public Perfil findByDescricao(PerfilUsuario description) {
		TypedQuery<Perfil> query = super.getEntityManager().createNamedQuery("Perfil.findByDescricao", Perfil.class);
		query.setParameter("descricao", description);
		return query.getSingleResult();
	}

}
