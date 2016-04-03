package br.com.ok.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import br.com.ok.model.enums.CorEvento;

/**
 * The persistent class for the tb_evento database table.
 * 
 */
@Entity
@Table(name = "tb_evento")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
		@NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
		@NamedQuery(name = "Evento.listaEventosCadastradosPeloUsuario", query = "SELECT e FROM Evento e where e.usuario.id = :usuario"),
		@NamedQuery(name = "Evento.findEventoById", query = "SELECT e FROM Evento e where e.id = :id"),

		@NamedQuery(name = "Evento.findEventosByProfessor", query = "SELECT distinct e FROM Evento e inner join e.disciplinas d where :professor member of d.professores"),

		@NamedQuery(name = "Evento.findEventosCursosByCoordenador", query = "SELECT distinct e FROM Evento e inner join e.cursos c where c.coordenador.id = :idCoordenador"),
		@NamedQuery(name = "Evento.findEventosTurmasByCoordenador", query = "SELECT distinct e FROM Evento e inner join e.turmas t where t.curso.coordenador.id = :idCoordenador"),

		@NamedQuery(name = "Evento.findEventosCursosByAluno", query = "SELECT distinct e FROM Evento e inner join e.cursos c inner join c.turmas t where :aluno member of t.alunos"),
		@NamedQuery(name = "Evento.findEventosDisciplinasExtrasByAluno", query = "SELECT distinct e FROM Evento e inner join e.disciplinas d where :aluno member of d.alunos"),
		@NamedQuery(name = "Evento.findEventosDisciplinasByAluno", query = "SELECT distinct e FROM Evento e inner join e.disciplinas d inner join d.cursos c inner join c.turmas t where :aluno member of t.alunos"),
		@NamedQuery(name = "Evento.findEventosTurmasByAluno", query = "SELECT distinct e FROM Evento e inner join e.turmas t where :aluno member of t.alunos") })
@Data
@ToString(of = "nome")
@EqualsAndHashCode(of = "id")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Evento implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7259125325655957161L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_evento")
	private Integer id;

	/** The descricao. */
	@Column(name = "ds_evento")
	private String descricao;

	/** The data final. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_final_evento")
	private Date dataFinal;

	/** The data inicial. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inicial_evento")
	private Date dataInicial;

	/** The nome. */
	@XmlElement(name = "title")
	private String nome;

	/** The cor evento. */
	@Column(name = "cor_evento")
	@Enumerated(EnumType.ORDINAL)
	private CorEvento corEvento;

	/** The usuario. */
	@XmlTransient
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	/** The cursos. */
	@XmlTransient
	@ManyToMany
	@JoinTable(name = "tb_evento_curso", joinColumns = { @JoinColumn(name = "id_evento") }, inverseJoinColumns = { @JoinColumn(name = "id_curso") })
	private List<Curso> cursos;

	/** The disciplinas. */
	@XmlTransient
	@ManyToMany
	@JoinTable(name = "tb_evento_disciplina", joinColumns = { @JoinColumn(name = "id_evento") }, inverseJoinColumns = { @JoinColumn(name = "id_disciplina") })
	private List<Disciplina> disciplinas;

	/** The turmas. */
	@XmlTransient
	@ManyToMany
	@JoinTable(name = "tb_evento_turma", joinColumns = { @JoinColumn(name = "id_evento") }, inverseJoinColumns = { @JoinColumn(name = "id_turma") })
	private List<Turma> turmas;

	/** The start. */
	@Transient
	private String start;

	/** The end. */
	@Transient
	private String end;

	/** The background color. */
	@Transient
	private String backgroundColor;

	/** The border color. */
	@Transient
	private String borderColor;

	/** The data inicial original. */
	@Transient
	private Date dataInicialOriginal;
}