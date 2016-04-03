package boletos;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Calendar;

import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Endereco;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;

public class Boletos {

	public static void main(String[] args) throws URISyntaxException {
		// Boletos.geraBoletoComBopepo();
		Boletos.geraBoletoComStella();
	}

	// private static void geraBoletoComBopepo() {
	// Calendar dataDocumento = Calendar.getInstance();
	// dataDocumento.set(Calendar.DAY_OF_MONTH, 25);
	// dataDocumento.set(Calendar.MONTH, 1);
	//
	// Calendar dataVencimento = Calendar.getInstance();
	// dataVencimento.set(Calendar.DAY_OF_MONTH, 27);
	// dataVencimento.set(Calendar.MONTH, 2);
	//
	// Cedente cedente = new
	// Cedente("IPEM-SP - Instituto de Pesos e Medidas do Estado de São Paulo",
	// "61.924.981/0001-58");
	//
	// Sacado sacado = new Sacado("Teste", "111.238.299-26");
	// org.jrimum.domkee.comum.pessoa.endereco.Endereco enderecoSacado = new
	// org.jrimum.domkee.comum.pessoa.endereco.Endereco();
	// enderecoSacado.setBairro("Teste");
	// enderecoSacado.setLogradouro("Teste");
	// enderecoSacado.setCep(new CEP("11111-111"));
	// enderecoSacado.setLocalidade("São Paulo");
	// enderecoSacado.setUF(UnidadeFederativa.SP);
	// sacado.addEndereco(enderecoSacado);
	//
	// ContaBancaria contaBancaria = new ContaBancaria();
	// contaBancaria.setBanco(BancosSuportados.BANCO_DO_BRASIL.create());
	// contaBancaria.setNumeroDaConta(new NumeroDaConta(80748));
	// contaBancaria.setCarteira(new Carteira(18));
	// contaBancaria.setAgencia(new Agencia(1897, "X"));
	//
	// Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
	// titulo.setNumeroDoDocumento("22126380000000407");
	// titulo.setNossoNumero("22126380000000407");
	// titulo.setValor(new BigDecimal("3.43"));
	// titulo.setValorCobrado(new BigDecimal("3.43"));
	// titulo.setDataDoDocumento(dataDocumento.getTime());
	// titulo.setDataDoVencimento(dataVencimento.getTime());
	// titulo.setTipoDeDocumento(TipoDeTitulo.ND_NOTA_DE_DEBITO);
	// titulo.setAceite(Aceite.N);
	// titulo.setDesconto(BigDecimal.ZERO);
	// titulo.setDeducao(BigDecimal.ZERO);
	// titulo.setMora(BigDecimal.ZERO);
	// titulo.setAcrecimo(BigDecimal.ZERO);
	// titulo.setValorCobrado(BigDecimal.ZERO);
	//
	// org.jrimum.bopepo.Boleto boleto = new org.jrimum.bopepo.Boleto(titulo);
	// boleto.setLocalPagamento("PAGÁVEL EM QUALQUER BANCO ATÉ O VENCIMENTO");
	// boleto.setInstrucao1("Não receber após o recebimento.");
	//
	// System.out.println(boleto.getLinhaDigitavel().write());
	// System.out.println(boleto.getCodigoDeBarras().write());
	//
	// BoletoViewer boletoViewer = new BoletoViewer(boleto);
	// boletoViewer.getPdfAsFile(new
	// File("C:\\Users\\mggoes\\Downloads\\BoletoBopepo.pdf"));
	//
	// System.out.println("OK");
	// }

	private static void geraBoletoComStella() {
		Calendar dataDocumento = Calendar.getInstance();
		dataDocumento.set(Calendar.DAY_OF_MONTH, 25);
		dataDocumento.set(Calendar.MONTH, 1);

		Calendar dataVencimento = Calendar.getInstance();
		dataVencimento.set(Calendar.DAY_OF_MONTH, 27);
		dataVencimento.set(Calendar.MONTH, 2);

		Datas datas = Datas.novasDatas().comDocumento(dataDocumento).comProcessamento(Calendar.getInstance()).comVencimento(dataVencimento);

		Beneficiario beneficiario = Beneficiario.novoBeneficiario().comNumeroConvenio("2212638").comDocumento("61.924.981/0001-58")
				.comNomeBeneficiario("IPEM-SP - Instituto de Pesos e Medidas do Estado de São Paulo").comAgencia("1897").comDigitoAgencia("X")
				.comCodigoBeneficiario("8074").comDigitoCodigoBeneficiario("8").comCarteira("18").comNossoNumero("22126380000000407");

		Endereco enderecoPagador = Endereco.novoEndereco().comLogradouro("Teste").comBairro("Teste").comCep("11111-111").comCidade("São Paulo").comUf("SP");

		Pagador pagador = Pagador.novoPagador().comDocumento("111.238.299-26").comEndereco(enderecoPagador);

		BancoDoBrasil banco = new BancoDoBrasil();

		Boleto boleto = Boleto.novoBoleto().comBanco(banco).comDatas(datas).comBeneficiario(beneficiario).comPagador(pagador).comValorBoleto("3.43")
				.comNumeroDoDocumento("22126380000000407").comInstrucoes("Não receber após o recebimento.")
				.comLocaisDePagamento("PAGÁVEL EM QUALQUER BANCO ATÉ O VENCIMENTO").comEspecieDocumento("N/D").comEspecieMoeda(null);

		GeradorDeBoleto geradorDeBoleto = new GeradorDeBoleto(boleto);
		geradorDeBoleto.geraPDF(new File("C:\\Users\\mggoes\\Downloads\\BoletoStella.pdf"));

		System.out.println("OK");
	}
}
