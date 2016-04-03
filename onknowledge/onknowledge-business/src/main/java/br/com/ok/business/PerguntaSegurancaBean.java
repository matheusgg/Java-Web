package br.com.ok.business;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.ok.model.PerguntaSeguranca;
import br.com.ok.model.dao.PerguntaSegurancaDAO;

/**
 * The Class PerguntaSegurancaBean.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@Stateless
public class PerguntaSegurancaBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5919156645073844682L;

	/** The pergunta seguranca dao. */
	@Inject
	private PerguntaSegurancaDAO perguntaSegurancaDAO;

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PerguntaSeguranca> findAll() {
		return this.perguntaSegurancaDAO.findAll();
	}

}
