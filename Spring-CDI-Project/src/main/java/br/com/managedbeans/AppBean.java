package br.com.managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.security.Secured;

/**
 * Para a utilização de interceptors, é necessária a utilização da anotação
 * '@Named' no lugar de '@ManagaedBean', isto é, é necessário que este bean seja
 * um bean CDI.
 * 
 * @author Matheus
 * 
 */
@Named
@SessionScoped
public class AppBean {
	private String permissao;

	public AppBean() {
		this.verificaPermissao();
	}

	private void verificaPermissao() {
		SecurityContext secContext = SecurityContextHolder.getContext();
		UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) secContext.getAuthentication();
		this.permissao = user.getAuthorities().toString();
	}

	/**
	 * Este será o método interceptado. Antes da sua execução será verificada a
	 * permissão do usuário que estiver tentando usá-lo.
	 */
	@Secured({ "ROLE_ADMIN" })
	public void metodo() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "O usuário logado tem permissão para acessar este método!", ""));
	}

	/**
	 * @return the permissao
	 */
	public String getPermissao() {
		return permissao;
	}

	/**
	 * @param permissao
	 *            the permissao to set
	 */
	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

}
