package br.com.ok.util.pagination;

import java.io.Serializable;

/**
 * The Interface IOKDefaultPaginator.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 * @param <T>
 *            the generic type
 */
@FunctionalInterface
public interface IOKDefaultPaginator<T> extends Serializable {

	/**
	 * Paginate.
	 *
	 * @param firstResult
	 *            the first result
	 * @param maxResult
	 *            the max results
	 * @return the list
	 */
	OKPaginatedList<T> paginate(int firstResult, int maxResult);

}
