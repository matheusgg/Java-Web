package br.com.ok.facade;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;

import br.com.ok.business.AnexoBean;
import br.com.ok.business.CodigoSegurancaBean;
import br.com.ok.business.PerfilBean;
import br.com.ok.business.TurmaBean;
import br.com.ok.business.UsuarioBean;
import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.facade.base.OKGenericFacade;
import br.com.ok.model.Aluno;
import br.com.ok.model.Anexo;
import br.com.ok.model.CodigoSeguranca;
import br.com.ok.model.Perfil;
import br.com.ok.model.Professor;
import br.com.ok.model.Turma;
import br.com.ok.model.Usuario;
import br.com.ok.model.enums.PerfilUsuario;
import br.com.ok.model.util.ProfilePicture;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.files.OKFileHandler;
import br.com.ok.util.pagination.OKPaginatedList;

/**
 * The Class UsuarioFacade.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
public class UsuarioFacade extends OKGenericFacade {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7772020167323699575L;

	/** The usuario bean. */
	@Inject
	private UsuarioBean usuarioBean;

	/** The turma bean. */
	@Inject
	private TurmaBean turmaBean;

	/** The codigo seguranca bean. */
	@Inject
	private CodigoSegurancaBean codigoSegurancaBean;

	/** The perfil bean. */
	@Inject
	private PerfilBean perfilBean;

	/** The anexo bean. */
	@Inject
	private AnexoBean anexoBean;

	/**
	 * Salva aluno.
	 *
	 * @param aluno
	 *            the aluno
	 * @return the aluno
	 * @throws Exception
	 *             the exception
	 */
	@Transactional(rollbackOn = Exception.class)
	public Aluno salvaAluno(Aluno aluno) throws Exception {
		this.validaTurmaAluno(aluno);
		this.validaCodigoSegurancaAluno(aluno);
		return (Aluno) this.salva(aluno);
	}

	/**
	 * Valida turma aluno.
	 *
	 * @param aluno
	 *            the aluno
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	private void validaTurmaAluno(Aluno aluno) throws OKBusinessException {
		try {
			Turma turma = this.turmaBean.pesquisaPorCodigoComDataAtual(aluno.getTurma().getCodigo());
			aluno.setTurma(turma);
		} catch (Exception e) {
			throw new OKBusinessException("{msg.erro.codigo.turma.invalido}", ":mainForm:turma");
		}
	}

	/**
	 * Valida codigo seguranca aluno.
	 *
	 * @param aluno
	 *            the aluno
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	private void validaCodigoSegurancaAluno(Aluno aluno) throws OKBusinessException {
		if (aluno.getId() == null) {
			try {
				CodigoSeguranca codigo = this.codigoSegurancaBean.pesquisaPorCodigo(aluno.getCodigoSeguranca().getCodigo());
				if (!codigo.getTurma().equals(aluno.getTurma()) || !codigo.getAtivo()) {
					throw new OKBusinessException();
				}
				codigo.setAtivo(false);
				aluno.setCodigoSeguranca(codigo);
			} catch (Exception e) {
				throw new OKBusinessException("{msg.erro.codigo.seguranca}", ":mainForm:codigoSeguranca");
			}
		}
	}

	/**
	 * Salva.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the usuario
	 * @throws Exception
	 *             the exception
	 */
	@Transactional(rollbackOn = Exception.class)
	public Usuario salva(Usuario usuario) throws Exception {
		this.preparaPerfilUsuario(usuario);
		this.usuarioBean.validaDuplicidadeEmail(usuario);
		this.usuarioBean.validaDuplicidadeLogin(usuario);
		this.usuarioBean.criptografaSenhaUsuario(usuario);
		this.usuarioBean.defineStatusUsuario(usuario);

		ProfilePicture profilePicture = this.manipulaFotoPerfilUsuario(usuario);
		usuario = this.usuarioBean.salva(usuario);
		usuario.setProfilePicture(profilePicture);
		return usuario;
	}

	/**
	 * Prepara perfil usuario.
	 *
	 * @param usuario
	 *            the usuario
	 */
	private void preparaPerfilUsuario(Usuario usuario) {
		if (usuario.getId() == null) {
			PerfilUsuario descricao = usuario.getPerfil().getDescricao();
			usuario.setPerfil(this.perfilBean.pesquisaPorDescricao(descricao));
		}
	}

	/**
	 * Manipula foto perfil usuario.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the profile picture
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private ProfilePicture manipulaFotoPerfilUsuario(Usuario usuario) throws IOException {
		Anexo foto = usuario.getFotoPerfil();
		ProfilePicture profilePicture = usuario.getProfilePicture();

		if (foto != null) {

			if (profilePicture != null && profilePicture.getPath() == null) {
				OKFileHandler.deleteFile(foto.getCaminhoArquivo());
				profilePicture = profilePicture.savePicture(foto.getCaminhoArquivo());

			} else if (profilePicture == null) {
				OKFileHandler.deleteFile(foto.getCaminhoArquivo());
				profilePicture = null;
				usuario.setFotoPerfil(null);
				this.anexoBean.remove(this.anexoBean.atualiza(foto));
			}

		} else if (profilePicture != null) {
			foto = this.criaAnexoFotoPerfil(profilePicture);
			profilePicture = profilePicture.savePicture(foto.getCaminhoArquivo());
			usuario.setFotoPerfil(foto);
			this.anexoBean.salva(foto);
		}

		return profilePicture;
	}

	/**
	 * Cria anexo foto perfil.
	 *
	 * @param profilePicture
	 *            the profile picture
	 * @return the anexo
	 */
	private Anexo criaAnexoFotoPerfil(ProfilePicture profilePicture) {
		String fileName = RandomStringUtils.randomAlphanumeric(OKConstants.VALOR_OITO);

		StringBuilder filePath = new StringBuilder(OKFileHandler.getDefaultFilePath());
		filePath.append(File.separator);
		filePath.append(fileName);
		filePath.append(OKConstants.PONTO_FINAL);
		filePath.append(profilePicture.getExtension());

		Anexo foto = new Anexo();
		foto.setCaminhoArquivo(filePath.toString());
		foto.setExtensaoArquivo(profilePicture.getExtension());
		foto.setNomeArquivo(profilePicture.getName());

		return foto;
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
	public Usuario preparaUsuarioPorPerfil(PerfilUsuario perfilUsuario, boolean inicializaDisciplinas) {
		return this.usuarioBean.preparaUsuarioPorPerfil(perfilUsuario, inicializaDisciplinas);
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
	public OKPaginatedList<Usuario> pesquisaUsuariosPaginados(Usuario usuario, int firstResult, int maxResult) {
		return this.usuarioBean.pesquisaUsuariosPaginados(usuario, firstResult, maxResult);
	}

	/**
	 * Pesquisa usuario por id.
	 *
	 * @param id
	 *            the id
	 * @return the usuario
	 */
	public Usuario pesquisaUsuarioPorId(Integer id) {
		return this.usuarioBean.pesquisaUsuarioPorId(id);
	}

	/**
	 * Prepara visualizacao perfil usuario.
	 *
	 * @param id
	 *            the id
	 * @return the usuario
	 */
	public Usuario preparaVisualizacaoPerfilUsuario(Integer id) {
		return this.usuarioBean.preparaVisualizacaoPerfilUsuario(id);
	}

	/**
	 * Altera status usuario.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the usuario
	 */
	public Usuario alteraStatusUsuario(Usuario usuario) {
		return this.usuarioBean.alteraStatusUsuario(usuario);
	}

	/**
	 * Redefine senha usuario.
	 *
	 * @param usuario
	 *            the usuario
	 * @return the usuario
	 */
	public Usuario redefineSenhaUsuario(Usuario usuario) {
		return this.usuarioBean.redefineSenhaUsuario(usuario);
	}

	/**
	 * Altera perfil professor.
	 *
	 * @param professor
	 *            the professor
	 * @param descricaoPerfil
	 *            the perfil usuario
	 * @return the professor
	 */
	public Professor alteraPerfilProfessor(Professor professor, PerfilUsuario descricaoPerfil) {
		Perfil novoPerfil = this.perfilBean.pesquisaPorDescricao(descricaoPerfil);
		return this.usuarioBean.alteraPerfilProfessor(professor, novoPerfil);
	}

	/**
	 * Pesquisa professores por disciplina.
	 *
	 * @param id
	 *            the id
	 * @return the list
	 */
	public List<Professor> pesquisaProfessoresPorDisciplina(Integer id) {
		return this.usuarioBean.pesquisaProfessoresPorDisciplina(id);
	}

	/**
	 * Lista nomes professores.
	 *
	 * @param nome
	 *            the nome
	 * @return the list
	 */
	public List<String> listaNomesProfessores(String nome) {
		return this.usuarioBean.listaNomesProfessores(nome);
	}

	/**
	 * Pesquisa por email.
	 *
	 * @param email
	 *            the email
	 * @return the usuario
	 */
	public Usuario pesquisaPorEmail(String email) {
		return this.usuarioBean.pesquisaPorEmail(email);
	}

	/**
	 * Atualiza.
	 *
	 * @param usuario
	 *            the usuario
	 */
	public void atualiza(Usuario usuario) {
		this.usuarioBean.atualiza(usuario);
	}

	/**
	 * Pesquisa logins usuario sistema.
	 *
	 * @return the list
	 */
	public List<String> pesquisaLoginsUsuariosAtivosSistema() {
		return this.usuarioBean.pesquisaLoginsUsuariosAtivosSistema();
	}
}
