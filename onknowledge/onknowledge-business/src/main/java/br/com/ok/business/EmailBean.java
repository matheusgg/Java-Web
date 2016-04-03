package br.com.ok.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.StringUtils;

import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.model.Anexo;
import br.com.ok.model.DiretorioEmail;
import br.com.ok.model.Email;
import br.com.ok.model.EmailEnviado;
import br.com.ok.model.EmailRecebido;
import br.com.ok.model.EmailUsuario;
import br.com.ok.model.Usuario;
import br.com.ok.model.dao.EmailDAO;
import br.com.ok.model.util.ProfilePicture;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.email.OKMailTemplateEngine;
import br.com.ok.util.logging.OKLogManager;
import br.com.ok.util.messages.OKMessages;

/**
 * The Class EmailBean.
 *
 * @author Fabricio
 * @version 1.0 - 18/10/2014
 */
@Stateless
public class EmailBean implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = -1147168417602550466L;

	/** The session. */
	@Resource(name = "java:/OnKnowledge")
	private Session session;

	/** The executor service. */
	@Resource
	private ManagedExecutorService executorService;

	/** The log manager. */
	@Inject
	private OKLogManager logManager;

	/** The email dao. */
	@Inject
	private EmailDAO emailDAO;

	/** The usuario bean. */
	@Inject
	private UsuarioBean usuarioBean;

	/** The anexo bean. */
	@Inject
	private AnexoBean anexoBean;

	/**
	 * Envia email externo.
	 *
	 * @param mensagem
	 *            the mensagem
	 * @param assunto
	 *            the assunto
	 * @param destinatarios
	 *            the destinatarios
	 * @param destinatariosOcultos
	 *            the destinatarios ocultos
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void enviaEmailExterno(String mensagem, String assunto, List<String> destinatarios, List<String> destinatariosOcultos) {
		this.executorService.submit(() -> {
			try {
				MimeBodyPart content = new MimeBodyPart();
				content.setContentLanguage(new String[] { OKConstants.UTF_8_ENCODING, OKConstants.ISO_8859_1_ENCODING });
				content.setContent(mensagem, OKConstants.TEXT_HTML_CONTENT_TYPE);

				MimeMessage message = new MimeMessage(this.session);
				message.setRecipients(RecipientType.TO, this.preparaDestinatariosEmailExterno(destinatarios));
				message.setRecipients(RecipientType.CC, this.preparaDestinatariosEmailExterno(destinatariosOcultos));
				message.setSubject(assunto);
				message.setSentDate(new Date());
				message.setContent(new MimeMultipart(content));

				Transport.send(message);
			} catch (Exception e) {
				this.logManager.getLogger().log(Level.SEVERE, e.getMessage(), e);
			}
		});
	}

	/**
	 * Prepara destinatarios email externo.
	 *
	 * @param destinatarios
	 *            the destinatarios
	 * @return the internet address[]
	 * @throws AddressException
	 *             the address exception
	 */
	private InternetAddress[] preparaDestinatariosEmailExterno(List<String> destinatarios) throws AddressException {
		List<InternetAddress> dests = new ArrayList<>();
		if (destinatarios != null) {
			for (String dest : destinatarios) {
				dests.add(new InternetAddress(dest));
			}
		}
		return dests.toArray(new InternetAddress[0]);
	}

	/**
	 * Pesquisa diretorios email usuario.
	 *
	 * @param idUsuario
	 *            the id usuario
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<DiretorioEmail> pesquisaDiretoriosEmailUsuario(Integer idUsuario) {
		return this.emailDAO.findDiretoriosEmailByIdUsuario(idUsuario);
	}

	/**
	 * Pesquisa emails caixa entrada destinatario.
	 *
	 * @param idDestinatario
	 *            the id destinatario
	 * @return the list
	 */
	public List<EmailRecebido> pesquisaEmailsCaixaEntradaDestinatario(Integer idDestinatario) {
		List<EmailRecebido> emails = this.emailDAO.findEmailsCaixaEntradaByIdDestinatario(idDestinatario);
		emails.forEach(email -> this.emailDAO.initProxy(email.getEmail().getAnexos()));
		return emails;
	}

	/**
	 * Pesquisa emails enviados by remetente.
	 *
	 * @param idRemetente
	 *            the id remetente
	 * @return the list
	 */
	public List<EmailEnviado> pesquisaEmailsEnviadosByRemetente(Integer idRemetente) {
		List<EmailEnviado> emails = this.emailDAO.findEmailsEnviadosByRemetente(idRemetente);
		emails.forEach(email -> {
			this.emailDAO.initProxy(email.getDestinatarios());
			this.emailDAO.initProxy(email.getEmail().getAnexos());
		});
		return emails;
	}

	/**
	 * Pesquisa emails excluidos usuario.
	 *
	 * @param idUsuario
	 *            the id usuario
	 * @return the list
	 */
	public List<EmailRecebido> pesquisaEmailsExcluidosUsuario(Integer idUsuario) {
		List<EmailRecebido> emails = this.emailDAO.findEmailsExcluidosByUsuario(idUsuario);
		emails.forEach(email -> this.emailDAO.initProxy(email.getEmail().getAnexos()));
		return emails;
	}

	/**
	 * Pesquisa emails by diretorio and destinatario.
	 *
	 * @param idDiretorio
	 *            the id diretorio
	 * @param idDestinatario
	 *            the id destinatario
	 * @return the list
	 */
	public List<EmailRecebido> pesquisaEmailsByDiretorioAndDestinatario(Integer idDiretorio, Integer idDestinatario) {
		List<EmailRecebido> emails = this.emailDAO.findEmailsByDiretorioAndDestinatario(idDiretorio, idDestinatario);
		emails.forEach(email -> this.emailDAO.initProxy(email.getEmail().getAnexos()));
		return emails;
	}

	/**
	 * Envia mensagem email.
	 *
	 * @param email
	 *            the email
	 * @param destinatarios
	 *            the destinatarios
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	public void enviaMensagemEmail(Email email, String destinatarios) throws OKBusinessException {
		this.validaCamposObrigatoriosEmail(email, destinatarios);

		String[] destinatariosEmail = destinatarios.split(OKConstants.VIRGULA);
		List<Usuario> usuariosDestinatarios = new ArrayList<>();

		for (String login : destinatariosEmail) {
			usuariosDestinatarios.add(this.usuarioBean.pesquisaUsuarioPorLogin(login));
		}

		email.setDataEnvio(new Date());

		this.anexoBean.salvaArquivosAnexosEmDisco(email.getAnexos());
		this.emailDAO.save(email);

		EmailEnviado emailEnviado = new EmailEnviado();
		emailEnviado.setEmail(email);
		emailEnviado.setDestinatarios(usuariosDestinatarios);
		this.emailDAO.salvaEmailUsuario(emailEnviado);

		this.salvaEmailsRecebidos(email, usuariosDestinatarios);
		this.enviaMensagemDestinatariosExternos(usuariosDestinatarios, email);
	}

	/**
	 * Salva emails recebidos.
	 *
	 * @param email
	 *            the email
	 * @param usuariosDestinatarios
	 *            the usuarios destinatarios
	 */
	private void salvaEmailsRecebidos(Email email, List<Usuario> usuariosDestinatarios) {
		EmailRecebido emailRecebido = null;
		for (Usuario usuarioDestinatario : usuariosDestinatarios) {
			emailRecebido = new EmailRecebido();
			emailRecebido.setEmail(email);
			emailRecebido.setLido(false);
			emailRecebido.setLixo(false);
			emailRecebido.setUsuarioDestinatario(usuarioDestinatario);
			this.emailDAO.salvaEmailUsuario(emailRecebido);
		}
	}

	/**
	 * Envia mensagem destinatarios externos.
	 *
	 * @param destinatarios
	 *            the destinatarios
	 * @param email
	 *            the email
	 */
	private void enviaMensagemDestinatariosExternos(List<Usuario> destinatarios, Email email) {
		Map<String, Object> params = new HashMap<>();
		params.put("titulo", OKMessages.getFormattedMessage("msg.recebimento.mensagem", email.getUsuarioRemetente().getLogin()));
		params.put("msg", email.getMensagem());

		String mensagemEmail = OKMailTemplateEngine.loadMessageFromTamplate("senha/mensagemEmail.vm", params);
		List<String> enderecosDestinatarios = destinatarios.stream().filter(usuario -> usuario.getRecebeNotificacoesPorEmail()).map(usuario -> usuario.getEmail())
				.collect(Collectors.toList());
		this.enviaEmailExterno(mensagemEmail, OKConstants.ONKNOWLEDGE, enderecosDestinatarios, null);
	}

	/**
	 * Valida campos obrigatorios email.
	 *
	 * @param email
	 *            the email
	 * @param destinatarios
	 *            the destinatarios
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	private void validaCamposObrigatoriosEmail(Email email, String destinatarios) throws OKBusinessException {
		String[] destinatariosEmail = destinatarios.split(OKConstants.VIRGULA);

		if (StringUtils.isBlank(destinatarios) || destinatariosEmail.length == OKConstants.VALOR_ZERO) {
			throw new OKBusinessException("{msg.validacao.qtd.minima.destinatarios}");
		}

		if (StringUtils.isBlank(email.getAssunto())) {
			throw new OKBusinessException("{msg.validacao.assunto.email.obrigatorio}");
		}

		if (StringUtils.isBlank(email.getMensagem())) {
			throw new OKBusinessException("{msg.validacao.texto.email.obrigatorio}");
		}
	}

	/**
	 * Atualiza email usuario.
	 *
	 * @param emailUsuario
	 *            the email usuario
	 */
	public void atualizaEmailUsuario(EmailUsuario emailUsuario) {
		this.emailDAO.atualizaEmailUsuario(emailUsuario);
	}

	/**
	 * Removes the email usuario.
	 *
	 * @param emailUsuario
	 *            the email usuario
	 */
	public void removeEmailUsuario(EmailUsuario emailUsuario) {
		this.emailDAO.removeEmailUsuario(emailUsuario);
	}

	/**
	 * Deleta emails excluidos by usuario.
	 *
	 * @param idUsuario
	 *            the id usuario
	 */
	public void deletaEmailsExcluidosByUsuario(Integer idUsuario) {
		this.emailDAO.deletaEmailsExcluidosByUsuario(idUsuario);
	}

	/**
	 * Salva diretorio email.
	 *
	 * @param diretorioEmail
	 *            the diretorio email
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	public void salvaDiretorioEmail(DiretorioEmail diretorioEmail) throws OKBusinessException {
		Long count = this.emailDAO.countDiretorioEmailByNomeAndUsuario(diretorioEmail.getNome(), diretorioEmail.getUsuario().getId());
		if (count > OKConstants.VALOR_ZERO) {
			throw new OKBusinessException("{msg.validacao.pasta.existente}");
		}
		this.emailDAO.salvaDiretorioEmail(diretorioEmail);
	}

	/**
	 * Exclui diretorio email usuario.
	 *
	 * @param diretorioEmail
	 *            the diretorio email
	 * @throws OKBusinessException
	 *             the OK business exception
	 */
	public void excluiDiretorioEmailUsuario(DiretorioEmail diretorioEmail) throws OKBusinessException {
		Long countEmails = this.emailDAO.countEmailsRecebidosByDiretorioAndUsuario(diretorioEmail.getId(), diretorioEmail.getUsuario().getId());
		if (countEmails > OKConstants.VALOR_ZERO) {
			throw new OKBusinessException("{msg.validacao.pasta.nao.vazia}");
		}
		this.emailDAO.excluiDiretorioEmail(diretorioEmail);
	}

	/**
	 * Pesquisa novos emails recebidos por destinatario.
	 *
	 * @param idDestinatario
	 *            the id destinatario
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<EmailRecebido> pesquisaNovosEmailsRecebidosPorDestinatario(Integer idDestinatario) {
		List<EmailRecebido> emails = this.emailDAO.findNovosEmailsCaixaEntradaByDestinatario(idDestinatario);
		emails.forEach(email -> {
			Usuario remetente = email.getEmail().getUsuarioRemetente();
			Anexo anexo = this.usuarioBean.recuperaFotoPerfilUsuario(remetente.getLogin());
			if (anexo != null) {
				remetente.setProfilePicture(new ProfilePicture(anexo));
			}
		});
		return emails;
	}

	/**
	 * Pesquisa email recebido por id.
	 *
	 * @param idEmailRecebido
	 *            the id email recebido
	 * @return the email recebido
	 */
	public EmailRecebido pesquisaEmailRecebidoPorId(Integer idEmailRecebido) {
		EmailRecebido emailRecebido = null;
		try {
			emailRecebido = this.emailDAO.findEmailRecebidoById(idEmailRecebido);
			this.emailDAO.initProxy(emailRecebido.getEmail().getAnexos());
		} catch (Exception e) {
			this.logManager.getLogger().log(Level.SEVERE, e.getMessage(), e);
		}
		return emailRecebido;
	}
}
