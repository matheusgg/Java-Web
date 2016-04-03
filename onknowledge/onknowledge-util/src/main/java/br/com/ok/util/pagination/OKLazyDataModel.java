package br.com.ok.util.pagination;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.ok.util.constants.OKConstants;

/**
 * The Class OKLazyDataModel.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 * @param <T>
 *            the generic type
 */
public class OKLazyDataModel<T> extends LazyDataModel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1734230812606043788L;

	/** The default paginator. */
	private IOKDefaultPaginator<T> defaultPaginator;

	/**
	 * Instantiates a new OK lazy data model.
	 *
	 * @param defaultPaginator
	 *            the default paginator
	 */
	public OKLazyDataModel(IOKDefaultPaginator<T> defaultPaginator) {
		this.defaultPaginator = defaultPaginator;
	}

	/**
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String,
	 *      org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		if (super.getWrappedData() != null && !FacesContext.getCurrentInstance().isValidationFailed()) {
			return this.handleNullOrEmptySearch(this.defaultPaginator.paginate(first, pageSize));
		} else {
			return this.handleNullOrEmptySearch(null);
		}
	}

	/**
	 * Handle null or empty search.
	 *
	 * @param paginatedList
	 *            the paginated list
	 * @return the OK paginated list
	 */
	private OKPaginatedList<T> handleNullOrEmptySearch(OKPaginatedList<T> paginatedList) {
		if (paginatedList == null || paginatedList.isEmpty()) {
			super.setRowCount(OKConstants.VALOR_ZERO);
			return new OKPaginatedList<>();
		}
		super.setRowCount(paginatedList.getRows().intValue());
		return paginatedList;
	}

}
