package financeiro.webservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import financeiro.conta.Conta;
import financeiro.conta.ContaRN;
import financeiro.lancamento.Lancamento;
import financeiro.lancamento.LancamentoRN;
import financeiro.util.RNException;

@WebService
public class FinanceiroWS {

	@WebMethod
	public float saldo(@WebParam(name = "conta") int conta,
			@WebParam(name = "dataSaldo") String data) {

		LancamentoRN lancamentoRN = new LancamentoRN();
		ContaRN contaRN = new ContaRN();

		Conta contaPesquisada = contaRN.carregar(new Integer(conta));
		Float saldo = null;
		
		String[] dataSaldo = data.split("/");
		Calendar dataFinal = new GregorianCalendar();
		dataFinal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dataSaldo[0]));
		dataFinal.set(Calendar.MONTH, Integer.parseInt(dataSaldo[1]) - 1);
		dataFinal.set(Calendar.YEAR, Integer.parseInt(dataSaldo[2]));
		
		try {
			saldo = lancamentoRN.saldo(contaPesquisada, dataFinal.getTime());
		} catch (RNException ex) {
			ex.printStackTrace();
		}
		return saldo.floatValue();
	}

	@WebMethod
	public List<LancamentoItem> extrato(@WebParam(name = "conta") int conta,
			@WebParam(name = "de") Date de, @WebParam(name = "ate") Date ate) {

		List<LancamentoItem> retorno = new ArrayList<LancamentoItem>();
		LancamentoItem lancamentoItem = null;

		Conta contaPesquisada = new ContaRN().carregar(new Integer(conta));
		List<Lancamento> listaLancamentos = new LancamentoRN().listar(
				contaPesquisada, de, ate);

		for (Lancamento lancamento : listaLancamentos) {
			lancamentoItem = new LancamentoItem();
			lancamentoItem.setData(lancamento.getData());
			lancamentoItem.setDescricao(lancamento.getDescricao());
			lancamentoItem.setValor(lancamento.getValor().floatValue());
			retorno.add(lancamentoItem);
		}

		return retorno;
	}

}
