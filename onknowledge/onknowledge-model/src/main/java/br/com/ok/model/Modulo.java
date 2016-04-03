package br.com.ok.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the tb_modulo database table.
 * 
 */
@Entity
@Table(name = "tb_modulo")
@NamedQueries({ @NamedQuery(name = "Modulo.findAll", query = "SELECT m FROM Modulo m"),
		@NamedQuery(name = "Modulo.findByIdDisciplina", query = "select m from Modulo m inner join m.disciplinas d where d.id = :id") })
@Data
@ToString(of = "nome")
@EqualsAndHashCode(of = "nome")
public class Modulo implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 731991210307732668L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_modulo")
	private Integer id;

	/** The nome. */
	private String nome;

	/** The atividades. */
	@OneToMany(mappedBy = "modulo")
	private List<Atividade> atividades;

	/** The disciplinas. */
	@ManyToMany(mappedBy = "modulos")
	private List<Disciplina> disciplinas;

	/** The novo modulo. */
	@Transient
	private Boolean novoModulo;

	/** The quantidade atividades. */
	@Transient
	private Long quantidadeQuestionarios;

}