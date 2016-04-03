package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * No JSF 2.2 nao e mais preciso informar explicitamente um nome para
 * conversores e validadores. Caso nao seja informado nada, o nome da classe sera
 * utilizado por convensao com a primeira letra sempre em minuscula.
 * 
 * @author Matheus
 * 
 */
@FacesConverter
public class CustomConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value.toString();
	}

}
