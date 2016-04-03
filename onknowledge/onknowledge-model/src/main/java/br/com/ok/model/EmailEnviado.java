package br.com.ok.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the tb_email_enviado database table.
 * 
 */
@Entity
@Table(name = "tb_email_enviado")
@NamedQueries({
		@NamedQuery(name = "EmailEnviado.findAll", query = "SELECT e FROM EmailEnviado e"),
		@NamedQuery(name = "EmailEnviado.findEmailsEnviadosByRemetente", query = "select e from EmailEnviado e inner join e.email em where em.usuarioRemetente.id = :idRemetente order by em.dataEnvio desc") })
@AttributeOverride(name = "id", column = @Column(name = "id_email_enviado"))
@Getter
@Setter
public class EmailEnviado extends EmailUsuario {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7383271262965563369L;

	/** The destinatarios. */
	@ManyToMany
	@JoinTable(name = "tb_destinatario_email_enviado", joinColumns = { @JoinColumn(name = "id_email_enviado") }, inverseJoinColumns = { @JoinColumn(name = "id_destinatario") })
	private List<Usuario> destinatarios;

}