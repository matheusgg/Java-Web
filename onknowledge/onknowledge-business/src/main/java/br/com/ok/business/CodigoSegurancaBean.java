package br.com.ok.business;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;

import br.com.ok.model.CodigoSeguranca;
import br.com.ok.model.dao.CodigoSegurancaDAO;
import br.com.ok.util.constants.OKConstants;

/**
 * The Class CodigoSegurancaBean.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@Stateless
public class CodigoSegurancaBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9131074122617926523L;

	/** The codigo seguranca dao. */
	@Inject
	private CodigoSegurancaDAO codigoSegurancaDAO;

	/**
	 * Pesquisa por codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the codigo seguranca
	 */
	public CodigoSeguranca pesquisaPorCodigo(String codigo) {
		return this.codigoSegurancaDAO.findByCodigo(codigo);
	}

	/**
	 * Salva codigo seguranca.
	 *
	 * @param codigoSeguranca
	 *            the codigo seguranca
	 * @return the codigo seguranca
	 */
	public CodigoSeguranca salvaCodigoSeguranca(CodigoSeguranca codigoSeguranca) {
		if (codigoSeguranca.getId() == null) {
			this.codigoSegurancaDAO.save(codigoSeguranca);
		} else {
			codigoSeguranca = this.codigoSegurancaDAO.update(codigoSeguranca);
		}
		return codigoSeguranca;
	}

	/**
	 * Gera codigo seguranca.
	 *
	 * @return the codigo seguranca
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CodigoSeguranca geraCodigoSeguranca() {
		CodigoSeguranca codigoSeguranca = new CodigoSeguranca();
		codigoSeguranca.setAtivo(true);
		codigoSeguranca.setCodigo(RandomStringUtils.randomAlphanumeric(OKConstants.VALOR_DEZ));
		return codigoSeguranca;
	}
}
