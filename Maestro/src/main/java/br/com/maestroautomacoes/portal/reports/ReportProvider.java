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
	 * M�todo que gera o relat�rio de or�amento de acordo com os par�metros
	 * informados. O relat�rio gerado � exibido diretamento no browser do
	 * usu�rio.
	 * 
	 * @param lista
	 *            Fonte do relat�rio.
	 * @param nomeArquivoJasper
	 *            Nome do arquivo jasper com a extens�o.
	 * @param nomeRelatorioGerado
	 *            Nome do relat�rio que ser� gerado.
	 * @param parametros
	 *            Par�metros que ser�o passados para o relat�rio.
	 * @return A refer�ncia para um objeto do tipo File que cont�m o caminho
	 *         f�sico do relat�rio gerado.
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

		// Exporta o relat�rio para gerar o arquivo f�sico que ser� enviado
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
	 * Adiciona na sess�o o or�amento que ser� gerado e redireciona para o
	 * Servlet respons�vel pela renderiza��o do relat�rio na p�gina do browser.
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
