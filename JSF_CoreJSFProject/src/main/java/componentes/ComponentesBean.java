package componentes;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ComponentesBean {

	private String comentario;
	private String itemSelecionado;
	private List<SelectItem> itens;

	private void loadList() {
		this.itens = new ArrayList<>();

		SelectItemGroup grupo1 = new SelectItemGroup("Grupo 1", "", false, this.loadItens(0));
		SelectItemGroup grupo2 = new SelectItemGroup("Grupo 2", "", false, this.loadItens(5));

		this.itens.add(grupo1);
		this.itens.add(grupo2);
	}

	private SelectItem[] loadItens(int startIndex) {
		SelectItem[] selectItens = new SelectItem[5];
		for (int i = 0; i < selectItens.length; i++, startIndex++) {
			selectItens[i] = new SelectItem("Valor" + startIndex, "Item " + startIndex);
		}
		return selectItens;
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
	 * @return the itemSelecionado
	 */
	public String getItemSelecionado() {
		return itemSelecionado;
	}

	/**
	 * @param itemSelecionado
	 *            the itemSelecionado to set
	 */
	public void setItemSelecionado(String itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	/**
	 * @return the itens
	 */
	public List<SelectItem> getItens() {
		if (this.itens == null) {
			this.loadList();
		}
		return itens;
	}

	/**
	 * @param itens
	 *            the itens to set
	 */
	public void setItens(List<SelectItem> itens) {
		this.itens = itens;
	}

}
