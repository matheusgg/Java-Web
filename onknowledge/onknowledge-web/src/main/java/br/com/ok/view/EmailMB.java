package br.com.ok.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;

import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.facade.EmailFacade;
import br.com.ok.facade.UsuarioFacade;
import br.com.ok.model.Anexo;
import br.com.ok.model.DiretorioEmail;
import br.com.ok.model.Email;
import br.com.ok.model.EmailRecebido;
import br.com.ok.model.EmailUsuario;
import br.com.ok.model.Usuario;
import br.com.ok.model.enums.ModoVisualizacaoEmail;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.files.OKFileHandler;
import br.com.ok.util.messages.OKMessages;
import br.com.ok.view.base.OKBaseMB;
import br.com.ok.view.base.annotation.OnKnowledgeMB;

/**
 * The Class EmailMB.
 *
 * @author Matheus
 * @version 1.0 - 04/11/2014
 */
@OnKnowledgeMB
public class EmailMB extends OKBaseMB {

	private static final long serialVersionUID = 8181572013477047800L;

	/** The email facade. */
	@Inject
	private EmailFacade emailFacade;

	/** The usuario facade. */
	@Inject
	private UsuarioFacade usuarioFacade;

	/** The usuario logado. */
	private Usuario usuarioLogado;

	/** The emails usuario. */
	@Getter
	private List<? extends EmailUsuario> emailsUsuario;

	/** The diretorios email usuario. */
	@Getter
	private List<DiretorioEmail> diretoriosEmailUsuario;

	/** The destinatarios. */
	@Getter
	private List<String> destinatarios;

	/** The destinatarios selecionados. */
	@Getter
	@Setter
	private String destinatariosSelecionados;

	/** The email. */
	@Getter
	@Setter
	private Email email;

	/** The email usuario. */
	@Getter
	@Setter
	private EmailUsuario emailUsuario;

	/** The modo visualizacao email. */
	@Getter
	@Setter
	private ModoVisualizacaoEmail modoVisualizacaoEmail;

	/** The diretorio corrente. */
	@Getter
	@Setter
	private DiretorioEmail diretorioCorrente;

