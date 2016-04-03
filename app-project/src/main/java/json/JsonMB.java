package json;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import org.omnifaces.util.Messages;

@Named
@ViewScoped
public class JsonMB implements Serializable {

	private static final long serialVersionUID = 5904413961654247497L;

	/**
	 * API de baixo nível de manipulacao de streams de entrada e saída de
	 * documentos JSON.
	 */
	public void testJson() {
		StringWriter writer = new StringWriter();

		// Criando um documento JSON
		JsonGenerator generator = Json.createGenerator(writer);
		generator.writeStartObject().write("nome", "João").writeStartObject("Endereco").write("Tipo", 1).write("Rua", "Rua A").write("Cidade", "Campinas")
				.writeEnd().writeStartArray("Telefone").writeStartObject().write("Numero", "123-456").write("DDD", "19").writeEnd().writeStartObject()
				.write("Numero", "333-555").write("DDD", "36").writeEnd().writeEnd().writeEnd().close();

		Messages.add(null, new FacesMessage(writer.toString()));

		// Lendo um documento JSON
		JsonParser parser = Json.createParser(new StringReader(writer.toString()));
		while (parser.hasNext()) {
			Event event = parser.next();

			switch (event) {
				case KEY_NAME: {
					System.out.print(parser.getString() + "=");
					break;
				}

				case VALUE_STRING: {
					System.out.println(parser.getString());
					break;
				}

				case VALUE_NUMBER: {
					System.out.println(parser.getString());
					break;
				}

				case VALUE_NULL: {
					System.out.println("null");
					break;
				}

				case START_ARRAY: {
					System.out.println("Inicio do Array de Telefone");
					break;
				}

				case END_ARRAY: {
					System.out.println("Final do Array de Telefone");
					break;
				}

				case END_OBJECT: {
					System.out.println("Final do Objeto Json");
					break;
				}

				default: {
					System.out.println("Start Object, Value False or value True");
				}
			}
		}
	}

	/**
	 * API de auto nível para manipulação de objetos
	 */
	public void testObjectJson() {
		JsonObject jsonObject = Json.createObjectBuilder().add("nome", "Matheus")
				.add("telefones", Json.createArrayBuilder().add(Json.createObjectBuilder().add("numero1", "123").add("numero2", "456"))).build();

		System.out.println(jsonObject);
	}
}
