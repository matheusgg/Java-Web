package br.com.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 2238686131628367594L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "unique_generator")
	@TableGenerator(name = "unique_generator", table = "unique_generator", pkColumnName = "id", pkColumnValue = "unique", valueColumnName = "seed")
	private Long id;

	private String nome;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Produto> produtos;

	public Cliente() {

	}

	/**
	 * @param nome
	 */
	public Cliente(String nome) {
		this.nome = nome;
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
	 * @return the produtos
	 */
	public List<Produto> getProdutos() {
		return produtos;
	}

	/**
	 * @param produtos
	 *            the produtos to set
	 */
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", produtos=" + produtos + "]";
	}

}
