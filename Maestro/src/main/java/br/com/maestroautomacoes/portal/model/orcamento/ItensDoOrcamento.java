package br.com.maestroautomacoes.portal.model.orcamento;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_itens_do_orcamento database table.
 * 
 */
@Entity
@Table(name="tb_itens_do_orcamento")
public class ItensDoOrcamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItensDoOrcamentoPK id;

	@Column(name="quantidade_do_item")
	private int quantidadeDoItem;

	//bi-directional many-to-one association to ItemOrcamento
    @ManyToOne
	@JoinColumn(name="id_item_orcamento", nullable=false, insertable=false, updatable=false)
	private ItemOrcamento itemOrcamento;

	//bi-directional many-to-one association to Orcamento
    @ManyToOne
	@JoinColumn(name="id_orcamento", nullable=false, insertable=false, updatable=false)
	private Orcamento orcamento;

    public ItensDoOrcamento() {
    }

	public ItensDoOrcamentoPK getId() {
		return this.id;
	}

	public void setId(ItensDoOrcamentoPK id) {
		this.id = id;
	}
	
	public int getQuantidadeDoItem() {
		return this.quantidadeDoItem;
	}

	public void setQuantidadeDoItem(int quantidadeDoItem) {
		this.quantidadeDoItem = quantidadeDoItem;
	}

	public ItemOrcamento getItemOrcamento() {
		return this.itemOrcamento;
	}

	public void setItemOrcamento(ItemOrcamento itemOrcamento) {
		this.itemOrcamento = itemOrcamento;
	}
	
	public Orcamento getOrcamento() {
		return this.orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	
}