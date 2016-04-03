package view;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;
import org.picketlink.Identity.AuthenticationResult;

@Named
@ViewScoped
public class LoginMB implements Serializable {

	private static final long serialVersionUID = -7761861223523141144L;

	@Inject
	private Identity identity;

	public String login() {
		AuthenticationResult result = this.identity.login();
		if (AuthenticationResult.SUCCESS.equals(result)) {
			return "/home?faces-redirect=true";
		}
		return "/login?faces-redirect=true";
	}

}
