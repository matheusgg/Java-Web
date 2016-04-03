package br.com.ok.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The persistent class for the tb_codigo_seguranca database table.
 * 
 */
@Entity
@Table(name = "tb_codigo_seguranca")
@NamedQueries({ @NamedQuery(name = "CodigoSeguranca.findAll", query = "SELECT c FROM CodigoSeguranca c"),
		@NamedQuery(name = "CodigoSeguranca.findByCodigo", query = "select c from CodigoSeguranca c where c.codigo = :codigo") })
@Data
@EqualsAndHashCode(of = "id")
public class CodigoSeguranca implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -122698188172633302L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_codigo")
	private Integer id;

	/** The codigo. */
	private String codigo;

	/** The ativo. */
	@Column(name = "fl_ativo")
	private Boolean ativo;

	/** The turma. */
	@ManyToOne
	@JoinColumn(name = "id_turma")
	private Turma turma;

}