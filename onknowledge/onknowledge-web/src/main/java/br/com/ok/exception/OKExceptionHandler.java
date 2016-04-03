package br.com.ok.exception;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewExpiredException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.faces.webapp.FacesServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.exception.base.OKException;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.messages.OKMessages;

import com.ocpsoft.pretty.PrettyContext;
import com.ocpsoft.pretty.faces.application.PrettyRedirector;

/**
 * The Class OKExceptionHandler.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
public class OKExceptionHandler extends ExceptionHandlerWrapper {

	/** The wrapped. */
	private ExceptionHandler wrapped;

	/** The logger. */
	private Logger logger;

	/**
	 * Instantiates a new OK exception wrapper.
	 *
	 * @param wrapped
	 *            the wrapped
	 */
	public OKExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
		this.logger = Logger.getLogger(this.getClass().getName());
	}

	/**
	 * Handle.
	 *
	 * @throws FacesException
	 *             the faces exception
	 * @see javax.faces.context.ExceptionHandlerWrapper#handle()
	 */
	@Override
	public void handle() throws FacesException {
		Iterable<ExceptionQueuedEvent> unhandledExceptions = super.getUnhandledExceptionQueuedEvents();
		PrettyContext prettyContext = PrettyContext.getCurrentInstance();

		for (ExceptionQueuedEvent event : unhandledExceptions) {
			ExceptionQueuedEventContext context = event.getContext();
			Throwable throwable = this.getRootCause(context.getException());
			this.logger.log(Level.SEVERE, throwable.getMessage(), throwable);

			if (throwable instanceof OKBusinessException) {
				this.handleBusinessException((OKBusinessException) throwable);
				break;

			} else if (throwable instanceof OKException) {
				OKMessages.showSmartConfirmationDialog(OKMessages.getMessage("exception.titulo"), this.getExceptionMessage(throwable),
						Faces.getInitParameter("exception.default.icon"), this.prepareRedirectCall(prettyContext), null, OKMessages.getMessage("label.ok"), null);
				break;

			} else if (throwable instanceof ViewExpiredException) {
				PrettyRedirector.getInstance().redirect(Faces.getContext(), OKConstants.PRETTY_PREFIX + prettyContext.getCurrentMapping().getId());
				break;

			} else {
				if (!Faces.isAjaxRequest()) {
					this.prepareErrorPageAttrs(throwable);
					PrettyRedirector.getInstance().redirect(Faces.getContext(), OKConstants.PRETTY_ERROR_PAGE);
				} else {
					super.handle();
				}
				break;
			}
		}
	}

	/**
	 * Handle business exception.
	 *
	 * @param businessException
	 *            the business exception
	 */
	private void handleBusinessException(OKBusinessException businessException) {
		String exceptionMessage = this.getExceptionMessage(businessException);
		String clientId = businessException.getClientId();
		if (clientId != null) {
			this.addClientErrorMessages(Arrays.asList(clientId.split(OKConstants.VIRGULA)), exceptionMessage);
		} else {
			Messages.add(FacesMessage.SEVERITY_WARN, null, exceptionMessage);
		}
		RequestContext.getCurrentInstance().execute("animateToTop()");
	}

	/**
	 * Gets the exception message.
	 *
	 * @param throwable
	 *            the throwable
	 * @return the exception message
	 */
	private String getExceptionMessage(Throwable throwable) {
		String exceptionMessage = throwable.getMessage();
		if (exceptionMessage != null && exceptionMessage.startsWith(OKConstants.CHAVES_INICIAIS) && exceptionMessage.endsWith(OKConstants.CHAVES_FINAIS)) {
			exceptionMessage = OKMessages.getMessage(exceptionMessage.replaceAll(OKConstants.REGEX_CHAVES, OKConstants.STRING_VAZIA));
		} else {
			exceptionMessage = OKMessages.getMessage("msg.erro.generico");
		}
		return exceptionMessage;
	}

	/**
	 * Adds the client error messages.
	 *
	 * @param clientIds
	 *            the client ids
	 * @param exceptionMessage
	 *            the message
	 */
	private void addClientErrorMessages(List<String> clientIds, String exceptionMessage) {
		UIViewRoot viewRoot = Faces.getViewRoot();
		for (String id : clientIds) {
			Messages.add(FacesMessage.SEVERITY_ERROR, this.decomposeClientId(id), exceptionMessage);
			UIComponent component = viewRoot.findComponent(id);
			if (component instanceof UIInput) {
				((UIInput) component).setValid(false);
			}
		}
	}

	/**
	 * Decompose client id.
	 *
	 * @param clientId
	 *            the client id
	 * @return the string
	 */
	private String decomposeClientId(String clientId) {
		String[] id = clientId.split(OKConstants.DOIS_PONTOS);
		return id[id.length - 1];
	}

	/**
	 * Prepare error page attrs.
	 *
	 * @param exception
	 *            the exception
	 */
	private void prepareErrorPageAttrs(Throwable exception) {
		Faces.getFlash().put(RequestDispatcher.ERROR_EXCEPTION, exception);
		Faces.getFlash().put(RequestDispatcher.ERROR_EXCEPTION_TYPE, exception.getClass());
		Faces.getFlash().put(RequestDispatcher.ERROR_MESSAGE, exception.getMessage());
		Faces.getFlash().put(RequestDispatcher.ERROR_REQUEST_URI, Faces.getRequestURI());
		Faces.getFlash().put(RequestDispatcher.ERROR_SERVLET_NAME, FacesServlet.class.getName());
		Faces.getFlash().put(RequestDispatcher.ERROR_STATUS_CODE, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		Faces.getFlash().put(OKConstants.STACK_TRACE_KEY, this.prepareStackTraceMessage(exception));
	}

	/**
	 * Prepare redirect call.
	 *
	 * @param prettyContext
	 *            the pretty context
	 * @return the string builder
	 */
	private String prepareRedirectCall(PrettyContext prettyContext) {
		StringBuilder redirectCall = new StringBuilder("redirect");
		redirectCall.append(OKConstants.PARENTESES_INICIAL);
		redirectCall.append(OKConstants.APOSTROFO);
		redirectCall.append(prettyContext.getContextPath());
		redirectCall.append(prettyContext.getCurrentMapping().getPattern());
		redirectCall.append(OKConstants.APOSTROFO);
		redirectCall.append(OKConstants.PARENTESES_FINAL);
		return redirectCall.toString();
	}

	/**
	 * Recupera a mensagem completa contendo a descrição da exceção lançada.
	 *
	 * @param exception
	 *            the exception
	 * @return the string
	 */
	private String prepareStackTraceMessage(Throwable exception) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(exception.getClass().getName());
		stringBuilder.append(OKConstants.DOIS_PONTOS);
		stringBuilder.append(OKConstants.WHITE_SPACE);
		stringBuilder.append(exception.getMessage());
		stringBuilder.append(OKConstants.SEPARADOR_LINHA);
		for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
			stringBuilder.append(stackTraceElement);
			stringBuilder.append(OKConstants.SEPARADOR_LINHA);
		}
		return stringBuilder.toString();
	}

	/**
	 * Recupera a verdadeira exceção lançada durante o processamento.
	 *
	 * @param throwable
	 *            the throwable
	 * @return the root cause
	 */
	@Override
	public Throwable getRootCause(Throwable throwable) {
		Exception cause = (Exception) throwable.getCause();
		if (throwable instanceof OKBusinessException || cause == null) {
			return throwable;
		} else {
			return this.getRootCause(cause);
		}
	}

	/**
	 * Gets the wrapped.
	 *
	 * @return the wrapped
	 * @see javax.faces.context.ExceptionHandlerWrapper#getWrapped()
	 */
	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

}
