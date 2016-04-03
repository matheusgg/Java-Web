package br.com.estudos.menu;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

public class Formacoes {
	private String nome;
	private List<Formacoes> formacoes;
	private DataModel<Formacoes> dataModel;

	public Formacoes() {
		formacoes = new ArrayList<Formacoes>();
		dataModel = new ListDataModel<Formacoes>(formacoes);
	}

	public Formacoes(String nome) {
		this.nome = nome;
	}

	public String inserir() {
		Formacoes f = new Formacoes(this.nome);
		formacoes.add(f);
		return "";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Formacoes> getFormacoes() {
		return formacoes;
	}

	public void setFormacoes(List<Formacoes> formacoes) {
		this.formacoes = formacoes;
	}

	public DataModel<Formacoes> getDataModel() {
		return dataModel;
	}

	public void setDataModel(DataModel<Formacoes> dataModel) {
		this.dataModel = dataModel;
	}
}
