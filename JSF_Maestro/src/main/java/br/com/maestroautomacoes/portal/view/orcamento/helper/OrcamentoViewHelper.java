package br.com.maestroautomacoes.portal.view.orcamento.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.maestroautomacoes.portal.model.orcamento.ItemOrcamento;
import br.com.maestroautomacoes.portal.model.orcamento.ItensDoOrcamento;
import br.com.maestroautomacoes.portal.model.orcamento.Orcamento;
import br.com.maestroautomacoes.portal.model.usuario.Usuario;
import br.com.maestroautomacoes.portal.security.SecurityManager;

public class OrcamentoViewHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4368211540919206605L;

	/*
	 * Atualização para persistencia em banco
	 */
	private Usuario usuario;

	private List<ItensDoOrcamento> itensDoOrcamento;
	private Orcamento novoOrcamento;
	private List<Integer> quantidade;
	private int quantidadeSelecionada;
	private String nomeCliente;
	private String emailCliente;
	private String campoDePesquisa;
	private List<ItemOrcamento> itensAmbiente;
	private List<ItemOrcamento> itensSom;
	private List<ItemOrcamento> itensSeguranca;
	private List<ItemOrcamento> itensVideo;
	private List<Orcamento> orcamentosCliente;
	private Orcamento orcamentoDetalhado;
	private ItensDoOrcamento itemDetalhado;
	private boolean copiaDoCliente;
	/*
	 * Parâmetros referentes ao drag and drop
	 */
	private String dataSource;
	private String tabToUpdate;

	public void inicializar() throws Exception {
		this.nomeCliente = "";
		this.emailCliente = "";
		this.campoDePesquisa = "Pesquise seu orçamento aqui!";
		this.itensAmbiente = new ArrayList<ItemOrcamento>();
		this.itensSeguranca = new ArrayList<ItemOrcamento>();
		this.itensSom = new ArrayList<ItemOrcamento>();
		this.itensVideo = new ArrayList<ItemOrcamento>();
		this.orcamentosCliente = new ArrayList<Orcamento>();
		this.orcamentoDetalhado = new Orcamento();
		this.tabToUpdate = "TabAmbiente";
		this.dataSource = ":tabView:dataGrid".concat(this.tabToUpdate);

		this.quantidade = new ArrayList<Integer>();
		this.usuario = SecurityManager.getUsuarioLogado();
		this.itensDoOrcamento = new ArrayList<ItensDoOrcamento>();
		this.novoOrcamento = new Orcamento();
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getCampoDePesquisa() {
		return campoDePesquisa;
	}

	public void setCampoDePesquisa(String campoDePesquisa) {
		this.campoDePesquisa = campoDePesquisa;
	}

	public List<ItemOrcamento> getItensAmbiente() {
		return itensAmbiente;
	}

	public void setItensAmbiente(List<ItemOrcamento> itensAmbiente) {
		this.itensAmbiente = itensAmbiente;
	}

	public List<ItemOrcamento> getItensSom() {
		return itensSom;
	}

	public void setItensSom(List<ItemOrcamento> itensSom) {
		this.itensSom = itensSom;
	}

	public List<ItemOrcamento> getItensSeguranca() {
		return itensSeguranca;
	}

	public void setItensSeguranca(List<ItemOrcamento> itensSeguranca) {
		this.itensSeguranca = itensSeguranca;
	}

	public List<ItemOrcamento> getItensVideo() {
		return itensVideo;
	}

	public void setItensVideo(List<ItemOrcamento> itensVideo) {
		this.itensVideo = itensVideo;
	}

	//
	public List<Orcamento> getOrcamentosCliente() {
		return orcamentosCliente;
	}

	public void setOrcamentosCliente(List<Orcamento> orcamentosCliente) {
		this.orcamentosCliente = orcamentosCliente;
	}

	public Orcamento getOrcamentoDetalhado() {
		return orcamentoDetalhado;
	}

	public void setOrcamentoDetalhado(Orcamento orcamentoDetalhado) {
		this.orcamentoDetalhado = orcamentoDetalhado;
	}

	public ItensDoOrcamento getItemDetalhado() {
		return itemDetalhado;
	}

	public void setItemDetalhado(ItensDoOrcamento itemDetalhado) {
		this.itemDetalhado = itemDetalhado;
	}

	public boolean isCopiaDoCliente() {
		return copiaDoCliente;
	}

	public void setCopiaDoCliente(boolean copiaDoCliente) {
		this.copiaDoCliente = copiaDoCliente;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getTabToUpdate() {
		return tabToUpdate;
	}

	public void setTabToUpdate(String tabToUpdate) {
		this.tabToUpdate = tabToUpdate;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ItensDoOrcamento> getItensDoOrcamento() {
		return itensDoOrcamento;
	}

	public void setItensDoOrcamento(List<ItensDoOrcamento> itensDoOrcamento) {
		this.itensDoOrcamento = itensDoOrcamento;
	}

	public Orcamento getNovoOrcamento() {
		return novoOrcamento;
	}

	public void setNovoOrcamento(Orcamento novoOrcamento) {
		this.novoOrcamento = novoOrcamento;
	}

	public List<Integer> getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(List<Integer> quantidade) {
		this.quantidade = quantidade;
	}

	public int getQuantidadeSelecionada() {
		return quantidadeSelecionada;
	}

	public void setQuantidadeSelecionada(int quantidadeSelecionada) {
		this.quantidadeSelecionada = quantidadeSelecionada;
	}

}
