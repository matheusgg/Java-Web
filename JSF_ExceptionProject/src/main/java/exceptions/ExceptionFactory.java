package exceptions;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * Esta classe � respons�vel por fabricar os manipuladores de exce��es. Ela �
 * instanciada no deploy da aplica��o e tem o m�todo sobrescrito
 * getExceptionHandler() chamado toda vez que o ciclo de vida do JSF se inicia.
 * Sua �nica fun��o � retornar uma implementa��o do manipulador de exce��es.
 * 
 * @author Matheus
 * 
 */
public class ExceptionFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory parent;

	/**
	 * Quando esta classe � instanciada, a FacesServlet passa um objeto do tipo
	 * ExceptionHandlerFactory como argumento. Com esta inst�ncia � poss�vel
	 * completar o m�todo getExceptionHandler().
	 * 
	 * @param parent
	 */
	public ExceptionFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	/**
	 * Retorna uma implementa��o de ExceptionHandler. Este m�todo � invocado
	 * sempre no in�cio do ciclo de vida de uma requisi��o.
	 */
	@Override
	public ExceptionHandler getExceptionHandler() {
		/*
		 * � necess�rio passar uma implementa��o de ExceptionHandler para a
		 * classe que ir� manipular as exce��es. Esta implementa��o � obtida
		 * atrav�s do m�todo getExceptionHandler() do objeto
		 * ExceptionHandlerFactory passado pela FacesServlet.
		 */
		return new ExceptionWrapper(this.parent.getExceptionHandler());
	}

}
