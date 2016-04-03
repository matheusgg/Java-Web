package br.com.ok.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the tb_turma database table.
 * 
 */
@Entity
@Table(name = "tb_turma")
@NamedQueries({
		@NamedQuery(name = "Turma.findAll", query = "SELECT t FROM Turma t"),
		@NamedQuery(name = "Turma.findByCodigoAndDataAtual", query = "select t from Turma t where t.codigo = :codigo and t.dataEncerramento >= :dataAtual"),
		@NamedQuery(name = "Turma.findCodigosByQuery", query = "select t.codigo from Turma t where t.codigo like :codigo"),
		@NamedQuery(name = "Turma.countByIdAndCoordenadorCurso", query = "select count(t) from Turma t where t.id = :idTurma and t.curso.coordenador.id = :idCoordenador") })
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Turma implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4595252151293156352L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_turma")
	private Integer id;

	/** The codigo. */
	@Column(name = "cod_turma")
	private String codigo;

	/** The data encerramento. */
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_encerramento")
	private Date dataEncerramento;

	/** The data inicio. */
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_inicio")
	private Date dataInicio;

	/** The curso. */
	@ManyToOne
	@JoinColumn(name = "id_curso")
	private Curso curso;

	/** The alunos. */
	@OneToMany(mappedBy = "turma")
	private List<Aluno> alunos;

	/** The codigos seguranca. */
	@OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
	private List<CodigoSeguranca> codigosSeguranca;

	/** The eventos. */
	@ManyToMany(mappedBy = "turmas")
	private List<Evento> eventos;

	/** The gera codigos seguranca. */
	@Transient
	private boolean geraCodigosSeguranca;

	/** The quantidade codigos seguranca. */
	@Transient
	private Integer quantidadeCodigosSeguranca;

	/** The data inicio original. */
	@Transient
	private Date dataInicioOriginal;

	/**
	 * Instantiates a new turma.
	 */
	public Turma() {

	}

	/**
	 * Instantiates a new turma.
	 *
	 * @param codigo
	 *            the codigo
	 */
	public Turma(String codigo) {
		this.codigo = codigo;
	}

}