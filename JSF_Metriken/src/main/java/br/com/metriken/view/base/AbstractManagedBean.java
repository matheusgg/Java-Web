package br.com.metriken.view.base;

import java.io.Serializable;

/**
 * Classe abstrata que deve ser estendida por todos os Beans do sistema. Aqui é
 * definido o viewHelper que será utlizado pelo Managed Bean.
 * 
 * @author Matheus
 * 
 * @param <VH>
 */
public abstract class AbstractManagedBean<VH extends AbstractViewHelper> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4499669933119845443L;

	private VH viewHelper;

	public AbstractManagedBean() {

	}

	public AbstractManagedBean(VH viewHelper) {
		this.viewHelper = viewHelper;
		this.viewHelper.inicializaAtributos();
	}

	/**
	 * @return the viewHelper
	 */
	public VH getViewHelper() {
		return viewHelper;
	}

	/**
	 * @param viewHelper
	 *            the viewHelper to set
	 */
	public void setViewHelper(VH viewHelper) {
		this.viewHelper = viewHelper;
	}
}
