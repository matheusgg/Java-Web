package br.com.paginacao;

import java.util.ArrayList;

public class PaginatorList<T> extends ArrayList<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3484368634393581847L;
	private int rowCount;

	/**
	 * @return the rowCount
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * @param rowCount
	 *            the rowCount to set
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

}
