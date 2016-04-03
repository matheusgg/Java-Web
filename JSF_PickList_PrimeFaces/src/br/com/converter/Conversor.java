package br.com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.Principal;
import br.com.model.Candidato;

public class Conversor implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		Principal principalBean = (Principal) context.getExternalContext()
				.getSessionMap().get("principalBean");

		for (Candidato candidato : principalBean.getCandidatosDoSistema()) {
			if (candidato.getNome().equals(value)) {
				return candidato;
			}
		}
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return String.valueOf(value);
	}

}
