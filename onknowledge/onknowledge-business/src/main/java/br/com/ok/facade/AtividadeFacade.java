package br.com.ok.facade;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.ok.business.AnexoBean;
import br.com.ok.business.AtividadeBean;
import br.com.ok.facade.base.OKGenericFacade;
import br.com.ok.model.Atividade;
import br.com.ok.model.AtividadeQuestionario;
import br.com.ok.util.pagination.OKPaginatedList;

/**
 * The Class AtividadeFacade.
 *
 * @author Matheus
 * @version 1.0 - 09/10/2014
 */
public class AtividadeFacade extends OKGenericFacade {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1636969119531509870L;

	/** The atividade bean. */
	@Inject
	private AtividadeBean atividadeBean;

	/** The anexo bean. */
	@Inject
	private AnexoBean anexoBean;

	/**
	 * Salva.
	 *
	 * @param atividade
	 *            the atividade
	 */
	@Transactional
	public void salva(Atividade atividade) {
		if (atividade instanceof AtividadeQuestionario) {
			AtividadeQuestionario atividadeQuestionario = (AtividadeQuestionario) atividade;
			this.atividadeBean.validaDataPrazoFinalQuestionario(atividadeQuestionario);
			this.atividadeBean.validaQuantidadeQuestoes(atividadeQuestionario);
			this.atividadeBean.preparaQuantidadeQuestoes(atividadeQuestionario);
			this.atividadeBean.preparaAlternativasQuestoesAtividade(atividadeQuestionario);
			atividadeQuestionario.getQuestoes().forEach(questao -> this.anexoBean.salvaArquivosAnexosEmDisco(questao.getAnexos()));
		}

		this.anexoBean.salvaArquivosAnexosEmDisco(atividade.getAnexos());
		this.atividadeBean.salva(atividade);
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
	public OKPaginatedList<Atividade> pesquisaAtividadesPorArgumentos(Atividade args, int firstResult, int maxResult) {
		return this.atividadeBean.pesquisaAtividadesPorArgumentos(args, firstResult, maxResult);
	}

	/**
	 * Pesquisa atividade por id.
	 *
	 * @param id
	 *            the id
	 * @return the atividade
	 */
	public Atividade pesquisaAtividadePorId(Integer id) {
		return this.atividadeBean.pesquisaAtividadePorId(id);
	}

	/**
	 * Pesquisa atividades por modulo.
	 *
	 * @param idModulo
	 *            the id modulo
	 * @return the list
	 */
	public List<Atividade> pesquisaAtividadesPorModulo(Integer idModulo) {
		return this.atividadeBean.pesquisaAtividadesPorModulo(idModulo);
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
	public List<Atividade> pesquisaQuestionariosPorModuloProfessor(Integer idModulo, Integer idProfessor) {
		return this.atividadeBean.pesquisaQuestionariosPorModuloProfessor(idModulo, idProfessor);
	}

}
