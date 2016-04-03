package br.com.ok.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.facade.TaskFacade;
import br.com.ok.model.Tarefa;
import br.com.ok.model.Usuario;
import br.com.ok.model.enums.StatusTarefa;
import br.com.ok.view.base.OKBaseMB;
import br.com.ok.view.base.annotation.OnKnowledgeMB;

/**
 * The Class TaskMB.
 *
 * @author Adilson
 * @version 1.0 - 12/10/2014
 */
@OnKnowledgeMB
public class TaskMB extends OKBaseMB {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 522780649257813531L;

	/** The taskfacade. */
	@Inject
	private TaskFacade taskFacade;

	/** The tarefa. */
	@Getter
	@Setter
	private Tarefa tarefa;

	/** The list tarefa ativa. */
	@Getter
	@Setter
	private List<Tarefa> listTarefaAtiva;

	/** The list tarefa inativa. */
	@Getter
	@Setter
	private List<Tarefa> listTarefaInativa;

	/** The incluir tarefa enable. */
	@Getter
	@Setter
	private boolean incluirTarefaEnable;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.findListTask();
	}

	/**
	 * Find list task.
	 */
	public void findListTask() {
		this.listTarefaAtiva = new ArrayList<>();
		this.listTarefaInativa = new ArrayList<>();

		List<Tarefa> tarefas = this.taskFacade.findTaskList(super.getSecurityContext().getLoggedUser().getId());

		for (Tarefa tarefa : tarefas) {

			if (tarefa.getStatusTarefa().equals(StatusTarefa.EM_ANDAMENTO)) {
				listTarefaAtiva.add(tarefa);
			}

			if (tarefa.getStatusTarefa().equals(StatusTarefa.ENCERRADA)) {
				listTarefaInativa.add(tarefa);
			}

		}
	}

	/**
	 * Selecionar funcao.
	 */
	public void selecionarFuncao() {
		if (this.tarefa.getId() == null) {
			this.saveTask();
		} else {
			this.updateTask(tarefa);
		}
	}

	/**
	 * Save task.
	 */
	public void saveTask() {
		this.tarefa.setUsuario(super.getSecurityContext().getLoggedUser());
		this.tarefa.setStatusTarefa(StatusTarefa.EM_ANDAMENTO);
		this.taskFacade.saveTask(tarefa);
		this.tarefa = null;
		this.findListTask();
		this.atualizaTarefasRecentes();
	}

	/**
	 * Delete task.
	 *
	 * @param tarefa
	 *            the tarefa
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	public void deleteTask(Tarefa tarefa) throws OKBusinessException {
		this.taskFacade.deleteTask(tarefa);
		this.tarefa = null;
		this.findListTask();
		this.atualizaTarefasRecentes();
	}

	/**
	 * Update task.
	 *
	 * @param tarefa
	 *            the tarefa
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	public void updateTask(Tarefa tarefa) throws OKBusinessException {
		this.taskFacade.updateTask(tarefa);
		this.tarefa = null;
		this.findListTask();
		this.atualizaTarefasRecentes();
	}

	/**
	 * Prepara inclusao nova tarefa.
	 */
	public void preparaInclusaoNovaTarefa() {
		this.tarefa = new Tarefa();
	}

	/**
	 * Inativa tarefa.
	 *
	 * @param tarefa
	 *            the tarefa
	 */
	public void inativaTarefa(Tarefa tarefa) {
		tarefa.setStatusTarefa(StatusTarefa.ENCERRADA);
		this.listTarefaAtiva.remove(tarefa);
		this.listTarefaInativa.add(tarefa);
	}

	/**
	 * Ativa tarefa.
	 *
	 * @param tarefa
	 *            the tarefa
	 */
	public void ativaTarefa(Tarefa tarefa) {
		tarefa.setStatusTarefa(StatusTarefa.EM_ANDAMENTO);
		this.listTarefaInativa.remove(tarefa);
		this.listTarefaAtiva.add(tarefa);
	}

	/**
	 * Atualiza tarefas recentes.
	 */
	private void atualizaTarefasRecentes() {
		Usuario usuario = super.getSecurityContext().getLoggedUser();
		List<Tarefa> tarefasRecentes = this.taskFacade.pesquisaTarefasRecentesPorUsuarioStatus(usuario.getId(), StatusTarefa.EM_ANDAMENTO);
		usuario.setTarefasRecentes(tarefasRecentes);
	}
}
