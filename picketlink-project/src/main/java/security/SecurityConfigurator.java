package security;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;

@Named
@ApplicationScoped
public class SecurityConfigurator implements Serializable {

	private static final long serialVersionUID = 1905414031192043664L;

	/**
	 * Realiza a configuração de segurança do PicketLink.
	 * 
	 * @param event
	 */
	public void configureSecurity(@Observes SecurityConfigurationEvent event) {
		SecurityConfigurationBuilder builder = event.getBuilder();

		builder.http().forPath("/logout").logout().redirectTo("/login.jsf").allPaths().authenticateWith().form().loginPage("/login.jsf")
				.errorPage("/error.jsf").restoreOriginalRequest().forPath("/protected/*").authorizeWith().role("ADMIN").redirectTo("/login.jsf")
				.whenForbidden();
	}

}
