package compra;

import javax.faces.flow.FlowScoped;
import javax.inject.Named;

@Named
@FlowScoped("compra")
public class CompraBean {

	private String nomeProduto;
	private float precoProduto;

	public CompraBean() {
		System.out.println("CompraBean");
	}

	public String getExitValue() {
		return "/index?faces-redirect=true";
	}

	/**
	 * @return the nomeProduto
	 */
	public String getNomeProduto() {
		return nomeProduto;
	}

	/**
	 * @param nomeProduto
	 *            the nomeProduto to set
	 */
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	/**
	 * @return the precoProduto
	 */
	public float getPrecoProduto() {
		return precoProduto;
	}

	/**
	 * @param precoProduto
	 *            the precoProduto to set
	 */
	public void setPrecoProduto(float precoProduto) {
		this.precoProduto = precoProduto;
	}

}
