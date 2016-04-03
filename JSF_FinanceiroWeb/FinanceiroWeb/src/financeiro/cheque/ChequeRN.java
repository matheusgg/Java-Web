package financeiro.cheque;

import java.util.Date;
import java.util.List;

import financeiro.conta.Conta;
import financeiro.lancamento.Lancamento;
import financeiro.util.DAOFactory;
import financeiro.util.RNException;

public class ChequeRN {
	private ChequeDAO chequeDAO;

	public ChequeRN() {
		this.chequeDAO = DAOFactory.criarChequeDAO();
	}

	public void salvar(Cheque cheque) {
		this.chequeDAO.salvar(cheque);
	}

	public int salvarSequencia(Conta conta, Integer chequeInicial,
			Integer chequeFinal) {
		Cheque cheque = null;
		Cheque chequeVerifica = null;
		ChequeId chequeId = null;
		int contaTotal = 0;

		for (int i = chequeInicial; i <= chequeFinal; i++) {
			chequeId = new ChequeId();
			chequeId.setCheque(i);
			chequeId.setConta(conta.getConta().intValue());
			chequeVerifica = this.carregar(chequeId);

			if (chequeVerifica == null) {
				cheque = new Cheque();
				cheque.setChequeId(chequeId);
				cheque.setSituacao(SituacaoCheque.NAO_EMITIDO.getSituacao());
				cheque.setDataCadastro(new Date(System.currentTimeMillis()));
				this.salvar(cheque);
				contaTotal++;
			}
		}
		return contaTotal;
	}

	public void excluir(Cheque cheque) throws RNException {
		if (cheque.getSituacao() == SituacaoCheque.NAO_EMITIDO.getSituacao()) {
			this.chequeDAO.excluir(cheque);
		} else {
			throw new RNException(
					"Não é possível excluir este cheque! Status não permitido para operação.");
		}
	}

	public Cheque carregar(ChequeId chequeId) {
		return this.chequeDAO.carregar(chequeId);
	}

	public List<Cheque> listar(Conta conta) {
		return this.chequeDAO.listar(conta);
	}

	public void cancelarCheque(Cheque cheque) throws RNException {
		if (cheque.getSituacao() == SituacaoCheque.NAO_EMITIDO.getSituacao()
				|| cheque.getSituacao() == SituacaoCheque.CANCELADO
						.getSituacao()) {
			cheque.setSituacao(SituacaoCheque.CANCELADO.getSituacao());
			this.chequeDAO.salvar(cheque);
		} else {
			throw new RNException(
					"Não é possível cancelar este cheque! Status não permitido para operação.");
		}
	}

	public void baixarCheque(ChequeId chequeId, Lancamento lancamento) {
		Cheque cheque = this.carregar(chequeId);
		if (cheque != null) {
			cheque.setSituacao(SituacaoCheque.BAIXADO.getSituacao());
			cheque.setLancamento(lancamento);
			this.chequeDAO.salvar(cheque);
		}
	}

	public void desvinculaLancamento(Conta conta, Integer numeroCheque)
			throws RNException {
		ChequeId chequeId = new ChequeId();
		chequeId.setCheque(numeroCheque);
		chequeId.setConta(conta.getConta().intValue());
		Cheque cheque = this.carregar(chequeId);

		if (cheque.getSituacao() == SituacaoCheque.CANCELADO.getSituacao()) {
			throw new RNException(
					"Não é possível utilizar este cheque cancelado.");
		} else {
			cheque.setSituacao(SituacaoCheque.NAO_EMITIDO.getSituacao());
			cheque.setLancamento(null);
			this.salvar(cheque);
		}
	}

}
