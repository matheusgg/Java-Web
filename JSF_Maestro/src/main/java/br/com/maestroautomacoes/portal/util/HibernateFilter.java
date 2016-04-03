package br.com.maestroautomacoes.portal.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.com.maestroautomacoes.portal.logger.LogManager;
import br.com.maestroautomacoes.portal.model.dao.util.HibernateUtil;

@WebFilter("/")
public class HibernateFilter implements Filter {

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		try {
			HibernateUtil.getSessionfactory();
		} catch (Exception e) {
			String mensagem = LogManager.makeStackTraceLog(e);
			LogManager.getLogger().error(mensagem);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
