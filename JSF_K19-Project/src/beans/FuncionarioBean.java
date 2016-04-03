package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Funcionario;

@ManagedBean
@SessionScoped
public class FuncionarioBean {
	private List<Funcionario> funcionarios;
	private Funcionario funcionarioSelecionado;

	public FuncionarioBean() {
		this.funcionarioSelecionado = new Funcionario();
		this.funcionarios = new ArrayList<Funcionario>();
	}

	public void cadastraFuncionario() {
		this.funcionarios.add(this.funcionarioSelecionado);
		this.funcionarioSelecionado = new Funcionario();
		FacesMessage msg = new FacesMessage("Funcionário cadastrado com sucesso!");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * @return the funcionarios
	 */
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	/**
	 * @param funcionarios
	 *            the funcionarios to set
	 */
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	/**
	 * @return the funcionarioSelecionado
	 */
	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	/**
	 * @param funcionarioSelecionado
	 *            the funcionarioSelecionado to set
	 */
	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

}
