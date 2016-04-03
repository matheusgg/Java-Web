package com.livro.capitulo3.locadora;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.livro.capitulo3.categoria.Categoria;
import com.livro.capitulo3.categoria.CategoriaDAO;
import com.livro.capitulo3.cliente.Cliente;
import com.livro.capitulo3.cliente.ClienteDAO;
import com.livro.capitulo3.endereco.Endereco;
import com.livro.capitulo3.endereco.EnderecoDAO;
import com.livro.capitulo3.filme.Filme;
import com.livro.capitulo3.filme.FilmeDAO;
import com.livro.capitulo3.locacao.Locacao;
import com.livro.capitulo3.locacao.LocacaoDAO;
import com.livro.capitulo3.media.Media;
import com.livro.capitulo3.media.MediaDAO;

public class Locadora {
	
	public static void main(String[] args) {
		Locadora locadora = new Locadora();
		locadora.cadastraCategorias();		
		locadora.cadatraFilmes();	
		locadora.cadastraMidias();
		locadora.insereDados();
		System.out.println("Cadastros gerados com sucesso!");		
	}

	public void cadastraCategorias() {
		String[] categorias = { "Aventura", "Ação", "Comédia" };
		Categoria categoria = null;
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		for (int i = 0; i < 3; i++) {
			categoria = new Categoria();
			categoria.setDescricao(categorias[i]);
			categoriaDAO.salvar(categoria);
		}
	}

	public void cadatraFilmes() {
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		String[] descricao = { "Senhor dos Anéis", "Transformes",
				"The Avengers" };
		Date[] filmesAnoProducao = { new Date(System.currentTimeMillis()),
				new Date(System.currentTimeMillis()),
				new Date(System.currentTimeMillis()) };
		FilmeDAO filmeDAO = new FilmeDAO();
		Filme filme = null;
		
		for (int i = 0; i < 3; i++) {
			filme = new Filme();
			filme.setDescricao(descricao[i]);
			filme.setAno(filmesAnoProducao[i]);
			/*
			 * Alterar conforme indice da chave no banco
			 */
			filme.setCategoria(categoriaDAO.buscarCategoria(i + 42));
			/*
			 * 
			 */
			filmeDAO.salvar(filme);
		}
	}
	
	public void cadastraMidias(){
		Media media = null;
		Filme filme = null;
		MediaDAO mediaDAO = new MediaDAO();
		FilmeDAO filmeDAO = new FilmeDAO();
		List<Filme> resultado = filmeDAO.listar();
		for (int i = 0; i < 3; i++) {
			media = new Media();
			filme = (Filme) resultado.get(i);
			media.setFilme(filme);
			media.setInutilizado("N");
			mediaDAO.salvar(media);
		}
	}
	
	public void insereDados(){		
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Endereco endereco = new Endereco();	
		
		Cliente cliente = new Cliente();
		ClienteDAO clienteDAO = new ClienteDAO();		
		
		Locacao locacao = new Locacao();
		LocacaoDAO locacaoDAO = new LocacaoDAO();
				
		cliente.setCelular("(11) 8332-1607");
		cliente.setEmail("matheuzinho.goes@gmail.com");
		cliente.setNome("Matheus Gomes Góes");
		cliente.setTelefone("(11) 5626-3435");
		cliente.setEndereco(endereco);
		
		endereco.setBairro("Jardim Miriam");
		endereco.setCep("04419-140");
		endereco.setCidade("São Paulo");
		endereco.setComplemento("Casa");
		endereco.setNumero(256);
		endereco.setRua("Tipo Pacheco");
		endereco.setUf("SP");
		endereco.setCliente(cliente);
		clienteDAO.salvar(cliente);
		enderecoDAO.salvar(endereco);
		
		locacao.setDataDevolucao(new Date(System.currentTimeMillis()));
		locacao.setDataEmprestimo(new Date(System.currentTimeMillis()));
		locacao.setObservacao("Devolução final");
		locacao.setHoraEmprestimo(new Time(System.currentTimeMillis()));
		locacao.setCliente(cliente);
					
		MediaDAO mediaDAO = new MediaDAO();
		/*
		 * Alterar conforme índice da chave no banco
		 */
		Media media = (Media) mediaDAO.buscarMedia(16);
		/*
		 * 
		 */
		locacao.setMedia(media);
		locacaoDAO.salvar(locacao);
	}

}
