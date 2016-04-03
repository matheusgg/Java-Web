package br.com.ok.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The persistent class for the tb_anexo database table.
 * 
 */
@Entity
@Table(name = "tb_anexo")
@NamedQuery(name = "Anexo.findAll", query = "SELECT a FROM Anexo a")
@Data
@EqualsAndHashCode(of = "id")
public class Anexo implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -267071405363046149L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_anexo")
	private Integer id;

	/** The caminho arquivo. */
	@Column(name = "caminho_arquivo")
	private String caminhoArquivo;

	/** The extensao arquivo. */
	@Column(name = "extensao_arquivo")
	private String extensaoArquivo;

	/** The nome arquivo. */
	@Column(name = "nome_arquivo")
	private String nomeArquivo;

	/** The emails. */
	@ManyToMany(mappedBy = "anexos")
	private List<Email> emails;

	/** The questoes. */
	@ManyToMany(mappedBy = "anexos")
	private List<Questao> questoes;

	/** The respostas. */
	@ManyToMany(mappedBy = "anexos")
	private List<Resposta> respostas;

	/** The atividades. */
	@ManyToMany(mappedBy = "anexos")
	private List<Atividade> atividades;

	/** The file data. */
	@Transient
	private byte[] fileData;

	/**
	 * Instantiates a new anexo.
	 */
	public Anexo() {

	}

	/**
	 * Instantiates a new anexo.
	 *
	 * @param nome
	 *            the nome
	 * @param extensao
	 *            the extensao
	 * @param fileData
	 *            the file data
	 */
	public Anexo(String nome, String extensao, byte[] fileData) {
		this.nomeArquivo = nome;
		this.extensaoArquivo = extensao;
		this.fileData = fileData;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.nomeArquivo;
	}

}