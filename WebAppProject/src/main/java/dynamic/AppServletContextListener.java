package dynamic;

import java.util.EnumSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class AppServletContextListener implements ServletContextListener {
	
	@Resource(name = "jdbc/app_db")
	private DataSource ds;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();

		try {

			// Listener
			DynamicServletRequestListener listener = servletContext.createListener(DynamicServletRequestListener.class);
			servletContext.addListener(listener);

			// Servlet
			DynamicServlet servlet = servletContext.createServlet(DynamicServlet.class);
			ServletRegistration.Dynamic sr = servletContext.addServlet(servlet.getClass().getSimpleName(), servlet);
			sr.setInitParameter("servletInitParam", "Teste");
			sr.setLoadOnStartup(1);
			Set<String> errorMappings = sr.addMapping("/DynamicServlet");
			System.out.println(errorMappings);

			// Filter
			DynamicFilter filter = servletContext.createFilter(DynamicFilter.class);
			FilterRegistration.Dynamic fr = servletContext.addFilter(filter.getClass().getSimpleName(), filter);
			fr.setInitParameter("filterInitParam", "Teste");
			fr.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, sr.getMappings().toArray(new String[0]));

		} catch (Exception e) {
			servletContext.log(e.getMessage(), e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
