package br.com.ok.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The persistent class for the tb_comentario database table.
 * 
 */
@Entity
@Table(name = "tb_comentario")
@NamedQueries({
	@NamedQuery(name = "Comentario.findAll", query = "SELECT c FROM Comentario c"),
	@NamedQuery(name = "Comentario.findByQuestao", query = "SELECT c FROM Comentario c where c.questao.id = :id order by c.dataEnvio desc"),
	@NamedQuery(name = "Comentario.findByAtividade", query = "SELECT c FROM Comentario c where c.atividade.id = :id order by c.dataEnvio desc")
})
@Data
@EqualsAndHashCode(of = "id")
public class Comentario implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 453262651396377021L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comentario")
	private Integer id;

	/** The descricao. */
	@Column(name = "ds_comentario")
	private String descricao;

	/** The data envio. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_envio")
	private Date dataEnvio;

	/** The usuario. */
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	/** The atividade. */
	@ManyToOne
	@JoinColumn(name = "id_atividade")
	private Atividade atividade;

	/** The questao. */
	@ManyToOne
	@JoinColumn(name = "id_questao")
	private Questao questao;

}