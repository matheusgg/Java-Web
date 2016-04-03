package checkout;

import javax.faces.flow.FlowScoped;
import javax.inject.Named;

@Named
@FlowScoped("checkout")
public class CheckoutBean {

	private String nome;
	private String nacionalidade;
	
	public CheckoutBean(){
		System.out.println("CheckoutBean");
	}

	public String getExitValue() {
		return "/index?faces-redirect=true";
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the nacionalidade
	 */
	public String getNacionalidade() {
		return nacionalidade;
	}

	/**
	 * @param nacionalidade
	 *            the nacionalidade to set
	 */
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

}
