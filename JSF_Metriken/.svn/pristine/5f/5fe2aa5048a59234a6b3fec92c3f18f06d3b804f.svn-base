package br.com.metriken.util;

import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.NavigationHandler;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;

import br.com.metriken.view.base.AbstractManagedBean;
import br.com.metriken.view.base.AbstractViewHelper;

public final class FacesUtils {

	private FacesUtils() {

	}

	/**
	 * Executa uma determinada função JavaScript informada pelo usuário.
	 * 
	 * @param script
	 */
	public static void executeJavaScript(String script) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute(script);
	}

	/**
	 * Adiciona o atributo informado na sessão com o identificador passado como
	 * parâmetro
	 * 
	 * @param attrName
	 * @param attr
	 */
	public static void addAttributeToSession(String attrName, Object attr) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute(attrName, attr);
	}

	/**
	 * faz o redirecionamento de acordo com o outcome informado.
	 * 
	 * @param outcome
	 */
	public static void redirect(String outcome) {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
		navigationHandler.handleNavigation(context, null, outcome);
	}

	/**
	 * Atualiza todos os componentes de acordo com os ID's informados.
	 * 
	 * @param componentsId
	 */
	public static void updateComponents(String... componentsId) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.update(Arrays.asList(componentsId));
	}

	/**
	 * Exibe uma determinada mensagem para o usuário dentro de uma janela modal.
	 * 
	 * @param titulo
	 *            O título do modal ou a chave da mensagem
	 * @param isTitleResourceBundle
	 *            Informa se o texto deve ser recuperado do arquivo de
	 *            mensagens.
	 * @param mensagem
	 *            A mensagem do modal ou a chave da mensagem.
	 * @param isTextResourceBundle
	 *            Informa se o texto deve ser recuperado do arquivo de
	 *            mensagens.
	 */
	public static void addInfoModalMessage(String titulo, boolean isTitleResourceBundle, String mensagem, boolean isTextResourceBundle) {
		FacesUtils.preparaModal(titulo, isTitleResourceBundle, mensagem, isTextResourceBundle, true);
	}

	/**
	 * Exibe uma determinada mensagem de erro para o usuário dentro de uma
	 * janela modal.
	 * 
	 * @param titulo
	 *            O título do modal ou a chave da mensagem
	 * @param isTitleResourceBundle
	 *            Informa se o texto deve ser recuperado do arquivo de
	 *            mensagens.
	 * @param mensagem
	 *            A mensagem do modal ou a chave da mensagem.
	 * @param isTextResourceBundle
	 *            Informa se o texto deve ser recuperado do arquivo de
	 *            mensagens.
	 */
	public static void addErrorModalMessage(String titulo, boolean isTitleResourceBundle, String mensagem, boolean isTextResourceBundle) {
		FacesUtils.preparaModal(titulo, isTitleResourceBundle, mensagem, isTextResourceBundle, false);
	}

	/**
	 * Prepara o modal a ser exibido para o usuário.
	 * 
	 * @param titulo
	 * @param isTitleResourceBundle
	 * @param mensagem
	 * @param isTextResourceBundle
	 * @param isInfoModal
	 */
	private static void preparaModal(String titulo, boolean isTitleResourceBundle, String mensagem, boolean isTextResourceBundle, boolean isInfoModal) {
		FacesContext context = FacesContext.getCurrentInstance();
		HtmlOutputText tituloModal = (HtmlOutputText) context.getViewRoot().findComponent("modaisForm:tituloModal");
		HtmlOutputText textModal = (HtmlOutputText) context.getViewRoot().findComponent("modaisForm:textModal");
		tituloModal.setValue(isTitleResourceBundle ? FacesUtils.getMessageByKey(titulo) : titulo);
		textModal.setValue(isTextResourceBundle ? FacesUtils.getMessageByKey(mensagem) : mensagem);
		FacesUtils.addAttributeToSession("tipoModal", isInfoModal ? "info" : "erro");
		FacesUtils.updateComponents("modaisForm");
		FacesUtils.executeJavaScript("$('#messagesModal').modal('show');");
	}

	/**
	 * Recupera a mensagem do arquivo bundle que estiver associada com a chave
	 * informada.
	 * 
	 * @param key
	 *            Chave
	 * @return
	 */
	public static String getMessageByKey(String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle resourceBundle = context.getApplication().getResourceBundle(context, "msgs");
		return resourceBundle.containsKey(key) ? resourceBundle.getString(key) : null;
	}

	/**
	 * Adiciona o NamedBean informado ao mapa da sessão de acordo com a chave.
	 * 
	 * @param beanName
	 * @param bean
	 */
	public static <T extends AbstractManagedBean<?>> void setBeanInSession(String beanName, T bean) {
		if (!beanName.contains("$")) {
			Map<String, Object> mapa = Faces.getExternalContext().getSessionMap();
			mapa.put(beanName, bean);
		}
	}

	/**
	 * Recupera o NamedBean associado a chave informada.
	 * 
	 * @param beanName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static AbstractManagedBean<? extends AbstractViewHelper> getBeanFromSession(String beanName) {
		Map<String, Object> mapa = Faces.getExternalContext().getSessionMap();
		if (beanName != null && mapa.containsKey(beanName)) {
			return (AbstractManagedBean<? extends AbstractViewHelper>) mapa.get(beanName);
		}
		return null;
	}

}
