package datatable;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

import model.Cliente;

@Named
@ViewScoped
public class TableBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6049107815633464945L;

	/**
	 * Recuperando a datasource configurada no glassfish
	 */
	@Resource(lookup = "jdbc/app_db")
	private DataSource dataSource;
	private CachedRowSet cachedRowSet;
	private List<Cliente> clientes;

	@PostConstruct
	public void init() {
		this.clientes = new ArrayList<>();
		Cliente cliente = null;
		for (int i = 0; i < 10; i++) {
			cliente = new Cliente((i + 1), "Cliente " + (i + 1), "Endereco " + (i + 1));
			this.clientes.add(cliente);
		}

		this.initDataSource();
	}

	@SuppressWarnings("restriction")
	public void initDataSource() {
		try (Connection connection = this.dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("select * from cliente");) {

			this.cachedRowSet = new com.sun.rowset.CachedRowSetImpl();
			this.cachedRowSet.populate(rs);

		} catch (Exception e) {
			FacesContext.getCurrentInstance().getExternalContext().log(e.getMessage());
		}
	}

	public void save() {
		for (Cliente cliente : this.clientes) {
			cliente.setSelected(false);
		}
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
	 * @return the cachedRowSet
	 */
	public CachedRowSet getCachedRowSet() {
		return cachedRowSet;
	}

	/**
	 * @param cachedRowSet
	 *            the cachedRowSet to set
	 */
	public void setCachedRowSet(CachedRowSet cachedRowSet) {
		this.cachedRowSet = cachedRowSet;
	}
}
