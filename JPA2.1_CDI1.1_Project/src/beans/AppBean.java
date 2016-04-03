package beans;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Cliente;
import dao.DAO;

@Named
@ViewScoped
public class AppBean {

	@Inject
	private DAO dao;

	private int resultados;

	public void search() {
		List<Cliente> clientes = this.dao.search();
		this.resultados = clientes.size();
	}

	public void pesquisaComFuncao() {
		try {
			this.dao.pesquisaComFuncao(8);
			this.resultados = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateComCriteria() {
		this.dao.updateComCriteria();
		this.showMessage("Atualizado!");
	}

	public void deleteComCriteria() {
		this.dao.deleteComCriteria();
		this.showMessage("Removido!");
	}

	public void callStoredProcedureComNamedStoredProcedureQuery() {
		List<Cliente> clientes = this.dao.callStoredProcedureComNamedStoredProcedureQuery("Cliente 1", new Date());
		this.resultados = clientes.size();
		this.showMessage("Cliente Inserido!");
	}

	public void storedProceduresCriadasDinamicamente() {
		this.dao.storedProceduresCriadasDinamicamente("Cliente 1", new Date());
		this.showMessage("Inserido!");
	}

	public void mapeamentoDeConstrutorDeResultados() {
		Cliente cliente = this.dao.mapeamentoDeConstrutorDeResultados();
		this.showMessage("Cliente: " + cliente.getId());
	}
	
	public void mapeamentoDeConstrutorDeResultadosEmbutido() {
		Cliente cliente = this.dao.mapeamentoDeConstrutorDeResultadosEmbutido();
		this.showMessage("Cliente: " + cliente.getId());
	}
	
	public void insere(){
		Cliente cliente = new Cliente();
		cliente.setNome("Cliente Teste");
		cliente.setNascimento(new Date());
		this.dao.insereCliente(cliente);
		this.showMessage("Inserido!");
	}

	private void showMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
	}

	public int getResultados() {
		return resultados;
	}

	public void setResultados(int resultados) {
		this.resultados = resultados;
	}

}
