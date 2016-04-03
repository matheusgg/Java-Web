function atualizaValor(elementId, valor, max, min) {
	var input = document.getElementById(elementId);
	var oldValue = parseInt(input.value == "" ? "0" : input.value);
	var newValue = oldValue + valor;
	if (newValue >= min && newValue <= max) {
		input.value = newValue;
	}
}