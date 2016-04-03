package br.com.ok.business;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.model.Curso;
import br.com.ok.model.dao.CursoDAO;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.pagination.OKPaginatedList;

/**
 * The Class CursoBean.
 *
 * @author Matheus
 * @version 1.0 - 20/09/2014
 */
@Stateless
public class CursoBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2396641752120193947L;

	/** The curso dao. */
	@Inject
	private CursoDAO cursoDAO;

	/**
	 * Lista por nome.
	 *
	 * @param nome
	 *            the nome
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<String> listaNomesCursos(String nome) {
		return this.cursoDAO.listByNome(nome);
	}

	/**
	 * Pesquisa cursos.
	 *
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Curso> pesquisaCursos() {
		return this.cursoDAO.listAll();
	}

	/**
	 * Salva.
	 *
	 * @param curso
	 *            the curso
	 * @return the curso
	 */
	public Curso salva(Curso curso) {
		this.verificaDuplicidadeNomeCurso(curso);

		if (curso.getCoordenador() == null) {
			throw new OKBusinessException("{msg.erro.selecione.coordenador}");
		}

		if (curso.getDisciplinas() == null || curso.getDisciplinas().isEmpty()) {
			throw new OKBusinessException("{msg.erro.selecione.disciplina}");
		}

		if (curso.getId() == null) {
			this.cursoDAO.save(curso);
		} else {
			curso = this.cursoDAO.update(curso);
		}

		return curso;
	}

	/**
	 * Verifica duplicidade nome curso.
	 *
	 * @param curso
	 *            the curso
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void verificaDuplicidadeNomeCurso(Curso curso) throws OKBusinessException {
		Long count = null;

		if (curso.getId() == null) {
			count = this.cursoDAO.countByName(curso.getNome());
		} else {
			count = this.cursoDAO.countByNameIgnoringId(curso.getNome(), curso.getId());
		}

		if (count > OKConstants.VALOR_ZERO) {
			throw new OKBusinessException("{msg.erro.curso.duplicado}");
		}
	}

	/**
	 * Pesquisa cursos por argumentos.
	 *
	 * @param args
	 *            the args
	 * @param firstResult
	 *            the first result
	 * @param maxResult
	 *            the max result
	 * @return the OK paginated list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public OKPaginatedList<Curso> pesquisaCursosPorArgumentos(Curso args, int firstResult, int maxResult) {
		return this.cursoDAO.findCursosByArgs(args, firstResult, maxResult);
	}

	/**
	 * Pesquisa curso por id.
	 *
	 * @param id
	 *            the id
	 * @return the curso
	 */
	public Curso pesquisaCursoPorId(Integer id) {
		Curso curso = this.cursoDAO.findById(id);
		this.cursoDAO.initProxy(curso.getDisciplinas());
		this.cursoDAO.initProxy(curso.getTurmas());
		return curso;
	}

	/**
	 * Count by id and coordenador.
	 *
	 * @param id
	 *            the id
	 * @param idCoordenador
	 *            the id coordenador
	 * @return the long
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Long countByIdAndCoordenador(Integer id, Integer idCoordenador) {
		return this.cursoDAO.countByIdAndCoordenador(id, idCoordenador);
	}
}