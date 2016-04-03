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
 * Para a utiliza��o de interceptors, � necess�ria a utiliza��o da anota��o
 * '@Named' no lugar de '@ManagaedBean', isto �, � necess�rio que este bean seja
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
	 * Este ser� o m�todo interceptado. Antes da sua execu��o ser� verificada a
	 * permiss�o do usu�rio que estiver tentando us�-lo.
	 */
	@Secured({ "ROLE_ADMIN" })
	public void metodo() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "O usu�rio logado tem permiss�o para acessar este m�todo!", ""));
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
