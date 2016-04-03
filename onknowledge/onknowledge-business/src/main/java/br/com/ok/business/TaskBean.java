package br.com.ok.business;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.model.Tarefa;
import br.com.ok.model.dao.TaskDAO;
import br.com.ok.model.enums.StatusTarefa;

/**
 * The Class TaskBean.
 *
 * @author Adilson
 * @version 1.0 - 12/10/2014
 */
@Component
@Stateless
public class TaskBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7935576439949259967L;

	/** The task dao. */
	@Inject
	private TaskDAO taskDAO;

	/**
	 * Find task list.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the list
	 */
	public List<Tarefa> findTaskList(Integer usuario) {
		List<Tarefa> tarefas = this.taskDAO.findTaskList(usuario);
		tarefas.forEach(tarefa -> tarefa.setDataTarefaOriginal(tarefa.getDataTarefa()));
		return tarefas;
	}

	/**
	 * Save task.
	 *
	 * @param tarefa
	 *            the tarefa
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	public void saveTask(Tarefa tarefa) throws OKBusinessException {
		this.taskDAO.save(tarefa);
	}

	/**
	 * Valida datas tarefa.
	 *
	 * @param tarefa
	 *            the tarefa
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void validaDatasTarefa(Tarefa tarefa) throws OKBusinessException {
		Date dataInicio = tarefa.getDataTarefa();
		LocalDate dataInformada = LocalDateTime.ofInstant(dataInicio.toInstant(), ZoneId.systemDefault()).toLocalDate();

		if (!dataInicio.equals(tarefa.getDataTarefaOriginal()) && dataInformada.isBefore(LocalDate.now())) {
			throw new OKBusinessException("{msg.validacao.data.inicial.invalida}", ":mainForm:tarefaData");
		}
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
		tarefa = this.taskDAO.update(tarefa);
		this.taskDAO.remove(tarefa);

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
		this.taskDAO.update(tarefa);
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
		return this.taskDAO.findByUsuarioAndStatus(idUsuario, statusTarefa);
	}

}
