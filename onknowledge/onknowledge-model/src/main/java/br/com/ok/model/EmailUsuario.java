package br.com.ok.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The Class EmailUsuario.
 *
 * @author Matheus
 * @version 1.0 - 02/11/2014
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "email")
public class EmailUsuario implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2810272450852431607L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_email_enviado")
	private Integer id;

	/** The email. */
	@OneToOne
	@JoinColumn(name = "id_email")
	private Email email;

	/** The selecionado. */
	@Transient
	private boolean selecionado;

}
