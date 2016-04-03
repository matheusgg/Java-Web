package br.com.ok.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The persistent class for the tb_pergunta_seguranca database table.
 * 
 */
@Entity
@Table(name = "tb_pergunta_seguranca")
@NamedQueries({ @NamedQuery(name = "PerguntaSeguranca.findAll", query = "SELECT p FROM PerguntaSeguranca p") })
@Data
@EqualsAndHashCode(of = "id")
public class PerguntaSeguranca implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8039308093420173227L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pergunta")
	private Integer id;

	/** The descricao. */
	@Column(name = "ds_pergunta")
	private String descricao;

}