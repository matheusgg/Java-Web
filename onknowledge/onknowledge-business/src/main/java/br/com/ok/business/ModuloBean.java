package br.com.ok.business;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ok.model.Modulo;
import br.com.ok.model.dao.ModuloDAO;

/**
 * The Class ModuloBean.
 *
 * @author Matheus
 * @version 1.0 - 22/09/2014
 */
@Stateless
public class ModuloBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3859149919139686084L;

	/** The modulo dao. */
	@Inject
	private ModuloDAO moduloDAO;

	/**
	 * Salva.
	 *
	 * @param modulo
	 *            the modulo
	 * @return the modulo
	 */
	public Modulo salva(Modulo modulo) {
		if (modulo.getId() == null) {
			this.moduloDAO.save(modulo);
		} else {
			modulo = this.atualiza(modulo);
		}
		return modulo;
	}

	/**
	 * Atualiza.
	 *
	 * @param modulo
	 *            the modulo
	 * @return the modulo
	 */
	public Modulo atualiza(Modulo modulo) {
		return this.moduloDAO.update(modulo);
	}

}
