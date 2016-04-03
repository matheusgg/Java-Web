package br.com.ok.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;
import br.com.ok.model.enums.Sexo;

/**
 * The persistent class for the tb_email_recebido database table.
 * 
 */
@Entity
@Table(name = "tb_email_recebido")
@NamedQueries({
		@NamedQuery(name = "EmailRecebido.findAll", query = "SELECT e FROM EmailRecebido e"),
		@NamedQuery(name = "EmailRecebido.findEmailsCaixaEntradaByIdDestinatario", query = "select e from EmailRecebido e where e.usuarioDestinatario.id = :idDestinatario and e.diretorioEmail = null and e.lixo = false order by e.email.dataEnvio desc"),
		@NamedQuery(name = "EmailRecebido.findEmailsExcluidosByUsuario", query = "select e from EmailRecebido e where e.usuarioDestinatario.id = :idUsuario and e.lixo = true"),
		@NamedQuery(name = "EmailRecebido.findEmailsByDiretorioAndDestinatario", query = "select e from EmailRecebido e where e.diretorioEmail.id = :idDiretorio and e.usuarioDestinatario.id = :idDestinatario and e.lixo = false"),
		@NamedQuery(name = "EmailRecebido.deletaEmailsExcluidosByUsuario", query = "delete from EmailRecebido e where e.lixo = true and e.usuarioDestinatario.id = :idUsuario"),
		@NamedQuery(name = "EmailRecebido.countEmailsRecebidosByDiretorioAndUsuario", query = "select count(e) from EmailRecebido e where e.diretorioEmail.id = :idDiretorio and e.usuarioDestinatario.id = :idUsuario"),
		@NamedQuery(name = "EmailRecebido.findNovosEmailsCaixaEntradaByDestinatario", query = "select new EmailRecebido(e.id, e.lido, em.dataEnvio, em.assunto, em.usuarioRemetente.login, em.usuarioRemetente.sexo) from EmailRecebido e inner join e.email em where e.usuarioDestinatario.id = :idDestinatario and e.lixo = false order by em.dataEnvio desc") })
@AttributeOverride(name = "id", column = @Column(name = "id_email_recebido"))
@Getter
@Setter
@XmlRootElement
public class EmailRecebido extends EmailUsuario {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8324811639636292516L;

	/** The lixo. */
	@Column(name = "fl_lixo")
	private Boolean lixo;

	/** The lido. */
	@Column(name = "fl_lido")
	private Boolean lido;

	/** The diretorio. */
	@ManyToOne
	@JoinColumn(name = "id_diretorio")
	private DiretorioEmail diretorioEmail;

	/** The usuario. */
	@ManyToOne
	@JoinColumn(name = "id_usuario_destinatario")
	private Usuario usuarioDestinatario;

	/**
	 * Instantiates a new email recebido.
	 */
	public EmailRecebido() {

	}

	/**
	 * Instantiates a new email recebido.
	 *
	 * @param id
	 *            the id
	 * @param lido
	 *            the lido
	 * @param dataEnvio
	 *            the data envio
	 * @param assunto
	 *            the assunto
	 * @param loginRemetente
	 *            the login remetente
	 * @param sexoUsuarioRemetente
	 *            the sexo usuario remetente
	 */
	public EmailRecebido(Integer id, Boolean lido, Date dataEnvio, String assunto, String loginRemetente, Sexo sexoUsuarioRemetente) {
		this.lido = lido;
		super.setId(id);

		Usuario remetente = new Usuario();
		remetente.setLogin(loginRemetente);
		remetente.setSexo(sexoUsuarioRemetente);

		super.setEmail(new Email(dataEnvio, assunto, remetente));
	}

}