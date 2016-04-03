/*
 * 
 */
package br.com.ok.view.base;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import lombok.AccessLevel;
import lombok.Getter;

import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;

import br.com.ok.exception.base.OKBusinessException;
import br.com.ok.model.Anexo;
import br.com.ok.security.OKSecurityContext;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.files.OKFileHandler;
import br.com.ok.util.logging.OKLogManager;

import com.ocpsoft.pretty.PrettyContext;
import com.ocpsoft.pretty.faces.application.PrettyRedirector;

/**
 * The Class OKBaseMB.
 *
 * @author Matheus
 * @version 1.0 - 21/09/2014
 */
@Getter(AccessLevel.PROTECTED)
public class OKBaseMB implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2409814660628941501L;

	/** The security context. */
	@Inject
	private OKSecurityContext securityContext;

	/** The log manager. */
	@Inject
	private OKLogManager logManager;

	/** The caller page. */
	@Getter(AccessLevel.PUBLIC)
	private String callerPage;

	/** The caller object. */
	@Getter(AccessLevel.PUBLIC)
	private Object callerObject;

	/** The selection mode. */
	@Getter(AccessLevel.PUBLIC)
	private String selectionMode;

	/** The source bean. */
	private String sourceBean;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void initPage() {
		String targetBean = Faces.getFlashAttribute(OKConstants.TARGET_BEAN);
		this.sourceBean = Faces.getFlashAttribute(OKConstants.SOURCE_BEAN);

		this.callerPage = Faces.getFlashAttribute(OKConstants.CALLER_PAGE_KEY);
		this.callerObject = Faces.getFlashAttribute(OKConstants.CALLER_OBJECT_KEY);
		this.selectionMode = Faces.getFlashAttribute(OKConstants.CALLER_SELECTION_MODE_KEY);

		if (this.getClass().getSimpleName().equals(targetBean)) {
			Faces.getFlash().putNow(OKConstants.CALLER_PAGE_KEY, null);
			Faces.getFlash().putNow(OKConstants.CALLER_OBJECT_KEY, null);
			Faces.getFlash().putNow(OKConstants.TARGET_BEAN, null);

		} else if (this.getClass().getSimpleName().equals(this.sourceBean)) {
			Faces.getFlash().putNow(OKConstants.SOURCE_BEAN, null);
			Faces.getFlash().putNow(OKConstants.CALLER_OBJECT_KEY, null);
			Faces.getFlash().putNow(OKConstants.CALLER_SELECTION_MODE_KEY, null);
		}
	}

	/**
	 * Gets the pretty context.
	 *
	 * @return the prettyContext
	 */
	protected PrettyContext getPrettyContext() {
		return PrettyContext.getCurrentInstance();
	}

	/**
	 * Gets the view id.
	 *
	 * @return the view id
	 */
	protected String getViewId() {
		return OKConstants.PRETTY_PREFIX + this.getPrettyContext().getCurrentMapping().getId();
	}

	/**
	 * Gets the query string.
	 *
	 * @return the query string
	 */
	protected String getQueryString() {
		return this.getPrettyContext().getRequestQueryString().toQueryString();
	}

	/**
	 * Gets the query string param value.
	 *
	 * @param param
	 *            the param
	 * @return the query string param value
	 */
	protected String getQueryStringParamValue(String param) {
		return this.getPrettyContext().getRequestQueryString().getParameter(param);
	}

	/**
	 * Gets the pretty redirector.
	 *
	 * @return the pretty redirector
	 */
	protected PrettyRedirector getPrettyRedirector() {
		return PrettyRedirector.getInstance();
	}

	/**
	 * Gets the request context.
	 *
	 * @return the requestContext
	 */
	protected RequestContext getRequestContext() {
		return RequestContext.getCurrentInstance();
	}

	/**
	 * Gets the partial view context.
	 *
	 * @return the partialViewContext
	 */
	protected PartialViewContext getPartialViewContext() {
		return FacesContext.getCurrentInstance().getPartialViewContext();
	}

	/**
	 * Call page.
	 *
	 * @param pageToCall
	 *            the page to call
	 * @param callerPageObject
	 *            the caller page object
	 * @param targetBean
	 *            the target bean
	 * @param selectionMode
	 *            the selection mode
	 * @return the string
	 */
	public String callPage(String pageToCall, Object callerPageObject, String targetBean, String selectionMode) {
		Faces.setFlashAttribute(OKConstants.CALLER_PAGE_KEY, this.getViewId());
		Faces.setFlashAttribute(OKConstants.CALLER_OBJECT_KEY, callerPageObject);
		Faces.setFlashAttribute(OKConstants.TARGET_BEAN, targetBean);
		Faces.setFlashAttribute(OKConstants.SOURCE_BEAN, this.getClass().getSimpleName());
		Faces.setFlashAttribute(OKConstants.CALLER_SELECTION_MODE_KEY, selectionMode);
		return pageToCall;
	}

	/**
	 * Return to caller page.
	 *
	 * @return the string
	 */
	public String returnToCallerPage() {
		Faces.setFlashAttribute(OKConstants.CALLER_OBJECT_KEY, this.callerObject);
		Faces.setFlashAttribute(OKConstants.SOURCE_BEAN, this.sourceBean);
		return this.callerPage;
	}

	/**
	 * Upload files to anexos list.
	 *
	 * @param anexos
	 *            the anexos
	 * @param componentName
	 *            the component name
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ServletException
	 *             the servlet exception
	 */
	public void uploadFilesToAnexosList(List<Anexo> anexos, String componentName) throws IOException, ServletException {
		Collection<Part> parts = Faces.getRequest().getParts();
		parts.stream().filter(part -> componentName.equals(part.getName()) && part.getSize() > OKConstants.VALOR_ZERO).forEach(part -> {
			this.adicionaAnexo(part, anexos, componentName);
		});
	}

	/**
	 * Adiciona anexo.
	 *
	 * @param part
	 *            the part
	 * @param anexos
	 *            the anexos
	 * @param componentName
	 *            the component name
	 */
	private void adicionaAnexo(Part part, List<Anexo> anexos, String componentName) {
		try {
			String[] fileInfo = OKFileHandler.extractInfoFromFileName(part.getSubmittedFileName());
			byte[] fileData = OKFileHandler.readDataFromInputStream(part.getInputStream());
			List<String> filesNames = anexos.stream().map(anexo -> anexo.getNomeArquivo()).collect(Collectors.toList());
			if (!filesNames.contains(fileInfo[0])) {
				anexos.add(new Anexo(fileInfo[0], fileInfo[1], fileData));
			}
		} catch (Exception e) {
			throw new OKBusinessException("{msg.erro.upload.arquivos}", e, ":mainForm:" + componentName);
		}
	}

	/**
	 * Removes the anexo.
	 *
	 * @param anexos
	 *            the anexos
	 * @param index
	 *            the index
	 */
	public void removeAnexo(List<Anexo> anexos, int index) {
		anexos.remove(index);
	}

	/**
	 * Download anexo.
	 *
	 * @param anexo
	 *            the anexo
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void downloadAnexo(Anexo anexo) throws IOException {
		byte[] fileData = anexo.getFileData();
		if (fileData == null) {
			fileData = OKFileHandler.loadFileToByteArray(anexo.getCaminhoArquivo());
		}
		StringBuilder fileName = new StringBuilder(anexo.getNomeArquivo());
		fileName.append(OKConstants.PONTO_FINAL).append(anexo.getExtensaoArquivo());
		Faces.sendFile(fileData, fileName.toString(), true);
		Faces.responseComplete();
	}

	/**
	 * Checks if is search event.
	 *
	 * @return true, if is search event
	 */
	protected boolean isSearchEvent() {
		String sourceEventId = Faces.getRequestParameter(OKConstants.EVENT_SOURCE_KEY);
		return sourceEventId != null;
	}

}
