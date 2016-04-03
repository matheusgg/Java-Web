package br.com.lazy.bean;

import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.lazy.datamodel.TableDataModel;

@ManagedBean
@SessionScoped
public class ClienteBean {
	/*
	 * Atributos de controle de filtro da tabela
	 */
	private String nomeCliente;
	private String descricaoCliente;
	private TableDataModel dataModel;
	private HashMap<String, String> filtros;

	public ClienteBean() {
		this.nomeCliente = "";
		this.descricaoCliente = "";
		this.filtros = new HashMap<String, String>();
		this.dataModel = new TableDataModel();
		this.dataModel.setFiltros(this.filtros);
	}

	/**
	 * @return the nomeCliente
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente
	 *            the nomeCliente to set
	 */
	public void setNomeCliente(String nomeCliente) {
		// Aqui os filtros são adicionados ao hashMap
		this.filtros.put("nome", nomeCliente);
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return the descricaoCliente
	 */
	public String getDescricaoCliente() {
		return descricaoCliente;
	}

	/**
	 * @param descricaoCliente
	 *            the descricaoCliente to set
	 */
	public void setDescricaoCliente(String descricaoCliente) {
		// Aqui os filtros são adicionados ao hashMap
		this.filtros.put("descricao", descricaoCliente);
		this.descricaoCliente = descricaoCliente;
	}

	/**
	 * @return the dataModel
	 */
	public TableDataModel getDataModel() {
		return dataModel;
	}

	/**
	 * @param dataModel
	 *            the dataModel to set
	 */
	public void setDataModel(TableDataModel dataModel) {
		this.dataModel = dataModel;
	}

}
