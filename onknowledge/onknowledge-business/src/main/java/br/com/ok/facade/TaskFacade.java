package br.com.ok.facade;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.ok.business.TaskBean;
import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.model.Tarefa;
import br.com.ok.model.enums.StatusTarefa;

/**
 * The Class TaskFacade.
 *
 * @author Adilson
 * @version 1.0 - 12/10/2014
 */
public class TaskFacade implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7948245677759505461L;

	/** The task bean. */
	@Inject
	private TaskBean taskBean;

	/**
	 * Find task list.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the list
	 */
	public List<Tarefa> findTaskList(Integer usuario) {
		return this.taskBean.findTaskList(usuario);
	}

	/**
	 * Save task.
	 *
	 * @param tarefa
	 *            the tarefa
	 */
	public void saveTask(Tarefa tarefa) {
		this.taskBean.validaDatasTarefa(tarefa);
		this.taskBean.saveTask(tarefa);
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
		this.taskBean.deleteTask(tarefa);
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
		this.taskBean.validaDatasTarefa(tarefa);
		this.taskBean.updateTask(tarefa);
	}

	/**
	 * Pesquisa tarefas recentes por usuario status.
	 *
	 * @param idUsuario
	 *            the id usuario
	 * @param statusTarefa
	 *            the status tarefa
	 * @return the list
	 */
	public List<Tarefa> pesquisaTarefasRecentesPorUsuarioStatus(Integer idUsuario, StatusTarefa statusTarefa) {
		return this.taskBean.pesquisaTarefasRecentesPorUsuarioStatus(idUsuario, statusTarefa);
	}

}
