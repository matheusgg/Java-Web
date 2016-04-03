package exceptions;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * Esta classe é responsável por fabricar os manipuladores de exceções. Ela é
 * instanciada no deploy da aplicação e tem o método sobrescrito
 * getExceptionHandler() chamado toda vez que o ciclo de vida do JSF se inicia.
 * Sua única função é retornar uma implementação do manipulador de exceções.
 * 
 * @author Matheus
 * 
 */
public class ExceptionFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory parent;

	/**
	 * Quando esta classe é instanciada, a FacesServlet passa um objeto do tipo
	 * ExceptionHandlerFactory como argumento. Com esta instância é possível
	 * completar o método getExceptionHandler().
	 * 
	 * @param parent
	 */
	public ExceptionFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	/**
	 * Retorna uma implementação de ExceptionHandler. Este método é invocado
	 * sempre no início do ciclo de vida de uma requisição.
	 */
	@Override
	public ExceptionHandler getExceptionHandler() {
		/*
		 * É necessário passar uma implementação de ExceptionHandler para a
		 * classe que irá manipular as exceções. Esta implementação é obtida
		 * através do método getExceptionHandler() do objeto
		 * ExceptionHandlerFactory passado pela FacesServlet.
		 */
		return new ExceptionWrapper(this.parent.getExceptionHandler());
	}

}
