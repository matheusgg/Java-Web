package br.com.ok.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import lombok.Getter;
import br.com.ok.facade.PerguntaSegurancaFacade;
import br.com.ok.model.PerguntaSeguranca;
import br.com.ok.view.base.OKBaseMB;
import br.com.ok.view.base.annotation.OnKnowledgeMB;

/**
 * The Class PerguntaSegurancaMB.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@OnKnowledgeMB
public class PerguntaSegurancaMB extends OKBaseMB {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5722877365413029760L;

	/** The pergunta seguranca facade. */
	@Inject
	private PerguntaSegurancaFacade perguntaSegurancaFacade;

	/**
	 * Gets the perguntas seguranca.
	 *
	 * @return the perguntas seguranca
	 */
	@Getter
	private List<PerguntaSeguranca> perguntasSeguranca;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.perguntasSeguranca = this.perguntaSegurancaFacade.findAll();
	}

}
