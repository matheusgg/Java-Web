package include;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

@Named
@RequestScoped
public class IncludeMB implements Serializable {

	private static final long serialVersionUID = -2834364911546500698L;

	private boolean cadastrando;

	public void novoCadastro() {
		this.cadastrando = true;
	}

	public void save() {
		Messages.addInfo(null, "Salvo com sucesso!");
	}

	/**
	 * @return the cadastrando
	 */
	public boolean isCadastrando() {
		return cadastrando;
	}

	/**
	 * @param cadastrando
	 *            the cadastrando to set
	 */
	public void setCadastrando(boolean cadastrando) {
		this.cadastrando = cadastrando;
	}

}
