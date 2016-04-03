package br.com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Servlet implementation class RelatorioServlet
 */
@WebServlet("/Relatorio")
public class RelatorioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RelatorioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String caminhoArquivoJasper = "C:\\Users\\Matheus\\Documents\\Relatórios iReports\\sub_relatorio.jasper";
		HashMap<String, Object> parametros = new HashMap<>();

		try {
			ServletOutputStream servletOutput = response.getOutputStream();

			/*
			 * Aqui é especificado o tipo de arquivo e se será feito o download
			 * (attachment) ou exibido no browser navegador (inline).
			 */
			response.setContentType("application/pdf");
			response.addHeader("Content-disposition", "inline; filename=\"Relatorio.pdf\"");

			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJasper);
			JasperPrint impressoraJasper = JasperFillManager.fillReport(report, parametros, this.getConexao());

			/*
			 * Aqui o relatório é exportado e armazenado no outputStream para
			 * ser descarregado no navegador.
			 */
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutput);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
			exporter.exportReport();

			/*
			 * Nesta linha o relatório é descarregado no browser através de um
			 * ServletOutputStream
			 */
			servletOutput.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private Connection getConexao() throws Exception {
		Connection conexao = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/financeiro", "root", "root");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return conexao;
	}

}
