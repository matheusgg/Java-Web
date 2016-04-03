package br.com.master.renders.datatable;

import java.io.IOException;
import java.util.Map;

import javax.el.MethodExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import br.com.master.api.MasterFacesEvent;
import br.com.master.components.column.UIMasterColumn;
import br.com.master.components.datatable.UIMasterData;

@FacesRenderer(componentFamily = "javax.faces.Data", rendererType = "master.UIMasterDatatable")
@ResourceDependencies({ @ResourceDependency(library = "js", name = "jquery-2.0.3.min.js"), @ResourceDependency(library = "js", name = "jquery.tablesorter.js"),
		@ResourceDependency(library = "js", name = "bootstrap.min.js"), @ResourceDependency(library = "css", name = "bootstrap.min.css"),
		@ResourceDependency(library = "javax.faces", name = "jsf.js"), @ResourceDependency(library = "js", name = "masterfaces.js"),
		@ResourceDependency(library = "css", name = "masterfaces.css") })
public class MasterTableRender extends Renderer {
	private String clientId;

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		UIMasterData masterDatatable = (UIMasterData) component;

		this.clientId = masterDatatable.getClientId();

		if (masterDatatable.getCurrentPageIndex() == 0 && !masterDatatable.isAlreadyRenderedOnClient()) {
			this.handlePagination(context, masterDatatable, null, null);
		}

		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("div", masterDatatable);
		writer.writeAttribute("id", this.clientId, "id");
		writer.writeAttribute("class", "container", "class");
		writer.writeAttribute("style", "margin-top: 20px", "style");

		this.encodeTableHead(context, masterDatatable);
	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		this.encodeTableBody(context, (UIMasterData) component);
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		UIMasterData masterDatatable = (UIMasterData) component;

		this.encodeTableFooter(context, masterDatatable);
		this.encodePaginationButtons(context, masterDatatable);

