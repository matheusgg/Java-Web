package br.com.metriken.model.nopersistence.apf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class APFEstimada implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4542975224796002724L;

	private TipoMetrica tipo;
	private long id;
	private String funcionalidade;
	private String complexidade;
	private int pontoFuncao;
	private String comentario;
	private boolean isSelected;

	private List<APFEstimada> elementoCrud;

	public APFEstimada() {
		this.tipo = new TipoMetrica();
		this.elementoCrud = new ArrayList<APFEstimada>();
		this.isSelected = false;
	}

	/**
	 * @return the tipo
	 */
	public TipoMetrica getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(TipoMetrica tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the funcionalidade
	 */
	public String getFuncionalidade() {
		return funcionalidade;
	}

	/**
	 * @param funcionalidade
	 *            the funcionalidade to set
	 */
	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	/**
	 * @return the complexidade
	 */
	public String getComplexidade() {
		return complexidade;
	}

	/**
	 * @param complexidade
	 *            the complexidade to set
	 */
	public void setComplexidade(String complexidade) {
		this.complexidade = complexidade;
	}

	/**
	 * @return the pontoFuncao
	 */
	public int getPontoFuncao() {
		return pontoFuncao;
	}

	/**
	 * @param pontoFuncao
	 *            the pontoFuncao to set
	 */
	public void setPontoFuncao(int pontoFuncao) {
		this.pontoFuncao = pontoFuncao;
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
		result = prime * result + ((complexidade == null) ? 0 : complexidade.hashCode());
		result = prime * result + ((funcionalidade == null) ? 0 : funcionalidade.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + pontoFuncao;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		APFEstimada other = (APFEstimada) obj;
		if (complexidade == null) {
			if (other.complexidade != null)
				return false;
		} else if (!complexidade.equals(other.complexidade))
			return false;
		if (funcionalidade == null) {
			if (other.funcionalidade != null)
				return false;
		} else if (!funcionalidade.equals(other.funcionalidade))
			return false;
		if (id != other.id)
			return false;
		if (pontoFuncao != other.pontoFuncao)
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @param comentario
	 *            the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * @return the elementoCrud
	 */
	public List<APFEstimada> getElementoCrud() {
		return elementoCrud;
	}

	/**
	 * @param elementoCrud
	 *            the elementoCrud to set
	 */
	public void setElementoCrud(List<APFEstimada> elementoCrud) {
		this.elementoCrud = elementoCrud;
	}

	/**
	 * @return the isSelected
	 */
	public boolean isSelected() {
		return isSelected;
	}

	/**
	 * @param isSelected
	 *            the isSelected to set
	 */
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
