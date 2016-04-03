package br.com.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = -2725577579219553821L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "unique_generator")
	@TableGenerator(name = "unique_generator", table = "unique_generator", pkColumnName = "id", pkColumnValue = "unique", valueColumnName = "seed")
	private Long id;

	private String nome;

	private BigDecimal preco;

	@ManyToOne(targetEntity = Cliente.class)
	private Cliente cliente;

	public Produto() {

	}

	/**
	 * @param nome
	 * @param preco
	 * @param cliente
	 */
	public Produto(String nome, BigDecimal preco, Cliente cliente) {
		this.nome = nome;
		this.preco = preco;
		this.cliente = cliente;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
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
	 * @return the preco
	 */
	public BigDecimal getPreco() {
		return preco;
	}

	/**
	 * @param preco
	 *            the preco to set
	 */
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + "]";
	}

}
