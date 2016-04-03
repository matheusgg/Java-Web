package br.com.maestroautomacoes.portal.model.orcamento;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tb_itens_do_orcamento database table.
 * 
 */
@Embeddable
public class ItensDoOrcamentoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_item_orcamento", unique=true, nullable=false)
	private Integer idItemOrcamento;

	@Column(name="id_orcamento", unique=true, nullable=false)
	private Integer idOrcamento;

    public ItensDoOrcamentoPK() {
    }
	public Integer getIdItemOrcamento() {
		return this.idItemOrcamento;
	}
	public void setIdItemOrcamento(Integer idItemOrcamento) {
		this.idItemOrcamento = idItemOrcamento;
	}
	public Integer getIdOrcamento() {
		return this.idOrcamento;
	}
	public void setIdOrcamento(Integer idOrcamento) {
		this.idOrcamento = idOrcamento;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idItemOrcamento == null) ? 0 : idItemOrcamento.hashCode());
		result = prime * result + ((idOrcamento == null) ? 0 : idOrcamento.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItensDoOrcamentoPK other = (ItensDoOrcamentoPK) obj;
		if (idItemOrcamento == null) {
			if (other.idItemOrcamento != null)
				return false;
		} else if (!idItemOrcamento.equals(other.idItemOrcamento))
			return false;
		if (idOrcamento == null) {
			if (other.idOrcamento != null)
				return false;
		} else if (!idOrcamento.equals(other.idOrcamento))
			return false;
		return true;
	}
}