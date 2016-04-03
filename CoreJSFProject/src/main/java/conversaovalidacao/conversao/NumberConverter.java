package conversaovalidacao.conversao;

import java.io.Serializable;
import java.lang.reflect.Constructor;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("master.numberConverter")
public class NumberConverter implements Converter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8963083577903074911L;

	private String numberType;
	private boolean addErrorStyle;
	private String convertErrorMessage;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Object convertedValue = null;
		try {
			convertedValue = this.convertValue(value);
		} catch (Exception e) {
			this.addErrorStyle(component);
			throw new ConverterException(this.getErrorMessage(e.getCause() == null ? e : e.getCause(), component));
		}
		return convertedValue;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value.toString();
	}

	@SuppressWarnings("unchecked")
	private Object convertValue(String value) throws Exception {
		Object convertedValue = null;
		Class<Number> clazz = (Class<Number>) Class.forName(this.numberType);
		Constructor<Number> constructor = clazz.getConstructor(String.class);
		convertedValue = constructor.newInstance(value);
		return convertedValue;
	}

	private FacesMessage getErrorMessage(Throwable cause, UIComponent component) {
		FacesMessage facesMessage = null;
		String detail = null;

		if (cause instanceof ClassNotFoundException) {
			detail = "A classe informada em 'numberType' nao foi encontrada!";
		} else if (cause instanceof NumberFormatException) {
			detail = "O valor informado deve ser um numero!";
		} else {
			detail = cause.getMessage();
		}

		if (this.convertErrorMessage != null) {
			facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, this.convertErrorMessage, detail);
		} else {
			facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, detail, this.convertErrorMessage);
		}

		return facesMessage;
	}

	private void addErrorStyle(UIComponent component) {
		if (this.addErrorStyle) {
			String style = (String) component.getAttributes().get("style");
			if (style != null) {
				style += ";border: 1px solid red";
			} else {
				style = "border: 1px solid red";
			}
			component.getAttributes().put("style", style);
		}
	}

	/**
	 * @return the numberType
	 */
	public String getNumberType() {
		return numberType;
	}

	/**
	 * @param numberType
	 *            the numberType to set
	 */
	public void setNumberType(String numberType) {
		this.numberType = numberType;
	}

	/**
	 * @return the addErrorStyle
	 */
	public boolean isAddErrorStyle() {
		return addErrorStyle;
	}

	/**
	 * @param addErrorStyle
	 *            the addErrorStyle to set
	 */
	public void setAddErrorStyle(boolean addErrorStyle) {
		this.addErrorStyle = addErrorStyle;
	}

	/**
	 * @return the convertErrorMessage
	 */
	public String getConvertErrorMessage() {
		return convertErrorMessage;
	}

	/**
	 * @param convertErrorMessage
	 *            the convertErrorMessage to set
	 */
	public void setConvertErrorMessage(String convertErrorMessage) {
		this.convertErrorMessage = convertErrorMessage;
	}
}
