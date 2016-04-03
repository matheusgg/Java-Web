package br.com.ok.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.model.Alternativa;
import br.com.ok.model.Atividade;
import br.com.ok.model.AtividadeQuestionario;
import br.com.ok.model.Disciplina;
import br.com.ok.model.dao.AtividadeDAO;
import br.com.ok.model.enums.TipoAtividade;
import br.com.ok.util.pagination.OKPaginatedList;

/**
 * The Class AtividadeBean.
 *
 * @author Matheus
 * @version 1.0 - 09/10/2014
 */
@Stateless
public class AtividadeBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -302060146881857027L;

	/** The atividade dao. */
	@Inject
	private AtividadeDAO atividadeDAO;

	@Inject
	private QuestaoBean questaoBean;

	/**
	 * Salva.
	 *
	 * @param atividade
	 *            the atividade
	 * @return the atividade
	 */
	public Atividade salva(Atividade atividade) {
		if (atividade.getId() == null) {
			atividade.setDataCadastro(new Date());
			this.atividadeDAO.save(atividade);
		} else {
			atividade = this.atividadeDAO.update(atividade);
		}
		return atividade;
	}

	/**
	 * Prepara quantidade questoes.
	 *
	 * @param atividadeQuestionario
	 *            the atividade questionario
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void preparaQuantidadeQuestoes(AtividadeQuestionario atividadeQuestionario) {
		atividadeQuestionario.setQuantidadeQuestoes(atividadeQuestionario.getQuestoes().size());
	}

	/**
	 * Valida quantidade questoes.
	 *
	 * @param atividadeQuestionario
	 *            the atividade questionario
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void validaQuantidadeQuestoes(AtividadeQuestionario atividadeQuestionario) throws OKBusinessException {
		if (atividadeQuestionario.getQuestoes().isEmpty()) {
			throw new OKBusinessException("{msg.validacao.qtd.minima.questoes}");
		}
	}

	/**
	 * Valida data prazo final questionario.
	 *
	 * @param atividadeQuestionario
	 *            the atividade questionario
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void validaDataPrazoFinalQuestionario(AtividadeQuestionario atividadeQuestionario) throws OKBusinessException {
		Date prazoFinal = atividadeQuestionario.getDataPrazoFinal();

		if (atividadeQuestionario.getId() != null) {
			Date prazoFinalOriginal = this.atividadeDAO.findDataPrazoFinalById(atividadeQuestionario.getId());

			if (!prazoFinal.equals(prazoFinalOriginal) && !this.verificaDataAtualMenorPrazoFinal(prazoFinal)) {
				throw new OKBusinessException("{msg.validacao.prazo.questionario}", ":mainForm:prazo");
			}

		} else if (!this.verificaDataAtualMenorPrazoFinal(prazoFinal)) {
			throw new OKBusinessException("{msg.validacao.prazo.questionario}", ":mainForm:prazo");
		}
	}

	/**
	 * Verifica data atual menor prazo final.
	 *
	 * @param prazoFinal
	 *            the prazo final
	 * @return true, if successful
	 */
	private boolean verificaDataAtualMenorPrazoFinal(Date prazoFinal) {
		return new Date().before(prazoFinal);
	}

	/**
	 * Prepara alternativas questoes atividade.
	 *
	 * @param atividadeQuestionario
	 *            the atividade questionario
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void preparaAlternativasQuestoesAtividade(AtividadeQuestionario atividadeQuestionario) {
		atividadeQuestionario.getQuestoes().forEach(
				questao -> {
					List<Alternativa> alternativas = new ArrayList<>();
					alternativas.addAll(questao.getAlternativas().stream()
							.filter(alternativa -> alternativa.getId() != null || StringUtils.isNotBlank(alternativa.getDescricao())).collect(Collectors.toList()));
					questao.setAlternativas(alternativas);
				});
	}

	/**
	 * Pesquisa atividades por argumentos.
	 *
	 * @param args
	 *            the args
	 * @param firstResult
	 *            the first result
	 * @param maxResult
	 *            the max result
	 * @return the OK paginated list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public OKPaginatedList<Atividade> pesquisaAtividadesPorArgumentos(Atividade args, int firstResult, int maxResult) {
		return this.atividadeDAO.findAtividadesByArgs(args, firstResult, maxResult);
	}

	/**
	 * Pesquisa atividade por id.
	 *
	 * @param id
	 *            the id
	 * @return the atividade
	 */
	public Atividade pesquisaAtividadePorId(Integer id) {
		Atividade atividade = this.atividadeDAO.findById(id);

		Disciplina disciplina = new Disciplina();
		disciplina.setModulos(Arrays.asList(atividade.getModulo()));
		atividade.setDisciplina(disciplina);

		this.atividadeDAO.initProxy(atividade.getAnexos());

		if (atividade instanceof AtividadeQuestionario) {
			AtividadeQuestionario atividadeQuestionario = (AtividadeQuestionario) atividade;
			this.questaoBean.inicializaQuestoes(atividadeQuestionario.getQuestoes());
			atividade.setTipoAtividade(TipoAtividade.QUESTIONARIO);

		} else {
			atividade.setTipoAtividade(TipoAtividade.SIMPLES);
		}

		return atividade;
	}

	/**
	 * Pesquisa atividades por modulo.
	 *
	 * @param idModulo
	 *            the id modulo
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Atividade> pesquisaAtividadesPorModulo(Integer idModulo) {
		return this.atividadeDAO.findByModulo(idModulo);
	}

	/**
	 * Pesquisa questionarios por modulo professor.
	 *
	 * @param idModulo
	 *            the id modulo
	 * @param idProfessor
	 *            the id professor
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Atividade> pesquisaQuestionariosPorModuloProfessor(Integer idModulo, Integer idProfessor) {
		return this.atividadeDAO.findQuestionariosByModuloAndProfessor(idModulo, idProfessor);
	}

	/**
	 * Count questionarios por modulo.
	 *
	 * @param idModulo
	 *            the id modulo
	 * @return the long
	 */
	public Long countQuestionariosPorModulo(Integer idModulo) {
		return this.atividadeDAO.countQuestionariosByModulo(idModulo);
	}

	/**
	 * Count questionarios por modulo professor.
	 *
	 * @param idModulo
	 *            the id modulo
	 * @param idProfessor
	 *            the id professor
	 * @return the long
	 */
	public Long countQuestionariosPorModuloProfessor(Integer idModulo, Integer idProfessor) {
		return this.atividadeDAO.countQuestionariosByModuloAndProfessor(idModulo, idProfessor);
	}
}
