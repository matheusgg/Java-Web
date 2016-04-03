package conversaovalidacao.validacao;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
@ApplicationScoped
public class RequiredValidator implements Validator, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4235410449363465817L;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null || value.toString().trim().length() == 0) {
			HtmlInputText inputText = (HtmlInputText) component;
			inputText.setValid(false);
			inputText.getAttributes().put("style", "border: 1px solid red");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Por favor, preencha o campo '" + inputText.getLabel() + "'"));
		}
	}

}
