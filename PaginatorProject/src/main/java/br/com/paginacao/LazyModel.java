package br.com.paginacao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyModel<T, DAO> extends LazyDataModel<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7109389974845828299L;
	private DAO dao;
	private Map<String, Object> filtros;
	private String methodToCall;

	public LazyModel(DAO dao, Map<String, Object> filtros, String methodToCall) {
		this.dao = dao;
		this.filtros = filtros;
		this.methodToCall = methodToCall;
	}

	@Override
	public PaginatorList<T> load(int startIndex, int pageSize, String arg2, SortOrder arg3, Map<String, String> arg4) {
		PaginatorList<T> lista = new PaginatorList<T>();
		try {
			lista = this.invokeMethod(startIndex, pageSize, this.methodToCall);
			super.setRowCount((int) lista.getRowCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private PaginatorList<T> invokeMethod(int startIndex, int pageSize, String methodName) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		PaginatorList<T> lista = null;
		Class<DAO> dao = (Class<DAO>) this.dao.getClass();
		Method[] methods = dao.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				lista = (PaginatorList<T>) method.invoke(this.dao, startIndex, pageSize, this.filtros);
			}
		}
		return lista;
	}

	/**
	 * @return the methodToCall
	 */
	public String getMethodToCall() {
		return methodToCall;
	}

	/**
	 * @param methodToCall
	 *            the methodToCall to set
	 */
	public void setMethodToCall(String methodToCall) {
		this.methodToCall = methodToCall;
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

}
