package dynamic;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DynamicFilter implements Filter {

	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Enumeration<String> initParameterNames = this.filterConfig.getInitParameterNames();
		while (initParameterNames.hasMoreElements()) {
			String parameterName = initParameterNames.nextElement();
			System.out.println(parameterName + ": " + this.filterConfig.getInitParameter(parameterName));
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
