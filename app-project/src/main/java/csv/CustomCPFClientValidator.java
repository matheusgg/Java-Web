package csv;

import java.util.HashMap;
import java.util.Map;

import javax.validation.metadata.ConstraintDescriptor;

import org.primefaces.validate.bean.ClientValidationConstraint;

public class CustomCPFClientValidator implements ClientValidationConstraint {

	public CustomCPFClientValidator() {
		this.getClass();
	}

	/**
	 * Disponibiliza meta informações para o componente que será validado. Cada
	 * par de chave-valor inserido no mapa retornado será renderizado como
	 * atributos da tag na qual este validador está relacionado.
	 * 
	 * @see org.primefaces.validate.bean.ClientValidationConstraint#getMetadata(javax.validation.metadata.ConstraintDescriptor)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> getMetadata(ConstraintDescriptor constraintDescriptor) {
		Map<String, Object> metadata = new HashMap<>();
		String message = (String) constraintDescriptor.getAttributes().get("message");
		if (message != null && !message.isEmpty()) {
			metadata.put("data-p-cpf-msg", message);
		}
		return metadata;
	}

	@Override
	public String getValidatorId() {
		return this.getClass().getName();
	}

}
