package br.com.metriken.view.base;

import java.io.Serializable;

/**
 * Classe abstrata que deve ser estendida por todas as classes ViewHelper do
 * sistema. Sua função é auxiliar o bean encapsulando os atributos que serão
 * utilizados na tela.
 * 
 * @author Matheus
 * 
 */
public abstract class AbstractViewHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2089691389811669010L;

	/**
	 * Metodo abstrato para inicializacao dos atributos.
	 */
	public abstract void inicializaAtributos();

}
