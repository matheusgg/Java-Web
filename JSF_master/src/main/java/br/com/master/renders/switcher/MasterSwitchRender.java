package br.com.master.renders.switcher;

import java.io.IOException;
import java.util.Map;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import br.com.master.components.switcher.UIMasterSwitch;

@FacesRenderer(rendererType = "master.switch", componentFamily = "javax.faces.SelectBoolean")
@ResourceDependencies({ @ResourceDependency(library = "js", name = "jquery-2.0.3.min.js"), @ResourceDependency(library = "js", name = "jquery.tablesorter.js"),
		@ResourceDependency(library = "js", name = "bootstrap.min.js"), @ResourceDependency(library = "css", name = "bootstrap.min.css"),
		@ResourceDependency(library = "javax.faces", name = "jsf.js"), @ResourceDependency(library = "js", name = "masterfaces.js"),
		@ResourceDependency(library = "css", name = "masterfaces.css") })
public class MasterSwitchRender extends Renderer {

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		UIMasterSwitch masterSwitch = (UIMasterSwitch) component;
		ResponseWriter writer = context.getResponseWriter();

		boolean value = (boolean) masterSwitch.getValue();

		writer.startElement("div", null);
		writer.writeAttribute("class", "onoffswitch", "class");

		writer.startElement("input", masterSwitch);
		if (value) {
			writer.writeAttribute("checked", "checked", "ckecked");
		}
		writer.writeAttribute("type", "checkbox", "type");
		writer.writeAttribute("name", masterSwitch.getClientId(), "clientId");
		writer.writeAttribute("class", "onoffswitch-checkbox", "class");
		writer.writeAttribute("id", masterSwitch.getClientId(), "clientId");
		writer.endElement("input");

		writer.startElement("label", null);
		writer.writeAttribute("class", "onoffswitch-label", "class");
		writer.writeAttribute("for", masterSwitch.getClientId(), "for");

		writer.startElement("div", null);
		writer.writeAttribute("class", "onoffswitch-inner", "class");
		writer.endElement("div");

		writer.startElement("div", null);
		writer.writeAttribute("class", "onoffswitch-switch", "class");
		writer.endElement("div");

		writer.endElement("label");
		writer.endElement("div");
	}

	@Override
	public void decode(FacesContext context, UIComponent component) {
		UIMasterSwitch masterSwitch = (UIMasterSwitch) component;
		Map<String, String> parametersMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		boolean value = false;
		if (parametersMap.containsKey(masterSwitch.getClientId())) {
			value = true;
		}
		masterSwitch.setSubmittedValue(value);
		masterSwitch.setValue(value);
		masterSwitch.setValid(true);
	}

}
