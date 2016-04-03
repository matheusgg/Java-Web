package components;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 * Agora com JSF 2.2 para criar um componente basta apenas anota-lo.
 * 
 * @author Matheus
 * 
 */
@FacesComponent(createTag = true, tagName = "custom")
public class CustomComponent extends UIComponentBase {

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		String value = (String) getAttributes().get("value");

		if (value != null) {
			ResponseWriter writer = context.getResponseWriter();
			writer.write(value.toUpperCase());
		}
	}

	@Override
	public String getFamily() {
		return "my.custom.component";
	}

}
