package br.com.ok.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.ok.model.DiretorioEmail;
import br.com.ok.model.Email;
import br.com.ok.model.EmailEnviado;
import br.com.ok.model.EmailRecebido;
import br.com.ok.model.EmailUsuario;
import br.com.ok.model.dao.base.OKGenericDAO;

/**
 * The Class EmailDAO.
 *
 * @author Matheus
 * @version 1.0 - 04/11/2014
 */
public class EmailDAO extends OKGenericDAO<Email> {

	/**
	 * The constant serialVersionUID.
	 */
	private static final long serialVersionUID = 8777808612102899354L;

	/**
	 * Salva email usuario.
	 *
	 * @param emailUsuario
	 *            the email usuario
	 */
	public void salvaEmailUsuario(EmailUsuario emailUsuario) {
		super.getEntityManager().persist(emailUsuario);
	}

	/**
	 * Atualiza email usuario.
	 *
	 * @param emailUsuario
	 *            the email usuario
	 * @return the email usuario
	 */
	public EmailUsuario atualizaEmailUsuario(EmailUsuario emailUsuario) {
		return super.getEntityManager().merge(emailUsuario);
	}

	/**
	 * Removes the email usuario.
	 *
	 * @param emailUsuario
	 *            the email usuario
	 */
	public void removeEmailUsuario(EmailUsuario emailUsuario) {
		super.getEntityManager().remove(this.atualizaEmailUsuario(emailUsuario));
	}

	/**
	 * Find diretorios email by id usuario.
	 *
	 * @param idUsuario
	 *            the id usuario
	 * @return the list
	 */
	public List<DiretorioEmail> findDiretoriosEmailByIdUsuario(Integer idUsuario) {
		TypedQuery<DiretorioEmail> query = super.getEntityManager().createNamedQuery("DiretorioEmail.findByIdUsuario", DiretorioEmail.class);
		query.setParameter("idUsuario", idUsuario);
		return query.getResultList();
	}

	/**
	 * Find emails caixa entrada by id destinatario.
	 *
	 * @param idDestinatario
	 *            the id destinatario
	 * @return the list
	 */
	public List<EmailRecebido> findEmailsCaixaEntradaByIdDestinatario(Integer idDestinatario) {
		TypedQuery<EmailRecebido> query = super.getEntityManager().createNamedQuery("EmailRecebido.findEmailsCaixaEntradaByIdDestinatario", EmailRecebido.class);
		query.setParameter("idDestinatario", idDestinatario);
		return query.getResultList();
	}

	/**
	 * Find emails enviados by remetente.
	 *
	 * @param idRemetente
	 *            the id remetente
	 * @return the list
	 */
	public List<EmailEnviado> findEmailsEnviadosByRemetente(Integer idRemetente) {
		TypedQuery<EmailEnviado> query = super.getEntityManager().createNamedQuery("EmailEnviado.findEmailsEnviadosByRemetente", EmailEnviado.class);
		query.setParameter("idRemetente", idRemetente);
		return query.getResultList();
	}

	/**
	 * Find emails excluidos by usuario.
	 *
	 * @param idUsuario
	 *            the id usuario
	 * @return the list
	 */
	public List<EmailRecebido> findEmailsExcluidosByUsuario(Integer idUsuario) {
		TypedQuery<EmailRecebido> query = super.getEntityManager().createNamedQuery("EmailRecebido.findEmailsExcluidosByUsuario", EmailRecebido.class);
		query.setParameter("idUsuario", idUsuario);
		return query.getResultList();
	}

	/**
	 * Find emails by diretorio and destinatario.
	 *
	 * @param idDiretorio
	 *            the id diretorio
	 * @param idDestinatario
	 *            the id destinatario
	 * @return the list
	 */
	public List<EmailRecebido> findEmailsByDiretorioAndDestinatario(Integer idDiretorio, Integer idDestinatario) {
		TypedQuery<EmailRecebido> query = super.getEntityManager().createNamedQuery("EmailRecebido.findEmailsByDiretorioAndDestinatario", EmailRecebido.class);
		query.setParameter("idDiretorio", idDiretorio);
		query.setParameter("idDestinatario", idDestinatario);
		return query.getResultList();
	}

	/**
	 * Deleta emails excluidos by usuario.
	 *
	 * @param idUsuario
	 *            the id usuario
	 */
	public void deletaEmailsExcluidosByUsuario(Integer idUsuario) {
		Query query = super.getEntityManager().createNamedQuery("EmailRecebido.deletaEmailsExcluidosByUsuario");
		query.setParameter("idUsuario", idUsuario);
		query.executeUpdate();
	}

	/**
	 * Salva diretorio email.
	 *
	 * @param diretorioEmail
	 *            the diretorio email
	 */
	public void salvaDiretorioEmail(DiretorioEmail diretorioEmail) {
		super.getEntityManager().persist(diretorioEmail);
	}

	/**
	 * Exclui diretorio email.
	 *
	 * @param diretorioEmail
	 *            the diretorio email
	 */
	public void excluiDiretorioEmail(DiretorioEmail diretorioEmail) {
		EntityManager em = super.getEntityManager();
		em.remove(em.merge(diretorioEmail));
	}

	/**
	 * Count diretorio email by nome and usuario.
	 *
	 * @param nome
	 *            the nome
	 * @param idUsuario
	 *            the id usuario
	 * @return the long
	 */
	public Long countDiretorioEmailByNomeAndUsuario(String nome, Integer idUsuario) {
		TypedQuery<Long> query = super.getEntityManager().createNamedQuery("DiretorioEmail.countByNomeAndUsuario", Long.class);
		query.setParameter("nome", nome);
		query.setParameter("idUsuario", idUsuario);
		return query.getSingleResult();
	}

	/**
	 * Count emails recebidos by diretorio and usuario.
	 *
	 * @param idDiretorio
	 *            the id diretorio
	 * @param idUsuario
	 *            the id usuario
	 * @return the long
	 */
	public Long countEmailsRecebidosByDiretorioAndUsuario(Integer idDiretorio, Integer idUsuario) {
		TypedQuery<Long> query = super.getEntityManager().createNamedQuery("EmailRecebido.countEmailsRecebidosByDiretorioAndUsuario", Long.class);
		query.setParameter("idDiretorio", idDiretorio);
		query.setParameter("idUsuario", idUsuario);
		return query.getSingleResult();
	}

	/**
	 * Find novos emails caixa entrada by destinatario.
	 *
	 * @param idDestinatario
	 *            the id destinatario
	 * @return the list
	 */
	public List<EmailRecebido> findNovosEmailsCaixaEntradaByDestinatario(Integer idDestinatario) {
		TypedQuery<EmailRecebido> query = super.getEntityManager().createNamedQuery("EmailRecebido.findNovosEmailsCaixaEntradaByDestinatario", EmailRecebido.class);
		query.setParameter("idDestinatario", idDestinatario);
		return query.getResultList();
	}

	/**
	 * Find email recebido by id.
	 *
	 * @param idEmailRecebido
	 *            the id email recebido
	 * @return the email recebido
	 */
	public EmailRecebido findEmailRecebidoById(Integer idEmailRecebido) {
		return super.getEntityManager().find(EmailRecebido.class, idEmailRecebido);
	}

}
