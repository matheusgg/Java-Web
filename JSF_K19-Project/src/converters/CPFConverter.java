package converters;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.CPF;

@FacesConverter(forClass = CPF.class)
public class CPFConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null) {
			value = value.trim();

			FacesMessage msg = null;
			if (!Pattern.matches("[0-9]{9}-[0-9]{2}", value)) {
				msg = new FacesMessage("Informe um CPF válido!");
				msg.setSeverity(FacesMessage.SEVERITY_FATAL);
				throw new ConverterException(msg);
			}

			String partesCPF[] = value.split("-");
			int identificacao = Integer.parseInt(partesCPF[0]);
			int primeiroDigito = Integer.parseInt(partesCPF[1].substring(0, 1));
			int segundoDigito = Integer.parseInt(partesCPF[1].substring(1));

			CPF cpf = new CPF();
			cpf.setNumeroIdentificacao(identificacao);
			cpf.setPrimeiroDigitoVerificador(primeiroDigito);
			cpf.setSegundoDigitoVerificador(segundoDigito);
			return cpf;
		}
		return value;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		CPF cpf = (CPF) value;
		return cpf.getNumeroIdentificacao() + "-" + cpf.getPrimeiroDigitoVerificador() + cpf.getSegundoDigitoVerificador();
	}

}
