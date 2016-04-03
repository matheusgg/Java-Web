package br.com.ok.facade;

import java.util.List;

import javax.inject.Inject;

import br.com.ok.business.EmailBean;
import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.facade.base.OKGenericFacade;
import br.com.ok.model.DiretorioEmail;
import br.com.ok.model.Email;
import br.com.ok.model.EmailEnviado;
import br.com.ok.model.EmailRecebido;
import br.com.ok.model.EmailUsuario;

/**
 * The Class EmailFacade.
 *
 * @author Fabricio
 * @version 1.0 - 18/10/2014
 */
public class EmailFacade extends OKGenericFacade {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9021640121202915159L;

	/** The email bean. */
	@Inject
	private EmailBean emailBean;

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
	public void enviaEmailExterno(String mensagem, String assunto, List<String> destinatarios, List<String> destinatariosOcultos) {
		this.emailBean.enviaEmailExterno(mensagem, assunto, destinatarios, destinatariosOcultos);
	}

	/**
	 * Pesquisa diretorios email usuario.
	 *
	 * @param idUsuario
	 *            the id usuario
	 * @return the list
	 */
	public List<DiretorioEmail> pesquisaDiretoriosEmailUsuario(Integer idUsuario) {
		return this.emailBean.pesquisaDiretoriosEmailUsuario(idUsuario);
	}

	/**
	 * Pesquisa emails caixa entrada destinatario.
	 *
	 * @param idDestinatario
	 *            the id destinatario
	 * @return the list
	 */
	public List<EmailRecebido> pesquisaEmailsCaixaEntradaDestinatario(Integer idDestinatario) {
		return this.emailBean.pesquisaEmailsCaixaEntradaDestinatario(idDestinatario);
	}

	/**
	 * Pesquisa emails enviados by remetente.
	 *
	 * @param idRemetente
	 *            the id remetente
	 * @return the list
	 */
	public List<EmailEnviado> pesquisaEmailsEnviadosByRemetente(Integer idRemetente) {
		return this.emailBean.pesquisaEmailsEnviadosByRemetente(idRemetente);
	}

	/**
	 * Pesquisa emails excluidos usuario.
	 *
	 * @param idUsuario
	 *            the id usuario
	 * @return the list
	 */
	public List<EmailRecebido> pesquisaEmailsExcluidosUsuario(Integer idUsuario) {
		return this.emailBean.pesquisaEmailsExcluidosUsuario(idUsuario);
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
		return this.emailBean.pesquisaEmailsByDiretorioAndDestinatario(idDiretorio, idDestinatario);
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
		this.emailBean.enviaMensagemEmail(email, destinatarios);
	}

	/**
	 * Atualiza email usuario.
	 *
	 * @param emailUsuario
	 *            the email usuario
	 */
	public void atualizaEmailUsuario(EmailUsuario emailUsuario) {
		this.emailBean.atualizaEmailUsuario(emailUsuario);
	}

	/**
	 * Removes the email usuario.
	 *
	 * @param emailUsuario
	 *            the email usuario
	 */
	public void removeEmailUsuario(EmailUsuario emailUsuario) {
		this.emailBean.removeEmailUsuario(emailUsuario);
	}

	/**
	 * Deleta emails excluidos by usuario.
	 *
	 * @param idUsuario
	 *            the id usuario
	 */
	public void deletaEmailsExcluidosByUsuario(Integer idUsuario) {
		this.emailBean.deletaEmailsExcluidosByUsuario(idUsuario);
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
		this.emailBean.salvaDiretorioEmail(diretorioEmail);
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
		this.emailBean.excluiDiretorioEmailUsuario(diretorioEmail);
	}

	/**
	 * Pesquisa novos emails recebidos por destinatario.
	 *
	 * @param idDestinatario
	 *            the id destinatario
	 * @return the list
	 */
	public List<EmailRecebido> pesquisaNovosEmailsRecebidosPorDestinatario(Integer idDestinatario) {
		return this.emailBean.pesquisaNovosEmailsRecebidosPorDestinatario(idDestinatario);
	}

	/**
	 * Pesquisa email recebido por id.
	 *
	 * @param idEmailRecebido
	 *            the id email recebido
	 * @return the email recebido
	 */
	public EmailRecebido pesquisaEmailRecebidoPorId(Integer idEmailRecebido) {
		return this.emailBean.pesquisaEmailRecebidoPorId(idEmailRecebido);
	}
}
