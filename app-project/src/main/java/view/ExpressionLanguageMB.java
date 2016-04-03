package view;

import java.io.Serializable;

import javax.el.ELProcessor;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@Named
@ViewScoped
public class ExpressionLanguageMB implements Serializable {

	private static final long serialVersionUID = -422571332010667267L;

	private String expression;

	public void testExpression() {
		try {
			ELProcessor elProcessor = new ELProcessor();
			Object value = elProcessor.eval(this.expression);
			Messages.addInfo(null, value.toString());
		} catch (Exception e) {
			Faces.getContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}
	}

	/**
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * @param expression
	 *            the expression to set
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}

}
