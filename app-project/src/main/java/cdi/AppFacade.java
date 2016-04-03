package cdi;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class AppFacade implements Serializable {

	private static final long serialVersionUID = -1772877164140120585L;

	@Intercept
	public String constructMessage() {
		return "Mensagem de Teste";
	}

}
