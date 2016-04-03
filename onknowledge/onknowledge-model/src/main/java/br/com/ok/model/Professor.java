package br.com.ok.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the tb_professor database table.
 * 
 */
@Entity
@Table(name = "tb_professor")
@PrimaryKeyJoinColumn(name = "id_professor", referencedColumnName = "id_usuario")
@NamedQueries({
		@NamedQuery(name = "Professor.findAll", query = "SELECT p FROM Professor p"),
		@NamedQuery(name = "Professor.findNomesProfessores", query = "SELECT p.nome FROM Professor p where p.nome like :nome"),
		@NamedQuery(name = "Professor.findProfessorByDisciplinaId", query = "select new Professor(obj.id, obj.nome, obj.sobrenome, obj.apelido, obj.login, "
				+ "obj.email, obj.dataCadastro, obj.perfil) from Professor obj inner join obj.disciplinas d where d.id = :id") })
@Getter
@Setter
public class Professor extends Usuario implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4398811529124259024L;

	/** The disciplinas. */
	@ManyToMany(mappedBy = "professores")
	private List<Disciplina> disciplinas;

	@OneToMany(mappedBy = "coordenador")
	private List<Curso> cursosCoordenados;

	/**
	 * Instantiates a new professor.
	 */
	public Professor() {

	}

	/**
	 * Instantiates a new professor.
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
	 * @param perfil
	 *            the perfil
	 */
	public Professor(Integer id, String nome, String sobrenome, String apelido, String login, String email, Date dataCadastro, Perfil perfil) {
		super(id, nome, sobrenome, apelido, login, email, dataCadastro, perfil);
	}
}