package financeiro.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.chart.PieChartModel;

import financeiro.bolsa.acao.Acao;
import financeiro.bolsa.acao.AcaoRN;
import financeiro.bolsa.acao.AcaoVirtual;
import financeiro.util.RNException;
import financeiro.web.util.YahooFinanceUtil;

@ManagedBean
@RequestScoped
public class AcaoBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1461128265659969371L;
	private AcaoVirtual selecionada = new AcaoVirtual();
	private PieChartModel graficoAcoesQuantidade = null;
	private PieChartModel graficoAcoesValor = null;
	private List<AcaoVirtual> lista = null;
	private String linkCodigoAcao = null;

	public void limpaDados() {
		this.selecionada = new AcaoVirtual();
		this.lista = null;
	}

	public String salvar() {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		Acao acao = this.selecionada.getAcao();
		acao.setSigla(acao.getSigla().toUpperCase());
		acao.setUsuario(contextoBean.getUsuarioLogado());
		new AcaoRN().salvar(acao);
		this.limpaDados();
		return "";
	}

	public void excluir() {
		new AcaoRN().excluir(this.selecionada.getAcao());
		this.limpaDados();
	}

	public PieChartModel getGraficoAcoesQuantidade() {
		this.graficoAcoesQuantidade = new PieChartModel();
		for (AcaoVirtual acao : this.getLista()) {
			this.graficoAcoesQuantidade.set(acao.getAcao().getSigla(), acao
					.getAcao().getQuantidade());
		}
		return graficoAcoesQuantidade;
	}

	public PieChartModel getGraficoAcoesValor() {
		this.graficoAcoesValor = new PieChartModel();
		for (AcaoVirtual acao : this.getLista()) {
			this.graficoAcoesValor.set(acao.getAcao().getSigla(),
					acao.getTotal());
		}
		return graficoAcoesValor;
	}

	public List<AcaoVirtual> getLista() {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		try {
			if (this.lista == null) {
				this.lista = new AcaoRN().listarAcaoVirtual(contextoBean
						.getUsuarioLogado());
			}
		} catch (RNException ex) {
			new RNException(
					"Erro ao listar as ações. Por favor, Tente novamente.");
			System.err.println(ex.getMessage());
		}
		return this.lista;
	}

	public String getLinkCodigoAcao() {
		if (this.selecionada != null) {
			this.linkCodigoAcao = new AcaoRN().montaLinkAcao(this.selecionada
					.getAcao());
		} else {
			this.linkCodigoAcao = YahooFinanceUtil.INDICE_BOVESPA;
		}
		return linkCodigoAcao;
	}

	public AcaoVirtual getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(AcaoVirtual selecionada) {
		this.selecionada = selecionada;
	}

	public void setLista(List<AcaoVirtual> lista) {
		this.lista = lista;
	}

	public void setLinkCodigoAcao(String linkCodigoAcao) {
		this.linkCodigoAcao = linkCodigoAcao;
	}

	public void setGraficoAcoesQuantidade(PieChartModel graficoAcoesQuantidade) {
		this.graficoAcoesQuantidade = graficoAcoesQuantidade;
	}

	public void setGraficoAcoesValor(PieChartModel graficoAcoesValor) {
		this.graficoAcoesValor = graficoAcoesValor;
	}

}
