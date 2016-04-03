package br.com.ok.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.model.Alternativa;
import br.com.ok.model.Resposta;
import br.com.ok.model.dao.RespostaDAO;
import br.com.ok.util.constants.OKConstants;

/**
 * The Class RespostaBean.
 *
 * @author Matheus
 * @version 1.0 - 10/10/2014
 */
@Stateless
public class RespostaBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2854277698390922808L;

	/** The resposta dao. */
	@Inject
	private RespostaDAO respostaDAO;

	/** The anexo bean. */
	@Inject
	private AnexoBean anexoBean;

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
		Resposta resposta = null;
		Long count = this.respostaDAO.countByQuestaoAndAluno(idQuestao, idAluno);
		if (count > OKConstants.VALOR_ZERO) {
			resposta = this.respostaDAO.findByQuestaoAndAluno(idQuestao, idAluno);
			this.respostaDAO.initProxy(resposta.getAnexos());
			this.respostaDAO.initProxy(resposta.getAlternativas());
		}
		return resposta;
	}

	/**
	 * Responde questao.
	 *
	 * @param resposta
	 *            the resposta
	 */
	public void respondeQuestao(Resposta resposta) {
		this.preparaAlternativasResposta(resposta);
		this.anexoBean.salvaArquivosAnexosEmDisco(resposta.getAnexos());

		if (resposta.getId() == null) {
			this.respostaDAO.save(resposta);
		} else {
			this.respostaDAO.update(resposta);
		}
	}

	/**
	 * Prepara alternativas resposta.
	 *
	 * @param resposta
	 *            the resposta
	 */
	private void preparaAlternativasResposta(Resposta resposta) {
		List<Alternativa> alternativasResposta = new ArrayList<>();
		List<Alternativa> alternativasQuestao = resposta.getQuestao().getAlternativas();

		alternativasQuestao.stream().filter(alt -> alt.getSelecionada()).forEach(alt -> {
			alternativasResposta.add(alt);
		});

		if (!alternativasQuestao.isEmpty() && alternativasResposta.isEmpty()) {
			throw new OKBusinessException("{msg.validacao.selecione.uma.alternativa}");
		}

		resposta.setAlternativas(alternativasResposta);
	}

	/**
	 * Pesquisa respostas por questao.
	 *
	 * @param idQuestao
	 *            the id questao
	 * @return the list
	 */
	public List<Resposta> pesquisaRespostasPorQuestao(Integer idQuestao) {
		List<Resposta> respostas = this.respostaDAO.findByQuestao(idQuestao);
		respostas.forEach(resposta -> {
			this.respostaDAO.initProxy(resposta.getAlternativas());
			this.respostaDAO.initProxy(resposta.getAnexos());
		});
		return respostas;
	}

	/**
	 * Atualiza resposta.
	 *
	 * @param resposta
	 *            the resposta
	 * @return the resposta
	 */
	public Resposta atualizaResposta(Resposta resposta) {
		return this.respostaDAO.update(resposta);
	}

}
