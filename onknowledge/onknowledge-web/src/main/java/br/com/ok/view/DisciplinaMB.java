package br.com.ok.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;

import br.com.ok.facade.DisciplinaFacade;
import br.com.ok.model.Aluno;
import br.com.ok.model.Disciplina;
import br.com.ok.model.Modulo;
import br.com.ok.model.Professor;
import br.com.ok.model.Usuario;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.messages.OKMessages;
import br.com.ok.util.pagination.OKLazyDataModel;
import br.com.ok.view.base.OKBaseMB;
import br.com.ok.view.base.annotation.OnKnowledgeMB;

/**
 * The Class DisciplinaMB.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@OnKnowledgeMB
public class DisciplinaMB extends OKBaseMB {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4677437097012714892L;

	/** The disciplina facade. */
	@Inject
	private DisciplinaFacade disciplinaFacade;

	/** The disciplina. */
	@Getter
	private Disciplina disciplina;

	/** The disciplinas data model. */
	@Getter
	private OKLazyDataModel<Disciplina> disciplinasDataModel;

	/** The disciplinas selecionadas. */
	@Getter
	@Setter
	private List<Disciplina> disciplinasSelecionadas;

	/** The disciplinas associadas aluno. */
	@Getter
	private List<String> disciplinasAssociadasAluno;

	/** The disciplina selecionada. */
	@Getter
	@Setter
	private Disciplina disciplinaSelecionada;

	/** The modulo. */
	@Getter
	@Setter
	private Modulo modulo;

	/** The novo modulo. */
	@Getter
	@Setter
	private Boolean novoModulo;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.disciplina = new Disciplina();
		this.preparaModuloDisciplina(false);
	}

	/**
	 * Prepara pesquisa disciplinas.
	 */
	public void preparaPesquisaDisciplinas() {
		this.disciplinasDataModel = new OKLazyDataModel<>((firstResult, maxResult) -> {
			if (super.isSearchEvent()) {
				boolean extraCurricular = super.getSecurityContext().getLoggedUser() instanceof Aluno;
				return this.disciplinaFacade.pesquisaDisciplinasPorArgumentos(this.disciplina, firstResult, maxResult, extraCurricular);
			} else {
				return null;
			}
		});
	}

	/**
	 * Prepara disciplinas associadas aluno.
	 */
	public void preparaDisciplinasAssociadasAluno() {
		Usuario usuario = super.getSecurityContext().getLoggedUser();
		if (usuario instanceof Aluno) {
			Aluno aluno = (Aluno) usuario;
			this.disciplinasAssociadasAluno = this.disciplinaFacade.pesquisaDisciplinasAssociadasAluno(aluno.getId());
		}
	}

	/**
	 * Prepara cadastro disciplina.
	 */
	public void preparaCadastroDisciplina() {
		Disciplina disciplina = (Disciplina) super.getCallerObject();
		Integer idDisciplina = Faces.getFlashAttribute(OKConstants.DISCIPLINA_ID_KEY);

		if (idDisciplina != null) {
			this.disciplina = this.disciplinaFacade.pesquisaDisciplinaPorId(idDisciplina);
			Faces.getFlash().putNow(OKConstants.DISCIPLINA_ID_KEY, null);

		} else if (disciplina != null) {
			this.disciplina = disciplina;
			this.recuperaProfessoresSelecionados();

		} else {
			this.disciplina.setCodigo(RandomStringUtils.randomAlphanumeric(OKConstants.VALOR_DEZ));
			this.disciplina.setDataCadastro(new Date());
			this.disciplina.setModulos(new ArrayList<>());
		}
	}

	/**
	 * Salva disciplina.
	 *
	 * @return the string
	 */
	public String salvaDisciplina() {
		this.disciplinaFacade.salvaDisciplina(this.disciplina);
		OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getFormattedMessage("msg.sucesso.cadastro.disciplina", this.disciplina.getNome()),
				OKMessages.Severity.SUCCESS);
		return OKConstants.PRETTY_DISCIPLINE_SEARCH;
	}

	/**
	 * Prepara edicao disciplina.
	 *
	 * @param idDisciplina
	 *            the id disciplina
	 * @return the string
	 */
	public String preparaEdicaoDisciplina(Integer idDisciplina) {
		Faces.setFlashAttribute(OKConstants.DISCIPLINA_ID_KEY, idDisciplina);
		return OKConstants.PRETTY_DISCIPLINE_REGISTER;
	}

	/**
	 * Prepara inclusao professores.
	 *
	 * @return the string
	 */
	public String preparaInclusaoProfessores() {
		return super.callPage(OKConstants.PRETTY_TEACHER_SEARCH, this.disciplina, UsuarioMB.class.getSimpleName(), OKConstants.MULTIPLE);
	}

	/**
	 * Recupera professores selecionados.
	 */
	private void recuperaProfessoresSelecionados() {
		List<Professor> professoresSelecionados = Faces.getFlashAttribute(OKConstants.PROFESSORES_SELECIONADOS_KEY);
		if (professoresSelecionados != null) {
			Set<Professor> professores = new HashSet<>(professoresSelecionados);

			if (this.disciplina.getProfessores() != null) {
				professores.addAll(this.disciplina.getProfessores());
			}

			this.disciplina.setProfessores(new ArrayList<>(professores));
		}
		Faces.getFlash().putNow(OKConstants.PROFESSORES_SELECIONADOS_KEY, null);
	}

	/**
	 * Removes the professor disciplina.
	 *
	 * @param index
	 *            the index
	 */
	public void removeProfessorDisciplina(int index) {
		this.disciplina.getProfessores().remove(index);
	}

	/**
	 * Removes the modulo disciplina.
	 *
	 * @param index
	 *            the index
	 */
	public void removeModuloDisciplina(int index) {
		this.disciplina.getModulos().remove(index);
	}

	/**
	 * Prepara modulo disciplina.
	 *
	 * @param openModulosModal
	 *            the open modulos modal
	 */
	public void preparaModuloDisciplina(boolean openModulosModal) {
		this.novoModulo = true;
		this.modulo = new Modulo();
		if (openModulosModal) {
			super.getRequestContext().execute("$('#modulosModal').modal('show')");
		}
	}

	/**
	 * Prepara tipo inclusao modulo disciplina.
	 */
	public void preparaTipoInclusaoModuloDisciplina() {
		this.modulo = new Modulo();
		if (!this.novoModulo) {
			this.disciplinaSelecionada = new Disciplina();
			this.pesquisaDisciplinasCarregandoModulos();
		}
		super.getRequestContext().reset(":mainForm:modalModulos");
	}

	/**
	 * Pesquisa disciplinas carregando modulos.
	 */
	public void pesquisaDisciplinasCarregandoModulos() {
		this.disciplinasSelecionadas = this.disciplinaFacade.pesquisaDisciplinasCarregandoModulos(this.disciplina.getId());
	}

	/**
	 * Pesquisa disciplinas por professor.
	 *
	 * @param idProfessor
	 *            the id professor
	 */
	public void pesquisaDisciplinasPorProfessor(Integer idProfessor) {
		this.disciplinasSelecionadas = this.disciplinaFacade.pesquisaDisciplinasPorProfessor(idProfessor);
	}

	/**
	 * Inclui modulo disciplina.
	 */
	public void incluiModuloDisciplina() {
		if (this.modulo != null && StringUtils.isNotBlank(this.modulo.getNome())) {
			this.modulo.setNovoModulo(true);
			Set<Modulo> modulos = new HashSet<>();
			modulos.addAll(this.disciplina.getModulos());
			modulos.add(this.modulo);
			this.disciplina.setModulos(new ArrayList<>(modulos));
			this.disciplina.getModulos().sort(Comparator.comparing(Modulo::getNome));
		}
	}

	/**
	 * Seleciona disciplinas.
	 *
	 * @return the string
	 */
	public String selecionaDisciplinas() {
		Faces.setFlashAttribute(OKConstants.DISCIPLINAS_SELECIONADAS_KEY, this.disciplinasSelecionadas);
		return super.returnToCallerPage();
	}

	/**
	 * Associa disciplina aluno.
	 *
	 * @param disciplina
	 *            the disciplina
	 * @return the string
	 */
	public String associaDisciplinaAluno(Disciplina disciplina) {
		this.disciplinaFacade.associaDisciplinaAluno(disciplina, super.getSecurityContext().getLoggedUser().getId());
		OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getFormattedMessage("msg.sucesso.disciplina.associada", disciplina.getNome()),
				OKMessages.Severity.SUCCESS);
		return OKConstants.PRETTY_DISCIPLINE_SEARCH;
	}

	/**
	 * Desassocia disciplina aluno.
	 *
	 * @param disciplina
	 *            the disciplina
	 * @return the string
	 */
	public String desassociaDisciplinaAluno(Disciplina disciplina) {
		this.disciplinaFacade.desassociaDisciplinaAluno(disciplina, super.getSecurityContext().getLoggedUser().getId());
		OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getFormattedMessage("msg.sucesso.disciplina.desassociada", disciplina.getNome()),
				OKMessages.Severity.SUCCESS);
		return OKConstants.PRETTY_DISCIPLINE_SEARCH;
	}

	/**
	 * Carrega disciplinas usuario.
	 *
	 * @param usuario
	 *            the usuario
	 */
	public void carregaDisciplinasUsuario(Usuario usuario) {
		this.disciplinasSelecionadas = this.disciplinaFacade.pesquisaDisciplinasUsuario(usuario);
	}
}
