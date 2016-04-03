package br.com.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.datatable.DataTable;

import br.com.model.Categoria;
import br.com.model.dao.CategoriaDAO;
import br.com.paginacao.LazyModel;

@Named
@SessionScoped
public class AppBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7076910215674043728L;

	private Map<String, Object> filtros;

	private LazyModel<Categoria, CategoriaDAO> categorias;

	@Inject
	private CategoriaDAO categoriaDAO;

	@PostConstruct
	public void inicializaAtributos() {
		this.filtros = new HashMap<>();
		this.categorias = new LazyModel<Categoria, CategoriaDAO>(this.categoriaDAO, this.filtros, "pesquisaClientes");

	}

	public void pesquisa() {
		((DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("form:table")).setFirst(0);
	}

	/**
	 * @return the filtros
	 */
	public Map<String, Object> getFiltros() {
		return filtros;
	}

	/**
	 * @param filtros
	 *            the filtros to set
	 */
	public void setFiltros(Map<String, Object> filtros) {
		this.filtros = filtros;
	}

	/**
	 * @return the categorias
	 */
	public LazyModel<Categoria, CategoriaDAO> getCategorias() {
		return categorias;
	}

	/**
	 * @param categorias
	 *            the categorias to set
	 */
	public void setCategorias(LazyModel<Categoria, CategoriaDAO> categorias) {
		this.categorias = categorias;
	}

}
