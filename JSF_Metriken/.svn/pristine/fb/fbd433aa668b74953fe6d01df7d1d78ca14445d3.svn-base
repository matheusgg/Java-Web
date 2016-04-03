package br.com.metriken.util.exception;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import br.com.metriken.util.FacesUtils;

public class ApplicationExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;

	public ApplicationExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	/**
	 * Faz o tratamento de erros e exceções que possam ser lançadas durante o
	 * processamento.
	 */
	@Override
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> events = super.getUnhandledExceptionQueuedEvents().iterator();

		while (events.hasNext()) {
			ExceptionQueuedEventContext eventContext = events.next().getContext();
			Exception exception = this.findException(eventContext.getException());
			if (exception instanceof MtrkException) {
				this.handleMtrkException(exception);
			} else if (exception instanceof ViewExpiredException) {
				FacesUtils.redirect("pretty:home");
			} else {
				String msg = this.makeStackTraceMessage(exception);
				FacesUtils.addAttributeToSession("errorMessage", msg);
				FacesUtils.redirect("pretty:erro");
			}
			FacesContext.getCurrentInstance().renderResponse();
		}
	}

	/**
	 * Trata a exceção de regra de negócio e exibe a mensagem da exceção para o
	 * usuário.
	 * 
	 * @param exception
	 */
	private void handleMtrkException(Exception exception) {
		String msg = exception.getMessage();
		if (msg.startsWith("{") && msg.endsWith("}")) {
			msg = msg.replaceAll("[{}]", "");
			FacesUtils.addErrorModalMessage("lbl_erro", true, msg, true);
		} else {
			FacesUtils.addErrorModalMessage("lbl_erro", true, msg, false);
		}
	}

	/**
	 * Recupera a mensagem completa contendo a descrição da exceção lançada.
	 * 
	 * @param Exception
	 * @return
	 */
	private String makeStackTraceMessage(Exception exception) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(exception.getClass().getName());
		stringBuilder.append(exception.getMessage() + "\n");
		for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
			stringBuilder.append(stackTraceElement.toString() + "\n");
		}
		return stringBuilder.toString();
	}

	/**
	 * Recupera a verdadeira exceção lançada durante o processamento.
	 * 
	 * @param throwable
	 * @return
	 */
	private Exception findException(Throwable throwable) {
		Exception excecao = (Exception) throwable;
		Exception cause = (Exception) throwable.getCause();
		if (cause == null) {
			return excecao;
		} else {
			return this.findException(cause);
		}
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

}
