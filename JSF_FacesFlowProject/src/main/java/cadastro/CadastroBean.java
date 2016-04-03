package cadastro;

import javax.faces.flow.FlowScoped;
import javax.inject.Named;

@Named
@FlowScoped(value = "cadastro")
public class CadastroBean {

	private String nome;
	private String telefone;

	public CadastroBean() {
		System.out.println("CadastroBean");
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
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone
	 *            the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
