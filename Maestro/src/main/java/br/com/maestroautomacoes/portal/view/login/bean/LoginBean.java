package br.com.maestroautomacoes.portal.view.login.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.maestroautomacoes.portal.util.Outcome;
import br.com.maestroautomacoes.portal.util.SiteUtil;
import br.com.maestroautomacoes.portal.view.login.helper.LoginViewHelper;
import br.com.maestroautomacoes.portal.view.orcamento.bean.OrcamentoBean;

@Named
@ConversationScoped
public class LoginBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3489090136798924099L;

	@Inject
	private LoginViewHelper viewHelper;

	@Inject
	private OrcamentoBean orcamentoBean;

	@PostConstruct
	public void inicializa() {
		this.viewHelper.inicializar();
	}

	public void login(String from) throws Exception {
		boolean logado = this.viewHelper.getSecurityManager().login(this.viewHelper.getEmail(), this.viewHelper.getSenha());
		if (!logado && !from.equals("efetuar_login")) {
			SiteUtil.redirect(Outcome.LOGIN);
		} else if (logado && !from.equals("menu_login")) {
			this.orcamentoBean.iniciarPagina();
			SiteUtil.redirect(Outcome.ORCAMENTO);
		} else {
			SiteUtil.executeJavaScript("modalLogin.hide()");
		}
		this.viewHelper.inicializar();
	}

	public String logout() {
		this.viewHelper.getSecurityManager().logout();
		return Outcome.HOME;
	}

	/**
	 * @return the viewHelper
	 */
	public LoginViewHelper getViewHelper() {
		return viewHelper;
	}
}
