package br.com.ok.facade;

import java.util.List;

import javax.inject.Inject;

import br.com.ok.business.DisciplinaBean;
import br.com.ok.business.UsuarioBean;
import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.facade.base.OKGenericFacade;
import br.com.ok.model.Aluno;
import br.com.ok.model.Disciplina;
import br.com.ok.model.Usuario;
import br.com.ok.util.pagination.OKPaginatedList;

/**
 * The Class DisciplinaFacade.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
public class DisciplinaFacade extends OKGenericFacade {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7496545232660453380L;

	/** The disciplina bean. */
	@Inject
	private DisciplinaBean disciplinaBean;

	@Inject
	private UsuarioBean usuarioBean;

	/**
	 * Lista nomes disciplinas.
	 *
	 * @param nome
	 *            the nome
	 * @return the list
	 */
	public List<Disciplina> listaNomesDisciplinas(String nome) {
		return this.disciplinaBean.listaNomesDisciplinas(nome);
	}

	/**
	 * Pesquisa disciplinas por argumentos.
	 *
	 * @param args
	 *            the args
	 * @param firstResult
	 *            the first result
	 * @param maxResult
	 *            the max result
	 * @param extraCurricular
	 *            the extra curricular
	 * @return the OK paginated list
	 */
	public OKPaginatedList<Disciplina> pesquisaDisciplinasPorArgumentos(Disciplina args, int firstResult, int maxResult, boolean extraCurricular) {
		return this.disciplinaBean.pesquisaDisciplinasPorArgumentos(args, firstResult, maxResult, extraCurricular);
	}

	/**
	 * Salva disciplina.
	 *
	 * @param disciplina
	 *            the disciplina
	 */
	public void salvaDisciplina(Disciplina disciplina) throws OKBusinessException {
		this.disciplinaBean.validaDatasDisciplinas(disciplina);
		this.disciplinaBean.validaQuantidadeMinimaProfessores(disciplina);
		this.disciplinaBean.salvaDisciplina(disciplina);
	}

	/**
	 * Pesquisa disciplina por id.
	 *
	 * @param id
	 *            the id
	 * @return the disciplina
	 */
	public Disciplina pesquisaDisciplinaPorId(Integer id) {
		Disciplina disciplina = this.disciplinaBean.pesquisaDisciplinaPorId(id);
		disciplina.setProfessores(this.usuarioBean.pesquisaProfessoresPorDisciplina(disciplina.getId()));
		disciplina.setDataInicioOriginal(disciplina.getDataInicio());
		return disciplina;
	}

	/**
	 * Pesquisa disciplinas carregando modulos.
	 *
	 * @param idDisciplina
	 *            the id disciplina
	 * @return the list
	 */
	public List<Disciplina> pesquisaDisciplinasCarregandoModulos(Integer idDisciplina) {
		return this.disciplinaBean.pesquisaDisciplinasCarregandoModulos(idDisciplina);
	}

	/**
	 * Pesquisa disciplinas por professor.
	 *
	 * @param idProfessor
	 *            the id professor
	 * @return the list
	 */
	public List<Disciplina> pesquisaDisciplinasPorProfessor(Integer idProfessor) {
		return this.disciplinaBean.pesquisaDisciplinasPorProfessor(idProfessor);
	}

	/**
	 * Pesquisa disciplinas por aluno.
	 *
	 * @param idAluno
	 *            the id aluno
	 * @return the list
	 */
	public List<Disciplina> pesquisaDisciplinasPorAluno(Integer idAluno) {
		return this.disciplinaBean.pesquisaDisciplinasPorAluno(idAluno);
	}

	/**
	 * Filtra disciplinas encerramento valido.
	 *
	 * @param disciplinas
	 *            the disciplinas
	 * @return the list
	 */
	public List<Disciplina> filtraDisciplinasEncerramentoValido(List<Disciplina> disciplinas) {
		return this.disciplinaBean.filtraDisciplinasEncerramentoValido(disciplinas);
	}

	/**
	 * Pesquisa disciplinas associadas aluno.
	 *
	 * @param idAluno
	 *            the id aluno
	 * @return the list
	 */
	public List<String> pesquisaDisciplinasAssociadasAluno(Integer idAluno) {
		return this.disciplinaBean.pesquisaDisciplinasAssociadasAluno(idAluno);
	}

	/**
	 * Associa disciplina aluno.
	 *
	 * @param disciplina
	 *            the disciplina
	 * @param idAluno
	 *            the id aluno
	 */
	public void associaDisciplinaAluno(Disciplina disciplina, Integer idAluno) {
		Aluno aluno = (Aluno) this.usuarioBean.pesquisaAlunoPorId(idAluno, true);
		aluno.getDisciplinas().add(disciplina);
		this.usuarioBean.atualiza(aluno);
	}

	/**
	 * Desassocia disciplina aluno.
	 *
	 * @param disciplina
	 *            the disciplina
	 * @param idAluno
	 *            the id aluno
	 */
	public void desassociaDisciplinaAluno(Disciplina disciplina, Integer idAluno) {
		Aluno aluno = (Aluno) this.usuarioBean.pesquisaAlunoPorId(idAluno, true);
		aluno.getDisciplinas().remove(disciplina);
		this.usuarioBean.atualiza(aluno);
	}

	/**
	 * Pesquisa disciplinas usuario.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the list
	 */
	public List<Disciplina> pesquisaDisciplinasUsuario(Usuario usuario) {
		return this.disciplinaBean.pesquisaDisciplinasUsuario(usuario);
	}

}
