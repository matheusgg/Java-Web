package br.com.maestroautomacoes.portal.view.faleConosco.bean;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.maestroautomacoes.portal.mail.MailProvider;
import br.com.maestroautomacoes.portal.util.Outcome;
import br.com.maestroautomacoes.portal.util.SiteUtil;
import br.com.maestroautomacoes.portal.view.faleConosco.helper.FaleConoscoViewHelper;

@Named
@ConversationScoped
public class FaleConoscoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7318612534074604175L;

	@Inject
	private FaleConoscoViewHelper viewHelper;

	public String iniciarPagina() {
		this.viewHelper.inicializar();
		return Outcome.FALE_CONOSCO;
	}

	public void envioEmail() throws Exception {
		mandaEmailHtml();
		this.iniciarPagina();
		this.viewHelper.setModal(true);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, SiteUtil.getMessageFromProperty("msg_email_enviado_sucesso"), ""));

	}

	private void mandaEmailHtml() throws Exception {
		MailProvider.mandaEmailHtml(this.viewHelper.getNome(), this.viewHelper.getEmail(), this.viewHelper.getAmbiente(),
				this.viewHelper.getAssuntoSelecionado(), this.viewHelper.getMensagem(), null, true);
	}

	/**
	 * @return the viewHelper
	 */
	public FaleConoscoViewHelper getViewHelper() {
		return viewHelper;
	}

}
