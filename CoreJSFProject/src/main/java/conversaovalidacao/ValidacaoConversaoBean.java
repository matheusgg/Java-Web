package conversaovalidacao;

import java.math.BigDecimal;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import model.Usuario;
import conversaovalidacao.beanvalidation.ValidationGroup;

@Named
@ViewScoped
public class ValidacaoConversaoBean {

	private String nome;
	private Integer idade;
	private BigDecimal salario;

	public void testBeanValidation() {
		Usuario usuario = new Usuario();

		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();

		/*
		 * Se nenhum grupo for informado ao metodo, apenas os campos que
		 * possuirem o grupo padrao (javax.validation.groups.Default, ou seja,
		 * nenhum grupo definido) serao validados.
		 */
		Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario, ValidationGroup.class);
		if (!violations.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ocorreram erros de validacao com BeanValidation."));
		}
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the idade
	 */
	public Integer getIdade() {
		return idade;
	}

	/**
	 * @param idade
	 *            the idade to set
	 */
	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	/**
	 * @return the salario
	 */
	public BigDecimal getSalario() {
		return salario;
	}

	/**
	 * @param salario
	 *            the salario to set
	 */
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

}
