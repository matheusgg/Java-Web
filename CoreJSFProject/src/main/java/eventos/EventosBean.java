package eventos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class EventosBean implements SystemEventListener, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8020159791715588944L;
	private List<Locale> localidades;
	private String local;

	@PostConstruct
	public void init() {
		this.localidades = new ArrayList<>();
		this.localidades.addAll(Arrays.asList(Locale.getAvailableLocales()));
	}

	public void change(ValueChangeEvent event) {
		this.local = event.getNewValue().toString();
	}

	public void action(ActionEvent event) {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		Map<String, String> parameterMap = context.getRequestParameterMap();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();

		request.setAttribute("x", parameterMap.get("btn.x"));
		request.setAttribute("y", parameterMap.get("btn.y"));
	}

	public void preRender(ComponentSystemEvent event) {
		event.getClass();
	}

	/**
	 * Esta classe implementa a interface SystemEventListener e e notificada
	 * toda vez que um evento de pre validacao ocorre em qualquer lugar na
	 * aplicacao. Este metodo e invocado pelo JSF para determinar se esta classe
	 * e um listener para a fonte informada. A fonte pode ser um componente ou
	 * uma vizualizacao. Caso positivo, o JSF invoca o metodo processEvent.
	 */
	@Override
	public boolean isListenerForSource(Object source) {
		if (source instanceof UIViewRoot) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo invocado toda vez que o metodo isListenerForSource retornar true,
	 * indicando que esta classe e um listener qualificado para tratamento de
	 * eventos relacionados a fonte informada.
	 */
	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {
		event.getClass();
	}

	/**
	 * @return the localidades
	 */
	public List<Locale> getLocalidades() {
		return localidades;
	}

	/**
	 * @param localidades
	 *            the localidades to set
	 */
	public void setLocalidades(List<Locale> localidades) {
		this.localidades = localidades;
	}

	/**
	 * @return the local
	 */
	public String getLocal() {
		return local;
	}

	/**
	 * @param local
	 *            the local to set
	 */
	public void setLocal(String local) {
		this.local = local;
	}

}
