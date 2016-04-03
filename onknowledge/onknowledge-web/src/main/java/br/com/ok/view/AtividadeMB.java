package br.com.ok.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

import org.omnifaces.util.Faces;

import br.com.ok.facade.AtividadeFacade;
import br.com.ok.facade.QuestaoFacade;
import br.com.ok.model.Alternativa;
import br.com.ok.model.Anexo;
import br.com.ok.model.Atividade;
import br.com.ok.model.AtividadeQuestionario;
import br.com.ok.model.AtividadeSimples;
import br.com.ok.model.Professor;
import br.com.ok.model.Questao;
import br.com.ok.model.enums.TipoAtividade;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.messages.OKMessages;
import br.com.ok.util.pagination.OKLazyDataModel;
import br.com.ok.view.base.OKBaseMB;
import br.com.ok.view.base.annotation.OnKnowledgeMB;

/**
 * The Class AtividadeMB.
 *
 * @author Matheus
 * @version 1.0 - 09/10/2014
 */
@OnKnowledgeMB
public class AtividadeMB extends OKBaseMB {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2976075241201459896L;

	/** The questao facade. */
	@Inject
	private QuestaoFacade questaoFacade;

	/** The atividade facade. */
	@Inject
	private AtividadeFacade atividadeFacade;

	/** The atividade. */
	@Getter
	@Setter
	private Atividade atividade;

	/** The questao selecionada. */
	@Getter
	@Setter
	private Questao questaoSelecionada;

	/** The anexos questao. */
	@Getter
	private List<Anexo> anexosQuestao;

	/** The edicao questao. */
	@Getter
	@Setter
	private boolean edicaoQuestao;

	/** The atividades data model. */
	@Getter
	private OKLazyDataModel<Atividade> atividadesDataModel;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.pesquisaAtividadeSelecionada();
	}

	/**
	 * Pesquisa atividade selecionada.
	 */
	private void pesquisaAtividadeSelecionada() {
		Integer idAtividade = Faces.getFlashAttribute(OKConstants.ATIVIDADE_ID_KEY);
		if (idAtividade != null) {
			this.atividade = this.atividadeFacade.pesquisaAtividadePorId(idAtividade);
			this.preparaCadastroAtividade();
		}
	}

	/**
	 * Altera tipo atividade.
	 *
	 * @param resetForm
	 *            the reset form
	 */
	public void alteraTipoAtividade(boolean resetForm) {
		if (this.atividade == null || TipoAtividade.SIMPLES.equals(this.atividade.getTipoAtividade())) {
			this.atividade = new AtividadeSimples();
			this.atividade.setTipoAtividade(TipoAtividade.SIMPLES);
		} else {
			AtividadeQuestionario atividadeQuestionario = new AtividadeQuestionario();
			atividadeQuestionario.setQuestoes(new ArrayList<>());
			atividadeQuestionario.setTipoAtividade(TipoAtividade.QUESTIONARIO);
			this.atividade = atividadeQuestionario;
		}

		this.atividade.setAnexos(new ArrayList<>());
		this.atividade.setProfessor(new Professor());
		this.questaoSelecionada = null;
		this.anexosQuestao = null;
		this.edicaoQuestao = false;

		if (resetForm) {
			super.getRequestContext().reset(":mainForm");
		}
	}

	/**
	 * Prepara pesquisa atividades.
	 */
	public void preparaPesquisaAtividades() {
		this.atividadesDataModel = new OKLazyDataModel<>((firstResult, maxResult) -> {
			if (super.isSearchEvent()) {
				return this.atividadeFacade.pesquisaAtividadesPorArgumentos(this.atividade, firstResult, maxResult);
			} else {
				return null;
			}
		});
	}

	/**
	 * Adiciona nova questao.
	 */
	public void adicionaNovaQuestao() {
		this.questaoSelecionada = new Questao();
		this.questaoSelecionada.setAnexos(new ArrayList<>());
		this.questaoSelecionada.setAlternativas(new ArrayList<>());
		this.questaoSelecionada.setAtividadeQuestionario((AtividadeQuestionario) this.atividade);
		this.preparaAnexosQuestao();
	}

	/**
	 * Adiciona nova alternativa.
	 */
	public void adicionaNovaAlternativa() {
		Alternativa alternativa = new Alternativa();
		alternativa.setQuestao(this.questaoSelecionada);
		this.questaoSelecionada.getAlternativas().add(alternativa);
	}

	/**
	 * Salva nova questao.
	 */
	public void salvaNovaQuestao() {
		this.questaoFacade.validaInformacoesQuestao(this.questaoSelecionada);
		AtividadeQuestionario atividadeQuestionario = (AtividadeQuestionario) this.atividade;

		if (!this.edicaoQuestao) {
			atividadeQuestionario.getQuestoes().add(this.questaoSelecionada);
		} else {
			this.edicaoQuestao = false;
		}

		this.questaoSelecionada.setAnexos(this.anexosQuestao);
		this.questaoSelecionada = null;
		this.preparaAnexosQuestao();
	}

	/**
	 * Prepara anexos questao.
	 */
	public void preparaAnexosQuestao() {
		this.anexosQuestao = new ArrayList<>();
	}

	/**
	 * Prepara edicao questao.
	 */
	public void preparaEdicaoQuestao() {
		this.anexosQuestao = new ArrayList<>(this.questaoSelecionada.getAnexos());
	}

	/**
	 * Salva atividade.
	 *
	 * @return the string
	 */
	public String salvaAtividade() {
		this.atividade.setProfessor((Professor) super.getSecurityContext().getLoggedUser());
		this.atividadeFacade.salva(this.atividade);
		OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getFormattedMessage("msg.sucesso.cadastro.atividade"), OKMessages.Severity.SUCCESS);
		return OKConstants.PRETTY_ACTIVITY_SEARCH;
	}

	/**
	 * Prepara cadastro atividade.
	 *
	 * @return the string
	 */
	public String preparaCadastroAtividade() {
		Faces.getFlash().putNow(OKConstants.ATIVIDADE_ID_KEY, null);
		return OKConstants.PRETTY_ACTIVITY_REGISTER;
	}

	/**
	 * Prepara edicao atividade.
	 *
	 * @param idAtividade
	 *            the id atividade
	 * @return the string
	 */
	public String preparaEdicaoAtividade(Integer idAtividade) {
		Faces.setFlashAttribute(OKConstants.ATIVIDADE_ID_KEY, idAtividade);
		return OKConstants.PRETTY_ACTIVITY_REGISTER;
	}
}
