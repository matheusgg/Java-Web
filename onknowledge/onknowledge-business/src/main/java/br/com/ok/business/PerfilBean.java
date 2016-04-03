package br.com.ok.business;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ok.model.Perfil;
import br.com.ok.model.dao.PerfilDAO;
import br.com.ok.model.enums.PerfilUsuario;

/**
 * The Class PerfilBean.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@Stateless
public class PerfilBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6381000417825433191L;

	/** The perfil dao. */
	@Inject
	private PerfilDAO perfilDAO;

	/**
	 * Pesquisa por descricao.
	 *
	 * @param descricao
	 *            the descricao
	 * @return the perfil
	 */
	public Perfil pesquisaPorDescricao(PerfilUsuario descricao) {
		return this.perfilDAO.findByDescricao(descricao);
	}

}
