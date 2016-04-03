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
	 * Método que verifica se o timeout da sessão foi atingido, caso positivo,
	 * ele redireciona a solicitação para a página de login para o usuário poder
	 * inserir as informações requieridas novamente
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
	 * Método que faz a verificação da autenticação do usuário que está tentanto
	 * acessar qualquer página. A verificação é executada sempre depois da fase
	 * INVOKE APPLICATION e antes da fase RENDER RESPONSE. Aqui, é testado se o
	 * usuário está logado através do método estático da classe LoginBean.
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
