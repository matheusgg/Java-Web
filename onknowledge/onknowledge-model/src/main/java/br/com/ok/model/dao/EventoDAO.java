package br.com.ok.model.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.ok.model.Aluno;
import br.com.ok.model.Evento;
import br.com.ok.model.Professor;
import br.com.ok.model.dao.base.OKGenericDAO;

/**
 * The Class EventoDAO.
 *
 * @author Adilson
 * @version 1.0 - 31/10/2014
 */
public class EventoDAO extends OKGenericDAO<Evento> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3422143004751705874L;

	/**
	 * Lista eventos cadastrados pelo usuario.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the list
	 */
	public List<Evento> listaEventosCadastradosPeloUsuario(Integer usuario) {
		TypedQuery<Evento> query = super.getEntityManager().createNamedQuery("Evento.listaEventosCadastradosPeloUsuario", Evento.class);
		query.setParameter("usuario", usuario);
		return query.getResultList();
	}

	/**
	 * Find by id.
	 *
	 * @param idEvento
	 *            the id evento
	 * @return the evento
	 */
	public Evento findEventoById(Integer idEvento) {
		TypedQuery<Evento> query = super.getEntityManager().createNamedQuery("Evento.findEventoById", Evento.class);
		query.setParameter("id", idEvento);
		return query.getSingleResult();
	}

	/**
	 * Find eventos by professor.
	 *
	 * @param professor
	 *            the professor
	 * @return the list
	 */
	public List<Evento> findEventosByProfessor(Professor professor) {
		TypedQuery<Evento> query = super.getEntityManager().createNamedQuery("Evento.findEventosByProfessor", Evento.class);
		query.setParameter("professor", professor);
		return query.getResultList();
	}

	/**
	 * Find eventos cursos by coordenador.
	 *
	 * @param idCoordenador
	 *            the id coordenador
	 * @return the list
	 */
	public List<Evento> findEventosCursosByCoordenador(Integer idCoordenador) {
		TypedQuery<Evento> query = super.getEntityManager().createNamedQuery("Evento.findEventosCursosByCoordenador", Evento.class);
		query.setParameter("idCoordenador", idCoordenador);
		return query.getResultList();
	}

	/**
	 * Find eventos turmas by coordenador.
	 *
	 * @param idCoordenador
	 *            the id coordenador
	 * @return the list
	 */
	public List<Evento> findEventosTurmasByCoordenador(Integer idCoordenador) {
		TypedQuery<Evento> query = super.getEntityManager().createNamedQuery("Evento.findEventosTurmasByCoordenador", Evento.class);
		query.setParameter("idCoordenador", idCoordenador);
		return query.getResultList();
	}

	/**
	 * Find eventos cursos by aluno.
	 *
	 * @param aluno
	 *            the aluno
	 * @return the list
	 */
	public List<Evento> findEventosCursosByAluno(Aluno aluno) {
		TypedQuery<Evento> query = super.getEntityManager().createNamedQuery("Evento.findEventosCursosByAluno", Evento.class);
		query.setParameter("aluno", aluno);
		return query.getResultList();
	}

	/**
	 * Find eventos disciplinas extras by aluno.
	 *
	 * @param aluno
	 *            the aluno
	 * @return the list
	 */
	public List<Evento> findEventosDisciplinasExtrasByAluno(Aluno aluno) {
		TypedQuery<Evento> query = super.getEntityManager().createNamedQuery("Evento.findEventosDisciplinasExtrasByAluno", Evento.class);
		query.setParameter("aluno", aluno);
		return query.getResultList();
	}

	/**
	 * Find eventos disciplinas by aluno.
	 *
	 * @param aluno
	 *            the aluno
	 * @return the list
	 */
	public List<Evento> findEventosDisciplinasByAluno(Aluno aluno) {
		TypedQuery<Evento> query = super.getEntityManager().createNamedQuery("Evento.findEventosDisciplinasByAluno", Evento.class);
		query.setParameter("aluno", aluno);
		return query.getResultList();
	}

	/**
	 * Find eventos turmas by aluno.
	 *
	 * @param aluno
	 *            the aluno
	 * @return the list
	 */
	public List<Evento> findEventosTurmasByAluno(Aluno aluno) {
		TypedQuery<Evento> query = super.getEntityManager().createNamedQuery("Evento.findEventosTurmasByAluno", Evento.class);
		query.setParameter("aluno", aluno);
		return query.getResultList();
	}

}
