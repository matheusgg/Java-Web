package flash;

import java.io.Serializable;

import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Faces;

@Named
@ViewScoped
public class FlashMB implements Serializable {

	private static final long serialVersionUID = 1552618762510825153L;

	public void testFlash() {
		Flash flash = Faces.getFlash();
		flash.put("param", "Valor de Teste");
	}

}
