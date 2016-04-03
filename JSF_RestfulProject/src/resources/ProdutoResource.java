package resources;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.Produto;

/**
 * Webservices Restful sao servicos que seguem as normas e restricoes da
 * arquitetura REST. REST é um estilo arquitetural de servicos web que se baseia
 * em cliente-servidor, onde o cliente envia uma solicitacao e o servidor
 * devolve uma resposta. O modelo REST preve algumas restricoes que padronizam o
 * desenvolvimento de servicoes restful, tudo que é disponibilizado é tratado
 * como recurso. Todo recurso precisa possuir um identificador. Não há
 * manutencao de estado, ou seja, os recursos sao stateless. A especificacao
 * JAX-RS possui varias implementacoes, entre elas estao o RESTEasy, Apache CXF,
 * Axis, Axis2 e a implementacao de referencia Jersey. De acordo com a
 * especificacao, é necessário criar um RootResource, que nada mais é do que uma
 * classe que possuirá métodos para manipulacao dos recursos disponibilizados.
 * Esses metodos sao os metodos padronizados HTTP: GET, POST, PUT, DELETE, HEAD,
 * OPTIO, etc.
 * 
 * @author Matheus
 * 
 */
/*
 * Indica a identificacao do recurso, onde o ID será informado na URL de
 * requisicao.
 */
@Path("produto/{id}")
public class ProdutoResource {

	/**
	 * Quando a requisicao for do tipo get, este metodo sera chamado e o ID
	 * informado na URL sera passado como parametro. A anotacao @Produces indica
	 * em qual formato esse metodo gerara uma resposta. É possível retornar mais
	 * de um tipo de resposta, basta especificar todos os MIME Types de resposta
	 * desejados. Por padrao o formato XML tem prioridade, entao para o cliente
	 * receber uma resposta em formato JSON basta adicionar um parametro no
	 * cabecalho chamado Accept => Accept: application/json
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Produto recuperaProdutoPorId(@PathParam("id") Integer id) {
		Produto produto = new Produto();
		produto.setId(id);
		produto.setNome("Notebook");
		produto.setPreco(2500);
		return produto;
	}

	@GET
	@Path("listaSemelhantes")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ArrayList<Produto> recuperaProdutos(@PathParam("id") Integer id) {
		Produto produto = new Produto();
		produto.setId(id);
		produto.setNome("Notebook");
		produto.setPreco(2500);
		return new ArrayList<Produto>(Arrays.asList(produto, produto, produto));
	}

	/**
	 * Consumes indica qual o formato de dado esse metodo esta esperando.
	 * QueryParam indica os parametros que serao passados pela url. Ex:
	 * produto?id=...&preco=... É possivel utilizar a anotacao Path nos metodos,
	 * desta forma, a URI do recurso será concatenada caso ja exista uma
	 * anotacao Path na classe, ficando da seguinte forma:
	 * http://applicacao/rest/produto/{id}/atualizaProduto?preco=... PathParam
	 * serve para recuperar um parametro declarado na URI, por exemplo, {id},=.
	 * Já QueryParam serve para recuperar um parametro passado na URL, exemplo,
	 * preco=3000.
	 * 
	 * @param id
	 * @param preco
	 */
	@PUT
	@Path("atualizaProduto")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_HTML)
	public String atualizaPrecoProduto(@PathParam("id") Integer id, @QueryParam("preco") float preco) {
		return "<h3>Produto atualizado com sucesso!</h3>";
	}
}
