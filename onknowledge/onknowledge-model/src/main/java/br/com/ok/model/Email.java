package br.com.ok.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the tb_email database table.
 * 
 */
@Entity
@Table(name = "tb_email")
@NamedQuery(name = "Email.findAll", query = "SELECT e FROM Email e")
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "assunto")
public class Email implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6443940850965000155L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_email")
	private Integer id;

	/** The data envio. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_envio")
	private Date dataEnvio;

	/** The assunto. */
	@Column(name = "assunto")
	private String assunto;

	/** The mensagem. */
	@Column(name = "txt_mensagem")
	private String mensagem;

	/** The usuario. */
	@ManyToOne
	@JoinColumn(name = "id_usuario_remetente")
	private Usuario usuarioRemetente;

	/** The emails enviados. */
	@ManyToMany
	@JoinTable(name = "tb_anexo_email", joinColumns = { @JoinColumn(name = "id_email") }, inverseJoinColumns = { @JoinColumn(name = "id_anexo") })
	private List<Anexo> anexos;

	/**
	 * Instantiates a new email.
	 */
	public Email() {

	}

	/**
	 * Instantiates a new email.
	 *
	 * @param dataEnvio
	 *            the data envio
	 * @param assunto
	 *            the assunto
	 * @param remetente
	 *            the remetente
	 */
	public Email(Date dataEnvio, String assunto, Usuario remetente) {
		this.dataEnvio = dataEnvio;
		this.assunto = assunto;
		this.usuarioRemetente = remetente;
	}

}