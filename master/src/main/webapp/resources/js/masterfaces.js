function selectMasterDataTableRow(tableId, tr) {
	var id = "#" + tableId + " tbody tr";
	var trs = $(id.replace(":", "\\:"));
	for ( var i = 0; i < trs.length; i++) {
		if ($(trs[i]).hasClass("selectedMasterDataTableRow")) {
			$(trs[i]).removeClass("selectedMasterDataTableRow");
		}
	}
	$(tr).addClass("selectedMasterDataTableRow");
}