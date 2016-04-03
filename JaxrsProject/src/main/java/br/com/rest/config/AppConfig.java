package br.com.rest.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class AppConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		// classes.add(ClienteResource.class);
		// classes.add(ProdutosResource.class);
		//
		// classes.add(CustomFilter.class);
		// classes.add(ProdutosFilter.class);
		//
		// classes.add(DynamicRegistration.class);

		return classes;
	}

}
