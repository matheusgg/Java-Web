package model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * O JAX-RS utiliza o JAX-B para realizar o parse das informacoes para formato
 * XML ou JSON. Por este motovo é necessário anotar a classe com XmlRootElement,
 * pois um objeto desse tipo será retornado como resposta, com isso, o JAX-B
 * saberá converter as informacoes dentro desse objeto para o formato XML ou
 * JSON.
 * 
 * @author Matheus
 * 
 */
@XmlRootElement
public class Produto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 463905623672419973L;

	private int id;
	private String nome;
	private float preco;

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
	 * @return the preco
	 */
	public float getPreco() {
		return preco;
	}

	/**
	 * @param preco
	 *            the preco to set
	 */
	public void setPreco(float preco) {
		this.preco = preco;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + "]";
	}

}
