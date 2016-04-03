package br.com.metriken.model.nopersistence.apf;

import java.io.Serializable;

public class Consideracao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3372466406854791087L;
	
	private String escopo;
	private String fronteira;
	
	
	/**
	 * @return the escopo
	 */
	public String getEscopo() {
		return escopo;
	}
	/**
	 * @param escopo the escopo to set
	 */
	public void setEscopo(String escopo) {
		this.escopo = escopo;
	}
	/**
	 * @return the fronteira
	 */
	public String getFronteira() {
		return fronteira;
	}
	/**
	 * @param fronteira the fronteira to set
	 */
	public void setFronteira(String fronteira) {
		this.fronteira = fronteira;
	}

}
