package br.com.ok.facade;

import java.util.List;

import javax.inject.Inject;

import br.com.ok.business.RespostaBean;
import br.com.ok.facade.base.OKGenericFacade;
import br.com.ok.model.Resposta;

/**
 * The Class RespostaFacade.
 *
 * @author Matheus
 * @version 1.0 - 10/10/2014
 */
public class RespostaFacade extends OKGenericFacade {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2861306860964935753L;

	/** The resposta bean. */
	@Inject
	private RespostaBean respostaBean;

	/**
	 * Pesquisa resposta por questao aluno.
	 *
	 * @param idQuestao
	 *            the id questao
	 * @param idAluno
	 *            the id aluno
	 * @return the resposta
	 */
	public Resposta pesquisaRespostaPorQuestaoAluno(Integer idQuestao, Integer idAluno) {
		return this.respostaBean.pesquisaRespostaPorQuestaoAluno(idQuestao, idAluno);
	}

	public void respondeQuestao(Resposta resposta) {
		this.respostaBean.respondeQuestao(resposta);
	}

	/**
	 * Pesquisa respostas por questao.
	 *
	 * @param idQuestao
	 *            the id questao
	 * @return the list
	 */
	public List<Resposta> pesquisaRespostasPorQuestao(Integer idQuestao) {
		return this.respostaBean.pesquisaRespostasPorQuestao(idQuestao);
	}

	/**
	 * Atualiza resposta.
	 *
	 * @param resposta
	 *            the resposta
	 * @return the resposta
	 */
	public Resposta atualizaResposta(Resposta resposta) {
		return this.respostaBean.atualizaResposta(resposta);
	}

}
