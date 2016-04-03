package beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import model.Cliente;

@Named
@SessionScoped
public class ClienteBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8500911033519325678L;
	private List<Cliente> clientes;
	private Cliente clienteSelecionado;
	private boolean selecionado;

	public ClienteBean() {
		this.clientes = new ArrayList<>();
		this.novoCliente();
	}

	public void novoCliente() {
		this.clienteSelecionado = new Cliente();
		this.selecionado = false;
	}

	public void adiciona() throws SQLException {
		if (Math.random() * 100 > 50) {
			throw new SQLException();
		}
		this.clienteSelecionado.setCodigo((int) (Math.random() * 1000));
		this.clientes.add(this.clienteSelecionado);
		this.novoCliente();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "O cliente foi adicionado com sucesso!", ""));
	}

	public void remove() {
		this.clientes.remove(this.clienteSelecionado);
		this.novoCliente();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "O cliente foi removido com sucesso!", ""));
	}

	public void atualiza() {
		this.novoCliente();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "O cliente foi atualizado com sucesso!", ""));
	}

	public void selecionaCliente() {
		this.selecionado = true;
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
	 * @return the clienteSelecionado
	 */
	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	/**
	 * @param clienteSelecionado
	 *            the clienteSelecionado to set
	 */
	public void setClienteSelecionado(Cliente clienteSelecionado) {
		if (clienteSelecionado != null)
			this.clienteSelecionado = clienteSelecionado;
	}

	/**
	 * @return the selecionado
	 */
	public boolean isSelecionado() {
		return selecionado;
	}

	/**
	 * @param selecionado
	 *            the selecionado to set
	 */
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

}
