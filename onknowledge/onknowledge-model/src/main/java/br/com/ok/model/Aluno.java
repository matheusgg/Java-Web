package br.com.ok.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the tb_aluno database table.
 * 
 */
@Entity
@Table(name = "tb_aluno")
@PrimaryKeyJoinColumn(name = "id_aluno", referencedColumnName = "id_usuario")
@NamedQuery(name = "Aluno.findAll", query = "SELECT a FROM Aluno a")
@Getter
@Setter
public class Aluno extends Usuario implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3816794024407518807L;

	/** The turma. */
	@ManyToOne
	@JoinColumn(name = "id_turma")
	private Turma turma;

	/** The codigo seguranca. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_codigo_seguranca")
	private CodigoSeguranca codigoSeguranca;

	/** The disciplinas. */
	@ManyToMany
	@JoinTable(name = "tb_aluno_disciplina", joinColumns = { @JoinColumn(name = "id_aluno") }, inverseJoinColumns = { @JoinColumn(name = "id_disciplina") })
	private List<Disciplina> disciplinas;

	/** The respostas. */
	@OneToMany(mappedBy = "aluno")
	private List<Resposta> respostas;

	/**
	 * Instantiates a new aluno.
	 */
	public Aluno() {

	}

	/**
	 * Instantiates a new aluno.
	 *
	 * @param id
	 *            the id
	 * @param nome
	 *            the nome
	 * @param sobrenome
	 *            the sobrenome
	 * @param apelido
	 *            the apelido
	 * @param login
	 *            the login
	 * @param email
	 *            the email
	 * @param dataCadastro
	 *            the data cadastro
	 * @param nomeCurso
	 *            the nome curso
	 * @param codigoTurma
	 *            the codigo turma
	 * @param perfil
	 *            the perfil
	 */
	public Aluno(Integer id, String nome, String sobrenome, String apelido, String login, String email, Date dataCadastro, String nomeCurso, String codigoTurma,
			Perfil perfil) {
		super(id, nome, sobrenome, apelido, login, email, dataCadastro, perfil);
		this.setTurma(new Turma(codigoTurma));
		this.getTurma().setCurso(new Curso(nomeCurso));
	}
}