package br.com.ok.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.RandomStringUtils;
import org.omnifaces.util.Faces;

import br.com.ok.facade.TurmaFacade;
import br.com.ok.model.Turma;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.messages.OKMessages;
import br.com.ok.util.pagination.OKLazyDataModel;
import br.com.ok.view.base.OKBaseMB;
import br.com.ok.view.base.annotation.OnKnowledgeMB;

/**
 * The Class TurmaMB.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@OnKnowledgeMB
public class TurmaMB extends OKBaseMB {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2700886573595059400L;

	/** The turma facade. */
	@Inject
	private TurmaFacade turmaFacade;

	/** The turma. */
	@Getter
	private Turma turma;

	/** The turmas data model. */
	@Getter
	private OKLazyDataModel<Turma> turmasDataModel;

	/** The turmas selecionadas. */
	@Getter
	@Setter
	private List<Turma> turmasSelecionadas;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.turma = new Turma();
	}

	/**
	 * Prepara pesquisa turmas.
	 */
	public void preparaPesquisaTurmas() {
		this.turmasDataModel = new OKLazyDataModel<>((firstResult, maxResult) -> {
			if (super.isSearchEvent()) {
				return this.turmaFacade.pesquisaTurmasPorArgumentos(this.turma, firstResult, maxResult);
			} else {
				return null;
			}
		});
	}

	/**
	 * Prepara cadastro turma.
	 */
	public void preparaCadastroTurma() {
		Integer idTurma = Faces.getFlashAttribute(OKConstants.TURMA_ID_KEY);

		if (idTurma != null) {
			this.turma = this.turmaFacade.pesquisaTurmaPorId(idTurma);
			Faces.getFlash().putNow(OKConstants.TURMA_ID_KEY, null);

		} else {
			this.turma.setCodigo(RandomStringUtils.randomAlphanumeric(OKConstants.VALOR_DEZ));
			this.turma.setCodigosSeguranca(new ArrayList<>());
		}
	}

	/**
	 * Prepara edicao turma.
	 *
	 * @param idTurma
	 *            the id turma
	 * @return the string
	 */
	public String preparaEdicaoTurma(Integer idTurma) {
		Faces.setFlashAttribute(OKConstants.TURMA_ID_KEY, idTurma);
		return OKConstants.PRETTY_CLASS_REGISTER;
	}

	/**
	 * Salva turma.
	 *
	 * @return the string
	 */
	public String salvaTurma() {
		this.turmaFacade.salvaTurma(this.turma);
		OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getFormattedMessage("msg.sucesso.cadastro.turma", this.turma.getCodigo()),
				OKMessages.Severity.SUCCESS);
		return OKConstants.PRETTY_CLASSES_SEARCH;
	}

	/**
	 * Seleciona turmas.
	 *
	 * @return the string
	 */
	public String selecionaTurmas() {
		Faces.setFlashAttribute(OKConstants.TURMAS_SELECIONADAS_KEY, this.turmasSelecionadas);
		return super.returnToCallerPage();
	}
}
