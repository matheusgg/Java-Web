package br.com.ok.util.pagination;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class OKPaginatedList.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 * @param <E>
 *            the element type
 */
public class OKPaginatedList<E> extends ArrayList<E> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2770998694986954186L;

	/** The total rows. */
	@Getter
	@Setter
	private Long rows;

	/**
	 * Instantiates a new OK paginated list.
	 */
	public OKPaginatedList() {

	}

	/**
	 * Instantiates a new OK paginated list.
	 *
	 * @param c
	 *            the c
	 */
	public OKPaginatedList(Collection<? extends E> c) {
		super(c);
	}

	/**
	 * Instantiates a new OK paginated list.
	 *
	 * @param c
	 *            the c
	 * @param totalRows
	 *            the total rows
	 */
	public OKPaginatedList(Collection<? extends E> c, Long rows) {
		super(c);
		this.rows = rows;
	}

}