		ResponseWriter writer = context.getResponseWriter();
		writer.endElement("div");
	}

	@Override
	public boolean getRendersChildren() {
		return true;
	}

	@Override
	public void decode(FacesContext context, UIComponent component) {
		UIMasterData masterDatatable = (UIMasterData) component;
		masterDatatable.setAlreadyRenderedOnClient(true);

		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
		String parametros = requestMap.get("params");
		String source = requestMap.get("javax.faces.source");

		this.handlePagination(context, masterDatatable, source, parametros);
		this.handleSelection(parametros, masterDatatable);
	}

	private void handleSelection(String parametros, UIMasterData masterDatatable) {
		if (parametros.contains("MasterFacesEvent." + MasterFacesEvent.SELECTION.toString())) {
			String[] params = parametros.split("_");
			masterDatatable.setRowIndex(Integer.valueOf(params[1]));
			masterDatatable.setSelection(masterDatatable.getRowData());
		}
	}

	private void handlePagination(FacesContext context, UIMasterData masterDatatable, String source, String parametros) {
		if (source != null && (source.contains("next") || source.contains("previous"))) {
			int currentPage = masterDatatable.getCurrentPageIndex();

			if (source.contains("next")) {
				masterDatatable.setCurrentPageIndex(++currentPage);
			} else {
				masterDatatable.setCurrentPageIndex(--currentPage);
			}

			long start = 0;
			long end = 0;

			for (int i = 0; i <= masterDatatable.getCurrentPageIndex(); i++) {
				start = end;
				end = end + masterDatatable.getRowsPerPage();
				if (end > masterDatatable.getTotalRows()) {
					end = start + (masterDatatable.getTotalRows() - start);
				}
			}
			masterDatatable.setStartIndex(start);

		} else if (parametros != null && parametros.contains(MasterFacesEvent.PAGINATION.toString())) {

			String[] params = parametros.split("_");

			masterDatatable.setStartIndex(Integer.valueOf(params[params.length - 3]));
			masterDatatable.setCurrentPageIndex(Integer.valueOf(params[params.length - 1]));
		}

		MethodExpression paginatorMethod = masterDatatable.getPaginatorMethod();
		long totalRows = (long) paginatorMethod.invoke(context.getELContext(),
				new Object[] { masterDatatable.getStartIndex(), masterDatatable.getRowsPerPage() });

		double pageCount = Double.valueOf(totalRows) / Double.valueOf(masterDatatable.getRowsPerPage());

		masterDatatable.setPageCount(Double.isInfinite(pageCount) ? 0 : (int) Math.ceil(pageCount));
		masterDatatable.setTotalRows(totalRows);
	}

	private void encodeTableHead(FacesContext context, UIMasterData masterDatatable) throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		writer.startElement("table", masterDatatable);
		writer.writeAttribute("id", this.clientId + ":table", "id");
		writer.writeAttribute("class", "table table-bordered", "class");
		writer.startElement("thead", masterDatatable);
		writer.startElement("tr", masterDatatable);

		for (UIComponent uiColumn : masterDatatable.getChildren()) {
			UIMasterColumn column = (UIMasterColumn) uiColumn;

			writer.startElement("th", column);
			writer.write(column.getHeaderText());
			writer.endElement("th");
		}

		writer.endElement("tr");
		writer.endElement("thead");
	}

	private void encodeTableBody(FacesContext context, UIMasterData masterDatatable) throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		writer.startElement("tbody", masterDatatable);
		int rowCount = masterDatatable.getRowCount();

		for (int i = 0; i < rowCount && rowCount > 0 && masterDatatable.getTotalRows() > 0 && !masterDatatable.getChildren().isEmpty(); i++) {
			masterDatatable.setRowIndex(i);

			writer.startElement("tr", masterDatatable);
			writer.writeAttribute("onclick",
					"selectMasterDataTableRow('" + this.clientId + "', this);" + this.prepareSubmitAjaxScript(null, MasterFacesEvent.SELECTION, i), "onclick");

			for (UIComponent uiColumn : masterDatatable.getChildren()) {

				UIMasterColumn column = (UIMasterColumn) uiColumn;
				column.encodeBegin(context);
			}
			writer.endElement("tr");
		}

		masterDatatable.setRowIndex(-1);
		writer.endElement("tbody");
	}

	private void encodePaginationButtons(FacesContext context, UIMasterData masterDatatable) throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		writer.startElement("div", masterDatatable);
		writer.writeAttribute("id", this.clientId + ":pagination", "id");
		writer.writeAttribute("class", "btn-toolbar pagination-right", "class");

		writer.startElement("div", masterDatatable);
		writer.writeAttribute("id", this.clientId + ":pagination-group", "id");
		writer.writeAttribute("class", "btn-group", "class");

		if (masterDatatable.getTotalRows() > 0) {

			int[] pIndexes = this.calculatePaginationButtonsIndex(masterDatatable.getPageCount(), masterDatatable.getCurrentPageIndex(), 0, 5);
			int pStart = pIndexes[0];
			int pEnd = pIndexes[1];

			long startIndex = 0;
			long endIndex = 0;
			for (int i = 0; i < pStart; i++) {
				startIndex = startIndex + masterDatatable.getRowsPerPage();
			}
			endIndex = endIndex + startIndex;

			boolean currentPage = masterDatatable.getCurrentPageIndex() == 0;
			this.encodeButton(writer, currentPage, this.clientId + ":start", "<<", 0, masterDatatable.getRowsPerPage(), 0);
			this.encodeButton(writer, currentPage, this.clientId + ":previous", "<");

			for (int i = pStart; i < pEnd && masterDatatable.getRowsPerPage() > 0 && masterDatatable.getRowCount() > 0; i++) {
				endIndex = endIndex + masterDatatable.getRowsPerPage();
				if (endIndex > masterDatatable.getTotalRows()) {
					endIndex = startIndex + (masterDatatable.getTotalRows() - startIndex);
				}

				currentPage = i == masterDatatable.getCurrentPageIndex();
				this.encodeButton(writer, currentPage, this.clientId + (i + 1), i + 1, startIndex, endIndex, i);
				startIndex = endIndex;
			}

			currentPage = this.verificaPaginaCorrenteFinal(masterDatatable);
			this.encodeButton(writer, currentPage, this.clientId + ":next", ">");
			this.encodeButton(writer, currentPage, this.clientId + ":end", ">>", masterDatatable.getTotalRows() - masterDatatable.getRowsPerPage(),
					masterDatatable.getTotalRows(), masterDatatable.getPageCount() - 1);
		} else {
			this.encodeButton(writer, true, this.clientId + ":start", "<<");
			this.encodeButton(writer, true, this.clientId + ":previous", "<");
			this.encodeButton(writer, true, this.clientId + ":next", ">");
			this.encodeButton(writer, true, this.clientId + ":end", ">>");
		}

		writer.endElement("div");
		writer.endElement("div");
	}

	private void encodeButton(ResponseWriter writer, boolean currentPage, String buttonId, Object value, Object... params) throws IOException {
		writer.startElement("input", null);
		if (currentPage) {
			writer.writeAttribute("disabled", "disabled", "disabled");
		}
		writer.writeAttribute("id", buttonId, "id");
		writer.writeAttribute("name", buttonId, "name");
		writer.writeAttribute("type", "button", "type");
		writer.writeAttribute("class", "btn", "class");
		writer.writeAttribute("onclick", this.prepareSubmitAjaxScript(this.clientId, MasterFacesEvent.PAGINATION, params), "onclick");
		writer.writeAttribute("value", value, "value");
		writer.endElement("input");
	}

	private void encodeTableFooter(FacesContext context, UIMasterData masterDatatable) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.endElement("table");
	}

	private String prepareSubmitAjaxScript(String render, MasterFacesEvent event, Object... params) {
		StringBuilder script = new StringBuilder("jsf.ajax.request(this,event");
		if (render != null) {
			script.append(",{render:'" + render + "',");
		} else {
			script.append(",{");
		}
		script.append("execute:'@form',params:'MasterFacesEvent." + event + ":_");

		for (int i = 0; i < params.length; i++) {
			script.append("" + params[i] + "_");
		}

		script.append("'});");
		return script.toString();
	}

	private int[] calculatePaginationButtonsIndex(int pageCount, int currentPageIndex, int start, int end) {
		if (pageCount > 5) {
			if (currentPageIndex > 3) {
				end = currentPageIndex + 2;
				start = end - 5;
			} else {
				start = 0;
				end = 5;
			}

			if (end > pageCount) {
				start--;
				end--;
			}
		} else {
			end = pageCount;
		}
		return new int[] { start, end };
	}

	private boolean verificaPaginaCorrenteFinal(UIMasterData masterDatatable) {
		if ((masterDatatable.getCurrentPageIndex() + 1 == masterDatatable.getPageCount()) || (masterDatatable.getRowsPerPage() == 0)
				|| (masterDatatable.getRowCount() == 0)) {
			return true;
		}
		return false;
	}
}
