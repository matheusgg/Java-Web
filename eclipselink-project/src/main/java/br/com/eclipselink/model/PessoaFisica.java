package br.com.eclipselink.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The persistent class for the pessoa_fisica database table.
 * 
 */
@Entity
@Table(name = "pessoa_fisica")
@NamedQuery(name = "PessoaFisica.findAll", query = "SELECT p FROM PessoaFisica p")
@DiscriminatorValue("F")
@Data
@EqualsAndHashCode(callSuper = false)
public class PessoaFisica extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5795392936470275076L;

	private Long cpf;

}