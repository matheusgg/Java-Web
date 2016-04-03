package br.com.ok.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.facade.EmailFacade;
import br.com.ok.facade.UsuarioFacade;
import br.com.ok.model.Usuario;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.email.OKMailTemplateEngine;
import br.com.ok.util.messages.OKMessages;
import br.com.ok.util.security.OKPasswordHandler;
import br.com.ok.view.base.OKBaseMB;
import br.com.ok.view.base.annotation.OnKnowledgeMB;

/**
 * The Class SenhaMB.
 *
 * @author Matheus
 * @version 1.0 - 18/10/2014
 */
@OnKnowledgeMB
public class SenhaMB extends OKBaseMB {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9158420641968927987L;

	/** The usuario facade. */
	@Inject
	private UsuarioFacade usuarioFacade;

	/** The email facade. */
	@Inject
	private EmailFacade emailFacade;

	/** The resposta seguranca. */
	@Getter
	@Setter
	private String respostaSeguranca;

	/** The usuario. */
	@Getter
	private Usuario usuario;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.usuario = new Usuario();
	}

	/**
	 * Verifica operacao enviar.
	 */
	public void verificaOperacaoEnviar() {
		if (this.usuario.getPerguntaSeguranca() == null) {
			this.pesquisaEmailUsuario();
		} else {
			this.verificaRespostaSeguranca();
		}
	}

	/**
	 * Pesquisa email usuario.
	 */
	private void pesquisaEmailUsuario() {
		try {
			this.usuario = this.usuarioFacade.pesquisaPorEmail(this.usuario.getEmail());
		} catch (Exception e) {
			throw new OKBusinessException("{msg.erro.email.nao.cadastrado}", "mainForm:email");
		}
	}

	/**
	 * Verifica resposta seguranca.
	 */
	private void verificaRespostaSeguranca() {
		String respostaUsuario = this.usuario.getRespostaPerguntaSeguranca();

		if (this.respostaSeguranca.equalsIgnoreCase(respostaUsuario)) {
			this.usuario.setSenha(OKPasswordHandler.generateDefaultPassword());
			this.usuarioFacade.atualiza(this.usuario);
			this.enviaMensagemRecuperacaoSenha();
			Messages.add(FacesMessage.SEVERITY_INFO, null, OKMessages.getMessage("msg.recuperacao.senha.enviada"));
			super.getPrettyRedirector().redirect(Faces.getContext(), "pretty:login");

		} else {
			throw new OKBusinessException("{msg.erro.resposta.incorreta}", "mainForm:resposta");
		}
	}

	/**
	 * Envia mensagem recuperacao senha.
	 */
	private void enviaMensagemRecuperacaoSenha() {
		String titulo = OKMessages.getFormattedMessage("label.recuperacao.senha");
		String msgRecuperacao = OKMessages.getFormattedMessage("msg.recuperacao.senha", this.usuario.getNome());
		String usuario = OKMessages.getFormattedMessage("msg.recuperacao.senha.usuario", this.usuario.getLogin());
		String senha = OKMessages.getFormattedMessage("msg.recuperacao.senha.usuario.senha", OKConstants.ONKNOWLEDGE);

		Map<String, Object> params = new HashMap<>();
		params.put("titulo", titulo);
		params.put("msg", msgRecuperacao);
		params.put("usuario", usuario);
		params.put("senha", senha);

		String mensagemEmail = OKMailTemplateEngine.loadMessageFromTamplate("senha/recuperacaoSenha.vm", params);
		this.emailFacade.enviaEmailExterno(mensagemEmail, titulo, Arrays.asList(this.usuario.getEmail()), null);
	}

	/**
	 * Limpa campos.
	 */
	public void limpaCampos() {
		this.init();
		super.getRequestContext().reset(":mainForm");
	}
}
