package br.com.login;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.interceptor.Access;
import br.com.model.Usuario;
import br.com.security.Provider;

@Named
@SessionScoped
public class LoginBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4057732800963955529L;

	@Inject
	private Usuario usuario;

	@Inject
	private Provider securityProvider;

	public String login() {
		String paginaRetorno = "pretty:login";
		boolean autenticado = false;
		try {
			Authentication auth = this.securityProvider.authenticate(new UsernamePasswordAuthenticationToken(this.usuario.getLogin(), this.usuario.getSenha()));
			autenticado = auth.isAuthenticated();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		} finally {
			this.usuario = new Usuario();
		}

		if (autenticado) {
			paginaRetorno = "pretty:home";
		}
		return paginaRetorno;
	}

	public String logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		sessao.removeAttribute("permissoes");
		this.usuario = new Usuario();
		return "pretty:login";
	}

	@Access({ "ROLE_ADMIN" })
	public void exibePermissoes() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		sessao.setAttribute("permissoes", usuarioLogado.getPermissoes());
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
