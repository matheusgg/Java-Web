package br.com.ok.facade;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.ok.business.EventoBean;
import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.model.Curso;
import br.com.ok.model.Disciplina;
import br.com.ok.model.Evento;
import br.com.ok.model.Turma;
import br.com.ok.model.Usuario;

/**
 * The Class EventoFacade.
 *
 * @author Adilson
 * @version 1.0 - 31/10/2014
 */
public class EventoFacade implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1255840384848415239L;

	/** The evento bean. */
	@Inject
	private EventoBean eventoBean;

	/**
	 * Lista eventos cadastrados pelo usuario.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the list
	 */
	public List<Evento> listaEventosCadastradosPeloUsuario(Integer usuario) {
		return this.eventoBean.listaEventosCadastradosPeloUsuario(usuario);
	}

	/**
	 * Lista eventos usuario.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the list
	 */
	public List<Evento> listaEventosUsuario(Usuario usuario) {
		return this.eventoBean.listaEventosUsuario(usuario);
	}

	/**
	 * Find evento by id.
	 *
	 * @param idEvento
	 *            the id evento
	 * @return the evento
	 */
	public Evento findEventoById(Integer idEvento) {
		return this.eventoBean.findEventoById(idEvento);
	}

	/**
	 * Save event.
	 *
	 * @param evento
	 *            the evento
	 */
	@Transactional
	public void saveEvent(Evento evento) {
		this.eventoBean.validaDatasEvento(evento);
		this.eventoBean.validaAlvosEvento(evento);
		this.eventoBean.saveEvent(evento);
	}

	/**
	 * Removes the evento.
	 *
	 * @param evento
	 *            the evento
	 */
	@Transactional
	public void removeEvento(Evento evento) {
		this.eventoBean.removeEvento(evento);
	}

	/**
	 * Verifica data final.
	 *
	 * @param evento
	 *            the evento
	 * @return the boolean
	 */
	public Boolean verificaDataFinal(Evento evento) {
		return this.eventoBean.verificaDataFinal(evento);

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
	public void filtraCursosNaoPermitidosUsuario(Evento evento, List<Curso> cursosSelecionados) throws OKBusinessException {
		this.eventoBean.filtraCursosNaoPermitidosUsuario(evento, cursosSelecionados);
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
	public void filtraDisciplinasNaoPermitidasUsuario(Evento evento, List<Disciplina> disciplinasSelecionadas) throws OKBusinessException {
		this.eventoBean.filtraDisciplinasNaoPermitidasUsuario(evento, disciplinasSelecionadas);
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
	public void filtraTurmasNaoPermitidasUsuario(Evento evento, List<Turma> turmasSelecionadas) throws OKBusinessException {
		this.eventoBean.filtraTurmasNaoPermitidasUsuario(evento, turmasSelecionadas);
	}

}
