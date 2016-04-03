package financeiro.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import financeiro.cheque.Cheque;
import financeiro.cheque.ChequeRN;
import financeiro.conta.Conta;
import financeiro.util.MensagemUtil;
import financeiro.util.RNException;

@ManagedBean
@RequestScoped
public class ChequeBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8485485503887415418L;
	private Cheque selecionado = new Cheque();
	private List<Cheque> lista = null;
	private Integer chequeInicial;
	private Integer chequeFinal;
	
	public void salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		Conta conta = contextoBean.getContaAtiva();
		
		int totalCheques = 0;
		if(this.chequeInicial == null || this.chequeFinal == null){
			String texto = MensagemUtil.getMensagem("cheque_erro_sequencia");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, "");
			context.addMessage(null, msg);
		}else if(this.chequeFinal.intValue() < this.chequeInicial.intValue()){
			String texto = MensagemUtil.getMensagem("cheque_erro_inicial_final", this.chequeInicial, this.chequeFinal);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, "");
			context.addMessage(null, msg);
		}else{
			totalCheques = new ChequeRN().salvarSequencia(conta, this.chequeInicial, this.chequeFinal);
			String texto = MensagemUtil.getMensagem("cheque_info_cadastro", this.chequeInicial, totalCheques);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, "");
			context.addMessage(null, msg);
			this.lista = null;
		}
	}
	
	public void excluir(){
		try{
			new ChequeRN().excluir(this.selecionado);
		}catch(RNException ex){
			FacesContext context = FacesContext.getCurrentInstance();
			String texto = MensagemUtil.getMensagem("cheque_erro_excluir");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, texto, "");
			context.addMessage(null, msg);
		}
		this.lista = null;
	}
	
	public void cancelar(){
		try{
			new ChequeRN().cancelarCheque(this.selecionado);
		}catch(RNException ex){
			FacesContext context = FacesContext.getCurrentInstance();
			String texto = MensagemUtil.getMensagem("cheque_erro_cancelar");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, texto, "");
			context.addMessage(null, msg);
		}
		this.lista = null;
	}

	public Cheque getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Cheque selecionado) {
		this.selecionado = selecionado;
	}

	public List<Cheque> getLista() {
		if(this.lista == null){
			ContextoBean contextoBean = ContextoUtil.getContextoBean();
			Conta conta = contextoBean.getContaAtiva();
			
			this.lista = new ChequeRN().listar(conta);
		}
		return lista;
	}

	public void setLista(List<Cheque> lista) {
		this.lista = lista;
	}

	public Integer getChequeInicial() {
		return chequeInicial;
	}

	public void setChequeInicial(Integer chequeInicial) {
		this.chequeInicial = chequeInicial;
	}

	public Integer getChequeFinal() {
		return chequeFinal;
	}

	public void setChequeFinal(Integer chequeFinal) {
		this.chequeFinal = chequeFinal;
	}
}