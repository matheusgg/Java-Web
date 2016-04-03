function createTreeView(jsonData){
	var $ul = jQuery("<ul>");
	createTreeNode(jsonData.children, $ul);
	$ul.appendTo("#treeView");
}

function createTreeNode(nodeData, ul){
	for(var i = 0; i < nodeData.length; i++){
		var element = nodeData[i];
		
		var label = element.element;
		var id = element.id;
		var name = element.name;
		var useCheckbox = element.useCheckbox;

		var li = jQuery("<li>").appendTo(ul);
		jQuery("<img>").attr("src", "/TreeViewProject/javax.faces.resource/icon-close.png.jsf?ln=img").appendTo(li);

		if(useCheckbox){
			var selected = element.selected;
			var $input = jQuery("<input>").attr("type", "checkbox").attr("id", id).attr("name", name);
			if(selected){
				$input.attr("checked", "checked");
			}
			$input.appendTo(li);
		}

		jQuery("<label>").text(label).attr("for", id).appendTo(li);

		if(element.children != null && element.children.length > 0){
			createTreeNode(element.children, jQuery("<ul>").appendTo(li));					
		}
	}
}