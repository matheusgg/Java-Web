package br.com.rest.dynamic;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

/**
 * Com DynamicFeature é possível registrar recursos de forma dinamica
 * atraves do parametro FeatureContext passado. Um DynamicFeature é invocado
 * para cada recurso registrado no contexto do JAX RS.
 * 
 * @author Matheus
 *
 */
@Provider
public class DynamicRegistration implements DynamicFeature {

	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		System.out.println("DynamicRegistration invoked to " + resourceInfo.getClass());
	}

}
