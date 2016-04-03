package br.com.lazy.datamodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.lazy.model.Cliente;
import br.com.lazy.model.dao.ClienteDAO;

public class TableDataModel extends LazyDataModel<Cliente> {
	private ClienteDAO clienteDAO;
	private HashMap<String, String> filtros;

	public TableDataModel() {
		this.clienteDAO = new ClienteDAO();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4960969593813816885L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		HashMap<String, Object> retorno = this.clienteDAO.busca(this.filtros, first, pageSize);
		super.setRowCount((Integer) retorno.get("count"));
		List<Cliente> lista = (List<Cliente>) retorno.get("lista");
		return lista;
	}

	/**
	 * @return the filtros
	 */
	public HashMap<String, String> getFiltros() {
		return filtros;
	}

	/**
	 * @param filtros
	 *            the filtros to set
	 */
	public void setFiltros(HashMap<String, String> filtros) {
		this.filtros = filtros;
	}

}
