package br.com.ok.business;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.model.Aluno;
import br.com.ok.model.Anexo;
import br.com.ok.model.CodigoSeguranca;
import br.com.ok.model.Curso;
import br.com.ok.model.Disciplina;
import br.com.ok.model.Perfil;
import br.com.ok.model.Professor;
import br.com.ok.model.Tarefa;
import br.com.ok.model.Turma;
import br.com.ok.model.Usuario;
import br.com.ok.model.dao.UsuarioDAO;
import br.com.ok.model.enums.PerfilUsuario;
import br.com.ok.model.enums.Sexo;
import br.com.ok.model.enums.StatusTarefa;
import br.com.ok.model.util.ProfilePicture;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.logging.OKLogManager;
import br.com.ok.util.pagination.OKPaginatedList;
import br.com.ok.util.security.OKPasswordHandler;

/**
 * The Class UsuarioBean.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@Component
@Stateless
public class UsuarioBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1379815844771138301L;

	/** The usuario dao. */
	@Inject
	private UsuarioDAO usuarioDAO;

	/** The task bean. */
	@Inject
	private TaskBean taskBean;

	/** The log manager. */
	@Inject
	private OKLogManager logManager;

	/**
	 * Login.
	 *
	 * @param login
	 *            the login
	 * @param password
	 *            the password
	 * @return the usuario
	 * @throws Exception
	 *             the exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario login(String login, String password) throws Exception {
		Usuario usuario = this.usuarioDAO.findByLoginAndPassword(login, OKPasswordHandler.doHash(password));

		if (!usuario.getAtivo()) {
			throw new OKBusinessException();
		}

		this.preparaFotoPerfilUsuario(usuario);
		this.preparaTarefasRecentesUsuario(usuario);

		return usuario;
	}

	/**
	 * Prepara tarefas recentes usuario.
	 *
	 * @param usuario
	 *            the usuario
	 */
	private void preparaTarefasRecentesUsuario(Usuario usuario) {
		List<Tarefa> tarefasRecentes = this.taskBean.pesquisaTarefasRecentesPorUsuarioStatus(usuario.getId(), StatusTarefa.EM_ANDAMENTO);
		usuario.setTarefasRecentes(tarefasRecentes);
	}

	/**
	 * Salva.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the usuario
	 */
	public Usuario salva(Usuario usuario) {
		if (usuario.getId() == null) {
			this.usuarioDAO.save(usuario);
		} else {
			usuario = this.atualiza(usuario);
		}
		return usuario;
	}

	/**
	 * Atualiza.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the usuario
	 */
	public Usuario atualiza(Usuario usuario) {
		return this.usuarioDAO.update(usuario);
	}

	/**
	 * Valida duplicidade email.
	 *
	 * @param usuario
	 *            the usuario
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void validaDuplicidadeEmail(Usuario usuario) {
		if (this.verificaDuplicidadeEmail(usuario)) {
			throw new OKBusinessException("{msg.erro.email.cadastrado}", ":mainForm:email");
		}
	}

	/**
	 * Valida duplicidade login.
	 *
	 * @param usuario
	 *            the usuario
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void validaDuplicidadeLogin(Usuario usuario) throws OKBusinessException {
		if (this.verificaDuplicidadeLogin(usuario)) {
			throw new OKBusinessException("{msg.erro.login.cadastrado}", ":mainForm:usuario");
		}
	}

	/**
	 * Criptografa senha usuario.
	 *
	 * @param usuario
	 *            the usuario
	 * @throws Exception
	 *             the exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void criptografaSenhaUsuario(Usuario usuario) throws Exception {
		usuario.setSenha(OKPasswordHandler.doHash(usuario.getPassword()));
	}

	/**
	 * Define status usuario.
	 *
	 * @param usuario
	 *            the usuario
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void defineStatusUsuario(Usuario usuario) {
		if (usuario.getId() == null) {
			usuario.setAtivo(true);
			usuario.setDataCadastro(new Date());
		}
	}

	/**
	 * Verifica duplicidade email.
	 *
	 * @param usuario
	 *            the usuario
	 * @return true, if successful
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean verificaDuplicidadeEmail(Usuario usuario) {
		Integer userId = usuario.getId();
		String email = usuario.getEmail();
		Long count = null;

		if (userId == null) {
			count = this.usuarioDAO.countByEmail(email);
		} else {
			count = this.usuarioDAO.countByEmailExcludingUserId(email, userId);
		}

		return count > OKConstants.VALOR_ZERO.longValue();
	}

	/**
	 * Verifica duplicidade login.
	 *
	 * @param usuario
	 *            the usuario
	 * @return true, if successful
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean verificaDuplicidadeLogin(Usuario usuario) {
		Integer userId = usuario.getId();
		String login = usuario.getLogin();
		Long count = null;

		if (userId == null) {
			count = this.usuarioDAO.countByLogin(login);
		} else {
			count = this.usuarioDAO.countByLoginExcludingUserId(login, userId);
		}

		return count > OKConstants.VALOR_ZERO.longValue();
	}

	/**
	 * Prepara usuario por perfil.
	 *
	 * @param perfilUsuario
	 *            the perfil usuario
	 * @param inicializaDisciplinas
	 *            the inicializa disciplinas
	 * @return the usuario
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario preparaUsuarioPorPerfil(PerfilUsuario perfilUsuario, boolean inicializaDisciplinas) {
		Usuario usuario = null;

		switch (perfilUsuario) {
			case ALUNO:
				Turma turma = new Turma();
				turma.setCurso(new Curso());
				Aluno aluno = new Aluno();
				aluno.setTurma(turma);
				aluno.setCodigoSeguranca(new CodigoSeguranca());
				aluno.setDisciplinas(this.preparaDisciplinas(inicializaDisciplinas));
				usuario = aluno;
				break;
			case ADMIN:
				usuario = new Usuario();
				break;
			default:
				Professor professor = new Professor();
				professor.setDisciplinas(this.preparaDisciplinas(inicializaDisciplinas));
				usuario = professor;
				break;
		}

		Perfil perfil = new Perfil();
		perfil.setDescricao(perfilUsuario);
		usuario.setPerfil(perfil);
		usuario.setSexo(Sexo.MASCULINO);
		return usuario;
	}

	/**
	 * Prepara disciplinas.
	 *
	 * @param inicializaDisciplinas
	 *            the inicializa disciplinas
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Disciplina> preparaDisciplinas(boolean inicializaDisciplinas) {
		if (inicializaDisciplinas) {
			return Arrays.asList(new Disciplina());
		}
		return null;
	}

	/**
	 * Pesquisa usuarios paginados.
	 *
	 * @param usuario
	 *            the usuario
	 * @param firstResult
	 *            the first result
	 * @param maxResult
	 *            the max results
	 * @return the OK paginated list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public OKPaginatedList<Usuario> pesquisaUsuariosPaginados(Usuario usuario, int firstResult, int maxResult) {
		if (usuario instanceof Aluno) {
			Aluno aluno = (Aluno) usuario;
			this.preparaDisciplinaParaPesquisa(aluno.getDisciplinas().get(0));
			return this.usuarioDAO.findAlunosByArguments(aluno, firstResult, maxResult);

		} else if (usuario instanceof Professor) {
			Professor professor = (Professor) usuario;
			this.preparaDisciplinaParaPesquisa(professor.getDisciplinas().get(0));
			return this.usuarioDAO.findProfessoresByArguments(professor, firstResult, maxResult);

		} else {
			return this.usuarioDAO.findAdminsByArguments(usuario, firstResult, maxResult);
		}
	}

	/**
	 * Prepara disciplina pesquisa.
	 *
	 * @param disciplina
	 *            the disciplina
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private void preparaDisciplinaParaPesquisa(Disciplina disciplina) {
		String nomeDisciplina = disciplina.getNome();
		if (StringUtils.trimToNull(nomeDisciplina) != null) {
			disciplina.setCodigo(nomeDisciplina.split(OKConstants.WORDS_SEPARATOR)[0]);
		} else {
			disciplina.setCodigo(null);
		}
	}

	/**
	 * Pesquisa usuario por id.
	 *
	 * @param id
	 *            the id
	 * @return the usuario
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario pesquisaUsuarioPorId(Integer id) {
		return this.usuarioDAO.findById(id);
	}

	/**
	 * Pesquisa aluno por id.
	 *
	 * @param id
	 *            the id
	 * @param inicializaDisciplinas
	 *            the inicializa disciplinas
	 * @return the aluno
	 */
	public Aluno pesquisaAlunoPorId(Integer id, boolean inicializaDisciplinas) {
		Aluno aluno = (Aluno) this.usuarioDAO.findById(id);
		if (inicializaDisciplinas) {
			this.usuarioDAO.initProxy(aluno.getDisciplinas());
		}
		return aluno;
	}

	/**
	 * Prepara visualizacao perfil usuario.
	 *
	 * @param id
	 *            the id
	 * @return the usuario
	 */
	public Usuario preparaVisualizacaoPerfilUsuario(Integer id) {
		Usuario usuario = this.pesquisaUsuarioPorId(id);

		if (usuario instanceof Aluno) {
			Aluno aluno = (Aluno) usuario;
			this.usuarioDAO.initProxy(aluno.getDisciplinas());

		} else if (usuario instanceof Professor) {
			Professor professor = (Professor) usuario;
			this.usuarioDAO.initProxy(professor.getDisciplinas());

			if (PerfilUsuario.COORDENADOR.equals(professor.getPerfil().getDescricao())) {
				this.usuarioDAO.initProxy(professor.getCursosCoordenados());
			}
		}

		this.preparaFotoPerfilUsuario(usuario);
		return usuario;
	}

	/**
	 * Prepara foto perfil usuario.
	 *
	 * @param usuario
	 *            the usuario
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void preparaFotoPerfilUsuario(Usuario usuario) {
		try {
			Anexo fotoPerfil = usuario.getFotoPerfil();
			if (fotoPerfil != null) {
				usuario.setProfilePicture(new ProfilePicture(usuario.getFotoPerfil()));
			}
		} catch (Exception e) {
			this.logManager.getLogger().log(Level.SEVERE, e.getMessage(), e);
		}
	}

	/**
	 * Altera status usuario.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the usuario
	 */
	public Usuario alteraStatusUsuario(Usuario usuario) {
		if (usuario.getAtivo()) {
			usuario.setAtivo(false);
		} else {
			usuario.setAtivo(true);
		}
		return this.usuarioDAO.update(usuario);
	}

	/**
	 * Redefine senha usuario.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the usuario
	 */
	public Usuario redefineSenhaUsuario(Usuario usuario) {
		usuario.setSenha(OKPasswordHandler.generateDefaultPassword());
		return this.usuarioDAO.update(usuario);
	}

	/**
	 * Altera perfil professor.
	 *
	 * @param professor
	 *            the professor
	 * @param novoPerfil
	 *            the novo perfil
	 * @return the professor
	 */
	public Professor alteraPerfilProfessor(Professor professor, Perfil novoPerfil) {
		if (!PerfilUsuario.COORDENADOR.equals(novoPerfil.getDescricao()) && professor.getCursosCoordenados() != null && !professor.getCursosCoordenados().isEmpty()) {
			throw new OKBusinessException("{msg.alteracao.perfil.coordenador}");
		}
		professor.setPerfil(novoPerfil);
		return (Professor) this.usuarioDAO.update(professor);
	}

	/**
	 * Pesquisa professores por disciplina.
	 *
	 * @param idDisciplina
	 *            the id disciplina
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Professor> pesquisaProfessoresPorDisciplina(Integer idDisciplina) {
		return this.usuarioDAO.findProfessoresByDisciplinaId(idDisciplina);
	}

	/**
	 * Lista nomes professores.
	 *
	 * @param nome
	 *            the nome
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<String> listaNomesProfessores(String nome) {
		return this.usuarioDAO.findNomesProfessores(nome);
	}

	/**
	 * Pesquisa por email.
	 *
	 * @param email
	 *            the email
	 * @return the usuario
	 */
	public Usuario pesquisaPorEmail(String email) {
		return this.usuarioDAO.findByEmail(email);
	}

	/**
	 * Pesquisa logins usuario sistema.
	 *
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<String> pesquisaLoginsUsuariosAtivosSistema() {
		return this.usuarioDAO.findAllActiveLogins();
	}

	/**
	 * Pesquisa usuario por login.
	 *
	 * @param login
	 *            the login
	 * @return the usuario
	 */
	public Usuario pesquisaUsuarioPorLogin(String login) {
		return this.usuarioDAO.findByLogin(login);
	}

	/**
	 * Recupera foto perfil usuario.
	 *
	 * @param login
	 *            the login
	 * @return the anexo
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Anexo recuperaFotoPerfilUsuario(String login) {
		Anexo anexo = null;
		try {
			anexo = this.usuarioDAO.findFotoPerfilByLoginUsuario(login);
		} catch (Exception e) {
			this.logManager.getLogger().log(Level.SEVERE, e.getMessage(), e);
		}
		return anexo;
	}
}
