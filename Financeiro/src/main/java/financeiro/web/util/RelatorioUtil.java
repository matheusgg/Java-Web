package financeiro.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import financeiro.util.UtilException;

public class RelatorioUtil {
	public StreamedContent geraRelatorio(HashMap<String, Object> parametrosRelatorio,
			String nomeRelatorioJasper, String nomeRelatorioSaida,
			Relatorio tipoRelatorio) {
		
		StreamedContent arquivoRetorno = null;
		try{
			Connection conexao = this.getConnection();
			
			/*
			 * Recupera o caminho at� a parta relat�rios da aplica��o
			 */
			//String caminhoRelatorio = FacesContext.getCurrentInstance().getExternalContext().getRealPath("relatorios");
			String caminhoRelatorio = "C:\\Users\\Matheus\\Documents\\Trabalhos\\Projetos\\Java Web\\JSF_FinanceiroWeb\\Financeiro_Maven_Project\\src\\main\\webapp\\relatorios";
			StringBuilder caminhoArquivoJasper = new StringBuilder();
			caminhoArquivoJasper.append(caminhoRelatorio);
			caminhoArquivoJasper.append(File.separator);
			caminhoArquivoJasper.append(nomeRelatorioJasper);
			caminhoArquivoJasper.append(".jasper");
			
			StringBuilder caminhoArquivoRelatorio = null;
			
			/*
			 * Aqui � feita a compila��o do arquivo do relat�rio
			 */	
			JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJasper.toString());
			
			/*
			 * Aqui � feito o carregamento do arquivo compilado do relat�rio em mem�ria.
			 * Nesta linha, � criado o arquivo .jasper que mais adiante ser� exportado
			 * para um formato espec�fico. Al�m disso, a classe JasperFillManager � repons�vel
			 * por preencher as informa��es que ser�o utilizadas no relat�rio.
			 */			
			JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, conexao);
			
			JRExporter tipoArquivoExportado = null;
			String extensaoArquivoExportado = "";
			File arquivoGerado = null;
			
			switch(tipoRelatorio){
			case PDF:
				tipoArquivoExportado = new JRPdfExporter();
				extensaoArquivoExportado = "pdf";
				break;
			case HTML:
				tipoArquivoExportado = new JRHtmlExporter();
				extensaoArquivoExportado = "html";
				break;
			case EXCEL:
				tipoArquivoExportado = new JRXlsExporter();
				extensaoArquivoExportado = "xls";
				break;
			case PLANILHA_OPEN_OFFICE:
				tipoArquivoExportado = new JROdsExporter();
				extensaoArquivoExportado = "ods";
				break;
			default:
				tipoArquivoExportado = new JRPdfExporter();
				extensaoArquivoExportado = "pdf";
				break;			
			}
			
			/*
			 * Prepara��o do caminho onde o relat�rio ser� salvo no servidor 
			 */
			caminhoArquivoRelatorio = new StringBuilder();
			caminhoArquivoRelatorio.append(caminhoRelatorio);
			caminhoArquivoRelatorio.append(File.separator);
			caminhoArquivoRelatorio.append(nomeRelatorioSaida);
			caminhoArquivoRelatorio.append(".");
			caminhoArquivoRelatorio.append(extensaoArquivoExportado);
			
			/*
			 * Aqui � especificado qual impressora Jasper ser� utilizada e qual o nome do arquivo
			 * f�sico que o relat�rio ser� gerado. � neste ponto que o relat�rio � exportado.
			 */
			arquivoGerado = new File(caminhoArquivoRelatorio.toString());
			tipoArquivoExportado.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
			tipoArquivoExportado.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);			
			tipoArquivoExportado.exportReport();
			arquivoGerado.deleteOnExit();
			
			StringBuilder nomeArquivoRetorno = new StringBuilder();
			nomeArquivoRetorno.append(nomeRelatorioSaida);
			nomeArquivoRetorno.append(".");
			nomeArquivoRetorno.append(extensaoArquivoExportado);
			
			InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);
			arquivoRetorno = new DefaultStreamedContent(conteudoRelatorio, "application" + "." + extensaoArquivoExportado, 
					nomeRelatorioSaida + "." + extensaoArquivoExportado);
		
		}catch(Exception ex){
			new UtilException("N�o foi poss�vel gerar o relat�rio");
			ex.printStackTrace();
		}	
		
		return arquivoRetorno;
	}

	/**
	 * @author Matheus
	 * M�todo que recupera e retorna a conex�o da refer�ncia JNDI 
	 * @return conexao
	 */
	private Connection getConnection() {
		Connection conexao = null;
		try{
			Context initContext = new InitialContext();
			/*
			 * Na utiliza��o de servidores como JBoss e GlassFish, esta linha n�o � necess�ria,
			 * pois a configura��o � feita diretamente no servidor e o nome do datasource ficaria
			 * apenas 'jdbc/FinanceiroDB'
			 */
			Context envContext = (Context) initContext.lookup("java:/comp/env/");
			DataSource ds = (DataSource) envContext.lookup("jdbc/FinanceiroDB");
			conexao = (Connection) ds.getConnection();						
		}catch(Exception ex){
			new UtilException("Ocorreu um erro na recupera��o da conex�o. Tente novamente.");
		}
		
		return conexao;
	}

}
