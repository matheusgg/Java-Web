package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import conversaovalidacao.beanvalidation.ValidationGroup;

/**
 * Os grupos servem para realizar determinadas validacoes em determinados
 * momentos. Por exemplo, e necessario que toda vez que um id e informado, o
 * nome e o nascimento do usuario sejam validados para verificar se nao estao
 * nulos. Para realizar esta operacao, grupos sao informados, e no momento da
 * validacao, ao inves de validar campo por campo, o grupo e validado, desta
 * forma, todos os atributos que possuirem este grupo tambem serao validados.
 * Quando nenhum grupo e informado, o grupo default e assumido
 * (javax.validation.groups.Default). O grupo precisa ser apenas uma interface.
 * 
 * @author Matheus
 * 
 */
@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private int id;

	@NotNull(groups = { ValidationGroup.class })
	private String nome;

	@NotNull(groups = { ValidationGroup.class })
	@Temporal(TemporalType.DATE)
	private Date nascimento;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the nascimento
	 */
	public Date getNascimento() {
		return nascimento;
	}

	/**
	 * @param nascimento
	 *            the nascimento to set
	 */
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

}
