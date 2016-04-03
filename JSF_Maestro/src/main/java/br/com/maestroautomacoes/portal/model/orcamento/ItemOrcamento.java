package br.com.maestroautomacoes.portal.model.orcamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the tb_item_orcamento database table.
 * 
 */

@Entity
@Table(name = "tb_item_orcamento")
public class ItemOrcamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_orcamento", unique = true, nullable = false)
	private int idItemOrcamento;

	@Column(name = "nome_item", length = 100)
	private String nomeItem;

	@Column(name = "descricao", length = 255)
	private String descricao;

	@Column(precision = 10, scale = 2)
	private BigDecimal valor;

	// bi-directional many-to-one association to TipoItem
	@ManyToOne
	@JoinColumn(name = "id_tipo_item", nullable = false)
	private TipoItem tipoItem;

	// bi-directional many-to-one association to ItensDoOrcamento
	@OneToMany(mappedBy = "itemOrcamento")
	private List<ItensDoOrcamento> itensDoOrcamento;

	@Transient
	private int quantidadeDoItem;

	public ItemOrcamento() {
	}

	public int getIdItemOrcamento() {
		return this.idItemOrcamento;
	}

	public void setIdItemOrcamento(int idItemOrcamento) {
		this.idItemOrcamento = idItemOrcamento;
	}

	public String getNomeItem() {
		return this.nomeItem;
	}

	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoItem getTipoItem() {
		return this.tipoItem;
	}

	public void setTipoItem(TipoItem tipoItem) {
		this.tipoItem = tipoItem;
	}

	public List<ItensDoOrcamento> getItensDoOrcamento() {
		return this.itensDoOrcamento;
	}

	public void setItensDoOrcamento(List<ItensDoOrcamento> itensDoOrcamento) {
		this.itensDoOrcamento = itensDoOrcamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidadeDoItem() {
		return quantidadeDoItem;
	}

	public void setQuantidadeDoItem(int quantidadeDoItem) {
		this.quantidadeDoItem = quantidadeDoItem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + idItemOrcamento;
		result = prime * result + ((nomeItem == null) ? 0 : nomeItem.hashCode());
		result = prime * result + quantidadeDoItem;
		result = prime * result + ((tipoItem == null) ? 0 : tipoItem.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		ItemOrcamento other = (ItemOrcamento) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idItemOrcamento != other.idItemOrcamento)
			return false;
		if (nomeItem == null) {
			if (other.nomeItem != null)
				return false;
		} else if (!nomeItem.equals(other.nomeItem))
			return false;
		if (quantidadeDoItem != other.quantidadeDoItem)
			return false;
		if (tipoItem == null) {
			if (other.tipoItem != null)
				return false;
		} else if (!tipoItem.equals(other.tipoItem))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

}