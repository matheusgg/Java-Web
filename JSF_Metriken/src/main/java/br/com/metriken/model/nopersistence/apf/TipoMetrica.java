package br.com.metriken.model.nopersistence.apf;

import java.io.Serializable;

public class TipoMetrica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -951964558934527267L;

	private long codigo;

	private String label;

	public TipoMetrica() {
		super();
	}

	public TipoMetrica(long codigo, String label) {
		super();
		this.codigo = codigo;
		this.label = label;
	}

	// Getters and Setters

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the codigo
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(long codigo) {
		this.codigo = codigo;
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
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
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
		TipoMetrica other = (TipoMetrica) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
}
