package exceptions;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.primefaces.context.RequestContext;

/**
 * Esta classe � respons�vel por tratar a exce��o. Ela � a implementa��o da
 * manipuladora de exce��es. O M�todo handle � invocado ao final de cada fase do
 * cliclo de vida de uma requisi��o do JSF.
 * 
 * @author Matheus
 * 
 */
public class ExceptionWrapper extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;

	public ExceptionWrapper(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	/**
	 * � ncess�rio sobrescrever este m�todo, pois ele � invocado automaticamente
	 * pela FacesServlet para obter uma inst�ncia de ExceptionHandler.
	 */
	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	/**
	 * Este m�todo tratar� as exce��es que podem ocorrer durante cada fase do
	 * ciclo de vida da requisi��o. Ele � invocado no final de cada fase do
	 * ciclo do JSF.
	 */
	@Override
	public void handle() throws FacesException {
		// Aqui s�o recuperados todos os eventos de exce��s que n�o foram
		// tratadas
		Iterator<ExceptionQueuedEvent> events = super.getUnhandledExceptionQueuedEvents().iterator();

		// Aqui as exce��es s�o percorridas e tratadas
		while (events.hasNext()) {
			ExceptionQueuedEvent event = events.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
			Throwable throwable = context.getException();
			if (throwable.getMessage().contains("SQLException")) {
				context.getContext().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um erro ao cadastra o cliente. Por favor, tente novamente.", ""));
				context.getContext().renderResponse();
			}

			if (throwable instanceof ViewExpiredException) {
				RequestContext.getCurrentInstance().execute("modalSessao.show()");
				context.getContext().renderResponse();
			}
		}
	}

}
