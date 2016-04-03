package cadastro;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.faces.flow.Flow;
import javax.faces.flow.builder.FlowBuilder;
import javax.faces.flow.builder.FlowBuilderParameter;
import javax.faces.flow.builder.FlowDefinition;

/**
 * É possivel configurar um flow de maneira programatica atraves de um metodo
 * produtor. Este metodo sera executado na inicializacao do container e
 * retornara um fluxo configurado.
 * 
 * @author Matheus
 * 
 */
public class Cadastro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4563560463434567094L;

	@Produces
	@FlowDefinition
	public Flow preparaFluxo(@FlowBuilderParameter FlowBuilder flowBuilder) {
		String flowId = "cadastro";

		// Definindo o ID do flow
		flowBuilder.id("", flowId);

		// Define um mapeamento de fluxo informando o ID e a pagina xhtml que
		// sera o ponto de entrada do mesmo
		flowBuilder.viewNode(flowId, "/" + flowId + "/" + flowId + ".xhtml").markAsStartNode();

		// Aqui é definido o ponto de saido do fluxo. O metodo getExitValue de
		// cadastroBean sera executado e o retorno sera utilizado como outcome
		flowBuilder.returnNode("exitValue").fromOutcome("#{cadastroBean.exitValue}");

		// Retorna o flow configurado
		return flowBuilder.getFlow();
	}
}
