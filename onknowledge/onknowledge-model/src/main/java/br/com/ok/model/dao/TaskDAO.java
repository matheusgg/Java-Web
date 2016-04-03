package br.com.ok.model.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import br.com.ok.model.Tarefa;
import br.com.ok.model.dao.base.OKGenericDAO;
import br.com.ok.model.enums.StatusTarefa;
import br.com.ok.util.constants.OKConstants;

/**
 * The Class TaskDAO.
 *
 * @author Adilson
 * @version 1.0 - 12/10/2014
 */
@Component
public class TaskDAO extends OKGenericDAO<Tarefa> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3896508984849631339L;

	/**
	 * Find task list.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the list
	 */
	public List<Tarefa> findTaskList(Integer usuario) {
		TypedQuery<Tarefa> query = super.getEntityManager().createNamedQuery("Tarefa.findList", Tarefa.class);
		query.setParameter("usuario", usuario);
		return query.getResultList();
	}

	/**
	 * Find by usuario and status.
	 *
	 * @param idUsuario
	 *            the id usuario
	 * @param statusTarefa
	 *            the status tarefa
	 * @return the list
	 */
	public List<Tarefa> findByUsuarioAndStatus(Integer idUsuario, StatusTarefa statusTarefa) {
		TypedQuery<Tarefa> query = super.getEntityManager().createNamedQuery("Tarefa.findByUsuario", Tarefa.class);
		query.setParameter("idUsuario", idUsuario);
		query.setParameter("statusTarefa", statusTarefa);
		query.setMaxResults(OKConstants.VALOR_TRES);
		return query.getResultList();

	}

}
