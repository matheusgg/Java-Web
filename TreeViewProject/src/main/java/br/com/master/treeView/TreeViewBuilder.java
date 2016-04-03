package br.com.master.treeView;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public final class TreeViewBuilder {

	private TreeViewBuilder() {

	}

	public static TreeView buildTreeView(List<?> elements) {
		TreeView treeView = null;

		try {

			JSONObject jsonChildren = new JSONObject().accumulate("children", new JSONArray());
			TreeNode root = new TreeNode(null, jsonChildren, new ArrayList<TreeNode>());

			TreeViewBuilderHelper helper = new TreeViewBuilderHelper();
			helper.createTreeNode(elements, root);

			treeView = new TreeView(root.getChildren(), root.getJsonObject().toString(), helper);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return treeView;
	}
}
