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
			 * Recupera o caminho até a parta relatórios da aplicação
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
			 * Aqui é feita a compilação do arquivo do relatório
			 */	
			JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJasper.toString());
			
			/*
			 * Aqui é feito o carregamento do arquivo compilado do relatório em memória.
			 * Nesta linha, é criado o arquivo .jasper que mais adiante será exportado
			 * para um formato específico. Além disso, a classe JasperFillManager é reponsável
			 * por preencher as informações que serão utilizadas no relatório.
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
			 * Preparação do caminho onde o relatório será salvo no servidor 
			 */
			caminhoArquivoRelatorio = new StringBuilder();
			caminhoArquivoRelatorio.append(caminhoRelatorio);
			caminhoArquivoRelatorio.append(File.separator);
			caminhoArquivoRelatorio.append(nomeRelatorioSaida);
			caminhoArquivoRelatorio.append(".");
			caminhoArquivoRelatorio.append(extensaoArquivoExportado);
			
			/*
			 * Aqui é especificado qual impressora Jasper será utilizada e qual o nome do arquivo
			 * físico que o relatório será gerado. É neste ponto que o relatório é exportado.
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
			new UtilException("Não foi possível gerar o relatório");
			ex.printStackTrace();
		}	
		
		return arquivoRetorno;
	}

	/**
	 * @author Matheus
	 * Método que recupera e retorna a conexão da referência JNDI 
	 * @return conexao
	 */
	private Connection getConnection() {
		Connection conexao = null;
		try{
			Context initContext = new InitialContext();
			/*
			 * Na utilização de servidores como JBoss e GlassFish, esta linha não é necessária,
			 * pois a configuração é feita diretamente no servidor e o nome do datasource ficaria
			 * apenas 'jdbc/FinanceiroDB'
			 */
			Context envContext = (Context) initContext.lookup("java:/comp/env/");
			DataSource ds = (DataSource) envContext.lookup("jdbc/FinanceiroDB");
			conexao = (Connection) ds.getConnection();						
		}catch(Exception ex){
			new UtilException("Ocorreu um erro na recuperação da conexão. Tente novamente.");
		}
		
		return conexao;
	}

}
