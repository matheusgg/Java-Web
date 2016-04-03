package br.com.login;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.model.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean {
	private HttpSession sessao = null;
	private Usuario usuarioLogado;
	private String usuario;
	private String senha;

	/**
	 * M�todo que verifica se o usu�rio e a senh�o est�o corretos, caso
	 * afirmativo, o usu�rio logado � setado na sess�o. Se o login falhar, o
	 * usu�rio � direcionado para uma tela de erro.
	 * 
	 * @return Matheus
	 */
	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = null;
		String outcome = "";

		if (this.usuario.equals("matheus.goes") && this.senha.equals("123")) {
			this.sessao = (HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(true);

			this.usuarioLogado = new Usuario();
			this.usuarioLogado.setLogin(this.usuario);
			this.usuarioLogado.setSenha(this.senha);

			this.sessao.setAttribute("user", this.usuarioLogado);
			outcome = "/sucesso?faces-redirect=true";
		} else {
			this.senha = "";
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Usu�rio ou senha inv�lidos!", "");
			context.addMessage(null, msg);
		}

		return outcome;
	}

	/**
	 * M�todo que faz o logout invalidando a sess�o e obrigando o usu�rio a
	 * informar o login e a senha novamente.
	 * 
	 * @return
	 */
	public String logout() {
		this.sessao = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		this.sessao.invalidate();
		return "/login?faces-redirect=true";
	}

	public static boolean isUsuarioLogado(HttpSession sessao) {
		boolean isLogado = false;
		try {
			if (sessao != null && sessao.getAttribute("user") != null) {
				isLogado = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isLogado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
