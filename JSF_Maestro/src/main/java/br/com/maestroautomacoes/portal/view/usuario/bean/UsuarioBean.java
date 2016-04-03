package br.com.maestroautomacoes.portal.view.usuario.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.maestroautomacoes.portal.mail.MailProvider;
import br.com.maestroautomacoes.portal.model.dao.usuario.UsuarioDao;
import br.com.maestroautomacoes.portal.model.usuario.Usuario;
import br.com.maestroautomacoes.portal.security.SecurityManager;
import br.com.maestroautomacoes.portal.util.Outcome;
import br.com.maestroautomacoes.portal.util.SiteUtil;
import br.com.maestroautomacoes.portal.view.usuario.helper.UsuarioViewHelper;

@Named
@SessionScoped
public class UsuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5440350573583982266L;

	@Inject
	private UsuarioViewHelper viewHelper;

	@Inject
	private UsuarioDao usuarioDao;

	public String iniciarPaginaDetalhesUsuario() throws Exception {
		this.viewHelper.inicializar();
		this.viewHelper.setUsuario(SecurityManager.getUsuarioLogado());
		return Outcome.DETALHE_CLIENTE;
	}

	@PostConstruct
	public String iniciarPaginaCadastroUsuario() {
		this.viewHelper.inicializar();
		return Outcome.CADASTRO_CLIENTE;
	}

	/**
	 * Verifica os endereços informados e envia a senha do usuário para o email
	 * inserido.
	 */
	public void recuperaSenha() throws Exception {
		if (this.viewHelper.getEmail().equals(this.viewHelper.getConfirmacaoEmail())) {
			Usuario usuario = this.usuarioDao.recuperaUsuario(this.viewHelper.getEmail());
			if (usuario != null) {
				this.enviaSenhaUsuario(this.viewHelper.getEmail(), usuario.getSenha());
				this.viewHelper.inicializar();
				SiteUtil.addInfoModalMessage("msg_mensagem_enviada_sucesso", true);
			} else {
				SiteUtil.addGrowlMessage("msg_email_nao_cadastrado", FacesMessage.SEVERITY_ERROR, true);
			}
		} else {
			SiteUtil.addGrowlMessage("msg_emails_incompativeis", FacesMessage.SEVERITY_ERROR, true);
		}
	}

	/**
	 * Prepara a mensagem a ser enviada contendo a senha do usuário.
	 */
	private void enviaSenhaUsuario(String email, String senha) throws Exception {
		StringBuilder msg = new StringBuilder();
		msg.append(SiteUtil.getMessageFromProperty("label_usuario") + ": " + email);
		msg.append("<br />" + SiteUtil.getMessageFromProperty("label_senha") + ": " + senha);
		MailProvider.mandaEmailHtml(email, email, "", SiteUtil.getMessageFromProperty("msg_recuperacao_senha"), msg.toString(), null, false);
	}

	public String alterarCadastro() {
		usuarioDao.alterar(this.viewHelper.getUsuario());
		return null;
	}

	public String confirmarCadastro() throws Exception {
		this.usuarioDao.cadastar(this.viewHelper.getUsuario());
		MailProvider.mandaEmailHtml(getViewHelper().getUsuario().getNome(), getViewHelper().getUsuario().getEmail(), "Seja Bem vindo a Maestro!<br />",
				"Seu cadastro foi efetuado com sucesso!  Seguem seus dados:", "Usuario:  " + getViewHelper().getUsuario().getEmail() + "<br />" + "Senha:  "
						+ getViewHelper().getUsuario().getSenha() + "<br /> <br /> Agora você já está pronto pra Montar sua orquestra!<br /> ", null, false);
		this.viewHelper.inicializar();
		SiteUtil.addInfoModalMessage("msg_cadastrado_sucesso", true);
		return null;
	}

	/**
	 * @return the viewHelper
	 */
	public UsuarioViewHelper getViewHelper() {
		return viewHelper;
	}

}
