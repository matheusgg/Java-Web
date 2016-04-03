package validators;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@ManagedBean
@ApplicationScoped
public class UploadValidator implements Validator {

	public UploadValidator() {
		this.getClass();
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Part file = (Part) value;
		if (!file.getContentType().endsWith("xml")) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "O arquivo informado deve possuir a extensao .xml", ""));
		}
	}

}
