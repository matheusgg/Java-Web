package conversaovalidacao.conversao;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Named;

@Named
@ApplicationScoped
public class IdadeConverter implements Converter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -77527230172172323L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().length() == 0) {
			HtmlInputText inputText = (HtmlInputText) component;
			inputText.setValid(false);
			inputText.getAttributes().put("style", "border: 1px solid red");
			throw new ConverterException(new FacesMessage("Informe a idade!"), null);
		}
		return Integer.valueOf(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value.toString();
	}

}
