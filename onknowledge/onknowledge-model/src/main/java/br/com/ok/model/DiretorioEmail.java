package br.com.ok.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The persistent class for the tb_diretorio_email database table.
 * 
 */
@Entity
@Table(name = "tb_diretorio_email")
@NamedQueries({ @NamedQuery(name = "DiretorioEmail.findAll", query = "SELECT d FROM DiretorioEmail d"),
		@NamedQuery(name = "DiretorioEmail.findByIdUsuario", query = "select d from DiretorioEmail d where d.usuario.id = :idUsuario"),
		@NamedQuery(name = "DiretorioEmail.countByNomeAndUsuario", query = "select count (d) from DiretorioEmail d where d.nome = :nome and d.usuario.id = :idUsuario") })
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "nome")
public class DiretorioEmail implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5892983036907014397L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_diretorio")
	private Integer id;

	/** The nome. */
	private String nome;

	/** The usuario. */
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	/** The emails. */
	@OneToMany(mappedBy = "diretorioEmail")
	private List<EmailRecebido> emails;

}