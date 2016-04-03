package view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import model.Cliente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import org.omnifaces.util.Faces;

@Named
@ViewScoped
public class ReportMB implements Serializable {

	private static final long serialVersionUID = -6515725055610956634L;

	private ExporterInput exporterInput;
	private ByteArrayOutputStream outputStream;

	private void generateReport() throws JRException, IOException {
		List<Cliente> clientes = new ArrayList<>();
		Cliente cliente = null;
		for (int i = 0; i < 10; i++) {
			cliente = new Cliente();
			cliente.setId(i + 1);
			cliente.setNome("Cliente " + (i + 1));
			clientes.add(cliente);
		}

		this.outputStream = new ByteArrayOutputStream();
		String reportPath = Faces.getRealPath("/WEB-INF/reports/example.jasper");

		JasperPrint print = JasperFillManager.fillReport(reportPath, new HashMap<>(), new JRBeanCollectionDataSource(clientes));
		this.exporterInput = new SimpleExporterInput(print);
	}

	/**
	 * DOCX
	 * 
	 * @throws JRException
	 * @throws IOException
	 */
	public void exportToDocx() throws JRException, IOException {
		this.generateReport();
		JRDocxExporter docxExporter = new JRDocxExporter();
		docxExporter.setExporterInput(this.exporterInput);
		SimpleOutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(this.outputStream);
		docxExporter.setExporterOutput(exporterOutput);
		docxExporter.exportReport();
		Faces.sendFile(this.outputStream.toByteArray(), "Example.docx", true);
	}

	/**
	 * PDF
	 * 
	 * @throws JRException
	 * @throws IOException
	 */
	public void exportToPdf() throws JRException, IOException {
		this.generateReport();
		JRPdfExporter pdfExporter = new JRPdfExporter();
		pdfExporter.setExporterInput(this.exporterInput);
		SimpleOutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(this.outputStream);
		pdfExporter.setExporterOutput(exporterOutput);
		pdfExporter.exportReport();
		Faces.sendFile(this.outputStream.toByteArray(), "Example.pdf", false);
	}

	/**
	 * XLSX
	 * 
	 * @throws JRException
	 * @throws IOException
	 */
	public void exportToXlsx() throws JRException, IOException {
		this.generateReport();
		JRXlsxExporter xlsxExporter = new JRXlsxExporter();
		xlsxExporter.setExporterInput(this.exporterInput);
		SimpleOutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(this.outputStream);
		xlsxExporter.setExporterOutput(exporterOutput);
		xlsxExporter.exportReport();
		Faces.sendFile(this.outputStream.toByteArray(), "Example.xlsx", true);
	}

	/**
	 * HTML
	 * 
	 * @throws JRException
	 * @throws IOException
	 */
	public void exportToHTML() throws JRException, IOException {
		this.generateReport();
		HtmlExporter htmlExporter = new HtmlExporter();
		htmlExporter.setExporterInput(this.exporterInput);
		SimpleHtmlExporterOutput exporterOutput = new SimpleHtmlExporterOutput(this.outputStream);
		htmlExporter.setExporterOutput(exporterOutput);
		htmlExporter.exportReport();
		Faces.sendFile(this.outputStream.toByteArray(), "Example.html", true);
	}

}
