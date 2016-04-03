package com.livro.capitulo3.crudxml;

import java.sql.Date;
import java.util.List;

import com.livro.capitulo3.crudannotations.Contato;

public class TestaProjeto {
	public static void main(String[] args) {
		String[] nomes = new String[]{"Matheus", "Rose", "Nelson"};
		String[] telefones = new String[]{"83321607", "80863139", "56263435"};
		String[] emails = new String[]{"Matheus@email", "Rose@email", "Nelson@email"};
		String[] observacoes = new String[]{"Observação 1", "Observação 2", "Observação 3"};
		
		ContatoCrudXML crud = new ContatoCrudXML();
		Contato contato = null;
		
		/*
		 * Salvar
		 */
		/*for (int i = 0; i < 3; i++) {
			contato = new Contato();
			contato.setNome(nomes[i]);
			contato.setTelefone(telefones[i]);
			contato.setEmail(emails[i]);
			contato.setDataCadastro(new Date(System.currentTimeMillis()));
			contato.setObservacao(observacoes[i]);
			
			crud.salvar(contato);			
		}
		
		/*
		 * Atualizar
		 */
		/*contato = new Contato();
		contato.setCodigo(4);
		contato.setNome("Matheus Gomes Góes");
		contato.setTelefone(telefones[0]);
		contato.setEmail("matheuzinho.goes@gmail.com");
		contato.setDataCadastro(new Date(System.currentTimeMillis()));
		contato.setObservacao(observacoes[0]);
		
		crud.atualizar(contato);
		
		/*
		 * Excluir
		 */
		/*contato = new Contato();
		contato.setCodigo(4);
		
		crud.excluir(contato);
		
		/*
		 * Listar
		 */
		/*List<Contato> listaContatos = crud.listar();
		System.out.println("==================================================");
		for (int i = 0; i < 2; i++) {
			contato = listaContatos.get(i);
			System.out.println("Nome: " + contato.getNome());
		}
		System.out.println("==================================================");
		
		/*
		 * Buscar
		 */
		contato = new Contato();
		contato = crud.buscar(5);
		System.out.println("==================================================");
		System.out.println("Nome: " + contato.getNome());
		System.out.println("Nome: " + contato.getTelefone());		
		System.out.println("==================================================");
	}

}
