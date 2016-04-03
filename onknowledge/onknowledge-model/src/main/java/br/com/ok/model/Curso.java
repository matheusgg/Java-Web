package br.com.ok.model;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the tb_curso database table.
 * 
 */
@Entity
@Table(name = "tb_curso")
@NamedQueries({ @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
		@NamedQuery(name = "Curso.listByNome", query = "select c.nome from Curso c where c.nome like :nome"),
		@NamedQuery(name = "Curso.listAll", query = "select new Curso(c.id, c.nome) from Curso c"),
		@NamedQuery(name = "Curso.countByName", query = "select count(c) from Curso c where c.nome = :nome"),
		@NamedQuery(name = "Curso.countByNameIgnoringId", query = "select count(c) from Curso c where c.nome = :nome and c.id != :id"),
		@NamedQuery(name = "Curso.countByIdAndCoordenador", query = "select count(c) from Curso c where c.id = :id and c.coordenador.id = :idCoordenador") })
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "nome")
public class Curso implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4143238546012045247L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_curso")
	private Integer id;

	/** The nome. */
	private String nome;

	/** The turmas. */
	@OneToMany(mappedBy = "curso")
	private List<Turma> turmas;

	/** The professor. */
	@ManyToOne
	@JoinColumn(name = "id_coordenador")
	private Professor coordenador;

	/** The disciplinas. */
	@OrderBy("nome")
	@ManyToMany
	@JoinTable(name = "tb_curso_disciplina", joinColumns = { @JoinColumn(name = "id_curso") }, inverseJoinColumns = { @JoinColumn(name = "id_disciplina") })
	private List<Disciplina> disciplinas;

	/** The eventos. */
	@ManyToMany(mappedBy = "cursos")
	private List<Evento> eventos;

	/**
	 * Instantiates a new curso.
	 */
	public Curso() {

	}

	/**
	 * Instantiates a new curso.
	 *
	 * @param nome
	 *            the nome
	 */
	public Curso(String nome) {
		this.nome = nome;
	}

	/**
	 * Instantiates a new curso.
	 *
	 * @param id
	 *            the id
	 * @param nome
	 *            the nome
	 */
	public Curso(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
}