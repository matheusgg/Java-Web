package br.com.ok.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.NamedQueries;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.ok.model.enums.StatusTarefa;

/**
 * The persistent class for the tb_tarefa database table.
 * 
 */
@Entity
@Table(name = "tb_tarefa")
@NamedQueries({
		@NamedQuery(name = "Tarefa.findAll", query = "SELECT t FROM Tarefa t"),
		@NamedQuery(name = "Tarefa.findList", query = "SELECT t FROM Tarefa t where t.usuario.id=:usuario order by dataTarefa desc"),
		@NamedQuery(name = "Tarefa.findByUsuario", query = "SELECT t FROM Tarefa t where t.usuario.id = :idUsuario and t.statusTarefa = :statusTarefa order by dataTarefa desc") })
@Data
@EqualsAndHashCode(of = "id")
public class Tarefa implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7528445447847228325L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tarefa")
	private Integer id;

	/** The descricao. */
	@Column(name = "ds_tarefa")
	private String descricao;

	/** The data cadastro. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_tarefa")
	private Date dataTarefa;

	/** The nome. */
	private String nome;

	/** The status tarefa. */
	@Column(name = "tp_status")
	@Enumerated(EnumType.ORDINAL)
	private StatusTarefa statusTarefa;

	/** The usuario. */
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	/** The data tarefa original. */
	@Transient
	private Date dataTarefaOriginal;

}