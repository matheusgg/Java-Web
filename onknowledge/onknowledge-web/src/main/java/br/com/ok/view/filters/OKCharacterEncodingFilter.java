package br.com.ok.view.filters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.com.ok.util.constants.OKConstants;

/**
 * The Class OKCharacterEncodingFilter.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@WebFilter(value = "/*", asyncSupported = true, dispatcherTypes = { DispatcherType.ASYNC, DispatcherType.ERROR, DispatcherType.FORWARD, DispatcherType.INCLUDE,
		DispatcherType.REQUEST })
public class OKCharacterEncodingFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(OKCharacterEncodingFilter.class.getName());

	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		OKCharacterEncodingFilter.LOGGER.log(Level.INFO, OKCharacterEncodingFilter.class.getName() + " initializing...");
	}

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(OKConstants.UTF_8_ENCODING);
		chain.doFilter(request, response);
		response.setCharacterEncoding(OKConstants.UTF_8_ENCODING);
	}

	/**
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		OKCharacterEncodingFilter.LOGGER.log(Level.INFO, OKCharacterEncodingFilter.class.getName() + " shutting down...");
	}

}
