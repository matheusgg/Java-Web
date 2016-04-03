package model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import beanValidators.CodigoValidator;

public class Funcionario {

	@NotNull
	@Min(value = 0, message = "O valor deve ser maior do que 0")
	private Double salario;

	@NotNull
	@CodigoValidator(max = 19, min = 5, message = "O valor deve ser menor que 20 e maior que 5")
	private Integer codigo;

	@NotNull(message = "Preencha a data")
	private Date nascimento;

	@NotNull
	private CPF cpf;

	/**
	 * @return the salario
	 */
	public Double getSalario() {
		return salario;
	}

	/**
	 * @param salario
	 *            the salario to set
	 */
	public void setSalario(Double salario) {
		this.salario = salario;
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nascimento
	 */
	public Date getNascimento() {
		return nascimento;
	}

	/**
	 * @param nascimento
	 *            the nascimento to set
	 */
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	/**
	 * @return the cpf
	 */
	public CPF getCpf() {
		return cpf;
	}

	/**
	 * @param cpf
	 *            the cpf to set
	 */
	public void setCpf(CPF cpf) {
		this.cpf = cpf;
	}

}
