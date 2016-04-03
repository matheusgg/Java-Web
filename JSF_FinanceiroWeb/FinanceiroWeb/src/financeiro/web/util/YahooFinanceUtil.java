package financeiro.web.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import financeiro.bolsa.acao.Acao;

public class YahooFinanceUtil {
	private String local;
	private String[] informacaoCotacao;
	public static final char ORIGEM_BOVESPA = 'B';
	public static final char ORIGEM_MUNDO = 'M';
	public static final String LOCAL_BOVESPA = "br";
	public static final String LOCAL_MUNDO = "download";
	public static final String POSFIXO_ACAO_BOVESPA = ".SA";
	public static final String SEPARADOR_BOVESPA = ";";
	public static final String SEPARADOR_MUNDO = ",";
	public static final String INDICE_BOVESPA = "^BVSP";
	public static final int SIGLA_ACAO_INDICE = 0;
	public static final int ULTIMO_PRECO_DIA_ACAO_INDICE = 1;
	public static final int DATA_NEGOCIACAO_ACAO_INDICE = 2;
	public static final int HORA_NEGOCIACAO_ACAO_INDICE = 3;
	public static final int VARIACAO_DIA_ACAO_INDICE = 4;
	public static final int PRECO_ABERTURA_DIA_ACAO_INDICE = 5;
	public static final int MAIOR_PRECO_DIA_ACAO_INDICE = 6;
	public static final int MENOR_PRECO_DIA_ACAO_INDICE = 7;
	public static final int VOLUME_NEGOCIACAO_DIA_ACAO_INDICE = 8;

	public YahooFinanceUtil(Acao acao) {
		if (acao.getOrigem() == YahooFinanceUtil.ORIGEM_BOVESPA) {
			this.local = YahooFinanceUtil.LOCAL_BOVESPA;
		} else {
			this.local = YahooFinanceUtil.LOCAL_MUNDO;
		}
	}

	public String retornaCotacao(int indiceInformacao, String acao)
			throws IOException {
		if (this.local == YahooFinanceUtil.LOCAL_BOVESPA) {
			acao = acao + YahooFinanceUtil.POSFIXO_ACAO_BOVESPA;
		}

		if ((indiceInformacao > 8) || (indiceInformacao < 0)) {
			indiceInformacao = YahooFinanceUtil.ULTIMO_PRECO_DIA_ACAO_INDICE;
		}

		StringBuilder endereco = new StringBuilder();
		endereco.append("http://");
		endereco.append(this.local);
		endereco.append(".finance.yahoo.com/d/quotes.cvs?s=");
		endereco.append(acao);
		endereco.append("&f=sl1d1t1c1ohgv&e=.cvs");

		String linha = null;
		URL url = null;
		String valorRetorno = null;

		try {
			url = new URL(endereco.toString());
			URLConnection conexao = url.openConnection();
			InputStreamReader conteudo = new InputStreamReader(
					conexao.getInputStream());
			BufferedReader arquivo = new BufferedReader(conteudo);

			while ((linha = arquivo.readLine()) != null) {
				linha = linha.replace("\"", "");
				this.informacaoCotacao = linha.split(new StringBuilder()
						.append("[").append(YahooFinanceUtil.SEPARADOR_BOVESPA)
						.append(YahooFinanceUtil.SEPARADOR_MUNDO).append("]")
						.toString());
			}

			arquivo.close();
			valorRetorno = this.informacaoCotacao[indiceInformacao];
		} catch (MalformedURLException ex) {
			System.err.println("URL Inválida! " + ex.getMessage());
		} catch (IOException ex) {
			System.err.println("Erro na escrita ou na leitura "
					+ ex.getLocalizedMessage());
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.err.println("Não existe o índice informado no array "
					+ ex.getMessage());
		}
		return valorRetorno;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String[] getInformacaoCotacao() {
		return informacaoCotacao;
	}

	public void setInformacaoCotacao(String[] informacaoCotacao) {
		this.informacaoCotacao = informacaoCotacao;
	}
}
