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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the tb_disciplina database table.
 * 
 */
@Entity
@Table(name = "tb_disciplina")
@NamedQueries({
		@NamedQuery(name = "Disciplina.findAll", query = "SELECT d FROM Disciplina d"),
		@NamedQuery(name = "Disciplina.listByNome", query = "select new Disciplina(d.codigo, d.nome) from Disciplina d where d.nome like :nome"),
		@NamedQuery(name = "Disciplina.findAllDisciplinasExcludingId", query = "select d from Disciplina d where d.id != :id"),
		@NamedQuery(name = "Disciplina.findCodigosByAluno", query = "select d.codigo from Disciplina d inner join d.alunos a where a.id = :id"),
		@NamedQuery(name = "Disciplina.findByIdProfessor", query = "select d from Disciplina d inner join d.professores p where p.id = :id"),
		@NamedQuery(name = "Disciplina.findByIdAluno", query = "select d from Disciplina d inner join d.alunos a where a.id = :id"),
		@NamedQuery(name = "Disciplina.findByTurma", query = "select d from Disciplina d inner join d.cursos c inner join c.turmas t where t.id = :id"),
		@NamedQuery(name = "Disciplina.countByIdAndProfessor", query = "select count(d) from Disciplina d inner join d.professores p where d.id = :idDisciplina and p.id = :idProfessor"),
		@NamedQuery(name = "Disciplina.countByIdAndCoordenadorCurso", query = "select count(d) from Disciplina d inner join d.cursos c where d.id = :idDisciplina and c.coordenador.id = :idCoordenador") })
@Data
@ToString(of = "nome")
@EqualsAndHashCode(of = "id")
public class Disciplina implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7494943625828351563L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_disciplina")
	private Integer id;

	/** The codigo. */
	@Column(name = "cod_disciplina")
	private String codigo;

	/** The data cadastro. */
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_cadastro")
	private Date dataCadastro;

	/** The data encerramento. */
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_encerramento")
	private Date dataEncerramento;

	/** The data inicio. */
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_inicio")
	private Date dataInicio;

	/** The nome. */
	private String nome;

	/** The alunos. */
	@ManyToMany(mappedBy = "disciplinas")
	private List<Aluno> alunos;

	/** The cursos. */
	@ManyToMany(mappedBy = "disciplinas")
	private List<Curso> cursos;

	/** The eventos. */
	@ManyToMany(mappedBy = "disciplinas")
	private List<Evento> eventos;

	/** The modulos. */
	@OrderBy("nome")
	@ManyToMany
	@JoinTable(name = "tb_modulo_disciplina", joinColumns = { @JoinColumn(name = "id_disciplina") }, inverseJoinColumns = { @JoinColumn(name = "id_modulo") })
	private List<Modulo> modulos;

	/** The professores. */
	@ManyToMany
	@JoinTable(name = "tb_professor_disciplina", joinColumns = { @JoinColumn(name = "id_disciplina") }, inverseJoinColumns = { @JoinColumn(name = "id_professor") })
	private List<Professor> professores;

	/** The data inicio original. */
	@Transient
	private Date dataInicioOriginal;

	/** The porcentagem questoes respondidas. */
	@Transient
	private double porcentagemQuestoesRespondidas;

	/**
	 * Instantiates a new disciplina.
	 */
	public Disciplina() {

	}

	/**
	 * Instantiates a new disciplina.
	 *
	 * @param codigo
	 *            the codigo
	 * @param nome
	 *            the nome
	 */
	public Disciplina(String codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

}