	/** The diretorio selecionado. */
	@Getter
	@Setter
	private DiretorioEmail diretorioSelecionado;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.usuarioLogado = super.getSecurityContext().getLoggedUser();
	}

	/**
	 * Verifica email recebido informado.
	 */
	public void recuperaEmailRecebidoInformado() {
		String idEmailRecebido = Faces.getRequestParameter(OKConstants.ID);
		if (StringUtils.isNotBlank(idEmailRecebido)) {
			this.emailUsuario = this.emailFacade.pesquisaEmailRecebidoPorId(Integer.valueOf(idEmailRecebido));
			this.email = this.emailUsuario.getEmail();
			if (this.emailUsuario != null) {
				this.preparaVisualizacaoEmail();
			}
		}
	}

	/**
	 * Pesquisa emails caixa entrada destinatario.
	 */
	public void pesquisaEmailsCaixaEntradaDestinatario() {
		if (Faces.getRequestParameter(OKConstants.ID) == null) {
			this.redefineEmailsEnviadosRecebidos();
			this.emailsUsuario = this.emailFacade.pesquisaEmailsCaixaEntradaDestinatario(this.usuarioLogado.getId());
			this.modoVisualizacaoEmail = ModoVisualizacaoEmail.CAIXA_ENTRADA;
		}
	}

	/**
	 * Pesquisa diretorios email usuario.
	 */
	public void pesquisaDiretoriosEmailUsuario() {
		this.diretoriosEmailUsuario = this.emailFacade.pesquisaDiretoriosEmailUsuario(this.usuarioLogado.getId());
	}

	/**
	 * Pesquisa emails enviados.
	 */
	public void pesquisaEmailsEnviados() {
		this.redefineEmailsEnviadosRecebidos();
		this.emailsUsuario = this.emailFacade.pesquisaEmailsEnviadosByRemetente(this.usuarioLogado.getId());
		this.modoVisualizacaoEmail = ModoVisualizacaoEmail.ENVIADOS;
	}

	/**
	 * Pesquisa emails excluidos usuario.
	 */
	public void pesquisaEmailsExcluidosUsuario() {
		this.redefineEmailsEnviadosRecebidos();
		this.emailsUsuario = this.emailFacade.pesquisaEmailsExcluidosUsuario(this.usuarioLogado.getId());
		this.modoVisualizacaoEmail = ModoVisualizacaoEmail.LIXEIRA;
	}

	/**
	 * Pesquisa emails by diretorio and destinatario.
	 */
	public void pesquisaEmailsByDiretorioAndDestinatario() {
		this.emailsUsuario = this.emailFacade.pesquisaEmailsByDiretorioAndDestinatario(this.diretorioCorrente.getId(), this.usuarioLogado.getId());
		this.modoVisualizacaoEmail = ModoVisualizacaoEmail.PASTAS;
		this.emailUsuario = null;
	}

	/**
	 * Prepara novo email.
	 */
	public void preparaNovoEmail() {
		this.redefineEmailsEnviadosRecebidos();

		this.destinatarios = this.usuarioFacade.pesquisaLoginsUsuariosAtivosSistema();
		this.modoVisualizacaoEmail = ModoVisualizacaoEmail.NOVO_EMAIL;

		this.email = new Email();
		this.email.setAnexos(new ArrayList<>());
		this.email.setUsuarioRemetente(super.getSecurityContext().getLoggedUser());

		super.getRequestContext().execute("initNewMailPage()");
	}

	/**
	 * Envia mensagem email.
	 */
	public void enviaMensagemEmail() {
		this.email.setUsuarioRemetente(this.usuarioLogado);
		this.emailFacade.enviaMensagemEmail(this.email, this.destinatariosSelecionados);
		OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getMessage("msg.sucesso.mensagem.enviada"), OKMessages.Severity.SUCCESS);
		this.pesquisaEmailsCaixaEntradaDestinatario();
	}

	/**
	 * Prepara visualizacao email.
	 */
	public void preparaVisualizacaoEmail() {
		if (this.emailUsuario instanceof EmailRecebido) {
			EmailRecebido emailRecebido = (EmailRecebido) this.emailUsuario;
			if (!emailRecebido.getLido()) {
				emailRecebido.setLido(true);
				this.emailFacade.atualizaEmailUsuario(this.emailUsuario);
			}
		}
		this.modoVisualizacaoEmail = ModoVisualizacaoEmail.LEITURA;
		super.getRequestContext().execute("initNewMailPage()");
	}

	/**
	 * Redefine emails enviados recebidos.
	 */
	private void redefineEmailsEnviadosRecebidos() {
		this.emailsUsuario = null;
		this.emailUsuario = null;
		this.email = null;
		this.diretorioCorrente = null;
		this.diretorioSelecionado = null;
		this.destinatarios = null;
		this.destinatariosSelecionados = null;
	}

	/**
	 * Exclui emails selecionados.
	 */
	public void excluiEmailsSelecionados() {
		if (ModoVisualizacaoEmail.CAIXA_ENTRADA.equals(this.modoVisualizacaoEmail) || ModoVisualizacaoEmail.LIXEIRA.equals(this.modoVisualizacaoEmail)
				|| ModoVisualizacaoEmail.PASTAS.equals(this.modoVisualizacaoEmail)) {

			if (ModoVisualizacaoEmail.LIXEIRA.equals(this.modoVisualizacaoEmail)) {
				this.excluiEmailsSelecionadosDefinitivamente();
				this.pesquisaEmailsExcluidosUsuario();

			} else {
				this.moveEmailsRecebidosSelecionadosParaLixeira();

				if (ModoVisualizacaoEmail.CAIXA_ENTRADA.equals(this.modoVisualizacaoEmail)) {
					this.pesquisaEmailsCaixaEntradaDestinatario();
				} else {
					this.pesquisaEmailsByDiretorioAndDestinatario();
				}
			}

		} else {
			this.excluiEmailsSelecionadosDefinitivamente();
			this.pesquisaEmailsEnviados();
		}
	}

	/**
	 * Exclui emails selecionados definitivamente.
	 */
	private void excluiEmailsSelecionadosDefinitivamente() {
		List<EmailUsuario> emails = this.emailsUsuario.stream().filter((EmailUsuario emailUsuario) -> emailUsuario.isSelecionado()).collect(Collectors.toList());
		if (!emails.isEmpty()) {
			emails.forEach((EmailUsuario emailUsuario) -> this.emailFacade.removeEmailUsuario(emailUsuario));
			OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getMessage("msg.sucesso.mensagens.excluidas"), OKMessages.Severity.SUCCESS);
		} else {
			throw new OKBusinessException("{msg.validacao.nenhum.email.selecionado}");
		}
	}

	/**
	 * Move emails recebidos selecionados para lixeira.
	 */
	private void moveEmailsRecebidosSelecionadosParaLixeira() {
		List<EmailUsuario> emails = this.emailsUsuario.stream().filter((EmailUsuario emailUsuario) -> emailUsuario.isSelecionado()).collect(Collectors.toList());
		if (!emails.isEmpty()) {
			emails.forEach((EmailUsuario emailUsuario) -> {
				EmailRecebido emailRecebido = (EmailRecebido) emailUsuario;
				emailRecebido.setLixo(true);
				emailRecebido.setDiretorioEmail(null);
				this.emailFacade.atualizaEmailUsuario(emailUsuario);
			});
			OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getMessage("msg.sucesso.mensagens.excluidas"), OKMessages.Severity.SUCCESS);
		} else {
			throw new OKBusinessException("{msg.validacao.nenhum.email.selecionado}");
		}
	}

	/**
	 * Esvazia lixeira.
	 */
	public void esvaziaLixeira() {
		this.emailFacade.deletaEmailsExcluidosByUsuario(this.usuarioLogado.getId());
		this.pesquisaEmailsCaixaEntradaDestinatario();
		OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getMessage("msg.sucesso.mensagens.excluidas2"), OKMessages.Severity.SUCCESS);
	}

	/**
	 * Prepara alteracao diretorio emails selecionados.
	 */
	public void preparaAlteracaoDiretorioEmailsSelecionados() {
		List<EmailUsuario> emails = this.emailsUsuario.stream().filter((EmailUsuario email) -> email.isSelecionado()).collect(Collectors.toList());
		if (!emails.isEmpty()) {
			super.getRequestContext().execute("$('#diretoriosUsuarioModal').modal('show');");
		} else {
			throw new OKBusinessException("{msg.validacao.nenhum.email.selecionado}");
		}
	}

	/**
	 * Move emails selecionados.
	 */
	public void moveEmailsSelecionados() {
		List<EmailUsuario> emails = this.emailsUsuario.stream().filter((EmailUsuario email) -> email.isSelecionado()).collect(Collectors.toList());
		if (!emails.isEmpty()) {
			emails.forEach((EmailUsuario emailUsuario) -> {
				EmailRecebido emailRecebido = (EmailRecebido) emailUsuario;
				emailRecebido.setLixo(false);
				emailRecebido.setDiretorioEmail(this.diretorioSelecionado);
				this.emailFacade.atualizaEmailUsuario(emailUsuario);
			});
		}

		OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getMessage("msg.sucesso.mensagens.movidas"), OKMessages.Severity.SUCCESS);
		this.preparaModoVisualizacaoPesquisaEmails();
	}

	/**
	 * Prepara cadastro novo diretorio email usuario.
	 */
	public void preparaCadastroNovoDiretorioEmailUsuario() {
		this.diretorioSelecionado = new DiretorioEmail();
		this.diretorioSelecionado.setUsuario(this.usuarioLogado);
		super.getRequestContext().execute("$('#novoDiretorioUsuarioModal').modal('show');");
	}

	/**
	 * Salva novo diretorio email usuario.
	 */
	public void salvaNovoDiretorioEmailUsuario() {
		if (StringUtils.isNotBlank(this.diretorioSelecionado.getNome())) {
			this.emailFacade.salvaDiretorioEmail(this.diretorioSelecionado);
			OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getFormattedMessage("msg.sucesso.cadastro.nova.pasta", this.diretorioSelecionado.getNome()),
					OKMessages.Severity.SUCCESS);
			this.pesquisaDiretoriosEmailUsuario();
			this.preparaModoVisualizacaoPesquisaEmails();
		} else {
			this.diretorioSelecionado = null;
		}
	}

	/**
	 * Exclui diretorio email usuario.
	 */
	public void excluiDiretorioEmailUsuario() {
		this.emailFacade.excluiDiretorioEmailUsuario(this.diretorioCorrente);
		OKMessages.showBoxNotification("{label.sucesso}", OKMessages.getMessage("msg.sucesso.remocao.pasta"), OKMessages.Severity.SUCCESS);
		this.pesquisaDiretoriosEmailUsuario();
		this.pesquisaEmailsCaixaEntradaDestinatario();
	}

	/**
	 * Prepara resposta email.
	 */
	public void preparaRespostaEmail() {
		Usuario remetente = this.email.getUsuarioRemetente();
		String assunto = this.email.getAssunto();
		this.preparaNovoEmail();
		this.destinatariosSelecionados = remetente.getLogin();
		this.email.setAssunto(assunto);
	}

	/**
	 * Prepara encaminhamento email.
	 */
	public void preparaEncaminhamentoEmail() {
		String assunto = this.email.getAssunto();
		String mensagem = this.email.getMensagem();

		List<Anexo> anexosOriginais = this.email.getAnexos();
		List<Anexo> anexos = new ArrayList<>();

		anexosOriginais.forEach(anexoOriginal -> {
			Anexo anexo = new Anexo();
			anexo.setNomeArquivo(anexoOriginal.getNomeArquivo());
			anexo.setExtensaoArquivo(anexoOriginal.getExtensaoArquivo());
			anexo.setFileData(OKFileHandler.loadFileToByteArray(anexoOriginal.getCaminhoArquivo()));
			anexos.add(anexo);
		});

		this.preparaNovoEmail();
		this.email.setAssunto(assunto);
		this.email.setMensagem(mensagem);
		this.email.setAnexos(anexos);
	}

	/**
	 * Prepara modo visualizacao pesquisa emails.
	 */
	private void preparaModoVisualizacaoPesquisaEmails() {
		switch (this.modoVisualizacaoEmail) {
			case CAIXA_ENTRADA:
				this.pesquisaEmailsCaixaEntradaDestinatario();
				break;

			case ENVIADOS:
				this.pesquisaEmailsEnviados();
				break;

			case LIXEIRA:
				this.pesquisaEmailsExcluidosUsuario();
				break;

			case PASTAS:
				this.pesquisaEmailsByDiretorioAndDestinatario();
				break;

			case LEITURA:
				this.preparaVisualizacaoEmail();
				break;

			case NOVO_EMAIL:
				this.preparaNovoEmail();
				break;
		}
	}
}
