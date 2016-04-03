function desabilitaBotaoRemover(){
	if(document.getElementById("form:btnRemover").disabled == true){
		document.getElementById("form:btnRemover").disabled = false;
	}else{
		document.getElementById("form:btnRemover").disabled = true;
	}
	return false;
}