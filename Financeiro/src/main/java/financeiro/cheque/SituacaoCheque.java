package financeiro.cheque;

public enum SituacaoCheque {
	NAO_EMITIDO('N'), CANCELADO('C'), BAIXADO('B');

	private final char situacao;

	private SituacaoCheque(char situacao) {
		this.situacao = situacao;
	}

	public char getSituacao() {
		return situacao;
	}
}
