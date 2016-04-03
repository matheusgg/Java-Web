package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import model.Estado;

@ManagedBean
@SessionScoped
public class CadastroBean {
	private String nome;
	private String sigla;
	private String siglaEstadoEscolhido;
	private Estado estadoSelecionado;
	private List<Estado> estados;

	public CadastroBean() {
		this.estados = new ArrayList<Estado>();

		Estado sp = new Estado();
		sp.setSigla("SP");
		sp.setNome("São Paulo");
		sp.getCidades().add("São Paulo");
		sp.getCidades().add("Campinas");

		Estado rj = new Estado();
		rj.setSigla("RJ");
		rj.setNome("Rio de Janeiro");
		rj.getCidades().add("Rio de Janeiro");
		rj.getCidades().add("Niterói");

		Estado rn = new Estado();
		rn.setSigla("RN");
		rn.setNome("Rio Grande no Norte");
		rn.getCidades().add("Natal");
		rn.getCidades().add("Mossoró");

		this.estados.add(sp);
		this.estados.add(rj);
		this.estados.add(rn);
	}

	public void mudaEstado(ValueChangeEvent event) {
		this.siglaEstadoEscolhido = event.getNewValue().toString();
		for (Estado e : this.estados) {
			if (e.getSigla().equals(this.siglaEstadoEscolhido)) {
				this.estadoSelecionado = e;
				break;
			}
		}
		FacesContext.getCurrentInstance().renderResponse();
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
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @param sigla
	 *            the sigla to set
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * @return the siglaEstadoEscolhido
	 */
	public String getSiglaEstadoEscolhido() {
		return siglaEstadoEscolhido;
	}

	/**
	 * @param siglaEstadoEscolhido
	 *            the siglaEstadoEscolhido to set
	 */
	public void setSiglaEstadoEscolhido(String siglaEstadoEscolhido) {
		this.siglaEstadoEscolhido = siglaEstadoEscolhido;
	}

	/**
	 * @return the estadoSelecionado
	 */
	public Estado getEstadoSelecionado() {
		return estadoSelecionado;
	}

	/**
	 * @param estadoSelecionado
	 *            the estadoSelecionado to set
	 */
	public void setEstadoSelecionado(Estado estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	/**
	 * @return the estados
	 */
	public List<Estado> getEstados() {
		return estados;
	}

	/**
	 * @param estados
	 *            the estados to set
	 */
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

}
