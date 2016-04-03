package event;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.component.UIComponentBase;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.FacesEvent;

import org.omnifaces.util.Faces;

public class CustomBroadcaster extends UIComponentBase {

	@Override
	public void broadcast(FacesEvent event) throws AbortProcessingException {
		if (event instanceof CustomEvent) {
			CustomEvent customEvent = (CustomEvent) event;
			String methodExpression = customEvent.getMethodExpression();

			ExpressionFactory expressionFactory = Faces.getApplication().getExpressionFactory();
			ELContext elContext = Faces.getContext().getELContext();
			MethodExpression method = expressionFactory.createMethodExpression(elContext, methodExpression, null, null);
			method.invoke(elContext, new Object[0]);
		}
	}

	@Override
	public void queueEvent(FacesEvent event) {
		if (event instanceof CustomEvent) {
			Faces.getViewRoot().queueEvent(event);
		}
	}

	@Override
	public String getFamily() {
		return null;
	}

}
