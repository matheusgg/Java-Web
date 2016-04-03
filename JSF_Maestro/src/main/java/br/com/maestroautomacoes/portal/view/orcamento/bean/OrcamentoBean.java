package br.com.maestroautomacoes.portal.view.orcamento.bean;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.DragDropEvent;
import org.primefaces.event.TabChangeEvent;

import br.com.maestroautomacoes.portal.enums.orcamento.StatusOrcamento;
import br.com.maestroautomacoes.portal.mail.MailProvider;
import br.com.maestroautomacoes.portal.model.dao.orcamento.OrcamentoDao;
import br.com.maestroautomacoes.portal.model.orcamento.ItemOrcamento;
import br.com.maestroautomacoes.portal.model.orcamento.ItensDoOrcamento;
import br.com.maestroautomacoes.portal.model.orcamento.ItensDoOrcamentoPK;
import br.com.maestroautomacoes.portal.model.orcamento.Orcamento;
import br.com.maestroautomacoes.portal.model.usuario.Usuario;
import br.com.maestroautomacoes.portal.reports.ReportProvider;
import br.com.maestroautomacoes.portal.security.SecurityManager;
import br.com.maestroautomacoes.portal.util.ConstantesUtils;
import br.com.maestroautomacoes.portal.util.Outcome;
import br.com.maestroautomacoes.portal.util.SiteUtil;
import br.com.maestroautomacoes.portal.view.orcamento.helper.OrcamentoViewHelper;

