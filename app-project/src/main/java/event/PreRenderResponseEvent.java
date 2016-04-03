package event;

import javax.faces.component.UIComponent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.FacesListener;
import javax.faces.event.NamedEvent;
import javax.faces.event.SystemEventListener;

@NamedEvent(shortName = "preRenderResponse")
public class PreRenderResponseEvent extends ComponentSystemEvent {

	private static final long serialVersionUID = 582904112679727754L;

	public PreRenderResponseEvent(UIComponent component) {
		super(component);
	}

	@Override
	public boolean isAppropriateListener(FacesListener listener) {
		return listener instanceof SystemEventListener;
	}

}
