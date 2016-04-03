package br.com.ok.business;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.model.Aluno;
import br.com.ok.model.Curso;
import br.com.ok.model.Disciplina;
import br.com.ok.model.Evento;
import br.com.ok.model.Professor;
import br.com.ok.model.Turma;
import br.com.ok.model.Usuario;
import br.com.ok.model.dao.EventoDAO;
import br.com.ok.model.enums.PerfilUsuario;
import br.com.ok.util.constants.OKConstants;

/**
 * The Class EventoBean.
 *
 * @author Adilson
 * @version 1.0 - 31/10/2014
 */
@Stateless
public class EventoBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1616754087880631950L;

	/** The evento dao. */
	@Inject
	private EventoDAO eventoDAO;

	/** The curso bean. */
	@Inject
	private CursoBean cursoBean;

	/** The disciplina bean. */
	@Inject
	private DisciplinaBean disciplinaBean;

	/** The turma bean. */
	@Inject
	private TurmaBean turmaBean;

	/**
	 * Lista eventos cadastrados pelo usuario.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Evento> listaEventosCadastradosPeloUsuario(Integer usuario) {
		List<Evento> eventos = this.eventoDAO.listaEventosCadastradosPeloUsuario(usuario);
		this.preparaInformacoesComplementaresEvento(eventos);
		return eventos;
	}

	/**
	 * Prepara informacoes complementares evento.
	 *
	 * @param eventos
	 *            the eventos
	 */
	private void preparaInformacoesComplementaresEvento(List<Evento> eventos) {
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime dataInicial = null;
		ZonedDateTime dataFinal = null;

		for (Evento evento : eventos) {
			dataInicial = ZonedDateTime.ofInstant(evento.getDataInicial().toInstant(), zoneId);
			evento.setStart(LocalDateTime.from(dataInicial).toString());

			dataFinal = ZonedDateTime.ofInstant(evento.getDataFinal().toInstant(), zoneId);
			if (!dataInicial.toLocalDate().equals(dataFinal.toLocalDate())) {
				dataFinal = dataFinal.plus(OKConstants.VALOR_UM, ChronoUnit.DAYS);
			}
			evento.setEnd(LocalDateTime.from(dataFinal).toString());

			evento.setBackgroundColor(evento.getCorEvento().getBackground().toString());
			evento.setBorderColor(evento.getCorEvento().getBackground().toString());
		}
	}

	/**
	 * Find evento by id.
	 *
	 * @param idEvento
	 *            the id evento
	 * @return the evento
	 */
	public Evento findEventoById(Integer idEvento) {
		Evento evento = this.eventoDAO.findEventoById(idEvento);
		evento.setDataInicialOriginal(evento.getDataInicial());
		this.eventoDAO.initProxy(evento.getCursos());
		this.eventoDAO.initProxy(evento.getTurmas());
		this.eventoDAO.initProxy(evento.getDisciplinas());
		return evento;
	}

	/**
	 * Save event.
	 *
	 * @param evento
	 *            the evento
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	public void saveEvent(Evento evento) throws OKBusinessException {
		if (evento.getId() == null) {
			this.eventoDAO.save(evento);
		} else {
			this.eventoDAO.update(evento);
		}
	}

	/**
	 * Verifica data final.
	 *
	 * @param evento
	 *            the evento
	 * @return true, if successful
	 */
	public boolean verificaDataFinal(Evento evento) {
		if (evento.getDataFinal().before(evento.getDataInicial())) {
			return false;
		}
		return true;
	}

	/**
	 * Valida datas evento.
	 *
	 * @param evento
	 *            the evento
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	public void validaDatasEvento(Evento evento) throws OKBusinessException {
		Date dataInicio = evento.getDataInicial();
		LocalDate dataInformada = LocalDateTime.ofInstant(dataInicio.toInstant(), ZoneId.systemDefault()).toLocalDate();

		if (!dataInicio.equals(evento.getDataInicialOriginal()) && dataInformada.isBefore(LocalDate.now())) {
			throw new OKBusinessException("{msg.validacao.data.inicial.evento}", ":mainForm:eventoDataInicial");
		}

		if (dataInicio.after(evento.getDataFinal())) {
			throw new OKBusinessException("{msg.validacao.data.final.evento}", ":mainForm:eventoDataFinal");
		}
	}

	/**
	 * Valida alvos evento.
	 *
	 * @param evento
	 *            the evento
	 */
	public void validaAlvosEvento(Evento evento) {
		if (evento.getCursos().isEmpty() && evento.getDisciplinas().isEmpty() && evento.getTurmas().isEmpty()) {
			throw new OKBusinessException("{msg.validacao.alvos.disciplina}");
		}
	}

	/**
	 * Filtra cursos nao permitidos usuario.
	 *
	 * @param evento
	 *            the evento
	 * @param cursosSelecionados
	 *            the cursos selecionados
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void filtraCursosNaoPermitidosUsuario(Evento evento, List<Curso> cursosSelecionados) throws OKBusinessException {
		List<Curso> cursosNaoPermitidos = new ArrayList<>();
		Set<Curso> cursosPermitidos = new HashSet<>();

		cursosPermitidos.addAll(evento.getCursos());

		if (PerfilUsuario.ADMIN.equals(evento.getUsuario().getPerfil().getDescricao())) {
			cursosPermitidos.addAll(cursosSelecionados);
		} else {
			cursosSelecionados.stream().filter(curso -> this.cursoBean.countByIdAndCoordenador(curso.getId(), evento.getUsuario().getId()) > OKConstants.VALOR_ZERO)
					.forEach(curso -> cursosPermitidos.add(curso));
		}

		evento.setCursos(new ArrayList<>(cursosPermitidos));
		cursosNaoPermitidos.addAll(cursosSelecionados.stream().filter(curso -> !cursosPermitidos.contains(curso)).collect(Collectors.toList()));

		if (!cursosNaoPermitidos.isEmpty()) {
			throw new OKBusinessException("{msg.validacao.cursos.nao.permitidos}");
		}
	}

	/**
	 * Filtra disciplinas nao permitidas usuario.
	 *
	 * @param evento
	 *            the evento
	 * @param disciplinasSelecionadas
	 *            the disciplinas selecionadas
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void filtraDisciplinasNaoPermitidasUsuario(Evento evento, List<Disciplina> disciplinasSelecionadas) throws OKBusinessException {
		Usuario usuario = evento.getUsuario();

		List<Disciplina> disciplinasNaoPermitidas = new ArrayList<>();
		Set<Disciplina> disciplinasPermitidas = new HashSet<>();

		disciplinasPermitidas.addAll(evento.getDisciplinas());

		if (PerfilUsuario.ADMIN.equals(evento.getUsuario().getPerfil().getDescricao())) {
			disciplinasPermitidas.addAll(disciplinasSelecionadas);
		} else {
			disciplinasPermitidas.addAll(disciplinasSelecionadas
					.stream()
					.filter(disc -> this.disciplinaBean.countByIdAndProfessor(disc.getId(), usuario.getId()) > OKConstants.VALOR_ZERO
							|| this.disciplinaBean.countByIdAndCoordenadorCurso(disc.getId(), usuario.getId()) > OKConstants.VALOR_ZERO).collect(Collectors.toList()));
		}

		evento.setDisciplinas(new ArrayList<>(disciplinasPermitidas));
		disciplinasNaoPermitidas.addAll(disciplinasSelecionadas.stream().filter(disc -> !disciplinasPermitidas.contains(disc)).collect(Collectors.toList()));

		if (!disciplinasNaoPermitidas.isEmpty()) {
			throw new OKBusinessException("{msg.validacao.disciplinas.nao.permitidas}");
		}
	}

	/**
	 * Filtra turmas nao permitidas usuario.
	 *
	 * @param evento
	 *            the evento
	 * @param turmasSelecionadas
	 *            the turmas selecionadas
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void filtraTurmasNaoPermitidasUsuario(Evento evento, List<Turma> turmasSelecionadas) throws OKBusinessException {
		List<Turma> turmasNaoPermitidas = new ArrayList<>();
		Set<Turma> turmasPermitidas = new HashSet<>();

		turmasPermitidas.addAll(evento.getTurmas());

		if (PerfilUsuario.ADMIN.equals(evento.getUsuario().getPerfil().getDescricao())) {
			turmasPermitidas.addAll(turmasSelecionadas);
		} else {
			turmasPermitidas.addAll(turmasSelecionadas.stream()
					.filter(turma -> this.turmaBean.countByIdAndCoordenadorCurso(turma.getId(), evento.getUsuario().getId()) > OKConstants.VALOR_ZERO)
					.collect(Collectors.toList()));
		}

		evento.setTurmas(new ArrayList<>(turmasPermitidas));
		turmasNaoPermitidas.addAll(turmasSelecionadas.stream().filter(turma -> !turmasPermitidas.contains(turma)).collect(Collectors.toList()));

		if (!turmasNaoPermitidas.isEmpty()) {
			throw new OKBusinessException("{msg.validacao.turmas.nao.permitidas}");
		}
	}

	/**
	 * Removes the evento.
	 *
	 * @param evento
	 *            the evento
	 */
	public void removeEvento(Evento evento) {
		this.eventoDAO.remove(this.eventoDAO.update(evento));
	}

	/**
	 * Lista eventos usuario.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Evento> listaEventosUsuario(Usuario usuario) {
		List<Evento> eventos = null;

		switch (usuario.getPerfil().getDescricao()) {
			case ADMIN:
				eventos = this.pesquisaEventos();
				break;

			case COORDENADOR:
				eventos = this.pesquisaEventosCoordenador((Professor) usuario);
				break;

			case PROFESSOR:
				eventos = this.pesquisaEventosProfessor((Professor) usuario);
				break;

			case ALUNO:
				eventos = this.pesquisaEventosAluno((Aluno) usuario);
				break;
		}

		this.preparaInformacoesComplementaresEvento(eventos);
		return eventos;
	}

	/**
	 * Pesquisa eventos.
	 *
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Evento> pesquisaEventos() {
		return this.eventoDAO.findAll();
	}

	/**
	 * Pesquisa eventos professor.
	 *
	 * @param professor
	 *            the professor
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Evento> pesquisaEventosProfessor(Professor professor) {
		return this.eventoDAO.findEventosByProfessor(professor);
	}

	/**
	 * Pesquisa eventos coordenador.
	 *
	 * @param professor
	 *            the professor
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Evento> pesquisaEventosCoordenador(Professor professor) {
		Set<Evento> eventos = new HashSet<>();
		eventos.addAll(this.eventoDAO.findEventosByProfessor(professor));
		eventos.addAll(this.eventoDAO.findEventosCursosByCoordenador(professor.getId()));
		eventos.addAll(this.eventoDAO.findEventosTurmasByCoordenador(professor.getId()));
		return new ArrayList<>(eventos);
	}

	/**
	 * Pesquisa eventos aluno.
	 *
	 * @param aluno
	 *            the aluno
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Evento> pesquisaEventosAluno(Aluno aluno) {
		Set<Evento> eventos = new HashSet<>();
		eventos.addAll(this.eventoDAO.findEventosCursosByAluno(aluno));
		eventos.addAll(this.eventoDAO.findEventosDisciplinasByAluno(aluno));
		eventos.addAll(this.eventoDAO.findEventosDisciplinasExtrasByAluno(aluno));
		eventos.addAll(this.eventoDAO.findEventosTurmasByAluno(aluno));
		return new ArrayList<>(eventos);
	}

}
