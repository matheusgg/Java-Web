package com.livro.capitulo3.cadastro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.livro.capitulo3.categoria.Categoria;
import com.livro.capitulo3.categoria.CategoriaDAO;
import com.livro.capitulo3.produto.Produto;
import com.livro.capitulo3.produto.ProdutoDAO;

public class Cadastro {
	public static void main(String[] args) {
		Cadastro cadastro = new Cadastro();
		
		cadastro.cadastraProdutos();
		cadastro.cadastraCategorias();
		System.out.println("Cadastros Gerados com sucesso!");
	}
	
	public void cadastraProdutos(){
		String[] descricao = {"Bicicleta", "Televisao", "DVD"};
		double[] preco = {356.83, 19.99, 195.60};
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = null;
		
		for (int i = 0; i < 3; i++) {
			produto = new Produto();
			produto.setDescricao(descricao[i]);
			produto.setPreco(preco[i]);
			produtoDAO.salvar(produto);
		}
	}
	
	public void cadastraCategorias(){
		String[] descricao = {"Utilidades", "Geral"};
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		Categoria categoria = null;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Set<Produto> produtos = new HashSet<Produto>();
		List<Produto> produtosListagem = produtoDAO.listar();
		
		for (int i = 0; i < produtosListagem.size(); i++) {
			produtos.add(produtosListagem.get(i));
		}
		
		for (int i = 0; i < 2; i++) {
			categoria = new Categoria();
			categoria.setDescricao(descricao[i]);
			categoria.setProdutos(produtos);
			categoriaDAO.salvar(categoria);
		}
	}

}
