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
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The persistent class for the tb_resposta database table.
 * 
 */
@Entity
@Table(name = "tb_resposta")
@NamedQueries({ @NamedQuery(name = "Resposta.findAll", query = "SELECT r FROM Resposta r"),
		@NamedQuery(name = "Resposta.findByQuestaoAndAluno", query = "SELECT r FROM Resposta r where r.questao.id = :idQuestao and r.aluno.id = :idAluno"),
		@NamedQuery(name = "Resposta.countByQuestaoAndAluno", query = "SELECT count(r) FROM Resposta r where r.questao.id = :idQuestao and r.aluno.id = :idAluno"),
		@NamedQuery(name = "Resposta.findByQuestao", query = "SELECT r FROM Resposta r where r.questao.id = :idQuestao") })
@Data
@EqualsAndHashCode(of = "id")
public class Resposta implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1218767427398914367L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_resposta")
	private Integer id;

	/** The descricao. */
	@Column(name = "ds_justificativa")
	private String descricao;

	/** The resposta correta. */
	@Column(name = "fl_resposta_correta")
	private Boolean respostaCorreta;

	/** The corrigida. */
	@Column(name = "fl_corrigida")
	private Boolean corrigida;

	/** The questao. */
	@ManyToOne
	@JoinColumn(name = "id_questao")
	private Questao questao;

	/** The aluno. */
	@ManyToOne
	@JoinColumn(name = "id_aluno")
	private Aluno aluno;

	/** The anexos. */
	@ManyToMany
	@JoinTable(name = "tb_resposta_anexo", joinColumns = { @JoinColumn(name = "id_resposta") }, inverseJoinColumns = { @JoinColumn(name = "id_anexo") })
	private List<Anexo> anexos;

	/** The alternativas. */
	@ManyToMany
	@JoinTable(name = "tb_resposta_alternativa", joinColumns = { @JoinColumn(name = "id_resposta") }, inverseJoinColumns = { @JoinColumn(name = "id_alternativa") })
	private List<Alternativa> alternativas;

}