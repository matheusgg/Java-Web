package functions;

public class FunctionsFactory {

	/**
	 * Para ser mapeado e utilizado como uma EL function, o metodo deve ser
	 * estatico. Caso nao possua retorno, quando a expressao for avaliada o
	 * valor null sera retornado.
	 * 
	 * @param value
	 * @param contains
	 * @return
	 */
	public static boolean containsWord(String value, String contains) {
		return value.contains(contains);
	}

}
