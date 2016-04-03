package br.com.ok.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.servlet.http.Part;

import lombok.Getter;
import lombok.Setter;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.ok.business.TaskBean;
import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.facade.UsuarioFacade;
import br.com.ok.model.Aluno;
import br.com.ok.model.Professor;
import br.com.ok.model.Usuario;
import br.com.ok.model.enums.PerfilUsuario;
import br.com.ok.model.enums.StatusTarefa;
import br.com.ok.model.util.ProfilePicture;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.messages.OKMessages;
import br.com.ok.util.pagination.OKLazyDataModel;
import br.com.ok.view.base.OKBaseMB;
import br.com.ok.view.base.annotation.OnKnowledgeMB;

/**
 * The Class UsuarioMB.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@OnKnowledgeMB
public class UsuarioMB extends OKBaseMB {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1956946354252649435L;

	/** The usuario facade. */
	@Inject
	private UsuarioFacade usuarioFacade;

	/** The task bean. */
	@Inject
	private TaskBean taskBean;

	/** The usuario. */
	@Getter
	private Usuario usuario;

	/** The uploaded picture. */
	@Getter
	@Setter
	private Part uploadedPicture;

	/** The coords. */
	@Getter
	@Setter
	private String coords;

	/** The search data model. */
	@Getter
	private OKLazyDataModel<Usuario> usuariosDataModel;

	/** The usuario selecionado. */
	@Getter
	@Setter
	private Usuario usuarioSelecionado;

	/** The usuarios selecionados. */
	@Getter
	@Setter
	private List<Usuario> usuariosSelecionados;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		switch (super.getViewId()) {
			case OKConstants.PRETTY_SIGNUP:
				this.usuario = this.usuarioFacade.preparaUsuarioPorPerfil(PerfilUsuario.ALUNO, false);
				break;
			case OKConstants.PRETTY_TEACHER_SEARCH:
				this.usuario = this.usuarioFacade.preparaUsuarioPorPerfil(PerfilUsuario.PROFESSOR, true);
				this.preparaPaginaPesquisa();
				break;
			case OKConstants.PRETTY_STUDENT_SEARCH:
				this.usuario = this.usuarioFacade.preparaUsuarioPorPerfil(PerfilUsuario.ALUNO, true);
				this.preparaPaginaPesquisa();
				break;
			case OKConstants.PRETTY_ADMIN_SEARCH:
				this.usuario = this.usuarioFacade.preparaUsuarioPorPerfil(PerfilUsuario.ADMIN, false);
				this.preparaPaginaPesquisa();
				break;
			case OKConstants.PRETTY_EDIT_PROFILE:
				this.usuario = super.getSecurityContext().getLoggedUser();
				break;
			case OKConstants.PRETTY_VIEW_PROFILE:
				this.preparaPaginaVisualizacaoPerfil();
				break;
			default:
				this.usuario = this.usuarioFacade.preparaUsuarioPorPerfil(PerfilUsuario.ADMIN, false);
		}
	}

	/**
	 * Prepara pagina pesquisa.
	 */
	private void preparaPaginaPesquisa() {
		this.usuarioSelecionado = new Usuario();
		this.usuariosSelecionados = new ArrayList<>();
		this.usuariosDataModel = new OKLazyDataModel<>((firstResult, maxResult) -> {
			if (super.isSearchEvent()) {
				return this.usuarioFacade.pesquisaUsuariosPaginados(this.usuario, firstResult, maxResult);
			} else {
				return null;
			}
		});
	}

	/**
	 * Prepara pagina visualizacao perfil.
	 */
	private void preparaPaginaVisualizacaoPerfil() {
		Integer userId = (Integer) Faces.getFlash().get(OKConstants.USER_ID_KEY);
		if (userId != null) {
			this.usuario = this.usuarioFacade.preparaVisualizacaoPerfilUsuario(userId);
		} else {
			super.getPrettyRedirector().redirect(Faces.getContext(), OKConstants.PRETTY_STUDENT_SEARCH);
		}
	}

	/**
	 * Visualiza perfil usuario selecionado.
	 *
	 * @param id
	 *            the id
	 * @return the string
	 */
	public String visualizaPerfilUsuarioSelecionado(Integer id) {
		Faces.getFlash().put(OKConstants.USER_ID_KEY, id);
		return OKConstants.PRETTY_VIEW_PROFILE;
	}

	/**
	 * Altera status usuario.
	 */
	public void alteraStatusUsuario() {
		this.usuario = this.usuarioFacade.alteraStatusUsuario(this.usuario);
		this.usuario = this.usuarioFacade.preparaVisualizacaoPerfilUsuario(this.usuario.getId());
		OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getFormattedMessage("msg.sucesso.status.alterado", this.usuario.getNome()),
				OKMessages.Severity.SUCCESS);
	}

	/**
	 * Redefine senha usuario.
	 */
	public void redefineSenhaUsuario() {
		this.usuario = this.usuarioFacade.redefineSenhaUsuario(this.usuario);
		this.usuario = this.usuarioFacade.preparaVisualizacaoPerfilUsuario(this.usuario.getId());
		OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getFormattedMessage("msg.sucesso.redefinicao.senha", this.usuario.getNome()),
				OKMessages.Severity.SUCCESS);
	}

	/**
	 * Altera perfil professor.
	 */
	public void alteraPerfilProfessor(ValueChangeEvent event) {
		Faces.renderResponse();
		PerfilUsuario perfilUsuario = (PerfilUsuario) event.getNewValue();
		this.usuario = this.usuarioFacade.alteraPerfilProfessor((Professor) this.usuario, perfilUsuario);
		this.usuario = this.usuarioFacade.preparaVisualizacaoPerfilUsuario(this.usuario.getId());
		OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getFormattedMessage("msg.sucesso.alteracao.perfil.professor", this.usuario.getNome()),
				OKMessages.Severity.SUCCESS);
	}

	/**
	 * Altera tipo usuario.
	 */
	public void alteraTipoUsuario(boolean inicializaDisciplinas) {
		this.usuario = this.usuarioFacade.preparaUsuarioPorPerfil(this.usuario.getPerfil().getDescricao(), inicializaDisciplinas);
		super.getRequestContext().reset(":mainForm");
	}

	/**
	 * Salva usuario.
	 *
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String salvaUsuario() throws Exception {
		this.usuario = this.usuarioFacade.salva(this.usuario);

		if (OKConstants.PRETTY_USER_SIGNUP.equals(super.getViewId())) {
			switch (this.usuario.getPerfil().getDescricao()) {
				case PROFESSOR:
					OKMessages.showBoxNotification("{label.sucesso}", "{msg.sucesso.professor.cadastrado}", OKMessages.Severity.SUCCESS);
					break;
				case COORDENADOR:
					OKMessages.showBoxNotification("{label.sucesso}", "{msg.sucesso.coordenador.cadastrado}", OKMessages.Severity.SUCCESS);
					break;
				default:
					OKMessages.showBoxNotification("{label.sucesso}", "{msg.sucesso.usuario.cadastrado}", OKMessages.Severity.SUCCESS);
					return OKConstants.PRETTY_ADMIN_SEARCH;
			}
		} else {
			this.usuario.setTarefasRecentes(this.taskBean.pesquisaTarefasRecentesPorUsuarioStatus(this.usuario.getId(), StatusTarefa.EM_ANDAMENTO));
			super.getSecurityContext().setLoggedUser(this.usuario);
			OKMessages.showBoxNotification("{label.sucesso}", "{msg.sucesso.alteracao.perfil}", OKMessages.Severity.SUCCESS);
			return OKConstants.PRETTY_START;
		}

		return OKConstants.PRETTY_TEACHER_SEARCH;
	}

	/**
	 * Salva aluno.
	 *
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String salvaAluno() throws Exception {
		this.usuario = this.usuarioFacade.salvaAluno((Aluno) this.usuario);

		switch (super.getViewId()) {
			case OKConstants.PRETTY_SIGNUP:
				Messages.add(FacesMessage.SEVERITY_INFO, null, OKMessages.getMessage("msg.sucesso.aluno.cadastrado"));
				return OKConstants.PRETTY_LOGIN;
			default:
				this.usuario.setTarefasRecentes(this.taskBean.pesquisaTarefasRecentesPorUsuarioStatus(this.usuario.getId(), StatusTarefa.EM_ANDAMENTO));
				super.getSecurityContext().setLoggedUser(this.usuario);
				OKMessages.showBoxNotification("{label.sucesso}", "{msg.sucesso.alteracao.perfil}", OKMessages.Severity.SUCCESS);
				return OKConstants.PRETTY_START;
		}
	}

	/**
	 * Upload profile picture.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void uploadProfilePicture() throws IOException {
		try {
			this.usuario.setProfilePicture(new ProfilePicture(this.uploadedPicture));
			super.getRequestContext().update("cropArea");
			super.getRequestContext().execute("createCropper();");
		} catch (Exception e) {
			throw new OKBusinessException(e.getMessage(), e);
		}
	}

	/**
	 * Crop profile picture.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void cropProfilePicture() throws IOException {
		String[] coordenadas = this.coords.split(OKConstants.UNDERSCORE);
		int x = Integer.valueOf(coordenadas[0]);
		int y = Integer.valueOf(coordenadas[1]);
		int width = Integer.valueOf(coordenadas[2]);
		int height = Integer.valueOf(coordenadas[3]);

		ProfilePicture croppedProfilePicture = this.usuario.getProfilePicture().getSubPicture(x, y, width, height);
		this.usuario.setProfilePicture(croppedProfilePicture);
		super.getRequestContext().execute("$('#cropperModal').modal('hide')");
	}

	/**
	 * Seleciona professores.
	 *
	 * @return the string
	 */
	public String selecionaProfessores() {
		if (this.usuariosSelecionados != null && !this.usuariosSelecionados.isEmpty()) {
			Faces.setFlashAttribute(OKConstants.PROFESSORES_SELECIONADOS_KEY, this.usuariosSelecionados);
		} else {
			Faces.setFlashAttribute(OKConstants.PROFESSORES_SELECIONADOS_KEY, this.usuarioSelecionado);
		}
		return super.returnToCallerPage();
	}
}
