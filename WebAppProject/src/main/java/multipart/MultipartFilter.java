package multipart;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(servletNames = "*", asyncSupported = true)
public class MultipartFilter implements Filter {

	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		this.filterConfig.getServletContext().log("Verificando se é uma requisicao multipart...");
		Boolean requisicaoMultipart = req.getContentType() != null && req.getContentType().contains("multipart/form-data") ? true : false;
		this.filterConfig.getServletContext().log(requisicaoMultipart.toString());

		if (requisicaoMultipart) {
			String servletName = req.getServletPath().replace("/", "");
			ServletRegistration.Dynamic servletRegistration = (Dynamic) this.filterConfig.getServletContext().getServletRegistration(servletName);

			if (servletRegistration != null) {
				this.filterConfig.getServletContext().log("Adicionando suporte multipart a servlet requisitada...");
				File tempDir = (File) this.filterConfig.getServletContext().getAttribute(ServletContext.TEMPDIR);

				MultipartConfigElement multipartConfigElement = new MultipartConfigElement(tempDir.getAbsolutePath());
				servletRegistration.setMultipartConfig(multipartConfigElement);
			}

			this.filterConfig.getServletContext().log("Servlet configurada, processando requisição...");
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
