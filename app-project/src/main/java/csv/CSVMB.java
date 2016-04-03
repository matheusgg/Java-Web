package csv;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("csvMB")
@ViewScoped
public class CSVMB implements Serializable {

	private static final long serialVersionUID = -3774550940927204237L;

	@CPF(message = "O CPF informado é inválido!")
	private String texto;

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto
	 *            the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

}
