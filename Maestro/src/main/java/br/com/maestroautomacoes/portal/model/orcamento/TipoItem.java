package br.com.maestroautomacoes.portal.model.orcamento;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_tipo_item database table.
 * 
 */
@Entity
@Table(name="tb_tipo_item")
public class TipoItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo_item", unique=true, nullable=false)
	private int idTipoItem;

	@Column(name="nome_tipo_item", length=100)
	private String nomeTipoItem;

	//bi-directional many-to-one association to ItemOrcamento
	@OneToMany(mappedBy="tipoItem")
	private List<ItemOrcamento> itemOrcamento;

    public TipoItem() {
    }

	public int getIdTipoItem() {
		return this.idTipoItem;
	}

	public void setIdTipoItem(int idTipoItem) {
		this.idTipoItem = idTipoItem;
	}

	public String getNomeTipoItem() {
		return this.nomeTipoItem;
	}

	public void setNomeTipoItem(String nomeTipoItem) {
		this.nomeTipoItem = nomeTipoItem;
	}

	public List<ItemOrcamento> getItemOrcamento() {
		return this.itemOrcamento;
	}

	public void setItemOrcamento(List<ItemOrcamento> itemOrcamento) {
		this.itemOrcamento = itemOrcamento;
	}
	
}