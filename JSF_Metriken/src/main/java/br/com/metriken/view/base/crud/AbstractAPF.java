package br.com.metriken.view.base.crud;

import br.com.metriken.view.base.AbstractManagedBean;

/**
 * Classe abstrata responável por auxiliar todos os beans relacionados a técnica
 * de métrica APF. Esta classe deve ser estendida pelos beans que trabalharam
 * com esta técnica. Aqui é definido o Business Service que será utilizado pelo
 * bean.
 * 
 * @author Matheus
 * 
 * @param <VH>
 *            Implementação do ViewHelper
 */
public abstract class AbstractAPF extends AbstractManagedBean<APFViewHelper> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3889402073721042708L;

	public AbstractAPF() {
		super(new APFViewHelper());
	}

	/**
	 * Método responsável pela troca de viewHelper na mudança de guias. Deve ser
	 * sobrescrito nos beans reponsáveis pelo trabalho com a técnica APF quando
	 * for necessário a recuperação de informações contidas dentro de algum
	 * viewHelper de outro bean que estaja sendo manipulado em outra guia.
	 * 
	 * @param viewHelper
	 */
	public void begin(APFViewHelper viewHelper) {
		this.getViewHelper().setDocumentosUtilizados(viewHelper.getDocumentosUtilizados());
		this.getViewHelper().setPremissas(viewHelper.getPremissas());
		this.getViewHelper().setProjeto(viewHelper.getProjeto());
		this.getViewHelper().setAPFEstimadas(viewHelper.getAPFEstimadas());
	}
}
