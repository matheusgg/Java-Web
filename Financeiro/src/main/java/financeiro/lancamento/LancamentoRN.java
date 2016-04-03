package financeiro.lancamento;

import java.util.Date;
import java.util.List;

import financeiro.conta.Conta;
import financeiro.util.DAOFactory;
import financeiro.util.RNException;

public class LancamentoRN {
	private LancamentoDAO lancamentoDAO;

	public LancamentoRN() {
		this.lancamentoDAO = DAOFactory.criarLancamentoDAO();
	}

	public void salvar(Lancamento lancamento) {
		this.lancamentoDAO.salvar(lancamento);
	}

	public void excluir(Lancamento lancamento) {
		this.lancamentoDAO.excluir(lancamento);
	}

	public Lancamento carregar(Integer lancamento) {
		return this.lancamentoDAO.caregar(lancamento);
	}

	public float saldo(Conta conta, Date data) throws RNException {
		if (conta.getDataCadastro().after(data)) {
			throw new RNException("Operação não permitida. Data de solicitação menor do que a data de cadastro da conta.");
		} else {
			float saldoInicial = conta.getSaldoInicial();
			float saldoNaData = this.lancamentoDAO.saldo(conta, data);
			return saldoInicial + saldoNaData;
		}
	}

	public List<Lancamento> listar(Conta conta, Date dataInicio, Date dataFim) {
		return this.lancamentoDAO.listar(conta, dataInicio, dataFim);
	}
}
