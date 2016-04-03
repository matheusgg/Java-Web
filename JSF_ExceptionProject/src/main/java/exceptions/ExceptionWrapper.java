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
 * Esta classe é responsável por tratar a exceção. Ela é a implementação da
 * manipuladora de exceções. O Método handle é invocado ao final de cada fase do
 * cliclo de vida de uma requisição do JSF.
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
	 * É ncessário sobrescrever este método, pois ele é invocado automaticamente
	 * pela FacesServlet para obter uma instância de ExceptionHandler.
	 */
	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	/**
	 * Este método tratará as exceções que podem ocorrer durante cada fase do
	 * ciclo de vida da requisição. Ele é invocado no final de cada fase do
	 * ciclo do JSF.
	 */
	@Override
	public void handle() throws FacesException {
		// Aqui são recuperados todos os eventos de exceçõs que não foram
		// tratadas
		Iterator<ExceptionQueuedEvent> events = super.getUnhandledExceptionQueuedEvents().iterator();

		// Aqui as exceções são percorridas e tratadas
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