@Named
@SessionScoped
public class OrcamentoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7333247371245652381L;

	@Inject
	private OrcamentoViewHelper viewHelper;

	@Inject
	private OrcamentoDao orcamentoDao;

	public String iniciarPagina() throws Exception {
		this.viewHelper.inicializar();
		this.carregarListaDeOrcamentosDoCliente();
		return Outcome.ORCAMENTO;
	}

	public void limparListaDeItensSelecionados() {
		this.viewHelper.getItensDoOrcamento().clear();
		this.carregarListaDeItensDisponiveis();
		this.updateImagensTipo();
	}

	public void vizualizaOrcamento() throws Exception {
		Orcamento orcamento = this.viewHelper.getOrcamentoDetalhado();
		this.preparaRelatorioOrcamento(orcamento.getCodigoOrcamento(), orcamento.getStatus(), orcamento.getItensDoOrcamento());
	}

	/**
	 * Gera uma relatório com o orçamento do cliente para envio por email.
	 */
	public void geraOrcamento() throws Exception {
		Orcamento orcamento = this.cadastarOrcamentoNaBase();
		this.preparaRelatorioOrcamento(orcamento.getCodigoOrcamento(), StatusOrcamento.AGUARDANDO, this.viewHelper.getItensDoOrcamento());
	}

	private Orcamento cadastarOrcamentoNaBase() {
		Orcamento orcamento = new Orcamento();
		this.viewHelper.setNovoOrcamento(orcamento);
		this.viewHelper.getNovoOrcamento().setStatus(StatusOrcamento.AGUARDANDO);
		this.viewHelper.getNovoOrcamento().setDataEntradaOrcamento(new Date());
		this.viewHelper.getNovoOrcamento().setUsuario(this.viewHelper.getUsuario());
		this.viewHelper.getNovoOrcamento().setItensDoOrcamento(this.viewHelper.getItensDoOrcamento());
		for (ItensDoOrcamento item : this.viewHelper.getItensDoOrcamento()) {
			ItensDoOrcamentoPK id = new ItensDoOrcamentoPK();
			id.setIdItemOrcamento(item.getItemOrcamento().getIdItemOrcamento());
			id.setIdOrcamento(this.viewHelper.getNovoOrcamento().getIdOrcamento());

			item.setId(id);
			item.setOrcamento(this.viewHelper.getNovoOrcamento());
		}
		this.viewHelper.getNovoOrcamento().setCodigoOrcamento("MAESTRO -");
		this.orcamentoDao.cadastrarNovoOrcamento(this.viewHelper.getNovoOrcamento());
		return orcamento;
	}

	/**
	 * Prepara as configurações do relatório que será gerado com o orçamento do
	 * cliente.
	 * 
	 * @param codigoOrcamento
	 * @param status
	 * @throws Exception
	 */
	private void preparaRelatorioOrcamento(String codigoOrcamento, StatusOrcamento status, List<ItensDoOrcamento> lista) throws Exception {
		Usuario cliente = SecurityManager.getUsuarioLogado();
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigo", codigoOrcamento);
		parametros.put("status", status.toString());

		List<ItemOrcamento> itens = this.preparaItensDoOrcamento(lista);
		File relatorio = ReportProvider.geraRelatorioOrcamento(itens, "Orcamento.jasper", "Orçamento - " + cliente.getNome(), parametros,
				this.viewHelper.isCopiaDoCliente());

		if (this.viewHelper.isCopiaDoCliente()) {
			MailProvider.mandaEmailHtml(cliente.getNome(), cliente.getEmail(), "Orçamento - " + cliente.getNome(),
					SiteUtil.getMessageFromProperty("lbl_orcamento_cliente") + cliente.getNome(), SiteUtil.getMessageFromProperty("msg_envio_orcamento"),
					relatorio, true);
		}
	}

	/**
	 * Prepara a lista de itens adicionados ao orçamento antes da geração do
	 * relatório.
	 * 
	 * @param itens
	 * @return
	 */
	private List<ItemOrcamento> preparaItensDoOrcamento(List<ItensDoOrcamento> itens) {
		List<ItemOrcamento> listaItens = new ArrayList<ItemOrcamento>();
		for (ItensDoOrcamento item : itens) {
			ItemOrcamento itemOrcamento = item.getItemOrcamento();
			itemOrcamento.setQuantidadeDoItem(item.getQuantidadeDoItem());
			listaItens.add(itemOrcamento);
		}
		return listaItens;
	}

	public void carregarListaDeItensDisponiveis() {
		this.viewHelper.setItensAmbiente(this.orcamentoDao.buscaListadeItens(ConstantesUtils.TIPO_ITEM_AMBIENTE));
		this.viewHelper.setItensSom(this.orcamentoDao.buscaListadeItens(ConstantesUtils.TIPO_ITEM_SOM));
		this.viewHelper.setItensSeguranca(this.orcamentoDao.buscaListadeItens(ConstantesUtils.TIPO_ITEM_SEGURANCA));
		this.viewHelper.setItensVideo(this.orcamentoDao.buscaListadeItens(ConstantesUtils.TIPO_ITEM_VIDEO));
		this.viewHelper.getItensDoOrcamento().clear();
	}

	public String acessarNovoOrcamento() {
		this.carregarListaDeItensDisponiveis();
		return Outcome.NOVO_ORCAMENTO;
	}

	/**
	 * Atualiza a datasource do droppable a cada troca de aba.
	 * 
	 * @param event
	 */
	public void updateDroppableForm(TabChangeEvent event) {
		String tabId = event.getTab().getId();
		this.viewHelper.setTabToUpdate(tabId);
		this.viewHelper.setDataSource(":tabView:dataGrid" + tabId);
	}

	/**
	 * Atualiza o caminho das imagens dos tipos de itens.
	 */
	public void updateImagensTipo() {
		this.viewHelper.setTabToUpdate("TabAmbiente");
		this.viewHelper.setDataSource(":tabView:dataGrid" + this.viewHelper.getTabToUpdate());
	}

	public void adicionarItemAoOrcamento(DragDropEvent ddEvent) throws Exception {
		ItemOrcamento orcamento = ((ItemOrcamento) ddEvent.getData());
		switch (orcamento.getTipoItem().getIdTipoItem()) {
		case 1:
			this.viewHelper.getItensAmbiente().remove(orcamento);
			break;
		case 2:
			this.viewHelper.getItensSom().remove(orcamento);
			break;
		case 3:
			this.viewHelper.getItensSeguranca().remove(orcamento);
			break;
		case 4:
			this.viewHelper.getItensVideo().remove(orcamento);
			break;
		}
		ItensDoOrcamento itensDoOrcamento = new ItensDoOrcamento();
		itensDoOrcamento.setItemOrcamento(orcamento);
		itensDoOrcamento.setQuantidadeDoItem(1);
		itensDoOrcamento.setOrcamento(this.viewHelper.getNovoOrcamento());

		this.viewHelper.getItensDoOrcamento().add(itensDoOrcamento);
	}

	private void carregarListaDeOrcamentosDoCliente() {
		this.viewHelper.setOrcamentosCliente(this.orcamentoDao.buscaOrcamentosDoCliente(this.viewHelper.getUsuario()));
	}

	public String pesquisar() {
		this.carregarListaDeOrcamentosDoCliente();
		return "";
	}

	public void removerItemDoOrcamento() {
		int posicao = -1;
		for (ItensDoOrcamento item : this.viewHelper.getItensDoOrcamento()) {

			if (this.viewHelper.getItemDetalhado().getItemOrcamento().getIdItemOrcamento() == item.getItemOrcamento().getIdItemOrcamento()) {
				posicao = this.viewHelper.getItensDoOrcamento().indexOf(item);
				break;
			}
		}

		this.viewHelper.getItensDoOrcamento().remove(posicao);
		this.colocarItemComoDispoivel(this.viewHelper.getItemDetalhado().getItemOrcamento());
		this.updateImagensTipo();
	}

	private void colocarItemComoDispoivel(ItemOrcamento itemDetalhado) {
		switch (itemDetalhado.getTipoItem().getIdTipoItem()) {
		case 1:
			this.viewHelper.getItensAmbiente().add(itemDetalhado);
			break;
		case 2:
			this.viewHelper.getItensSom().add(itemDetalhado);
			break;
		case 3:
			this.viewHelper.getItensSeguranca().add(itemDetalhado);
			break;
		case 4:
			this.viewHelper.getItensVideo().add(itemDetalhado);
			break;
		}

	}

	/**
	 * @return the viewHelper
	 */
	public OrcamentoViewHelper getViewHelper() {
		return viewHelper;
	}

}
