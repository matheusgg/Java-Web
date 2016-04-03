package br.com.jpa2hibernate;

import java.util.List;

import br.com.jpa2hibernate.model.Editora;
import br.com.jpa2hibernate.model.dao.EditoraDAO;

public class Livraria {
	public static void main(String[] args) {
		EditoraDAO editoraDAO = new EditoraDAO();
		List<Editora> lista = editoraDAO.lista("id", 5, Integer.class);
		System.out.println(lista.size());
	}
}
