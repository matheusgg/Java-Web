package br.com.master.components.column;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIColumn;

@FacesComponent("master.UIMasterColumn")
public class UIMasterColumn extends UIColumn {

	private String headerText;

	public UIMasterColumn() {
		super.setRendererType("master.UIMasterColumn");
	}

	/**
	 * @return the headerText
	 */
	public String getHeaderText() {
		if (this.headerText == null) {
			return (String) super.getValueExpression("headerText").getValue(super.getFacesContext().getELContext());
		}
		return headerText;
	}

	/**
	 * @param headerText
	 *            the headerText to set
	 */
	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

}
