package financeiro.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.StreamedContent;

import financeiro.conta.Conta;
import financeiro.conta.ContaRN;
import financeiro.web.util.Relatorio;
import financeiro.web.util.RelatorioUtil;

@ManagedBean
@RequestScoped
public class ContaBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3856904946729457380L;
	private Conta selecionada = new Conta();
	private List<Conta> lista = null;
	private StreamedContent arquivoRetorno;
	private int tipoRelatorio;

	public void salvar() {
		ContextoBean contextBean = ContextoUtil.getContextoBean();
		this.selecionada.setUsuario(contextBean.getUsuarioLogado());
		new ContaRN().salvar(this.selecionada);
		this.selecionada = new Conta();
		this.lista = null;
	}

	public void editar() {

	}

	public void excluir() {
		new ContaRN().excluir(this.selecionada);
		this.selecionada = new Conta();
		this.lista = null;
	}

	public void tornarFavorita() {
		new ContaRN().tornarFavorita(this.selecionada);
		this.selecionada = new Conta();

	}

	private Relatorio verificaTipoRelatorio() {
		Relatorio tipo = null;
		switch (this.tipoRelatorio) {
		case 1:
			tipo = Relatorio.PDF;
			break;
		case 2:
			tipo = Relatorio.EXCEL;
			break;
		case 3:
			tipo = Relatorio.HTML;
			break;
		case 4:
			tipo = Relatorio.PLANILHA_OPEN_OFFICE;
			break;
		default:
			tipo = Relatorio.PDF;
			break;
		}
		return tipo;
	}

	public Conta getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Conta selecionada) {
		this.selecionada = selecionada;
	}

	public List<Conta> getLista() {
		if (this.lista == null) {
			this.lista = new ContaRN().listar(ContextoUtil.getContextoBean()
					.getUsuarioLogado());
		}
		return lista;
	}

	public void setLista(List<Conta> lista) {
		this.lista = lista;
	}

	public StreamedContent getArquivoRetorno() {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();

		String usuario = contextoBean.getUsuarioLogado().getLogin();
		String nomeRelatorioJasper = "contas";
		String nomeRelatorioSaida = "Relatorio_de_Contas_" + usuario;

		HashMap<String, Object> parametrosRelatorio = new HashMap<String, Object>();
		parametrosRelatorio.put("codigoUsuario", contextoBean
				.getUsuarioLogado().getCodigo());

		this.arquivoRetorno = new RelatorioUtil().geraRelatorio(
				parametrosRelatorio, nomeRelatorioJasper, nomeRelatorioSaida,
				this.verificaTipoRelatorio());
		
		return arquivoRetorno;
	}

	public void setArquivoRetorno(StreamedContent arquivoRetorno) {
		this.arquivoRetorno = arquivoRetorno;
	}

	public int getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(int tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
}
