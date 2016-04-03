package br.com.metriken.view.base.crud;

import java.util.ArrayList;
import java.util.List;

import br.com.metriken.model.nopersistence.apf.APFDetalhada;
import br.com.metriken.model.nopersistence.apf.APFEstimada;
import br.com.metriken.model.nopersistence.apf.Cfps;
import br.com.metriken.model.nopersistence.apf.Documento;
import br.com.metriken.model.nopersistence.apf.Premissa;
import br.com.metriken.model.nopersistence.apf.Projeto;
import br.com.metriken.model.nopersistence.apf.TipoContagem;
import br.com.metriken.model.nopersistence.apf.TipoMetrica;
import br.com.metriken.model.nopersistence.apf.TipoProjeto;
import br.com.metriken.view.base.AbstractViewHelper;

public class APFViewHelper extends AbstractViewHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6426028328079352399L;

	private Projeto projeto;
	private List<Premissa> premissas;
	private List<Documento> documentosUtilizados;
	private List<APFEstimada> APFEstimadas;
	private List<APFDetalhada> APFDetalhadas;

	private List<TipoContagem> comboTipoContagem;
	private List<TipoProjeto> comboTipoProjeto;
	private List<Cfps> comboCFPS;

	private List<TipoMetrica> comboTipoMetrica;

	@Override
	public void inicializaAtributos() {
		this.projeto = new Projeto();
		this.premissas = new ArrayList<Premissa>();
		this.documentosUtilizados = new ArrayList<Documento>();
	}

	/**
	 * @return the projeto
	 */
	public Projeto getProjeto() {
		return projeto;
	}

	/**
	 * @param projeto
	 *            the projeto to set
	 */
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	/**
	 * @return the premissas
	 */
	public List<Premissa> getPremissas() {
		return premissas;
	}

	/**
	 * @param premissas
	 *            the premissas to set
	 */
	public void setPremissas(List<Premissa> premissas) {
		this.premissas = premissas;
	}

	/**
	 * @return the documentosUtilizados
	 */
	public List<Documento> getDocumentosUtilizados() {
		return documentosUtilizados;
	}

	/**
	 * @param documentosUtilizados
	 *            the documentosUtilizados to set
	 */
	public void setDocumentosUtilizados(List<Documento> documentosUtilizados) {
		this.documentosUtilizados = documentosUtilizados;
	}

	/**
	 * @return the aPFEstimadas
	 */
	public List<APFEstimada> getAPFEstimadas() {
		return APFEstimadas;
	}

	/**
	 * @param aPFEstimadas
	 *            the aPFEstimadas to set
	 */
	public void setAPFEstimadas(List<APFEstimada> aPFEstimadas) {
		this.APFEstimadas = aPFEstimadas;
	}

	/**
	 * @return the comboTipoContagem
	 */
	public List<TipoContagem> getComboTipoContagem() {
		return comboTipoContagem;
	}

	/**
	 * @param comboTipoContagem
	 *            the comboTipoContagem to set
	 */
	public void setComboTipoContagem(List<TipoContagem> comboTipoContagem) {
		this.comboTipoContagem = comboTipoContagem;
	}

	/**
	 * @return the comboTipoProjeto
	 */
	public List<TipoProjeto> getComboTipoProjeto() {
		return comboTipoProjeto;
	}

	/**
	 * @param comboTipoProjeto
	 *            the comboTipoProjeto to set
	 */
	public void setComboTipoProjeto(List<TipoProjeto> comboTipoProjeto) {
		this.comboTipoProjeto = comboTipoProjeto;
	}

	/**
	 * @return the comboCFPS
	 */
	public List<Cfps> getComboCFPS() {
		return comboCFPS;
	}

	/**
	 * @param comboCFPS
	 *            the comboCFPS to set
	 */
	public void setComboCFPS(List<Cfps> comboCFPS) {
		this.comboCFPS = comboCFPS;
	}

	/**
	 * @return the aPFDetalhadas
	 */
	public List<APFDetalhada> getAPFDetalhadas() {
		return APFDetalhadas;
	}

	/**
	 * @param aPFDetalhadas
	 *            the aPFDetalhadas to set
	 */
	public void setAPFDetalhadas(List<APFDetalhada> aPFDetalhadas) {
		APFDetalhadas = aPFDetalhadas;
	}

	/**
	 * @return the comboTipoMetrica
	 */
	public List<TipoMetrica> getComboTipoMetrica() {
		return comboTipoMetrica;
	}

	/**
	 * @param comboTipoMetrica
	 *            the comboTipoMetrica to set
	 */
	public void setComboTipoMetrica(List<TipoMetrica> comboTipoMetrica) {
		this.comboTipoMetrica = comboTipoMetrica;
	}

}
