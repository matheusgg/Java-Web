package resourcehandler;

import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.application.ResourceHandlerWrapper;
import javax.faces.application.ViewResource;
import javax.faces.context.FacesContext;

/**
 * ResourceHandler responsavel por resolver os recursos utilizados nas paginas.
 * Estes recursos podem ser tanto arquivos xhtml quanto csss ou javascript.
 * 
 * @author Matheus
 * 
 */
public class CustomResourceHandler extends ResourceHandlerWrapper {

	private ResourceHandler parent;

	/**
	 * Construtor invocado pela FacesServlet na inicializacao do container.
	 * 
	 * @param parent
	 */
	public CustomResourceHandler(ResourceHandler parent) {
		this.parent = parent;
	}

	/**
	 * Metodo invocado para resolver os arquivos xhtml.
	 */
	@Override
	public ViewResource createViewResource(FacesContext context, String resourceName) {
		return this.parent.createViewResource(context, resourceName);
	}

	/**
	 * Metodo invocado para resolver os recursos css ou js utilizados nas
	 * paginas.
	 */
	@Override
	public Resource createResource(String resourceName, String libraryName) {
		Resource resource = null;
		if (libraryName == null) {
			resource = new CustomResource(this.createResource(resourceName), resourceName);
		} else if (libraryName.equals("css") || libraryName.equals("js")) {
			resource = new CustomResource(this.createResource(resourceName), resourceName, libraryName);
		} else {
			resource = super.createResource(resourceName, libraryName);
		}
		return resource;
	}

	/**
	 * Metodo invocado a cada requisicao para devolver o manipulador de
	 * recursos.
	 */
	@Override
	public ResourceHandler getWrapped() {
		return this.parent;
	}

}
