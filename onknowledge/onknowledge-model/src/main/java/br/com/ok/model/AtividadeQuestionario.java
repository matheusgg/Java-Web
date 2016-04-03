package br.com.ok.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the tb_atividade_questionario database table.
 * 
 */
@Entity
@Table(name = "tb_atividade_questionario")
@PrimaryKeyJoinColumn(name = "id_atividade_questionario", referencedColumnName = "id_atividade")
@NamedQueries({
		@NamedQuery(name = "AtividadeQuestionario.findAll", query = "SELECT a FROM AtividadeQuestionario a"),
		@NamedQuery(name = "AtividadeQuestionario.findByModulo", query = "SELECT a FROM AtividadeQuestionario a inner join a.modulo m where m.id = :id order by a.descricao"),
		@NamedQuery(name = "AtividadeQuestionario.countByModulo", query = "SELECT count(a) FROM AtividadeQuestionario a inner join a.modulo m where m.id = :id"),
		@NamedQuery(name = "AtividadeQuestionario.findByModuloAndProfessor", query = "SELECT a FROM AtividadeQuestionario a inner join a.modulo m inner join a.professor p where m.id = :idModulo and p.id = :idProfessor order by a.descricao"),
		@NamedQuery(name = "AtividadeQuestionario.countByModuloAndProfessor", query = "SELECT count(a) FROM AtividadeQuestionario a where a.modulo.id = :idModulo and a.professor.id = :idProfessor") })
@Getter
@Setter
public class AtividadeQuestionario extends Atividade implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6978263983078125307L;

	/** The quantidade questoes. */
	@Column(name = "qtd_questoes")
	private Integer quantidadeQuestoes;

	/** The questoes. */
	@OneToMany(mappedBy = "atividadeQuestionario", cascade = CascadeType.ALL)
	private List<Questao> questoes;

}