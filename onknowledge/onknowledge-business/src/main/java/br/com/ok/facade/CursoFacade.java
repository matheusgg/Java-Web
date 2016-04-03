package br.com.ok.facade;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.ok.business.CursoBean;
import br.com.ok.facade.base.OKGenericFacade;
import br.com.ok.model.Curso;
import br.com.ok.util.pagination.OKPaginatedList;

/**
 * The Class CursoFacade.
 *
 * @author Matheus
 * @version 1.0 - 20/09/2014
 */
public class CursoFacade extends OKGenericFacade {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8151419093323639545L;

	/** The curso bean. */
	@Inject
	private CursoBean cursoBean;

	/**
	 * Lista id nome curso.
	 *
	 * @param nome
	 *            the nome
	 * @return the list
	 */
	public List<String> listaNomesCursos(String nome) {
		return this.cursoBean.listaNomesCursos(nome);
	}

	/**
	 * Pesquisa cursos.
	 *
	 * @return the list
	 */
	public List<Curso> pesquisaCursos() {
		return this.cursoBean.pesquisaCursos();
	}

	/**
	 * Salva.
	 *
	 * @param curso
	 *            the curso
	 * @return the curso
	 */
	@Transactional(rollbackOn = Exception.class)
	public Curso salva(Curso curso) {
		return this.cursoBean.salva(curso);
	}

	/**
	 * Verifica duplicidade nome curso.
	 *
	 * @param curso
	 *            the curso
	 */
	public void verificaDuplicidadeNomeCurso(Curso curso) {
		this.cursoBean.verificaDuplicidadeNomeCurso(curso);
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
	public OKPaginatedList<Curso> pesquisaCursosPorArgumentos(Curso args, int firstResult, int maxResult) {
		return this.cursoBean.pesquisaCursosPorArgumentos(args, firstResult, maxResult);
	}

	/**
	 * Pesquisa curso por id.
	 *
	 * @param id
	 *            the id
	 * @return the curso
	 */
	public Curso pesquisaCursoPorId(Integer id) {
		return this.cursoBean.pesquisaCursoPorId(id);
	}
}
