package br.com.ok.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.model.Alternativa;
import br.com.ok.model.Questao;
import br.com.ok.model.dao.QuestaoDAO;

/**
 * The Class QuestaoBean.
 *
 * @author Matheus
 * @version 1.0 - 04/10/2014
 */
@Stateless
public class QuestaoBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5238766822355843639L;

	@Inject
	private QuestaoDAO questaoDAO;

	/**
	 * Valida informacoes questao.
	 *
	 * @param questao
	 *            the questao
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void validaInformacoesQuestao(Questao questao) {
		if (StringUtils.isBlank(questao.getDescricao())) {
			throw new OKBusinessException("{msg.validacao.descricao.questao}");
		}

		List<Alternativa> alternativas = questao.getAlternativas().stream().filter(alternativa -> StringUtils.isNotBlank(alternativa.getDescricao()))
				.collect(Collectors.toList());

		alternativas.forEach(alternativa -> alternativa.setQuestao(questao));
		questao.setAlternativas(alternativas);

		if (questao.getJustivicativaObrigatoria() == null || !questao.getJustivicativaObrigatoria()) {
			alternativas = alternativas.stream().filter(alt -> alt.getCorreta()).collect(Collectors.toList());

			if (alternativas.isEmpty()) {
				throw new OKBusinessException("{msg.validacao.nenhuma.alternativa.correta.selecionada}");
			}
		}
	}

	/**
	 * Count questoes por disciplina aluno.
	 *
	 * @param idDisciplina
	 *            the id disciplina
	 * @param idAluno
	 *            the id aluno
	 * @return the long
	 */
	public Long countQuestoesPorDisciplinaAluno(Integer idDisciplina, Integer idAluno) {
		return this.questaoDAO.countByDisciplinaAndAluno(idDisciplina, idAluno);
	}

	/**
	 * Count questoes por disciplina.
	 *
	 * @param idDisciplina
	 *            the id disciplina
	 * @return the long
	 */
	public Long countQuestoesPorDisciplina(Integer idDisciplina) {
		return this.questaoDAO.countByDisciplina(idDisciplina);
	}

	/**
	 * Inicializa questoes.
	 *
	 * @param questoes
	 *            the questoes
	 * @return the list
	 */
	public List<Questao> inicializaQuestoes(List<Questao> questoes) {
		List<Questao> questions = new ArrayList<>();

		questoes.forEach(questao -> {
			Questao question = this.questaoDAO.findById(questao.getId());
			this.questaoDAO.initProxy(question.getAlternativas());
			this.questaoDAO.initProxy(question.getAnexos());
			questions.add(question);
		});

		return questions;
	}
}
