package financeiro.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.model.StreamedContent;

import financeiro.categoria.Categoria;
import financeiro.cheque.Cheque;
import financeiro.cheque.ChequeId;
import financeiro.cheque.ChequeRN;
import financeiro.cheque.SituacaoCheque;
import financeiro.conta.Conta;
import financeiro.entidade.Entidade;
import financeiro.entidade.EntidadeRN;
import financeiro.lancamento.Lancamento;
import financeiro.lancamento.LancamentoRN;
import financeiro.util.RNException;
import financeiro.util.UtilException;
import financeiro.web.util.Relatorio;
import financeiro.web.util.RelatorioUtil;

@ManagedBean
@ViewScoped
public class LancamentoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6646738159894374132L;

	private List<Lancamento> lista;
	private List<Double> saldos;
	private float saldoGeral;
	private Lancamento editado = new Lancamento();
	private List<Lancamento> listaAteHoje;
	private List<Lancamento> listaFuturos;
	private Integer numeroCheque;
	private Date dataInicialRelatorio;
	private Date dataFinalRelatorio;
	private StreamedContent arquivoRelatorio;

	public LancamentoBean() {
		this.novo();
	}

	public void novo() {
		this.editado = new Lancamento();
		this.editado.setData(new Date(System.currentTimeMillis()));
		this.editado.setEntidade(new Entidade());
		this.numeroCheque = null;
	}

	public void editar() {
		Cheque cheque = this.editado.getCheque();
		if (cheque != null) {
			this.numeroCheque = cheque.getChequeId().getCheque();
		}
	}

	public void salvar() {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		this.editado.setUsuario(contextoBean.getUsuarioLogado());
		this.editado.setConta(contextoBean.getContaAtiva());

		EntidadeRN entidadeRN = new EntidadeRN();
		Entidade entidade = entidadeRN.buscar(this.editado.getEntidade()
				.getNome());

		if (entidade == null) {
			entidadeRN
					.salvar(new Entidade(this.editado.getEntidade().getNome()));
			entidade = entidadeRN.buscar(this.editado.getEntidade().getNome());
		}

		this.editado.setEntidade(entidade);

		ChequeRN chequeRN = new ChequeRN();
		ChequeId chequeId = null;
		if (this.numeroCheque != null) {
			chequeId = new ChequeId();
			chequeId.setConta(contextoBean.getContaAtiva().getConta());
			chequeId.setCheque(this.numeroCheque);
			Cheque cheque = chequeRN.carregar(chequeId);
			FacesContext context = FacesContext.getCurrentInstance();
			if (cheque == null) {
				FacesMessage msg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Cheque não cadastrado",
						"");
				context.addMessage(null, msg);
			} else if (cheque.getSituacao() == SituacaoCheque.CANCELADO
					.getSituacao()) {
				FacesMessage msg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Cheque já cancelado", "");
				context.addMessage(null, msg);
			} else {
				this.editado.setCheque(cheque);
				chequeRN.baixarCheque(chequeId, this.editado);
			}
		}

		new LancamentoRN().salvar(this.editado);

		this.novo();
		this.lista = null;
		this.saldos = null;
	}

	public void mudouCheque(ValueChangeEvent event) {
		/*
		 * O método getOldValue captura o valor anterior informado no campo onde
		 * o evento está registrado. Este método funciona corretamente quando o
		 * bean possui um espoco de tempo de vida maior como session, view ou
		 * application.
		 */
		Integer chequeAnterior = (Integer) event.getOldValue();
		if (chequeAnterior != null) {
			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			ChequeRN chequeRN = new ChequeRN();
			try {
				chequeRN.desvinculaLancamento(contextoBean.getContaAtiva(),
						chequeAnterior);
			} catch (RNException ex) {
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Erro na operação. Tente novamente", "");
				context.addMessage(null, msg);
			}
		}
	}

	public void excluir() {
		LancamentoRN lancamentoRN = new LancamentoRN();
		this.editado = lancamentoRN.carregar(this.editado.getLancamento());
		lancamentoRN.excluir(this.editado);
		this.lista = null;
		this.saldos = null;
	}

	public List<Lancamento> getLista() {
		if (this.lista == null && this.saldos == null) {
			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			Conta conta = contextoBean.getContaAtiva();

			Calendar dataSaldo = new GregorianCalendar();
			/*
			 * Este método adiciona o resultado do mês atual subtraído de um mês
			 * no objeto dataSaldo
			 */
			dataSaldo.add(Calendar.MONTH, -1);
			/*
			 * Aqui, é adicionado o resultado do dia atual subtraído de um dia
			 * no objeto dataSaldo
			 */
			dataSaldo.add(Calendar.DAY_OF_MONTH, -1);

			Calendar dataFinal = new GregorianCalendar();

			LancamentoRN lancamentoRN = new LancamentoRN();
			try {
				this.saldoGeral = lancamentoRN
						.saldo(conta, dataSaldo.getTime());
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
			this.lista = lancamentoRN.listar(conta, null, dataFinal.getTime());

			Categoria categoria = null;
			this.saldos = new ArrayList<Double>();
			double saldo = this.saldoGeral;
			for (Lancamento lancamento : this.lista) {
				categoria = lancamento.getCategoria();
				saldo = saldo
						+ (lancamento.getValor().floatValue() * categoria
								.getFator());
				this.saldos.add(saldo);
			}
		}
		return this.lista;
	}
	
	private void geraRelatorioLancamentos(){
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		String usuario = contextoBean.getUsuarioLogado().getLogin();
		String nomeRelatorioJasper = "extrato";
		String nomeRelatorioSaida = "Extrato - " + usuario;
		LancamentoRN lancamentoRN = new LancamentoRN();
		Calendar calendario = new GregorianCalendar();
		calendario.setTime(this.getDataInicialRelatorio());
		calendario.add(Calendar.DAY_OF_MONTH, -1);
		Date dataSaldo = new Date(calendario.getTimeInMillis());
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigoUsuario", contextoBean.getUsuarioLogado().getCodigo());
		parametros.put("numeroConta", contextoBean.getContaAtiva().getConta());
		parametros.put("dataInicial", this.getDataInicialRelatorio());
		parametros.put("dataFinal", this.getDataFinalRelatorio());
		try{
			parametros.put("saldoAnterior", lancamentoRN.saldo(contextoBean.getContaAtiva(), dataSaldo));
			this.arquivoRelatorio = relatorioUtil.geraRelatorio(parametros, nomeRelatorioJasper, nomeRelatorioSaida, Relatorio.PDF);
		}catch(Exception ex){
			new UtilException(ex.getMessage());
		}		
	}	

	public List<Double> getSaldos() {
		return saldos;
	}

	public void setSaldos(List<Double> saldos) {
		this.saldos = saldos;
	}

	public float getSaldoGeral() {
		return saldoGeral;
	}

	public void setSaldoGeral(float saldoGeral) {
		this.saldoGeral = saldoGeral;
	}

	public Lancamento getEditado() {
		return editado;
	}

	public void setEditado(Lancamento editado) {
		this.editado = editado;
	}

	public void setLista(List<Lancamento> lista) {
		this.lista = lista;
	}

	public List<Lancamento> getListaAteHoje() {
		if (this.listaAteHoje == null) {
			Conta conta = ContextoUtil.getContextoBean().getContaAtiva();

			Calendar hoje = new GregorianCalendar();

			this.listaAteHoje = new LancamentoRN().listar(conta, null,
					hoje.getTime());
		}
		return listaAteHoje;
	}

	public void setListaAteHoje(List<Lancamento> listaAteHoje) {
		this.listaAteHoje = listaAteHoje;
	}

	public List<Lancamento> getListaFuturos() {
		if (this.listaFuturos == null) {
			Conta conta = ContextoUtil.getContextoBean().getContaAtiva();

			Calendar amanha = new GregorianCalendar();
			amanha.add(Calendar.DAY_OF_MONTH, 1);

			this.listaFuturos = new LancamentoRN().listar(conta,
					amanha.getTime(), null);
		}
		return listaFuturos;
	}

	public void setListaFuturos(List<Lancamento> listaFuturos) {
		this.listaFuturos = listaFuturos;
	}

	public Integer getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(Integer numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public Date getDataInicialRelatorio() {
		return dataInicialRelatorio;
	}

	public void setDataInicialRelatorio(Date dataInicialRelatorio) {
		this.dataInicialRelatorio = dataInicialRelatorio;
	}

	public Date getDataFinalRelatorio() {
		return dataFinalRelatorio;
	}

	public void setDataFinalRelatorio(Date dataFinalRelatorio) {
		this.dataFinalRelatorio = dataFinalRelatorio;
	}

	public StreamedContent getArquivoRelatorio() {
		this.geraRelatorioLancamentos();
		return arquivoRelatorio;
	}

	public void setArquivoRelatorio(StreamedContent arquivoRelatorio) {
		this.arquivoRelatorio = arquivoRelatorio;
	}

}
