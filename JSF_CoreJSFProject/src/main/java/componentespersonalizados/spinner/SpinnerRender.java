package componentespersonalizados.spinner;

import java.io.IOException;
import java.util.Map;

import javax.el.MethodExpression;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.FacesEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.event.PhaseId;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

/**
 * Classe responsavel pela tarefa de renderizacao do componente Spinner
 * 
 * @author Matheus
 * 
 */
@FacesRenderer(componentFamily = "javax.faces.Input", rendererType = "ui.spinner")
@ResourceDependency(library = "js", name = "spinner.js", target = "head")
public class SpinnerRender extends Renderer {
	private static final String MORE = ".more";
	private static final String LESS = ".less";

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		UISpinner spinner = (UISpinner) component;
		ResponseWriter writer = context.getResponseWriter();
		String clientId = component.getClientId();

		// Codifica o campo de texto
		this.encodeInputField(writer, clientId, (UISpinner) component);

		// Codifica o botao de decremento
		this.encodeButton(writer, clientId + SpinnerRender.LESS, "<", "-1", spinner);

		// Codifica o botao de incremento
		this.encodeButton(writer, clientId + SpinnerRender.MORE, ">", "1", spinner);

	}

	@Override
	public void decode(FacesContext context, UIComponent component) {
		UISpinner spinner = (UISpinner) component;
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
		String clientId = component.getClientId(context); // input text

		int increment = 0;
		if (requestMap.containsKey(clientId + SpinnerRender.MORE)) {
			increment++;
		} else if (requestMap.containsKey(clientId + SpinnerRender.LESS)) {
			increment--;
		}

		// Pega o valor do input text
		String valor = requestMap.get(clientId);
		if (valor != null && valor.equals("")) {
			valor = "0";
		}

		try {

			// Tenta converter o valor informado para um Integer
			int submittedValue = Integer.valueOf(valor);

			/*
			 * Verifica se existe metodos associados ao componente e os adicona
			 * na fila de execucao
			 */
			MethodExpression metodo = spinner.getActionMethod();

			if (metodo != null) {
				// Cria e instala um listener no componente
				ActionListener listener = new MethodExpressionActionListener(metodo);
				spinner.addFacesListener(listener);

				/*
				 * Esta parte, informa ao JSF que os listeners instalados no
				 * spinner devem ser executados na fase INVOKE_APPLICATION.
				 * Entao quando este evento for disparado, os listeners serao
				 * notificados, consequentemente o metodo sera executado.
				 */
				FacesEvent event = new ActionEvent(spinner);
				event.setPhaseId(PhaseId.INVOKE_APPLICATION);
				spinner.queueEvent(event);
			}

			// Calcula o novo valor
			Integer newValue = this.getIncrementedValue(submittedValue, increment, spinner);

			// Seta o novo valor no componente para ser convertido pelo
			// IntegerConverter posteriormente
			spinner.setSubmittedValue(newValue.toString());
		} catch (Exception e) {
			/*
			 * Caso o usuario tenha digitado um valor invalido no input text,
			 * este valor é definido no componente e mais tarde o
			 * IntegerConverter gerara um erro de conversao por causa do valor
			 * invalido informado.
			 */
			spinner.setSubmittedValue(valor);
		}
	}

	private int getIncrementedValue(int submittedValue, int increment, UISpinner component) {
		Integer max = component.getMax();
		Integer min = component.getMin();

		int value = submittedValue + increment;

		if ((min == null || value >= min) && (max == null || value <= max)) {
			return value;
		} else {
			return submittedValue;
		}

	}

	private void encodeInputField(ResponseWriter writer, String clientId, UISpinner component) throws IOException {
		Object value = component.getValue();
		String size = component.getSize();
		String placeholder = (String) component.getPassThroughAttributes().get("placeholder");

		writer.startElement("input", component);
		writer.writeAttribute("type", "text", "type");
		writer.writeAttribute("name", clientId, "name");
		writer.writeAttribute("id", clientId, "id");

		if (value != null) {
			writer.writeAttribute("value", value, "value");
		}
		if (size != null) {
			writer.writeAttribute("size", size, "size");
		}
		if (placeholder != null) {
			writer.writeAttribute("placeholder", placeholder, "placeholder");
		}
		writer.endElement("input");
	}

	private void encodeButton(ResponseWriter writer, String clientId, Object value, String onclickIncrementDecrement, UISpinner component) throws IOException {
		writer.startElement("input", component);
		writer.writeAttribute("type", "button", "type");
		writer.writeAttribute("name", clientId, "name");
		writer.writeAttribute("value", value, "value");
		writer.writeAttribute("onclick", this.buildIncrementDecrementScript(onclickIncrementDecrement, component), "onclick");
		writer.endElement("input");
	}

	private String buildIncrementDecrementScript(String onclickIncrementDecrement, UISpinner component) {
		StringBuilder onclickBuilder = new StringBuilder();
		onclickBuilder.append("atualizaValor(");
		onclickBuilder.append("'" + component.getClientId() + "'");
		onclickBuilder.append(",");
		onclickBuilder.append(onclickIncrementDecrement);
		onclickBuilder.append(",");
		onclickBuilder.append(component.getMax());
		onclickBuilder.append(",");
		onclickBuilder.append(component.getMin());
		onclickBuilder.append(")");
		return onclickBuilder.toString();
	}

	/**
	 * Metodo invocado peolo JSF quando o metodo getRendersChildren retornar
	 * true, significando assim, que este componente possui subordinados, ou
	 * seja, possui componentes filhos.
	 */
	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		super.encodeChildren(context, component);
	}

	/**
	 * Metodo invocado pelo JSF apos a execucao de encodeChildren (caso o
	 * componente possua filhos) ou encodeBegin(caso o componente nao possua
	 * filhos).
	 */
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		super.encodeEnd(context, component);
	}

	/**
	 * Retorna um booleano indicando se este componente possui filhos. Por
	 * padrao ele retorna false.
	 */
	@Override
	public boolean getRendersChildren() {
		return super.getRendersChildren();
	}

}
