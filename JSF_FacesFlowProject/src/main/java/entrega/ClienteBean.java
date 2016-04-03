package entrega;

import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

/**
 * Os fluxo do JSF sao subrotinas que possuem um ponto de entrada e um ponto de
 * saida determinados. Além disso, podem receber e retornar multiplos valores.
 * Existem duas formas de se configurar um flow; xml ou programaticamente.
 * Durante a utilizacao de um fluxo, os beans anotados com FlowScoped focarão
 * disponiveis junto com um mapa utilitario para manipulacao de parametros
 * (flowScope).O conceito de flow no JSF é padronizado, por este motivo a
 * configuracao pode se tornar bem simples. Para este exemplo, foi criado um xml
 * de configuracao vazio, desta forma o JSF assume que o nome do fluxo é o nome
 * da xhtml entrega dentro da pasta entrega. Por conversao, o nome do arquivo
 * xml deve terminar com -flow.xml. O nome da pagina de retorno (ponto de saida
 * do fluxo) deve terminar com -return.xml.
 * 
 * @author Matheus
 * 
 */
@Named
@FlowScoped("entrega")
public class ClienteBean {

	private String nomeCliente;
	private String cpf;
	private String endereco;
	private String observacoes;

	public ClienteBean() {
		System.out.println("ClienteBean");
	}

	public String confirmaDados() {
		FacesContext.getCurrentInstance().getApplication().getFlowHandler().getCurrentFlowScope().put("nomeCliente", this.nomeCliente);
		FacesContext.getCurrentInstance().getApplication().getFlowHandler().getCurrentFlowScope().put("cpf", this.cpf);
		FacesContext.getCurrentInstance().getApplication().getFlowHandler().getCurrentFlowScope().put("endereco", this.endereco);
		FacesContext.getCurrentInstance().getApplication().getFlowHandler().getCurrentFlowScope().put("observacoes", this.observacoes);
		return "confirmacao?faces-redirect=true";
	}

	public String getExitFlow() {
		return "/index?faces-redirect=true";
	}

	/**
	 * @return the nomeCliente
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente
	 *            the nomeCliente to set
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf
	 *            the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco
	 *            the endereco to set
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return the observacoes
	 */
	public String getObservacoes() {
		return observacoes;
	}

	/**
	 * @param observacoes
	 *            the observacoes to set
	 */
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

}
