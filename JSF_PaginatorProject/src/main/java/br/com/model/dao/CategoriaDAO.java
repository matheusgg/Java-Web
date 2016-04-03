package br.com.model.dao;

import java.util.HashMap;

import org.hibernate.Criteria;

import br.com.model.Categoria;
import br.com.paginacao.PaginatorList;
import br.com.utils.TipoConsulta;

public class CategoriaDAO extends AbstractDAO<Categoria> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9216750810975436528L;

	public PaginatorList<Categoria> pesquisaClientes(int startIndex, int pageSize, HashMap<String, Object> filtros) {
		Criteria criteria = super.prepareCriteria(startIndex, pageSize, filtros, TipoConsulta.LIKE);

		PaginatorList<Categoria> categorias = new PaginatorList<>(super.list(criteria), super.count(criteria));

		return categorias;
	}
}
