package beans;

import java.util.concurrent.ExecutionException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Produto;

@ManagedBean
@ViewScoped
public class RestBean {

	public void testRestfulServices() throws InterruptedException, ExecutionException {
		Client client = ClientBuilder.newClient();

		// POST - Chamada Assíncrona
		// WebTarget target =
		// client.target("http://localhost:8081/RESTEasyProject/rest/produto/async");
		// target = target.queryParam("id", 1);
		// target = target.queryParam("preco", 3000f);
		// target = target.queryParam("nome", "TV");
		// Future<Response> responseFuture =
		// target.request().async().post(Entity.entity("Cadastro",
		// MediaType.TEXT_PLAIN));
		// Response response = responseFuture.get();
		// System.out.println(response.readEntity(String.class));

		// GET
		WebTarget target = client.target("http://localhost:8080/RestfulProject/rest/produto/{id}");
		target = target.resolveTemplate("id", 1);
		Response response = target.request(MediaType.APPLICATION_XML).get();
		Produto produto = response.readEntity(Produto.class);
		System.out.println(produto.getId());

		// GET
		target = client.target("http://localhost:8080/RestfulProject/rest/produto/1/listaSemelhantes");
		/*
		 * Registra um MessageBodyWriter ou MessageBodyReader para parsear a
		 * solicitacao ou a requisicao
		 */
		// target.register(new CustomMessageBodyReader());
		response = target.request(MediaType.APPLICATION_JSON).get();
		String produtos = response.readEntity(String.class);
		System.out.println(produtos);

		// PUT
		target = client.target("http://localhost:8080/RestfulProject/rest/produto/1/atualizaProduto");
		response = target.queryParam("preco", 5000).request().put(Entity.entity("", MediaType.TEXT_PLAIN));
		System.out.println(response.readEntity(String.class));
	}

}
