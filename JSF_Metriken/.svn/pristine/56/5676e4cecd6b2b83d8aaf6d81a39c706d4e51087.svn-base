package br.com.metriken.model.nopersistence.apf;

import java.io.Serializable;

public class Cfps implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3506195847936120469L;
	
	private long value;
	private String label;
	
	
	public Cfps(){
		super();
	}
	
	
	
	public Cfps(long value, String label) {
		super();
		this.value = value;
		this.label = label;
	}


	public boolean getIsCFPS(){
		return value == 1 ? true : false;
	}

	// Getters and Setters

	/**
	 * @return the value
	 */
	public long getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(long value) {
		this.value = value;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + (int) (value ^ (value >>> 32));
		return result;
	}
	/* (non-Javadoc)
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
		Cfps other = (Cfps) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (value != other.value)
			return false;
		return true;
	}

}
