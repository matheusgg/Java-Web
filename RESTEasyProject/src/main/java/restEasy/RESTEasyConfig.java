package restEasy;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import resources.ProdutoResource;

@ApplicationPath("rest")
public class RESTEasyConfig extends Application {

	/**
	 * Registros das classes que serao monitoradas pelo RESTEasy
	 */

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> managedClasses = new HashSet<>();
		managedClasses.add(ProdutoResource.class);
		return managedClasses;
	}

}
