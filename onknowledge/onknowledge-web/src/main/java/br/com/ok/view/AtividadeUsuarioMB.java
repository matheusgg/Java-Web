package br.com.ok.view;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.omnifaces.util.Faces;

import lombok.Getter;
import lombok.Setter;
import br.com.ok.facade.AtividadeFacade;
import br.com.ok.facade.RespostaFacade;
import br.com.ok.model.Alternativa;
import br.com.ok.model.Aluno;
import br.com.ok.model.Atividade;
import br.com.ok.model.Questao;
import br.com.ok.model.Resposta;
import br.com.ok.model.Usuario;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.messages.OKMessages;
import br.com.ok.view.base.OKBaseMB;
import br.com.ok.view.base.annotation.OnKnowledgeMB;

/**
 * The Class AtividadeUsuarioMB.
 *
 * @author Matheus
 * @version 1.0 - 11/10/2014
 */
@OnKnowledgeMB
public class AtividadeUsuarioMB extends OKBaseMB {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8423817276560225105L;

	/** The teste. */
	@Getter
	@Setter
	private String teste;

	/** The atividade facade. */
	@Inject
	private AtividadeFacade atividadeFacade;

	/** The resposta facade. */
	@Inject
	private RespostaFacade respostaFacade;

	/** The atividades. */
	@Getter
	private List<Atividade> atividades;

	/** The atividade. */
	@Getter
	private Atividade atividade;

	/** The resposta. */
	@Getter
	@Setter
	private Resposta resposta;

	/** The respostas. */
	@Getter
	@Setter
	private List<Resposta> respostas;

	/**
	 * Carrega atividades por modulo.
	 *
	 * @param idModulo
	 *            the id modulo
	 */
	public void carregaAtividadesPorModulo(Integer idModulo, Usuario usuario) {
		if (usuario instanceof Aluno) {
			this.atividades = this.atividadeFacade.pesquisaAtividadesPorModulo(idModulo);
		} else {
			this.atividades = this.atividadeFacade.pesquisaQuestionariosPorModuloProfessor(idModulo, usuario.getId());
		}
	}

	/**
	 * Prepara atividade selecionada.
	 *
	 * @param idAtividade
	 *            the id atividade
	 */
	public void preparaAtividadeSelecionada(Integer idAtividade) {
		if (this.atividade == null || !this.atividade.getId().equals(idAtividade)) {
			this.atividade = this.atividadeFacade.pesquisaAtividadePorId(idAtividade);
		}
	}

	/**
	 * Prepara adicao resposta.
	 *
	 * @param questao
	 *            the questao
	 * @param aluno
	 *            the aluno
	 */
	public void preparaAdicaoResposta(Questao questao, Aluno aluno) {
		this.resposta = this.respostaFacade.pesquisaRespostaPorQuestaoAluno(questao.getId(), aluno.getId());
		if (this.resposta != null) {
			List<Alternativa> alternativas = this.resposta.getAlternativas();
			questao.getAlternativas().stream().filter(alt -> alternativas.contains(alt)).forEach(alt -> alt.setSelecionada(true));
		} else {
			this.resposta = new Resposta();
			this.resposta.setAnexos(new ArrayList<>());
			this.resposta.setAluno(aluno);
		}
		this.resposta.setQuestao(questao);
	}

	/**
	 * Responde questao.
	 */
	public void respondeQuestao() {
		this.respostaFacade.respondeQuestao(this.resposta);
		this.resposta = null;
		OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getFormattedMessage("msg.sucesso.resposta.salva"), OKMessages.Severity.SUCCESS);
	}

	/**
	 * Prepara respostas questao.
	 *
	 * @param idQuestao
	 *            the id questao
	 */
	public void preparaRespostasQuestao(Integer idQuestao) {
		this.respostas = this.respostaFacade.pesquisaRespostasPorQuestao(idQuestao);
	}

	/**
	 * Prepara visualizacao resposta.
	 *
	 * @param resposta
	 *            the resposta
	 */
	public void preparaVisualizacaoResposta(Resposta resposta) {
		this.resposta = resposta;
		super.getRequestContext().execute("$('#respostaModal').modal('show')");
	}

	/**
	 * Corrige resposta.
	 *
	 * @param respostaCorreta
	 *            the resposta correta
	 */
	public void corrigeResposta(Boolean respostaCorreta) {
		this.resposta.setRespostaCorreta(respostaCorreta);
		this.resposta.setCorrigida(true);

		Resposta resposta = this.respostaFacade.atualizaResposta(this.resposta);
		int index = this.respostas.indexOf(resposta);
		this.respostas.set(index, resposta);
		this.resposta = null;

		super.getRequestContext().execute("$('#respostaModal').modal('hide')");
		OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getFormattedMessage("msg.sucesso.correcao.resposta"), OKMessages.Severity.SUCCESS);
	}

	/**
	 * Prepara visualizacao atividade.
	 *
	 * @param idAtividade
	 *            the id atividade
	 * @return the string
	 */
	public String preparaVisualizacaoAtividade(Integer idAtividade) {
		Faces.setFlashAttribute(OKConstants.ATIVIDADE_ID_KEY, idAtividade);
		return OKConstants.PRETTY_ACTIVITY_REGISTER;
	}
}
