package app.jpa.util;

import java.util.ArrayList;
import java.util.List;

import app.jpa.model.Categoria;
import app.jpa.model.Cliente;
import app.jpa.model.Produto;
import app.jpa.model.dao.CategoriaDAO;
import app.jpa.model.dao.ClienteDAO;
import app.jpa.model.dao.ProdutoDAO;

public class Principal {
	private static ClienteDAO clienteDAO;
	private static CategoriaDAO categoriaDAO;
	private static ProdutoDAO produtoDAO;

	static {
		clienteDAO = new ClienteDAO();
		categoriaDAO = new CategoriaDAO();
		produtoDAO = new ProdutoDAO();
	}

	public static void main(String[] args) {
		cadastraCliente();
		cadastraCategoria();
		buscaCliente();
		buscaCategoria();
		listaProdutos();
		EntityManagerProvider.getEntitymanagerfactory().createEntityManager()
				.close();
	}

	public static void cadastraCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Matheus Gomes");
		cliente.setEndereco("Endereço");
		cliente.setTelefone("56478374");
		clienteDAO.salva(cliente);
	}

	public static void cadastraCategoria() {
		Categoria categoria = new Categoria();
		categoria.setNome("Categoria 1");
		categoriaDAO.salva(categoria);
		List<Produto> listaProdutos = new ArrayList<Produto>();
		for (int i = 0; i < 5; i++) {
			Produto prod = new Produto();
			prod.setNome("Produto " + i);
			prod.setQuantidade(i * 10);
			prod.setCategoria(categoria);
			produtoDAO.salva(prod);
			listaProdutos.add(prod);
		}

		/*
		 * Atualizar Categoria
		 */
		categoria.setListaProdutos(listaProdutos);
		categoriaDAO.atualiza(categoria);
	}

	public static void buscaCliente() {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(1);
		cliente = clienteDAO.busca(cliente);
		System.out.println("============================================");
		System.out.println(cliente.getNome());
		System.out.println(cliente.getEndereco());
		System.out.println(cliente.getTelefone());
	}

	public static void buscaCategoria() {
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(5);
		categoria = categoriaDAO.buscar(categoria);
		System.out.println("============================================");
		System.out.println(categoria.getNome());
		for (int i = 0; i < categoria.getListaProdutos().size(); i++) {
			System.out.println(categoria.getListaProdutos().get(i).getNome());
			System.out.println(categoria.getListaProdutos().get(i)
					.getQuantidade());
		}
	}

	public static void listaProdutos() {
		List<Produto> listaProdutos = produtoDAO.listaProdutos();
		for (Produto produto : listaProdutos) {
			System.out.println(produto.getNome());
			System.out.println(produto.getQuantidade());
		}
	}

}
