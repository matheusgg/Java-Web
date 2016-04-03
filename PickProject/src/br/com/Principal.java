package br.com;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DualListModel;

import br.com.model.Candidato;

public class Principal {
	private DualListModel<Candidato> lista;
	private List<Candidato> candidatosDoSistema;

	public Principal() {
		this.lista = new DualListModel<Candidato>();
		this.candidatosDoSistema = new ArrayList<Candidato>();
		this.carregaCandidatos();
	}

	private void carregaCandidatos() {
		for (int i = 0; i < 5; i++) {
			this.candidatosDoSistema.add(new Candidato("Candidato " + i,
					18 + i, 1));
		}

		this.lista.setSource(this.candidatosDoSistema);
	}

	public void mostraDados() {
		for (Candidato candidato : this.getLista().getTarget()) {
			System.out.println(candidato.getNome());
			System.out.println(candidato.getIdade());
			System.out.println(candidato.getSituacao());
			System.out.println("***************************************************");
		}
	}

	public DualListModel<Candidato> getLista() {
		return lista;
	}

	public void setLista(DualListModel<Candidato> lista) {
		this.lista = lista;
	}

	public List<Candidato> getCandidatosDoSistema() {
		return candidatosDoSistema;
	}

	public void setCandidatosDoSistema(List<Candidato> candidatosDoSistema) {
		this.candidatosDoSistema = candidatosDoSistema;
	}
}
