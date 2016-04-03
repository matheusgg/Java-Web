package componentespersonalizados.fontspinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehavior;
import javax.faces.component.behavior.ClientBehaviorContext;
import javax.faces.component.behavior.ClientBehaviorContext.Parameter;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

@FacesRenderer(componentFamily = "javax.faces.Command", rendererType = "ui.fontSpinner")
@ResourceDependency(library = "javax.faces", name = "jsf.js")
public class FontSpinnerRender extends Renderer {

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		UIFontSpinner fontSpinner = (UIFontSpinner) component;
		String clientId = fontSpinner.getClientId();

		ResponseWriter writer = context.getResponseWriter();

		String scriptAjaxMore = null;
		String scriptAjaxLess = null;
		if (this.isAjaxAssociate(fontSpinner)) {
			scriptAjaxMore = this.getAssociateAjaxScript(context, fontSpinner, ".more");
			scriptAjaxLess = this.getAssociateAjaxScript(context, fontSpinner, ".less");
		} else {
			scriptAjaxMore = this.prepareAjaxScript(context, clientId, clientId + ".more", fontSpinner);
			scriptAjaxLess = this.prepareAjaxScript(context, clientId, clientId + ".less", fontSpinner);
		}

		writer.startElement("div", fontSpinner);
		writer.writeAttribute("name", clientId, "name");
		writer.writeAttribute("id", clientId, "id");

		writer.startElement("span", null);
		writer.write("Tamanho da fonte: " + fontSpinner.getFontSize());
		writer.endElement("span");

		writer.startElement("input", null);
		writer.writeAttribute("type", "button", "type");
		writer.writeAttribute("name", clientId + ".less", "name");
		writer.writeAttribute("id", clientId + ".less", "id");
		writer.writeAttribute("onclick", scriptAjaxLess, "onclick");
		writer.writeAttribute("value", "<<", "value");
		writer.endElement("input");

		writer.startElement("input", null);
		writer.writeAttribute("type", "button", "type");
		writer.writeAttribute("name", clientId + ".more", "name");
		writer.writeAttribute("id", clientId + ".more", "id");
		writer.writeAttribute("onclick", scriptAjaxMore, "onclick");
		writer.writeAttribute("value", ">>", "value");
		writer.endElement("input");

		writer.endElement("div");
	}

	private boolean isAjaxAssociate(UIFontSpinner fontSpinner) {
		return fontSpinner.getClientBehaviors().size() > 0;
	}

	private String getAssociateAjaxScript(FacesContext context, UIFontSpinner fontSpinner, String btn) {
		/*
		 * Parametros adicionais da requisicao ajax
		 */
		List<Parameter> parametros = new ArrayList<Parameter>();
		parametros.add(new Parameter("execute", fontSpinner.getClientId() + btn));

		/*
		 * Cria o contexto de comportamento ajax para o evento click.
		 */
		ClientBehaviorContext behaviorContext = ClientBehaviorContext.createClientBehaviorContext(context, fontSpinner, "click", fontSpinner.getClientId(),
				parametros);

		/*
		 * Recupera o mapa de comportamentos ajax associado ao componente.
		 */
		Map<String, List<ClientBehavior>> clientBehaviors = fontSpinner.getClientBehaviors();

		/*
		 * Como e sabido que ha apanas um evento ajax, este evento e recuperado
		 * a partir do indice 0 da lista de eventos do tipo click.
		 */
		ClientBehavior clientBehavior = clientBehaviors.get("click").get(0);

		/*
		 * Recupera o script ajax para o evento click associado ao contexto ajax
		 * informado.
		 */
		String script = clientBehavior.getScript(behaviorContext);

		return script;

	}

	private String prepareAjaxScript(FacesContext context, String clientId, String buttonId, UIFontSpinner fontSpinner) {
		StringBuilder script = new StringBuilder("jsf.ajax.request(");
		script.append("'" + clientId + "',");
		script.append("null,");
		script.append("{execute:'");
		script.append(buttonId);
		script.append("',");

		if (fontSpinner.getUpdate() != null) {
			UIComponent component = fontSpinner.findComponent(fontSpinner.getUpdate());

			script.append("render:'");
			script.append(clientId + " ");
			script.append(component.getClientId(context) + "'});");
		} else {
			script.append("render:'" + clientId + "'});");
		}

		return script.toString();
	}

	@Override
	public void decode(FacesContext context, UIComponent component) {
		UIFontSpinner fontSpinner = (UIFontSpinner) component;
		String clientId = fontSpinner.getClientId();

		Integer fontSize = fontSpinner.getFontSize();

		/*
		 * As requisicoes ajax sao diferentes das requisicoes normais. Nas
		 * requisicoes normais, somente o ID do componente que gerou o evento e
		 * enviado para o servidor, ja nas requisicoes ajax, todos os IDs sao
		 * enviados para o servidor. Para saber quem gerou o evento, e preciso
		 * verificar o valor do parametro javax.faces.partial.execute, pois ele
		 * possui a informacao do gerador do evento.
		 */
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
		String execute = requestMap.get("javax.faces.partial.execute");

		if (execute.contains(clientId + ".more")) {
			fontSize++;
		} else if (execute.contains(clientId + ".less") && fontSize > 0) {
			fontSize--;
		}

		fontSpinner.setFontSize(fontSize);

		this.decodeClientBehaviors(context, fontSpinner);
	}

	/**
	 * Os comportamentos ajax tambem possuem estados, por este otivo e uma boa
	 * pratica decodificar todos eles.
	 * 
	 * @param context
	 * @param fontSpinner
	 */
	private void decodeClientBehaviors(FacesContext context, UIFontSpinner fontSpinner) {
		Map<String, List<ClientBehavior>> behaviors = fontSpinner.getClientBehaviors();
		if (!behaviors.isEmpty() && behaviors.get("click") != null && !behaviors.get("click").isEmpty()) {
			List<ClientBehavior> clientBehaviors = behaviors.get("click");
			for (ClientBehavior clientBehavior : clientBehaviors) {
				clientBehavior.decode(context, fontSpinner);
			}
		}
	}

}
