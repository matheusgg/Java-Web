package br.com.app;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.primefaces.context.RequestContext;

import br.com.beanValidator.MinAge;

@ManagedBean
@SessionScoped
public class AppBean {
	private List<String> lista;
	private String numero;

	/**
	 * Esta annotation faz parte da especificação BeanValidations que foi
	 * incorporada a versão 2 do JSF. Com isso, é possível validar atributos
	 * diretamente na classe modelo. Além de NotNull existem: AssertFalse;
	 * AssertTrue; DecimalMax; DecimalMin; Digits; Future; Max; Min; Null; Past;
	 * Pattern; Size.
	 */
	@NotNull(message = "Por favor, informe um endereço.")
	private String endereco;

	/**
	 * Utilizando BeanValidations, é possível criar validadores personalizados
	 * através de anotações e aplicá-las a atributos. É possível também definir
	 * qual mensagem aparecerá para o usuário passando apenas a chave da
	 * mensagem que está no arquivo ValidationsMessages.properties que deve
	 * estar na pasta src da aplicação.
	 */
	@MinAge(idadeMinima = 18, message = "{idade_menor}")
	@Digits(integer = 2, fraction = 0, message = "Por favor, informe um valor de 2 dígitos.")
	private int idade;

	public AppBean() {
		this.lista = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			lista.add("Teste " + i);
		}
	}

	public void showModal() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("modal.show()");
		context.update("dialogForm");
	}

	/**
	 * @return the lista
	 */
	public List<String> getLista() {
		return lista;
	}

	/**
	 * @param lista
	 *            the lista to set
	 */
	public void setLista(List<String> lista) {
		this.lista = lista;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the idade
	 */
	public int getIdade() {
		return idade;
	}

	/**
	 * @param idade
	 *            the idade to set
	 */
	public void setIdade(int idade) {
		this.idade = idade;
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

}
