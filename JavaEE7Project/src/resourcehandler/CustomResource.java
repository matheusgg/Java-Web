package resourcehandler;

import java.io.File;

import javax.faces.application.Resource;
import javax.faces.application.ResourceWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Implementacao customizada de um Resource. Esta classe encapsula o caminho
 * customizado dos recursos css e js, que devem estar dentro da pasta res.
 * 
 * @author Matheus
 * 
 */
public final class CustomResource extends ResourceWrapper {

	private Resource resource;
	private String path;

	public CustomResource(Resource resource) {
		this.resource = resource;
		this.path = this.findResourceFolderPath();
	}

	public CustomResource(Resource resource, String resourceName) {
		this(resource);
		this.handlePathWithoutLibrary(resourceName);
	}

	public CustomResource(Resource resource, String resourceName, String libraryName) {
		this(resource);
		this.handlePathWithLibrary(resourceName, libraryName);
	}

	/**
	 * Cria o caminho do recurso considerando apenas o nome.
	 * 
	 * @param resourceName
	 */
	private void handlePathWithoutLibrary(String resourceName) {
		this.path += resourceName;
	}

	/**
	 * Cria o caminho do recurso considerando o nome e a biblioteca.
	 * 
	 * @param resourceName
	 * @param libraryName
	 */
	private void handlePathWithLibrary(String resourceName, String libraryName) {
		this.path += libraryName + File.separator + resourceName;
	}

	/**
	 * Recupera o caminho padrao dos recursos informado pelo usuario como
	 * parametro de inicializacao na variavel "RESOURCE_FOLDER". Caso o
	 * parametro nao seja informado, o caminho padrao "res" sera utilizado.
	 * 
	 * @return
	 */
	private String findResourceFolderPath() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		String path = context.getApplicationContextPath() + File.separator;
		String initFolder = context.getInitParameter("RESOURCE_FOLDER");

		if (initFolder == null || initFolder.trim().length() == 0) {
			initFolder = "res";
		}

		path += initFolder + File.separator;
		return path;
	}

	@Override
	public Resource getWrapped() {
		return this.resource;
	}

	@Override
	public String getContentType() {
		return this.getWrapped().getContentType();
	}

	@Override
	public String getRequestPath() {
		return this.path;
	}
}
