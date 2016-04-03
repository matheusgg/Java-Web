package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter2 implements Filter {

	public MyFilter2() {
		System.out.println("Instancia de MyFilter2 criada!");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		filterConfig.getServletContext().log("Init de MyFilter2");
		System.out.println(filterConfig.getInitParameter("myFilter2Param"));
	}

	/**
	 * Quando o fluxo volta para o filtro, depois da chamado ao metodo doFilter,
	 * diferentemente do metodo forward de RequestDispatcher, a resposta ainda
	 * nao esta commitada, logo é possivel alterar o response para enviar algo
	 * mais para o cliente.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setAttribute("msg", "O metodo doFilter de MyFilter2 foi executado e o fluxo foi passado para MyServlet2");

		chain.doFilter(request, response);

		response.getWriter().write("\nFim da execucao de MyFilter2, a resposta sera enviada para o cliente...");
	}

	@Override
	public void destroy() {
		System.out.println("Destroy de MyFilter2");
	}

}
