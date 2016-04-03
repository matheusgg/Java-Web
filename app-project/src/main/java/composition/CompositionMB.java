package composition;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

@Named
@ViewScoped
public class CompositionMB implements Serializable {

	private static final long serialVersionUID = -6093150775455590819L;

	public void showMessage() {
		Messages.addInfo(null, "Mensagem de Teste");
	}

}
