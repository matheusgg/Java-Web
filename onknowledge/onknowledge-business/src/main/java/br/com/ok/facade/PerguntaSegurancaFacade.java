package br.com.ok.facade;

import java.util.List;

import javax.inject.Inject;

import br.com.ok.business.PerguntaSegurancaBean;
import br.com.ok.facade.base.OKGenericFacade;
import br.com.ok.model.PerguntaSeguranca;

/**
 * The Class PerguntaSegurancaFacade.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
public class PerguntaSegurancaFacade extends OKGenericFacade {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1314729477048934128L;

	/** The pergunta seguranca bean. */
	@Inject
	private PerguntaSegurancaBean perguntaSegurancaBean;

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<PerguntaSeguranca> findAll() {
		return this.perguntaSegurancaBean.findAll();
	}
}
