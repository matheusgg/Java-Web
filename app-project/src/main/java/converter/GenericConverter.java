package converter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Classe respons�vel por converter objetos para uma representa��o textual e
 * vice-versa.
 */
@FacesConverter("genericConverter")
public class GenericConverter implements Converter {

	/** The Constant SOURCE_VALUES_KEY. */
	private static final String SOURCE_VALUES_KEY = "br.com.sourceValues";

	/**
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (this.isNotEmpty(value)) {
			return this.getSourceValues(component).get(value);
		}
		return null;
	}

	/**
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String key = null;
		if (this.isNotEmpty(value)) {
			Map<String, Object> sourceValues = this.getSourceValues(component);
			if (!sourceValues.containsValue(value)) {
				key = this.generateRandomKey();
				sourceValues.put(key, value);
			} else {
				key = this.getKeyFromValue(sourceValues, value);
			}
		}
		return key;
	}

	/**
	 * Recupera o mapa de valores do componente no qual este conversor est�
	 * associado. Caso o mapa n�o seja encontrado nos atributos do componente,
	 * um novo mapa ser� criado, adicionado ao componente em quest�o e retornado
	 * por este m�todo.
	 *
	 * @param component
	 *            O componente associado a este conversor
	 * @return O mapa de valores
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> getSourceValues(UIComponent component) {
		Map<String, Object> sourceValues = (Map<String, Object>) component.getAttributes().get(GenericConverter.SOURCE_VALUES_KEY);
		if (sourceValues == null) {
			sourceValues = new HashMap<String, Object>();
			component.getAttributes().put(GenericConverter.SOURCE_VALUES_KEY, sourceValues);
		}
		return sourceValues;
	}

	/**
	 * Recupera a chave associada ao objeto informado caso esteja contido no
	 * mapa de valores.
	 *
	 * @param sourceValues
	 *            O mapa de valores
	 * @param value
	 *            O objeto que ser� verificado
	 * @return A chave associada ao objeto informado
	 */
	private String getKeyFromValue(Map<String, Object> sourceValues, Object value) {
		// for (Entry<String, Object> entry : sourceValues.entrySet()) {
		// if (entry.getValue().equals(value)) {
		// return entry.getKey();
		// }
		// }
		return sourceValues.entrySet().parallelStream().filter(e -> e.getValue().equals(value)).map(e -> e.getKey()).findFirst().orElse(null);
	}

	/**
	 * Gera uma nova chave de associa��o.
	 *
	 * @return the string
	 */
	private String generateRandomKey() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Verifica se o objeto informado est� nulo <em>(obj == null)</em> , ou caso
	 * seja uma String, verifica se a mesma n�o est� vazia
	 * <em>(!str.toString().trim().isEmpty())</em>.
	 *
	 * @param value
	 *            O valor a ser verificado
	 * @return true, se n�o estiver vazio
	 */
	private boolean isNotEmpty(Object value) {
		return value != null && !value.toString().trim().isEmpty();
	}
}
