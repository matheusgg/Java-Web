package br.com.cdi.observer;

import java.util.logging.Logger;

import javax.enterprise.event.Observes;
import javax.servlet.http.HttpServletRequest;

public class RequestObserver {

	private static final Logger LOG = Logger.getLogger(RequestObserver.class.getSimpleName());

	/**
	 * O padrao Observer também ja esta implementado no CDI. Basta criar um
	 * metodo (simples ou estatico) que recebera os eventos ocorridos no objeto
	 * que este metodo recebe como parametro e esta anotado com @Observes.
	 * 
	 * @param request
	 */
	public static void registerAttr(@Observes HttpServletRequest request) {
		RequestObserver.LOG.info("Evento disparado sobre " + request.getClass().getSimpleName());
	}

}
