package br.com.master.treeView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import br.com.master.treeView.annotations.TreeViewElement;
import br.com.master.treeView.annotations.TreeViewNode;

final class TreeViewBuilderHelper {

	public void createTreeNode(List<?> elements, TreeNode parent) throws IllegalArgumentException, IllegalAccessException {
		for (int index = 0; elements != null && index < elements.size(); index++) {
			this.createTreeNodeElement(elements.get(index), parent);
		}
	}

	private void createTreeNodeElement(Object element, TreeNode parent) throws IllegalArgumentException, IllegalAccessException {
		Object[] elementValues = this.getElementValues(element);

		Object treeElementValue = elementValues[0];
		Object treeNodeValue = elementValues[1];
		TreeViewElement treeViewElement = (TreeViewElement) elementValues[2];

		if (treeElementValue != null) {
			String id = UUID.randomUUID().toString().substring(0, 8);

			TreeNode treeNode = new TreeNode(element, new JSONObject(), new ArrayList<TreeNode>());
			treeNode.setId(id);

			treeNode.getJsonObject().accumulate("id", id);
			treeNode.getJsonObject().accumulate("name", "master:treeView:" + id);
			treeNode.getJsonObject().accumulate("element", treeElementValue);

			this.handleCheckbox(treeViewElement, treeNode);
			this.handleChildren(treeNodeValue, treeNode);

			parent.getJsonObject().getJSONArray("children").add(treeNode.getJsonObject());
			parent.getChildren().add(treeNode);
		}
	}

	/**
	 * @param treeViewElement
	 * @param treeNode
	 */
	private void handleCheckbox(TreeViewElement treeViewElement, TreeNode treeNode) {
		if (treeViewElement.useCheckbox()) {
			treeNode.getJsonObject().accumulate("useCheckbox", true);
		} else {
			treeNode.getJsonObject().accumulate("useCheckbox", false);
		}

		treeNode.setSelected(false);
		treeNode.getJsonObject().accumulate("selected", false);
	}

	/**
	 * @param treeNodeValue
	 * @param treeNode
	 * @throws IllegalAccessException
	 */
	private void handleChildren(Object treeNodeValue, TreeNode treeNode) throws IllegalAccessException {
		if (treeNodeValue != null && treeNodeValue instanceof List) {
			treeNode.getJsonObject().accumulate("children", new JSONArray());
			this.createTreeNode((List<?>) treeNodeValue, treeNode);
		}
	}

	private Object[] getElementValues(Object element) throws IllegalArgumentException, IllegalAccessException {
		Object[] elementValues = new Object[3];

		Class<?> elementClazz = element.getClass();
		Field[] elementFields = elementClazz.getDeclaredFields();

		for (Field field : elementFields) {
			field.setAccessible(true);
			Object value = field.get(element);

			if (value != null) {
				if (field.isAnnotationPresent(TreeViewElement.class)) {
					elementValues[0] = value;
					elementValues[2] = field.getAnnotation(TreeViewElement.class);

				} else if (field.isAnnotationPresent(TreeViewNode.class)) {
					elementValues[1] = value;
				}
			}
		}

		return elementValues;
	}

	public List<TreeNode> getSelectedElements(List<TreeNode> elements, Map<String, String> requestMap) {
		List<TreeNode> selectedElements = new ArrayList<>();

		for (Entry<String, String> entry : requestMap.entrySet()) {
			if (entry.getKey().startsWith("master:treeView:") && "on".equals(entry.getValue())) {

				String id = entry.getKey().split(":")[2];
				TreeNode treeNode = this.findNodeById(elements, id);

				if (treeNode != null) {
					treeNode.setSelected(true);
					selectedElements.add(treeNode);
				}
			}
		}

		return selectedElements;
	}

	private TreeNode findNodeById(List<TreeNode> elements, String id) {
		TreeNode node = null;

		for (TreeNode treeNode : elements) {
			treeNode.setSelected(false);

			if (treeNode.getId().equals(id)) {
				node = treeNode;
				break;

			} else if (!treeNode.getChildren().isEmpty()) {
				node = this.findNodeById(treeNode.getChildren(), id);

				if (node != null) {
					break;
				}
			}
		}

		return node;
	}

	public String updateTreeData(List<TreeNode> elements, Map<String, String> requestMap, String jsonString) {
		List<TreeNode> selectedElements = this.getSelectedElements(elements, requestMap);
		Set<String> selectedElementsId = new HashSet<>();

		JSONObject root = JSONObject.fromObject(jsonString);
		JSONArray children = root.getJSONArray("children");

		if (!selectedElements.isEmpty()) {
			for (TreeNode node : selectedElements) {
				selectedElementsId.add(node.getId());
			}
		}

		this.updateSelectedElements(selectedElementsId, children);
		root.element("children", children);

		return root.toString();
	}

	/**
	 * @param selectedElementsId
	 * @param children
	 */
	private void updateSelectedElements(Set<String> selectedElementsId, JSONArray children) {
		ListIterator<?> iterator = children.listIterator();

		while (iterator.hasNext()) {
			JSONObject jsonObject = (JSONObject) iterator.next();

			if (selectedElementsId.contains(jsonObject.getString("id"))) {
				jsonObject.element("selected", true);
			} else {
				jsonObject.element("selected", false);
			}

			if (jsonObject.containsKey("children")) {
				this.updateSelectedElements(selectedElementsId, jsonObject.getJSONArray("children"));
			}
		}
	}
}
