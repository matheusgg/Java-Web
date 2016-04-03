package br.com.principal;

import java.util.ArrayList;
import java.util.List;

public class Principal {
	private List<String> lista;

	public Principal() {
		this.lista = new ArrayList<String>();
		this.carregaLista();
	}

	private void carregaLista() {
		for (int i = 0; i < 50; i++) {
			this.lista.add("Mensagem " + i);
		}
	}

	public List<String> getLista() {
		return lista;
	}

	public void setLista(List<String> lista) {
		this.lista = lista;
	}
}
