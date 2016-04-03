package financeiro.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import financeiro.entidade.Entidade;
import financeiro.entidade.EntidadeRN;

@ManagedBean
@RequestScoped
public class EntidadeBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7198205160279300529L;
	private List<Entidade> listaEntidades;

	public List<String> autoCompleteEntidade(String query) {
		this.listaEntidades = new EntidadeRN().listar(query);

		List<String> lista = new ArrayList<String>();
		for (Entidade entidade : this.listaEntidades) {
			lista.add(entidade.getNome());
		}

		return lista;
	}
}
