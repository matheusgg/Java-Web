package br.com;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.model.Cliente;

@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3177284638143555959L;

	private List<Cliente> listaClientes;
	private List<SelectItem> listaFiltros;
	private Cliente clienteSelecionado;

	public String iniciarPagina() {
		this.listaClientes = new ArrayList<Cliente>();
		this.clienteSelecionado = new Cliente();
		this.listaFiltros = new ArrayList<SelectItem>();
		this.carregaLista();
		this.preparaFiltros();
		return "clientes?faces-redirect=true";
	}

	private void carregaLista() {
		for (int i = 0; i < 10; i++) {
			this.listaClientes.add(new Cliente("Cliente " + (i + 1),
					"Endereço " + (i + 1)));
		}
	}

	private void preparaFiltros() {
		for (Cliente cliente : this.listaClientes) {
			this.listaFiltros.add(new SelectItem(cliente.getEndereco(), cliente
					.getEndereco()));
		}
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public List<SelectItem> getListaFiltros() {
		return listaFiltros;
	}

	public void setListaFiltros(List<SelectItem> listaFiltros) {
		this.listaFiltros = listaFiltros;
	}

}
