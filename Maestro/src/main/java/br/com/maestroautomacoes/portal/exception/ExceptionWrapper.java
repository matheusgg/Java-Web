package br.com.maestroautomacoes.portal.exception;

import java.util.Iterator;

import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.mail.internet.AddressException;

import br.com.maestroautomacoes.portal.logger.LogManager;
import br.com.maestroautomacoes.portal.util.Outcome;

public class ExceptionWrapper extends ExceptionHandlerWrapper {
	private ExceptionHandler wrapped;
	private NavigationHandler navigationHandler;

	public ExceptionWrapper(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public void handle() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		this.navigationHandler = ctx.getApplication().getNavigationHandler();
		try {
			Iterator<ExceptionQueuedEvent> events = super.getUnhandledExceptionQueuedEvents().iterator();
			while (events.hasNext()) {
				ExceptionQueuedEventContext context = events.next().getContext();
				Exception exception = this.getCause(context.getException());
				if (!(exception instanceof ViewExpiredException)) {
					LogManager.getLogger().error(LogManager.makeStackTraceLog(exception));
					if (exception instanceof AddressException) {
						ctx.renderResponse();
					} else {
						this.navigationHandler.handleNavigation(ctx, null, Outcome.ERRO);
					}
				}
			}
		} catch (Exception e) {
			LogManager.getLogger().error(LogManager.makeStackTraceLog(e));
			this.navigationHandler.handleNavigation(ctx, null, Outcome.ERRO);
			ctx.renderResponse();
		}
	}

	/**
	 * Recupera a verdadeira exceção lançada em alguma fase do ciclo de vida do
	 * JSF.
	 * 
	 * @param throwable
	 * @return
	 * @throws Exception
	 */
	private Exception getCause(Throwable throwable) {
		Exception exception = null;
		Exception ex = (Exception) throwable.getCause();
		if (ex == null) {
			exception = (Exception) throwable;
		} else {
			exception = this.getCause(ex);
		}
		return exception;
	}

}
