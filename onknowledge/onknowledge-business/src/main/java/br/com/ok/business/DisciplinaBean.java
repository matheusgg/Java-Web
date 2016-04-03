/*
 * 
 */
package br.com.ok.business;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.model.Aluno;
import br.com.ok.model.Disciplina;
import br.com.ok.model.Modulo;
import br.com.ok.model.Usuario;
import br.com.ok.model.dao.DisciplinaDAO;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.messages.OKMessages;
import br.com.ok.util.messages.OKMessages.Severity;
import br.com.ok.util.pagination.OKPaginatedList;

/**
 * The Class DisciplinaBean.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@Stateless
public class DisciplinaBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2229850960968268604L;

	/** The disciplina dao. */
	@Inject
	private DisciplinaDAO disciplinaDAO;

	/** The modulo bean. */
	@Inject
	private ModuloBean moduloBean;

	/** The questao bean. */
	@Inject
	private QuestaoBean questaoBean;

	/** The atividade bean. */
	@Inject
	private AtividadeBean atividadeBean;

	/**
	 * Lista nomes disciplinas.
	 *
	 * @param nome
	 *            the nome
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Disciplina> listaNomesDisciplinas(String nome) {
		return this.disciplinaDAO.listByNome(nome);
	}

	/**
	 * Pesquisa disciplinas por argumentos.
	 *
	 * @param args
	 *            the args
	 * @param firstResult
	 *            the first result
	 * @param maxResult
	 *            the max result
	 * @param extraCurricular
	 *            the extra curricular
	 * @return the OK paginated list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public OKPaginatedList<Disciplina> pesquisaDisciplinasPorArgumentos(Disciplina args, int firstResult, int maxResult, boolean extraCurricular) {
		return this.disciplinaDAO.findDisciplinasByArgs(args, firstResult, maxResult, extraCurricular);
	}

	/**
	 * Salva disciplina.
	 *
	 * @param disciplina
	 *            the disciplina
	 * @return the disciplina
	 */
	public Disciplina salvaDisciplina(Disciplina disciplina) {
		this.preparaModulosDisciplina(disciplina);
		if (disciplina.getId() == null) {
			this.disciplinaDAO.save(disciplina);
		} else {
			disciplina = this.disciplinaDAO.update(disciplina);
		}
		return disciplina;
	}

	/**
	 * Prepara modulos disciplina.
	 *
	 * @param disciplina
	 *            the disciplina
	 */
	private void preparaModulosDisciplina(Disciplina disciplina) {
		List<Modulo> modulos = disciplina.getModulos();
		if (modulos != null && !modulos.isEmpty()) {
			modulos.stream().filter(modulo -> modulo.getId() == null).forEach(modulo -> this.moduloBean.salva(modulo));
		}
	}

	/**
	 * Valida datas disciplinas.
	 *
	 * @param disciplina
	 *            the disciplina
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void validaDatasDisciplinas(Disciplina disciplina) throws OKBusinessException {
		Date dataInicio = disciplina.getDataInicio();
		LocalDate dataInformada = LocalDateTime.ofInstant(dataInicio.toInstant(), ZoneId.systemDefault()).toLocalDate();

		if (!dataInicio.equals(disciplina.getDataInicioOriginal()) && dataInformada.isBefore(LocalDate.now())) {
			throw new OKBusinessException("{msg.validacao.data.inicial.invalida}", ":mainForm:dataInicio");
		}

		if (!disciplina.getDataEncerramento().after(dataInicio)) {
			throw new OKBusinessException("{msg.validacao.data.encerramento.invalida}", ":mainForm:dataTermino");
		}
	}

	/**
	 * Valida quantidade minima professores.
	 *
	 * @param disciplina
	 *            the disciplina
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void validaQuantidadeMinimaProfessores(Disciplina disciplina) throws OKBusinessException {
		if (disciplina.getProfessores() == null || disciplina.getProfessores().isEmpty()) {
			throw new OKBusinessException("{msg.validacao.qtd.minima.professores}");
		}
	}

	/**
	 * Pesquisa disciplina por id.
	 *
	 * @param id
	 *            the id
	 * @return the disciplina
	 */
	public Disciplina pesquisaDisciplinaPorId(Integer id) {
		Disciplina disciplina = this.disciplinaDAO.findById(id);
		this.disciplinaDAO.initProxy(disciplina.getModulos());
		return disciplina;
	}

	/**
	 * Pesquisa disciplinas carregando modulos.
	 *
	 * @param idDisciplina
	 *            the id disciplina
	 * @return the list
	 */
	public List<Disciplina> pesquisaDisciplinasCarregandoModulos(Integer idDisciplina) {
		List<Disciplina> disciplinas = null;
		if (idDisciplina != null) {
			disciplinas = this.disciplinaDAO.findAllDisciplinasExcludingId(idDisciplina);
		} else {
			disciplinas = this.disciplinaDAO.findAll();
		}
		disciplinas.forEach(disciplina -> this.disciplinaDAO.initProxy(disciplina.getModulos()));
		return disciplinas;
	}

	/**
	 * Pesquisa disciplinas por professor.
	 *
	 * @param idProfessor
	 *            the id professor
	 * @return the list
	 */
	public List<Disciplina> pesquisaDisciplinasPorProfessor(Integer idProfessor) {
		List<Disciplina> disciplinas = this.disciplinaDAO.findByIdProfessor(idProfessor);
		disciplinas.forEach(disciplina -> this.disciplinaDAO.initProxy(disciplina.getModulos()));
		return disciplinas;
	}

	/**
	 * Pesquisa disciplinas por aluno.
	 *
	 * @param idAluno
	 *            the id aluno
	 * @return the list
	 */
	public List<Disciplina> pesquisaDisciplinasPorAluno(Integer idAluno) {
		List<Disciplina> disciplinas = this.disciplinaDAO.findByIdAluno(idAluno);
		disciplinas.forEach(disciplina -> this.disciplinaDAO.initProxy(disciplina.getModulos()));
		return disciplinas;
	}

	/**
	 * Filtra disciplinas encerramento valido.
	 *
	 * @param disciplinas
	 *            the disciplinas
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Disciplina> filtraDisciplinasEncerramentoValido(List<Disciplina> disciplinas) {
		List<Disciplina> disciplinasValidas = new ArrayList<>();
		List<Disciplina> disciplinasInvalidas = new ArrayList<>();

		LocalDate now = ZonedDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).toLocalDate();

		disciplinas.forEach(disciplina -> {
			LocalDate dataEncerramento = LocalDate.parse(disciplina.getDataEncerramento().toString(), DateTimeFormatter.ofPattern(OKConstants.PATTERN_EN_US_DATE));

			if (!dataEncerramento.isBefore(now)) {
				disciplinasValidas.add(disciplina);
			} else {
				disciplinasInvalidas.add(disciplina);
			}
		});

		if (!disciplinasInvalidas.isEmpty()) {
			OKMessages.addFacesMessage("{msg.validacao.disciplinas.encerramento.invalido}", Severity.WARNING, null);
		}

		return disciplinasValidas;
	}

	/**
	 * Pesquisa disciplinas associadas aluno.
	 *
	 * @param idAluno
	 *            the id aluno
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<String> pesquisaDisciplinasAssociadasAluno(Integer idAluno) {
		return this.disciplinaDAO.findCodigosByAluno(idAluno);
	}

	/**
	 * Pesquisa disciplinas usuario.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the list
	 */
	public List<Disciplina> pesquisaDisciplinasUsuario(Usuario usuario) {
		List<Disciplina> disciplinas = new ArrayList<>();

		if (usuario instanceof Aluno) {
			Aluno aluno = (Aluno) usuario;
			Integer turma = aluno.getTurma().getId();
			disciplinas.addAll(this.disciplinaDAO.findByTurma(turma));
			disciplinas.addAll(this.disciplinaDAO.findByIdAluno(aluno.getId()));

			disciplinas.forEach(disciplina -> {
				Long qtdQuestoes = this.questaoBean.countQuestoesPorDisciplina(disciplina.getId());
				double porcentagemRespondida = OKConstants.VALOR_ZERO;

				if (qtdQuestoes > OKConstants.VALOR_ZERO) {
					Long qtdQuestoesRespondidadas = this.questaoBean.countQuestoesPorDisciplinaAluno(disciplina.getId(), aluno.getId());
					porcentagemRespondida = (qtdQuestoesRespondidadas * OKConstants.VALOR_CEM) / qtdQuestoes;
				}

				disciplina.setPorcentagemQuestoesRespondidas(porcentagemRespondida);
			});

		} else {
			disciplinas = this.pesquisaDisciplinasPorProfessor(usuario.getId());
		}

		disciplinas.forEach(disciplina -> {
			this.disciplinaDAO.initProxy(disciplina.getModulos());
			disciplina.getModulos().forEach(modulo -> {
				modulo.setQuantidadeQuestionarios(this.atividadeBean.countQuestionariosPorModuloProfessor(modulo.getId(), usuario.getId()));
			});
		});

		disciplinas.sort(Comparator.comparing(Disciplina::getNome));

		return disciplinas;
	}

	/**
	 * Count by id and professor.
	 *
	 * @param idDisciplina
	 *            the id disciplina
	 * @param idProfessor
	 *            the id professor
	 * @return the long
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Long countByIdAndProfessor(Integer idDisciplina, Integer idProfessor) {
		return this.disciplinaDAO.countByIdAndProfessor(idDisciplina, idProfessor);
	}

	/**
	 * Count by id and coordenador curso.
	 *
	 * @param idDisciplina
	 *            the id disciplina
	 * @param idCoordenador
	 *            the id coordenador
	 * @return the long
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Long countByIdAndCoordenadorCurso(Integer idDisciplina, Integer idCoordenador) {
		return this.disciplinaDAO.countByIdAndCoordenadorCurso(idDisciplina, idCoordenador);
	}
}
