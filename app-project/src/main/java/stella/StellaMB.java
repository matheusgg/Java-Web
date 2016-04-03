package stella;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Endereco;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.bancos.GeradorDeLinhaDigitavel;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;

@Named
@ViewScoped
public class StellaMB implements Serializable {

	private static final long serialVersionUID = 3574523468757167470L;

	public void geraBoletoBB(boolean downloadFile) throws IOException {
		Datas datas = Datas.novasDatas().comDocumento(Calendar.getInstance()).comProcessamento(Calendar.getInstance()).comVencimento(Calendar.getInstance());

		Beneficiario beneficiario = Beneficiario.novoBeneficiario().comNumeroConvenio("001").comDocumento("61.924.981/0001-58")
				.comNomeBeneficiario("IPEM-SP - Instituto de Pesos e Medidas do Estado de São Paulo").comAgencia("1897").comDigitoAgencia("X")
				.comCodigoBeneficiario("80748").comCarteira("18").comNossoNumero("22126380000000260");

		Endereco enderecoPagador = Endereco.novoEndereco().comLogradouro("teste").comBairro("teste").comCep("11111-111").comCidade("São Paulo").comUf("SP");

		Pagador pagador = Pagador.novoPagador().comDocumento("33.203.299/0001-30").comEndereco(enderecoPagador);

		BancoDoBrasil banco = new BancoDoBrasil();

		Boleto boleto = Boleto.novoBoleto().comBanco(banco).comDatas(datas).comBeneficiario(beneficiario).comPagador(pagador).comValorBoleto("3.43")
				.comNumeroDoDocumento("22126380000000260").comInstrucoes("Não receber após o recebimento.")
				.comLocaisDePagamento("PAGÁVEL EM QUALQUER BANCO ATÉ O VENCIMENTO");

		GeradorDeBoleto geradorDeBoleto = new GeradorDeBoleto(boleto);
		byte[] boletoBytes = geradorDeBoleto.geraPDF();

		if (downloadFile) {
			Faces.sendFile(boletoBytes, "boleto.pdf", true);
		} else {
			String codigoDeBarras = banco.geraCodigoDeBarrasPara(boleto);
			Messages.addInfo(null, "Código de barras: " + codigoDeBarras);
			Messages.addInfo(null, "Linha Digitável: " + new GeradorDeLinhaDigitavel().geraLinhaDigitavelPara(codigoDeBarras, banco));
		}
	}
}
