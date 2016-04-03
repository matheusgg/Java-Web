package event;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;

public class CustomEvent extends ActionEvent {

	private static final long serialVersionUID = 3154790974818844406L;

	private String methodExpression;

	public CustomEvent(UIComponent component, String methodExpression, PhaseId phaseId) {
		super(component);
		this.methodExpression = methodExpression;
		super.setPhaseId(phaseId);
	}

	/**
	 * @return the methodExpression
	 */
	public String getMethodExpression() {
		return methodExpression;
	}

	/**
	 * @param methodExpression
	 *            the methodExpression to set
	 */
	public void setMethodExpression(String methodExpression) {
		this.methodExpression = methodExpression;
	}

}
