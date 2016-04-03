package br.com.maestroautomacoes.portal.reports;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.maestroautomacoes.portal.logger.LogManager;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;

/**
 * Servlet respons�vel por renderizar o or�amento gerado pelo cliente
 * diretamente no browser.
 */
@WebServlet("/RelatorioOrcamento")
public class ReportServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5727668103284906945L;

	/**
	 * M�todo executado na captura da URL para renderizar o relat�rio no
	 * browser.
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			JRExporter exporter = (JRExporter) session.getAttribute("rel");
			String nomeRelatorioGerado = String.valueOf(session.getAttribute("relName"));

			ServletOutputStream outputStream = response.getOutputStream();
			response.setHeader("Content-Type", "application/pdf");
			response.setHeader("Content-disposition", "inline;filename=" + nomeRelatorioGerado);

			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			exporter.exportReport();

			outputStream.flush();
			session.removeAttribute("rel");
			session.removeAttribute("relName");
		} catch (Exception e) {
			String mensagem = LogManager.makeStackTraceLog(e);
			LogManager.getLogger().error(mensagem);
		}
	}

}
