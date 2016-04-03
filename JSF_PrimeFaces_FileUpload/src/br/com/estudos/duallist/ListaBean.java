package br.com.estudos.duallist;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DualListModel;

public class ListaBean {
	private DualListModel<String> listaPessoas;
	private List<Pessoa> list;
	private String teste;

	public ListaBean() {
		list = new ArrayList<Pessoa>();
		list.add(new Pessoa("Nome 1", "Tempo 1"));
		list.add(new Pessoa("Nome 2", "Tempo 2"));

		List<String> peopleSource = new ArrayList<String>();
		List<String> peopleTarget = new ArrayList<String>();

		peopleSource.add(list.get(0).getNome());
		peopleSource.add(list.get(1).getNome());

		listaPessoas = new DualListModel<String>(peopleSource, peopleTarget);
	}

	public String getTeste() {
		return teste;
	}

	public void setTeste(String teste) {
		this.teste = teste;
	}

	public List<Pessoa> getList() {
		return list;
	}

	public void setList(List<Pessoa> list) {
		this.list = list;
	}

	public String clique() {
		System.out.println(listaPessoas.getTarget().size());
		return "";
	}

	public DualListModel<String> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(DualListModel<String> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}
}
