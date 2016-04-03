package br.com.maestroautomacoes.portal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.application.NavigationHandler;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

public abstract class SiteUtil {

	/**
	 * Recupera a mensagem associada a uma determinada chave do arquivo de
	 * mensagens.
	 * 
	 * @param key
	 * @return
	 */
	public static String getMessageFromProperty(String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle resourceBundle = context.getApplication().getResourceBundle(context, "msgs");
		String msg = "";
		if (resourceBundle.containsKey(key)) {
			msg = resourceBundle.getString(key);
		}
		return msg;
	}

	/**
	 * Atualiza um, ou vários, componentes com os identificadores informados.
	 * 
	 * @param ids
	 */
	public static void updateComponents(String... ids) {
		List<String> componentes = new ArrayList<String>();
		componentes.addAll(Arrays.asList(ids));
		RequestContext context = RequestContext.getCurrentInstance();
		context.update(componentes);
	}

	/**
	 * Recupera um determinado ManagedBean da sessão.
	 * 
	 * @param name
	 * @return
	 */
	public static Object getBeanFromSession(String name) {
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		ELResolver elResolver = elContext.getELResolver();
		Object bean = null;
		try {
			bean = elResolver.getValue(elContext, null, name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * Adiciona um determinado atributo na sessão.
	 * 
	 * @param name
	 * @param attribute
	 */
	public static void addAttibuteInHttpSession(String name, Object attribute) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute(name, attribute);
	}

	/**
	 * Recupera um determinado atributo da sessão.
	 * 
	 * @param name
	 * @return
	 */
	public static Object getAttibuteFromHttpSession(String name) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Object atributo = session.getAttribute(name);
		return atributo;
	}

	/**
	 * Executa uma determinada função JavaScript.
	 * 
	 * @param script
	 */
	public static void executeJavaScript(String script) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute(script);
	}

	/**
	 * Adiciona uma nova mensagem ao contexto para ser exibida com o Growl.
	 * 
	 * @param mensagem
	 * @param severidade
	 */
	public static void addGrowlMessage(String mensagem, Severity severidade, boolean isResourceBundle) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severidade, isResourceBundle ? SiteUtil.getMessageFromProperty(mensagem) : mensagem, ""));
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("formDialogoPadrao");
	}

	/**
	 * Adiciona uma nova mensagem ao contexto para ser exibida como uma simples
	 * Message.
	 * 
	 * @param mensagem
	 * @param severidade
	 */
	public static void addSimpleMessage(String mensagem, Severity severidade, boolean isResourceBundle) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severidade, isResourceBundle ? SiteUtil.getMessageFromProperty(mensagem) : mensagem, ""));
	}

	/**
	 * Exibe um modal com a mensagem informada.
	 * 
	 * @param mensagem
	 * @param isResourceBundle
	 */
	public static void addInfoModalMessage(String mensagem, boolean isResourceBundle) {
		RequestContext context = RequestContext.getCurrentInstance();
		HtmlForm form = (HtmlForm) FacesContext.getCurrentInstance().getViewRoot().findComponent(":formDialogoPadrao");
		HtmlOutputText outputText = (HtmlOutputText) form.findComponent(":formDialogoPadrao:textDialogoPadrao");
		outputText.setValue(isResourceBundle ? SiteUtil.getMessageFromProperty(mensagem) : mensagem);
		context.update("formDialogoPadrao");
		context.execute("dialogo.show()");
	}

	/**
	 * Redireciona o usuário para a página informada.
	 * 
	 * @param outcome
	 */
	public static void redirect(String outcome) {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, outcome);
	}
}
