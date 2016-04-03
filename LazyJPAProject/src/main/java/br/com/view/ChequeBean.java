package br.com.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.model.Cheque;
import br.com.model.dao.ChequeDAO;
import br.com.paginacao.LazyModel;

@ManagedBean
@SessionScoped
public class ChequeBean {
	private ChequeDAO dao;
	private LazyModel<Cheque, ChequeDAO> cheques;
	private Date dataInicial;
	private Date dataFinal;
	private HashMap<String, String> filtros;
	private Cheque cheque;

	public ChequeBean() {
		this.dao = new ChequeDAO();
		this.cheques = new LazyModel<Cheque, ChequeDAO>(this.dao);
		this.filtros = new HashMap<String, String>();
		this.cheques.setMethodToCall("buscaChequesComDataLimite");
		this.cheques.setFiltros(this.filtros);
	}

	public void pesquisaChequesPorData() {
		this.filtros = new HashMap<String, String>();
		this.filtros.put("dataInicial", new SimpleDateFormat("yyyy-MM-dd").format(this.dataInicial));
		this.filtros.put("dataFinal", new SimpleDateFormat("yyyy-MM-dd").format(this.dataFinal));
		this.cheques.setMethodToCall("buscaChequesComFiltro");
		this.cheques.setFiltros(this.filtros);
	}

	public void filtraCheques() {
		this.cheques.setMethodToCall("buscaChequesComFiltro");
		this.cheques.setFiltros(this.filtros);
	}

	/**
	 * @return the cheques
	 */
	public LazyModel<Cheque, ChequeDAO> getCheques() {
		return cheques;
	}

	/**
	 * @param cheques
	 *            the cheques to set
	 */
	public void setCheques(LazyModel<Cheque, ChequeDAO> cheques) {
		this.cheques = cheques;
	}

	/**
	 * @return the cheque
	 */
	public Cheque getCheque() {
		return cheque;
	}

	/**
	 * @param cheque
	 *            the cheque to set
	 */
	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}

	/**
	 * @return the dataInicial
	 */
	public Date getDataInicial() {
		return dataInicial;
	}

	/**
	 * @param dataInicial
	 *            the dataInicial to set
	 */
	public void setDataInicial(Date dataInicial) {
		this.filtros.put("dataInicial", new SimpleDateFormat("yyyy-MM-dd").format(dataInicial));
		this.dataInicial = dataInicial;
	}

	/**
	 * @return the dataFinal
	 */
	public Date getDataFinal() {
		return dataFinal;
	}

	/**
	 * @param dataFinal
	 *            the dataFinal to set
	 */
	public void setDataFinal(Date dataFinal) {
		this.filtros.put("dataFinal", new SimpleDateFormat("yyyy-MM-dd").format(dataFinal));
		this.dataFinal = dataFinal;
	}

	/******************** Filtros ************************/
	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return this.filtros.get("cliente");
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(String cliente) {
		this.filtros.put("cliente", cliente);
	}

	/**
	 * @return the banco
	 */
	public String getBanco() {
		return this.filtros.get("banco");
	}

	/**
	 * @param banco
	 *            the banco to set
	 */
	public void setBanco(String banco) {
		this.filtros.put("banco", banco);
	}

	/**
	 * @return the valor
	 */
	public String getValor() {
		return this.filtros.get("valor");
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(String valor) {
		this.filtros.put("valor", valor);
	}

}
