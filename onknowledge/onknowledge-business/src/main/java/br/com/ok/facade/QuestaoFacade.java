package br.com.ok.facade;

import java.util.List;

import javax.inject.Inject;

import br.com.ok.business.QuestaoBean;
import br.com.ok.facade.base.OKGenericFacade;
import br.com.ok.model.Questao;

/**
 * The Class QuestaoFacade.
 *
 * @author Matheus
 * @version 1.0 - 04/10/2014
 */
public class QuestaoFacade extends OKGenericFacade {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2025041104636285200L;

	/** The questao bean. */
	@Inject
	private QuestaoBean questaoBean;

	/**
	 * Valida informacoes questao.
	 *
	 * @param questao
	 *            the questao
	 */
	public void validaInformacoesQuestao(Questao questao) {
		this.questaoBean.validaInformacoesQuestao(questao);
	}

	/**
	 * Inicializa questoes.
	 *
	 * @param questoes
	 *            the questoes
	 * @return the list
	 */
	public List<Questao> inicializaQuestoes(List<Questao> questoes) {
		return this.questaoBean.inicializaQuestoes(questoes);
	}
}
