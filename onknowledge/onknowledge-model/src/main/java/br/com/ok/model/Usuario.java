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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.Email;

import br.com.ok.model.enums.Sexo;
import br.com.ok.model.util.ProfilePicture;

/**
 * The persistent class for the tb_usuario database table.
 * 
 */
@Entity
@Table(name = "tb_usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
		@NamedQuery(name = "Usuario.findByLoginAndPassword", query = "select u from Usuario u where u.login = :login and u.senha = :senha"),
		@NamedQuery(name = "Usuario.countByLogin", query = "select count(u) from Usuario u where u.login = :login"),
		@NamedQuery(name = "Usuario.countByLoginExcludingUserId", query = "select count(u) from Usuario u where u.login = :login and u.id != :userId"),
		@NamedQuery(name = "Usuario.countByEmail", query = "select count(u) from Usuario u where u.email = :email"),
		@NamedQuery(name = "Usuario.findByEmail", query = "select u from Usuario u where u.email = :email"),
		@NamedQuery(name = "Usuario.countByEmailExcludingUserId", query = "select count(u) from Usuario u where u.email = :email and u.id != :userId"),
		@NamedQuery(name = "Usuario.findAllLogins", query = "select u.login from Usuario u where u.ativo = true"),
		@NamedQuery(name = "Usuario.findByLogin", query = "select u from Usuario u where u.login = :login"),
		@NamedQuery(name = "Usuario.findFotoPerfilByLogin", query = "select u.fotoPerfil from Usuario u where u.login = :login")})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8819976397359011419L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer id;

	/** The apelido. */
	private String apelido;

	/** The data cadastro. */
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_cadastro")
	private Date dataCadastro;

	/** The data nascimento. */
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_nascimento")
	private Date dataNascimento;

	/** The email. */
	@Email(message = "{msg.validacao.email.invalido}", regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
	private String email;

	/** The ativo. */
	@Column(name = "fl_ativo")
	private Boolean ativo;

	/** The recebe notificacoes por email. */
	@Column(name = "fl_recebe_notificacoes_por_email")
	private Boolean recebeNotificacoesPorEmail;

	/** The login. */
	private String login;

	/** The nome. */
	private String nome;

	/** The resposta pergunta seguranca. */
	@Column(name = "resposta_pergunta_seguranca")
	private String respostaPerguntaSeguranca;

	/** The senha. */
	@Lob
	@Column(columnDefinition = "BLOB")
	private byte[] senha;

	/** The sobrenome. */
	private String sobrenome;

	/** The sexo. */
	@Column(name = "tp_sexo")
	@Enumerated(EnumType.ORDINAL)
	private Sexo sexo;

	/** The perfil. */
	@ManyToOne
	@JoinColumn(name = "id_perfil")
	private Perfil perfil;

	/** The pergunta seguranca. */
	@ManyToOne
	@JoinColumn(name = "id_pergunta_seguranca")
	private PerguntaSeguranca perguntaSeguranca;

	/** The anexo. */
	@ManyToOne
	@JoinColumn(name = "id_foto")
	private Anexo fotoPerfil;

	/** The emails. */
	@OneToMany(mappedBy = "usuarioRemetente")
	private List<br.com.ok.model.Email> emailsEnviados;

	/** The emails. */
	@OneToMany(mappedBy = "usuarioDestinatario")
	private List<EmailRecebido> emailsRecebidos;

	/** The diretorios emails. */
	@OneToMany(mappedBy = "usuario")
	private List<DiretorioEmail> diretoriosEmails;

	/** The eventos. */
	@OneToMany(mappedBy = "usuario")
	private List<Evento> eventos;

	/** The comentarios. */
	@OneToMany(mappedBy = "usuario")
	private List<Comentario> comentarios;

	/** The tarefas. */
	@OneToMany(mappedBy = "usuario")
	private List<Tarefa> tarefas;

	/** The password. */
	@Transient
	@Size(min = 10)
	private String password;

	/** The confirm password. */
	@Transient
	@Size(min = 10)
	private String confirmPassword;

	/** The profile picture. */
	@Transient
	private ProfilePicture profilePicture;

	/** The data cadastro final. */
	@Transient
	private Date dataCadastroFinal;

	/** The tarefas recentes. */
	@Transient
	private List<Tarefa> tarefasRecentes;

	/**
	 * Instantiates a new usuario.
	 */
	public Usuario() {

	}

	/**
	 * Instantiates a new usuario.
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
	public Usuario(Integer id, String nome, String sobrenome, String apelido, String login, String email, Date dataCadastro, Perfil perfil) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.apelido = apelido;
		this.login = login;
		this.email = email;
		this.dataCadastro = dataCadastro;
		this.perfil = perfil;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.login;
	}
}