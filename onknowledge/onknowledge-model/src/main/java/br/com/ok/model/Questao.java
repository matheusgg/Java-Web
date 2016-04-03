package br.com.ok.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the tb_questao database table.
 * 
 */
@Entity
@Table(name = "tb_questao")
@NamedQueries({
		@NamedQuery(name = "Questao.findAll", query = "SELECT q FROM Questao q"),
		@NamedQuery(name = "Questao.countByDisciplinaAndAluno", query = "SELECT count(q) FROM Questao q inner join q.respostas r inner join q.atividadeQuestionario a inner join a.modulo m inner join m.disciplinas d where d.id = :idDisciplina and r.aluno.id = :idAluno"),
		@NamedQuery(name = "Questao.countByDisciplina", query = "SELECT count(q) FROM Questao q inner join q.atividadeQuestionario a inner join a.modulo m inner join m.disciplinas d where d.id = :idDisciplina") })
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Questao implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4260746733338467503L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_questao")
	private Integer id;

	/** The descricao. */
	@Column(name = "ds_questao")
	private String descricao;

	/** The justivicativa obrigatoria. */
	@Column(name = "fl_justivicativa_obrigatoria")
	private Boolean justivicativaObrigatoria;

	/** The alternativas. */
	@OneToMany(mappedBy = "questao", cascade = CascadeType.ALL)
	private List<Alternativa> alternativas;

	/** The comentarios. */
	@OneToMany(mappedBy = "questao")
	private List<Comentario> comentarios;

	/** The atividade questionario. */
	@ManyToOne
	@JoinColumn(name = "id_atividade_questionario")
	private AtividadeQuestionario atividadeQuestionario;

	/** The anexos. */
	@ManyToMany
	@JoinTable(name = "tb_questao_anexo", joinColumns = { @JoinColumn(name = "id_questao") }, inverseJoinColumns = { @JoinColumn(name = "id_anexo") })
	private List<Anexo> anexos;

	/** The respostas. */
	@OneToMany(mappedBy = "questao")
	private List<Resposta> respostas;

}