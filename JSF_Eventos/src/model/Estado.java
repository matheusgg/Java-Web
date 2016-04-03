package model;

import java.util.ArrayList;
import java.util.List;

public class Estado {
	private String nome;
	private String sigla;
	private List<String> cidades;
	
	public Estado(){
		this.cidades = new ArrayList<String>();
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
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @param sigla
	 *            the sigla to set
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * @return the cidades
	 */
	public List<String> getCidades() {
		return cidades;
	}

	/**
	 * @param cidades
	 *            the cidades to set
	 */
	public void setCidades(List<String> cidades) {
		this.cidades = cidades;
	}
	

}
