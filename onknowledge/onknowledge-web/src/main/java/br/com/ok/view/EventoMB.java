package br.com.ok.view;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

import org.omnifaces.util.Faces;

import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.facade.EventoFacade;
import br.com.ok.model.Curso;
import br.com.ok.model.Disciplina;
import br.com.ok.model.Evento;
import br.com.ok.model.Turma;
import br.com.ok.model.enums.CorEvento;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.messages.OKMessages;
import br.com.ok.view.base.OKBaseMB;
import br.com.ok.view.base.annotation.OnKnowledgeMB;

/**
 * The Class EventoMB.
 *
 * @author Adilson
 * @version 1.0 - 31/10/2014
 */
@OnKnowledgeMB
public class EventoMB extends OKBaseMB {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2779779856554885846L;

	/** The evento facade. */
	@Inject
	private EventoFacade eventoFacade;

	/** The evento. */
	@Getter
	@Setter
	private Evento evento;

	/**
	 * Prepara cadastro evento.
	 */
	public void preparaCadastroEvento() {
		if (super.getCallerObject() != null) {
			this.evento = (Evento) super.getCallerObject();
			this.recuperaCursosSelecionados();
			this.preparaDisciplinasSelecionadas();
			this.preparaTurmasSelecionadas();
		} else {
			this.preparaNovoEvento();
		}
	}

	/**
	 * Prepara novo evento.
	 */
	public void preparaNovoEvento() {
		this.evento = new Evento();
		this.evento.setUsuario(super.getSecurityContext().getLoggedUser());
		this.evento.setCorEvento(CorEvento.DARKEN);
		this.evento.setCursos(new ArrayList<>());
		this.evento.setDisciplinas(new ArrayList<>());
		this.evento.setTurmas(new ArrayList<>());
	}

	/**
	 * Save event.
	 *
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	public void saveEvent() throws OKBusinessException {
		this.eventoFacade.saveEvent(this.evento);
		OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getFormattedMessage("msg.sucesso.evento.cadastrado"), OKMessages.Severity.SUCCESS);
		this.preparaNovoEvento();
		super.getRequestContext().execute("updateCalendar()");
	}

	/**
	 * Removes the event.
	 */
	public void removeEvent() {
		this.eventoFacade.removeEvento(this.evento);
		OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getFormattedMessage("msg.sucesso.evento.excluido"), OKMessages.Severity.SUCCESS);
		this.preparaNovoEvento();
		super.getRequestContext().execute("updateCalendar()");
	}

	/**
	 * Recupera cursos selecionados.
	 */
	private void recuperaCursosSelecionados() {
		List<Curso> cursosSelecionados = Faces.getFlashAttribute(OKConstants.CURSOS_SELECIONADOS_KEY);
		Faces.getFlash().putNow(OKConstants.CURSOS_SELECIONADOS_KEY, null);

		if (cursosSelecionados != null) {
			this.evento.setDisciplinas(new ArrayList<>());
			this.evento.setTurmas(new ArrayList<>());
			this.eventoFacade.filtraCursosNaoPermitidosUsuario(this.evento, cursosSelecionados);
		}
	}

	/**
	 * Prepara disciplinas selecionadas.
	 */
	private void preparaDisciplinasSelecionadas() {
		List<Disciplina> disciplinasSelecionadas = Faces.getFlashAttribute(OKConstants.DISCIPLINAS_SELECIONADAS_KEY);
		Faces.getFlash().putNow(OKConstants.DISCIPLINAS_SELECIONADAS_KEY, null);

		if (disciplinasSelecionadas != null) {
			this.evento.setCursos(new ArrayList<>());
			this.evento.setTurmas(new ArrayList<>());
			this.eventoFacade.filtraDisciplinasNaoPermitidasUsuario(this.evento, disciplinasSelecionadas);
		}
	}

	/**
	 * Prepara turmas selecionadas.
	 */
	private void preparaTurmasSelecionadas() {
		List<Turma> turmasSelecionadas = Faces.getFlashAttribute(OKConstants.TURMAS_SELECIONADAS_KEY);
		Faces.getFlash().putNow(OKConstants.TURMAS_SELECIONADAS_KEY, null);

		if (turmasSelecionadas != null) {
			this.evento.setCursos(new ArrayList<>());
			this.evento.setDisciplinas(new ArrayList<>());
			this.eventoFacade.filtraTurmasNaoPermitidasUsuario(this.evento, turmasSelecionadas);
		}
	}

	/**
	 * Removes the item selecionado.
	 *
	 * @param itens
	 *            the itens
	 * @param index
	 *            the index
	 */
	public void removeItemSelecionado(List<?> itens, int index) {
		itens.remove(index);
	}

	/**
	 * Seleciona evento.
	 */
	public void selecionaEvento() {
		String idEvento = Faces.getRequestParameter("idEvento");
		this.evento = this.eventoFacade.findEventoById(Integer.valueOf(idEvento));
	}

	/**
	 * Prepara detalhes evento.
	 */
	public void preparaDetalhesEvento() {
		this.selecionaEvento();
		super.getRequestContext().execute("$('#eventModal').modal('show')");
	}
}
