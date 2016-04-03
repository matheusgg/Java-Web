package br.com.metriken.model.nopersistence.apf;

import java.io.Serializable;

public class Documento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1379239199177938830L;

	private String descriao;

	/**
	 * @return the descriao
	 */
	public String getDescriao() {
		return descriao;
	}

	/**
	 * @param descriao
	 *            the descriao to set
	 */
	public void setDescriao(String descriao) {
		this.descriao = descriao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descriao == null) ? 0 : descriao.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;
		if (descriao == null) {
			if (other.descriao != null)
				return false;
		} else if (!descriao.equals(other.descriao))
			return false;
		return true;
	}

}
