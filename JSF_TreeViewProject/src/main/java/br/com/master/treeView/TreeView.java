package br.com.master.treeView;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class TreeView {

	private List<TreeNode> elements;
	private String jsonString;
	private TreeViewBuilderHelper builder;

	public TreeView(List<TreeNode> elements, String jsonString, TreeViewBuilderHelper builder) {
		this.elements = elements;
		this.jsonString = jsonString;
		this.builder = builder;
	}

	public void addTreeToView(FacesContext facesContext) {
		if (facesContext == null) {
			throw new IllegalArgumentException("FacesContext nao pode ser nulo!");
		}

		Map<String, String> requestParameterMap = facesContext.getExternalContext().getRequestParameterMap();
		this.jsonString = this.builder.updateTreeData(this.elements, requestParameterMap, this.jsonString);
		((HttpServletRequest) facesContext.getExternalContext().getRequest()).setAttribute("master.treeView.data", this.jsonString);
	}

	public List<TreeNode> getSelectedElements(FacesContext facesContext) {
		if (facesContext == null) {
			throw new IllegalArgumentException("FacesContext nao pode ser nulo!");
		}

		Map<String, String> requestParameterMap = facesContext.getExternalContext().getRequestParameterMap();
		return this.builder.getSelectedElements(this.elements, requestParameterMap);
	}

	/**
	 * @return the elements
	 */
	public List<TreeNode> getElements() {
		return elements;
	}

	/**
	 * @return the jsonString
	 */
	public String getJsonString() {
		return jsonString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.jsonString;
	}

}
