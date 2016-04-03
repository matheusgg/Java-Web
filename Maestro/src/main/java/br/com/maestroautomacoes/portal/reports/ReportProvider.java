package br.com.maestroautomacoes.portal.reports;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import br.com.maestroautomacoes.portal.util.SiteUtil;

public abstract class ReportProvider {

	/**
	 * Método que gera o relatório de orçamento de acordo com os parâmetros
	 * informados. O relatório gerado é exibido diretamento no browser do
	 * usuário.
	 * 
	 * @param lista
	 *            Fonte do relatório.
	 * @param nomeArquivoJasper
	 *            Nome do arquivo jasper com a extensão.
	 * @param nomeRelatorioGerado
	 *            Nome do relatório que será gerado.
	 * @param parametros
	 *            Parâmetros que serão passados para o relatório.
	 * @return A referência para um objeto do tipo File que contêm o caminho
	 *         físico do relatório gerado.
	 */
	public static File geraRelatorioOrcamento(List<?> lista, String nomeArquivoJasper, String nomeRelatorioGerado, HashMap<String, Object> parametros,
			boolean criarArquivoExterno) throws Exception {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		String caminhoArquivoJasper = externalContext.getRealPath("resources") + File.separator + "relatorios" + File.separator + nomeArquivoJasper;
		String caminhoImagens = externalContext.getRealPath("resources") + File.separator + "images" + File.separator;
		parametros.put("caminhoImagens", caminhoImagens);
		File relatorio = null;

		JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJasper);
		JasperPrint print = JasperFillManager.fillReport(report, parametros, ReportProvider.create(lista));

		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

		// Exporta o relatório para gerar o arquivo físico que será enviado
		// por email
		if (criarArquivoExterno) {
			relatorio = new File(caminhoArquivoJasper.replace(nomeArquivoJasper, nomeRelatorioGerado + ".pdf"));
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE, relatorio);
			exporter.exportReport();
		}

		ReportProvider.renderizaRelatorioNoBrowser(exporter, nomeRelatorioGerado);
		return relatorio;
	}

	private static JRDataSource create(List<?> dataSource) throws JRException {
		return new JRBeanCollectionDataSource(dataSource);
	}

	/**
	 * Adiciona na sessão o orçamento que será gerado e redireciona para o
	 * Servlet responsável pela renderização do relatório na página do browser.
	 * 
	 * @param exporter
	 * @param nomeRelatorioGerado
	 * @throws Exception
	 */
	private static void renderizaRelatorioNoBrowser(JRExporter exporter, String nomeRelatorioGerado) throws Exception {
		SiteUtil.addAttibuteInHttpSession("rel", exporter);
		SiteUtil.addAttibuteInHttpSession("relName", nomeRelatorioGerado);
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		context.redirect(context.getRequestContextPath() + "/RelatorioOrcamento");
	}
}
