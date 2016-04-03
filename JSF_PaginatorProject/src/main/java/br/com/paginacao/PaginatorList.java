package br.com.paginacao;

import java.util.ArrayList;
import java.util.List;

public class PaginatorList<T> extends ArrayList<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3484368634393581847L;
	private long rowCount;

	public PaginatorList() {

	}

	public PaginatorList(List<T> list) {
		super(list);
	}

	public PaginatorList(long rowCount) {
		this.rowCount = rowCount;
	}

	public PaginatorList(List<T> list, long rowCount) {
		super(list);
		this.rowCount = rowCount;
	}

	/**
	 * @return the rowCount
	 */
	public long getRowCount() {
		return rowCount;
	}

	/**
	 * @param rowCount
	 *            the rowCount to set
	 */
	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
	}

}
