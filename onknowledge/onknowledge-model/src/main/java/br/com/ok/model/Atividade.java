package br.com.ok.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.ok.model.enums.TipoAtividade;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the tb_atividade database table.
 * 
 */
@Entity
@Table(name = "tb_atividade")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({ @NamedQuery(name = "Atividade.findAll", query = "SELECT a FROM Atividade a"),
		@NamedQuery(name = "Atividade.findDataPrazoFinalById", query = "SELECT a.dataPrazoFinal FROM Atividade a where a.id = :id"),
		@NamedQuery(name = "Atividade.findByModulo", query = "SELECT a FROM Atividade a inner join a.modulo m where m.id = :id order by a.descricao") })
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Atividade implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7854509925426887225L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_atividade")
	private Integer id;

	/** The descricao. */
	@Column(name = "ds_atividade")
	private String descricao;

	/** The data cadastro. */
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_cadastro")
	private Date dataCadastro;

	/** The data prazo final. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_prazo_final")
	private Date dataPrazoFinal;

	/** The modulo. */
	@ManyToOne
	@JoinColumn(name = "id_modulo")
	private Modulo modulo;

	/** The professor. */
	@ManyToOne
	@JoinColumn(name = "id_professor")
	private Professor professor;

	/** The comentarios. */
	@OneToMany(mappedBy = "atividade")
	private List<Comentario> comentarios;

	/** The anexos. */
	@ManyToMany
	@JoinTable(name = "tb_atividade_anexo", joinColumns = { @JoinColumn(name = "id_atividade") }, inverseJoinColumns = { @JoinColumn(name = "id_anexo") })
	private List<Anexo> anexos;

	/** The disciplina. */
	@Transient
	private Disciplina disciplina;

	/** The tipo atividade. */
	@Transient
	private TipoAtividade tipoAtividade;
}