package resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import model.Produto;

import org.jboss.resteasy.annotations.Form;

@Path("/produto")
public class ProdutoResource {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Produto recuperaProdutoPorId(@PathParam("id") int id) {
		Produto produto = new Produto();
		produto.setId(id);
		produto.setNome("Notebook");
		produto.setPreco(3000);
		return produto;
	}

	/**
	 * Com o RESTEasy e possivel utilizar a anotacao Form para receber um objeto
	 * que possui seus atributos mapeados para receber os valores do formulario
	 * em uma requisicao post.
	 * 
	 * @param produto
	 * @return
	 */
	@POST
	@Path("cadastro")
	@Produces(MediaType.TEXT_HTML)
	public String cadastraProduto(@Form Produto produto) {
		return "<h3>Produto cadastrado com sucesso!</h3>";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> recuperaProdutos(@QueryParam("preco") float preco) {
		Produto produto = new Produto();
		produto.setId(1);
		produto.setNome("Notebook");
		produto.setPreco(preco);

		return new ArrayList<>(Arrays.asList(produto, produto));
	}

	@POST
	@Path("async")
	@Produces(MediaType.APPLICATION_JSON)
	public void cadastraProduto(@QueryParam("id") int id, @QueryParam("nome") String nome, @QueryParam("preco") float preco,
			@Suspended AsyncResponse asyncResponse) {
		Produto produto = new Produto();
		produto.setId(1);
		produto.setNome("Notebook");
		produto.setPreco(preco);

		asyncResponse.resume(new ArrayList<>(Arrays.asList(produto, produto)).size());
	}

}
