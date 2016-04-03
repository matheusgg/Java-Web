package br.com.ok.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the tb_atividade_simples database table.
 * 
 */
@Entity
@Table(name = "tb_atividade_simples")
@PrimaryKeyJoinColumn(name = "id_atividade_simples", referencedColumnName = "id_atividade")
@NamedQuery(name = "AtividadeSimples.findAll", query = "SELECT a FROM AtividadeSimples a")
@Getter
@Setter
public class AtividadeSimples extends Atividade implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2012207093518867842L;
	
	/** The conteudo. */
	@Column(name = "conteudo")
	private String conteudo;

}