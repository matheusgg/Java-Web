package br.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@ManagedBean
@SessionScoped
public class RelatorioBean {

	public String iniciarPagina() {
		return "relatorios?faces-redirect=true";

	}

	/**
	 * Este método gera o relatório solicitado e exibe o .pdf diretamente no
	 * browser
	 * 
	 * @return
	 */
	public String geraRelatorio() {
		String caminhoArquivoJasper = "C:\\Users\\Matheus\\Documents\\Relatórios iReports\\sub_relatorio.jasper";
		HashMap<String, Object> parametros = new HashMap<>();

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			ServletOutputStream servletOutput = response.getOutputStream();
			/*
			 * Aqui são especificados algumas informações sobre o .pdf que será
			 * gerado no browser
			 */
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline;filename=Relatorio.pdf");

			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJasper);
			JasperPrint impressoraJasper = JasperFillManager.fillReport(report, parametros, this.getConexao());

			/*
			 * Aqui, o relatório é exportado para um vetor de bytes que contém o
			 * conteúdo do mesmo em formato pdf
			 */
			byte[] relatorioEmBytes = JasperExportManager.exportReportToPdf(impressoraJasper);

			/*
			 * Nesta linha o relatório é enviado para o browser através de um
			 * ServletOutputStream
			 */
			servletOutput.write(relatorioEmBytes);

			context.renderResponse();
			context.responseComplete();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "";
	}

	public void redirecionaParaGeracaoRelatorio() {
		try {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.redirect(context.getRequestContextPath().concat("/Relatorio"));
		} catch (Exception e) {
			e.printStackTrace();
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
