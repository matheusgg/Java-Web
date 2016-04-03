package br.com.security;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.login.LoginBean;

public class SecurityPhaseListener implements PhaseListener {
	private HttpSession sessao;
	private NavigationHandler navegador;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * M�todo que verifica se o timeout da sess�o foi atingido, caso positivo,
	 * ele redireciona a solicita��o para a p�gina de login para o usu�rio poder
	 * inserir as informa��es requieridas novamente
	 */
	@Override
	public void afterPhase(PhaseEvent event) {
		String viewId = event.getFacesContext().getViewRoot().getViewId();
		HttpSession session = (HttpSession) event.getFacesContext()
				.getExternalContext().getSession(false);
		if (!viewId.contains("login") && session == null) {
			this.navegador = event.getFacesContext().getApplication()
					.getNavigationHandler();
			this.navegador.handleNavigation(FacesContext.getCurrentInstance(),
					null, "login");
		}

	}

	/**
	 * M�todo que faz a verifica��o da autentica��o do usu�rio que est� tentanto
	 * acessar qualquer p�gina. A verifica��o � executada sempre depois da fase
	 * INVOKE APPLICATION e antes da fase RENDER RESPONSE. Aqui, � testado se o
	 * usu�rio est� logado atrav�s do m�todo est�tico da classe LoginBean.
	 */
	@Override
	public void beforePhase(PhaseEvent event) {
		this.sessao = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		if (event.getPhaseId() == PhaseId.RENDER_RESPONSE
				&& LoginBean.isUsuarioLogado(sessao) == false) {
			this.navegador = event.getFacesContext().getApplication()
					.getNavigationHandler();
			this.navegador.handleNavigation(FacesContext.getCurrentInstance(),
					null, "login");
		}

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
