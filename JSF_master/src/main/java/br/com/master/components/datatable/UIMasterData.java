package br.com.master.components.datatable;

import javax.el.MethodExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIData;

@FacesComponent("master.UIMasterDatatable")
public class UIMasterData extends UIData {

	private MethodExpression paginatorMethod;
	private int rowsPerPage;

	public UIMasterData() {
		super.setRendererType("master.UIMasterDatatable");
		super.getStateHelper().put("currentPageIndex", 0);
		super.getStateHelper().put("startIndex", 0l);
		super.getStateHelper().put("totalRows", 0l);
		super.getStateHelper().put("pageCount", 0);
		super.getStateHelper().put("alreadyRenderedOnClient", false);
	}

	/**
	 * @return the paginatorMethod
	 */
	public MethodExpression getPaginatorMethod() {
		return paginatorMethod;
	}

	/**
	 * @param paginatorMethod
	 *            the paginatorMethod to set
	 */
	public void setPaginatorMethod(MethodExpression paginatorMethod) {
		this.paginatorMethod = paginatorMethod;
	}

	/**
	 * @return the rowsPerPage
	 */
	public int getRowsPerPage() {
		return rowsPerPage;
	}

	/**
	 * @param rowsPerPage
	 *            the rowsPerPage to set
	 */
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	/**
	 * @return the startIndex
	 */
	public long getStartIndex() {
		return (long) super.getStateHelper().get("startIndex");
	}

	/**
	 * @param startIndex
	 *            the startIndex to set
	 */
	public void setStartIndex(long startIndex) {
		super.getStateHelper().put("startIndex", startIndex);
	}

	/**
	 * @return the pageCount
	 */
	public int getPageCount() {
		return (int) super.getStateHelper().get("pageCount");
	}

	/**
	 * @param pageCount
	 *            the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		super.getStateHelper().put("pageCount", pageCount);
	}

	/**
	 * @return the totalRows
	 */
	public long getTotalRows() {
		return (long) super.getStateHelper().get("totalRows");
	}

	/**
	 * @param totalRows
	 *            the totalRows to set
	 */
	public void setTotalRows(long totalRows) {
		super.getStateHelper().put("totalRows", totalRows);
	}

	/**
	 * @return the currentPageIndex
	 */
	public int getCurrentPageIndex() {
		return (int) super.getStateHelper().get("currentPageIndex");
	}

	/**
	 * @param currentPageIndex
	 *            the currentPageIndex to set
	 */
	public void setCurrentPageIndex(int currentPageIndex) {
		super.getStateHelper().put("currentPageIndex", currentPageIndex);
	}

	/**
	 * @return the alreadyRenderedOnClient
	 */
	public boolean isAlreadyRenderedOnClient() {
		return (boolean) super.getStateHelper().get("alreadyRenderedOnClient");
	}

	/**
	 * @param alreadyRenderedOnClient
	 *            the alreadyRenderedOnClient to set
	 */
	public void setAlreadyRenderedOnClient(boolean alreadyRenderedOnClient) {
		super.getStateHelper().put("alreadyRenderedOnClient", alreadyRenderedOnClient);
	}

	/**
	 * @return the selection
	 */
	public Object getSelection() {
		return super.getValueExpression("selection").getValue(super.getFacesContext().getELContext());
	}

	/**
	 * @param selection
	 *            the selection to set
	 */
	public void setSelection(Object selection) {
		super.getValueExpression("selection").setValue(super.getFacesContext().getELContext(), selection);
	}
}
