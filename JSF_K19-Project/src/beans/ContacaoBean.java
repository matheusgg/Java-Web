package beans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ContacaoBean {
	private Double valoDolar;
	private Double valorAConverter;
	private Double valorConvertido;
	private Date horaAtual;

	public void converteValor() {
		this.valorConvertido = this.valorAConverter / this.valoDolar;
	}

	/**
	 * @return the valoDolar
	 */
	public Double getValoDolar() {
		return valoDolar;
	}

	/**
	 * @param valoDolar
	 *            the valoDolar to set
	 */
	public void setValoDolar(Double valoDolar) {
		this.valoDolar = valoDolar;
	}

	/**
	 * @return the valorAConverter
	 */
	public Double getValorAConverter() {
		return valorAConverter;
	}

	/**
	 * @param valorAConverter
	 *            the valorAConverter to set
	 */
	public void setValorAConverter(Double valorAConverter) {
		this.valorAConverter = valorAConverter;
	}

	/**
	 * @return the valorConvertido
	 */
	public Double getValorConvertido() {
		return valorConvertido;
	}

	/**
	 * @param valorConvertido
	 *            the valorConvertido to set
	 */
	public void setValorConvertido(Double valorConvertido) {
		this.valorConvertido = valorConvertido;
	}

	/**
	 * @return the horaAtual
	 */
	public Date getHoraAtual() {
		return horaAtual;
	}

	/**
	 * @param horaAtual
	 *            the horaAtual to set
	 */
	public void setHoraAtual(Date horaAtual) {
		this.horaAtual = horaAtual;
	}

}
