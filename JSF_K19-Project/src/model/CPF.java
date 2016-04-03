package model;

public class CPF {
	private int numeroIdentificacao;
	private int primeiroDigitoVerificador;
	private int segundoDigitoVerificador;

	/**
	 * @return the numeroIdentificacao
	 */
	public int getNumeroIdentificacao() {
		return numeroIdentificacao;
	}

	/**
	 * @param numeroIdentificacao
	 *            the numeroIdentificacao to set
	 */
	public void setNumeroIdentificacao(int numeroIdentificacao) {
		this.numeroIdentificacao = numeroIdentificacao;
	}

	/**
	 * @return the primeiroDigitoVerificador
	 */
	public int getPrimeiroDigitoVerificador() {
		return primeiroDigitoVerificador;
	}

	/**
	 * @param primeiroDigitoVerificador
	 *            the primeiroDigitoVerificador to set
	 */
	public void setPrimeiroDigitoVerificador(int primeiroDigitoVerificador) {
		this.primeiroDigitoVerificador = primeiroDigitoVerificador;
	}

	/**
	 * @return the segundoDigitoVerificador
	 */
	public int getSegundoDigitoVerificador() {
		return segundoDigitoVerificador;
	}

	/**
	 * @param segundoDigitoVerificador
	 *            the segundoDigitoVerificador to set
	 */
	public void setSegundoDigitoVerificador(int segundoDigitoVerificador) {
		this.segundoDigitoVerificador = segundoDigitoVerificador;
	}

}
