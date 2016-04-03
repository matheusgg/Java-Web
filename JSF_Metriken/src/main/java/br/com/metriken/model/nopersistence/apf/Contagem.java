package br.com.metriken.model.nopersistence.apf;

import java.io.Serializable;
import java.util.Date;

public class Contagem implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1558001070022512677L;
	
	private Date contagemEm;
	private Date contagemFim;
	
	
	
	/**
	 * @return the contagemEm
	 */
	public Date getContagemEm() {
		return contagemEm;
	}
	/**
	 * @param contagemEm the contagemEm to set
	 */
	public void setContagemEm(Date contagemEm) {
		this.contagemEm = contagemEm;
	}
	/**
	 * @return the contagemFim
	 */
	public Date getContagemFim() {
		return contagemFim;
	}
	/**
	 * @param contagemFim the contagemFim to set
	 */
	public void setContagemFim(Date contagemFim) {
		this.contagemFim = contagemFim;
	}
}
