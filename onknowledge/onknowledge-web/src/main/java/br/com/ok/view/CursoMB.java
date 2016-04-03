package br.com.ok.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

import org.omnifaces.util.Faces;

import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.facade.CursoFacade;
import br.com.ok.facade.DisciplinaFacade;
import br.com.ok.model.Curso;
import br.com.ok.model.Disciplina;
import br.com.ok.model.Professor;
import br.com.ok.model.enums.PerfilUsuario;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.messages.OKMessages;
import br.com.ok.util.pagination.OKLazyDataModel;
import br.com.ok.view.base.OKBaseMB;
import br.com.ok.view.base.annotation.OnKnowledgeMB;

/**
 * The Class CursoMB.
 *
 * @author Matheus
 * @version 1.0 - 20/09/2014
 */
@OnKnowledgeMB
public class CursoMB extends OKBaseMB {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8513800337173348013L;

	/** The curso facade. */
	@Inject
	private CursoFacade cursoFacade;

	@Inject
	private DisciplinaFacade disciplinaFacade;

	/** The curso. */
	@Getter
	private Curso curso;

	/** The cursos. */
	@Getter
	private List<Curso> cursos;

	/** The cursos data model. */
	@Getter
	private OKLazyDataModel<Curso> cursosDataModel;

	/** The cursos selecionados. */
	@Getter
	@Setter
	private List<Curso> cursosSelecionados;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.curso = new Curso();
	}

	/**
	 * Prepara pesquisa cursos.
	 */
	public void preparaPesquisaCursos() {
		this.curso.setCoordenador(new Professor());
		this.cursosDataModel = new OKLazyDataModel<>(((firstResult, maxResult) -> {
			if (super.isSearchEvent()) {
				return this.cursoFacade.pesquisaCursosPorArgumentos(this.curso, firstResult, maxResult);
			} else {
				return null;
			}
		}));
	}

	/**
	 * Prepara cadastro curso.
	 */
	public void preparaCadastroCurso() {
		Curso curso = (Curso) super.getCallerObject();
		Integer idCurso = Faces.getFlashAttribute(OKConstants.CURSO_ID_KEY);

		if (idCurso != null) {
			this.curso = this.cursoFacade.pesquisaCursoPorId(idCurso);
			Faces.getFlash().putNow(OKConstants.CURSO_ID_KEY, null);

		} else if (curso != null) {
			this.curso = curso;
			this.recuperaCoordenadorSelecionado();
			this.recuperaDisciplinasSelecionadas();

		} else if (!OKConstants.PRETTY_COURSE_REGISTER.equals(super.getViewId())) {
			super.getPrettyRedirector().redirect(Faces.getContext(), OKConstants.PRETTY_COURSE_REGISTER);
		}
	}

	/**
	 * Recupera coordenador selecionado.
	 */
	private void recuperaCoordenadorSelecionado() {
		Professor professor = Faces.getFlashAttribute(OKConstants.PROFESSORES_SELECIONADOS_KEY);
		if (professor != null) {
			if (!PerfilUsuario.COORDENADOR.equals(professor.getPerfil().getDescricao())) {
				throw new OKBusinessException("{msg.erro.professor.perfil.coordenador}");
			}
			this.curso.setCoordenador(professor);
		}
		Faces.getFlash().putNow(OKConstants.PROFESSORES_SELECIONADOS_KEY, null);
	}

	/**
	 * Recupera disciplinas selecionadas.
	 */
	private void recuperaDisciplinasSelecionadas() {
		List<Disciplina> disciplinasSelecionadas = Faces.getFlashAttribute(OKConstants.DISCIPLINAS_SELECIONADAS_KEY);
		if (disciplinasSelecionadas != null) {
			disciplinasSelecionadas = this.disciplinaFacade.filtraDisciplinasEncerramentoValido(disciplinasSelecionadas);
			Set<Disciplina> disciplinas = new HashSet<>();

			if (this.curso.getDisciplinas() != null) {
				disciplinas.addAll(this.curso.getDisciplinas());
			}

			disciplinas.addAll(disciplinasSelecionadas);
			disciplinasSelecionadas = new ArrayList<>(disciplinas);
			disciplinasSelecionadas.sort(Comparator.comparing(Disciplina::getNome));

			this.curso.setDisciplinas(disciplinasSelecionadas);
		}
		Faces.getFlash().putNow(OKConstants.DISCIPLINAS_SELECIONADAS_KEY, null);
	}

	/**
	 * Removes the disciplina associada.
	 *
	 * @param index
	 *            the index
	 */
	public void removeDisciplinaAssociada(int index) {
		this.curso.getDisciplinas().remove(index);
	}

	/**
	 * Salva curso.
	 *
	 * @return the string
	 */
	public String salvaCurso() {
		this.cursoFacade.salva(this.curso);
		OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getFormattedMessage("msg.sucesso.curso.cadastrado", this.curso.getNome()),
				OKMessages.Severity.SUCCESS);
		return OKConstants.PRETTY_COURSE_SEARCH;
	}

	/**
	 * Seleciona curso para edicao.
	 *
	 * @param cursoId
	 *            the curso id
	 * @param coursePage
	 *            the course page
	 * @return the string
	 */
	public String selecionaCursoParaEdicao(Integer cursoId, String coursePage) {
		Faces.setFlashAttribute(OKConstants.CURSO_ID_KEY, cursoId);
		return coursePage;
	}

	/**
	 * Pesquisa cursos.
	 *
	 * @return the list
	 */
	public void pesquisaCursos() {
		this.cursos = this.cursoFacade.pesquisaCursos();
	}

	/**
	 * Seleciona disciplinas.
	 *
	 * @return the string
	 */
	public String selecionaCursos() {
		Faces.setFlashAttribute(OKConstants.CURSOS_SELECIONADOS_KEY, this.cursosSelecionados);
		return super.returnToCallerPage();
	}
}
