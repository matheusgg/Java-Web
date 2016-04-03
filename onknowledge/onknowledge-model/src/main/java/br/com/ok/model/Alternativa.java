package br.com.ok.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the tb_alternativa database table.
 * 
 */
@Entity
@Table(name = "tb_alternativa")
@NamedQuery(name = "Alternativa.findAll", query = "SELECT a FROM Alternativa a")
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Alternativa implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2238401054813338462L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_alternativa")
	private Integer id;

	/** The descricao. */
	@Column(name = "ds_opcao")
	private String descricao;

	/** The correta. */
	@Column(name = "fl_correta")
	private Boolean correta;

	/** The questao. */
	@ManyToOne
	@JoinColumn(name = "id_questao")
	private Questao questao;

	/** The respostas. */
	@ManyToMany(mappedBy = "alternativas")
	private List<Resposta> respostas;

	/** The selecionada. */
	@Transient
	private Boolean selecionada;

}