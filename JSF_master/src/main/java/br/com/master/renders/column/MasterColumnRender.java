package br.com.master.renders.column;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

@FacesRenderer(componentFamily = "javax.faces.Column", rendererType = "master.UIMasterColumn")
public class MasterColumnRender extends Renderer {

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("td", component);

		this.encodeCellContent(context, component);

		writer.endElement("td");
	}

	private void encodeCellContent(FacesContext context, UIComponent component) throws IOException {
		if (!component.getChildren().isEmpty()) {
			for (UIComponent uiComponent : component.getChildren()) {
				uiComponent.encodeAll(context);
			}
		}
	}

}
