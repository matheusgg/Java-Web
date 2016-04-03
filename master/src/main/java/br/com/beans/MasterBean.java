package br.com.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.model.Cliente;

@ManagedBean
@ViewScoped
public class MasterBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7274745461193052047L;
	private List<Cliente> clientes;
	private Cliente cliente;
	
	public MasterBean(){
		this.getClass();
	}

	public long paginate(long startIndex, int pageSize) {
		this.clientes = new ArrayList<>();
		for (int i = 0; i < pageSize; i++) {
			this.clientes.add(new Cliente((i + 1), "Cliente " + (i + 1)));
		}
		return 100;
	}

	/**
	 * @return the clientes
	 */
	public List<Cliente> getClientes() {
		return clientes;
	}

	/**
	 * @param clientes
	 *            the clientes to set
	 */
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
