package nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NashornApp {
	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("nashorn");

		Cliente cliente = new Cliente();
		cliente.setId(10);

		engine.put("cliente", cliente);

		StringBuilder builder = new StringBuilder();
		builder.append("var ArrayList = java.util.ArrayList;");
		builder.append("var lista = new ArrayList();");
		builder.append("lista.add(1);");
		builder.append("lista.add(2);");
		builder.append("lista.add(cliente);");
		builder.append("print(lista);");

		engine.eval(builder.toString());
	}
}

class Cliente {

	private Integer id;
	private String nome;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

}
