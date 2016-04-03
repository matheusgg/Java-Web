package br.com.paginacao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyModel<T, E> extends LazyDataModel<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7109389974845828299L;
	private E dao;
	private HashMap<String, String> filtros;
	private String methodToCall;

	public LazyModel(E dao) {
		this.dao = dao;
	}

	@Override
	public PaginatorList<T> load(int startIndex, int pageSize, String arg2, SortOrder arg3, Map<String, String> arg4) {
		PaginatorList<T> lista = new PaginatorList<T>();
		try {
			lista = this.invokeMethod(startIndex, pageSize, this.methodToCall);
			super.setRowCount(lista.getRowCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	private PaginatorList<T> invokeMethod(int startIndex, int pageSize, String methodName) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		PaginatorList<T> lista = null;
		Class<E> dao = (Class<E>) this.dao.getClass();
		Method[] methods = dao.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				lista = (PaginatorList<T>) method.invoke(this.dao, startIndex, pageSize, this.filtros);
			}
		}
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

}
