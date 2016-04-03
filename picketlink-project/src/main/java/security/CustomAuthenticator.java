package security;

import javax.enterprise.context.RequestScoped;
import javax.faces.FacesException;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.inject.Inject;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Utils;
import org.picketlink.annotations.PicketLink;
import org.picketlink.authentication.BaseAuthenticator;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.model.basic.User;

@PicketLink
@RequestScoped
public class CustomAuthenticator extends BaseAuthenticator {

	@Inject
	private DefaultLoginCredentials loginCredentials;

	@Inject
	private IdentityManager identityManager;

	@Inject
	private RelationshipManager relationshipManager;

	@Override
	public void authenticate() {
		String username = this.loginCredentials.getUserId();
		String password = this.loginCredentials.getPassword();

		ConfigurableNavigationHandler nh = (ConfigurableNavigationHandler) Faces.getApplication().getNavigationHandler();

		if (!Utils.isBlank(username) && !Utils.isBlank(password)) {
			/*
			 * Definição de Role
			 */
			Role adminRole = new Role("ADMIN");
			this.identityManager.add(adminRole);

			/*
			 * Criação e adição de usuários na memória
			 */
			User user = new User("root");
			user.setEmail("email@email.com");
			user.setEnabled(true);
			user.setFirstName("Matheus");
			user.setLastName("Góes");

			this.identityManager.add(user);

			/*
			 * Relacionamento de usuários com as roles
			 */
			BasicModel.grantRole(this.relationshipManager, user, adminRole);

			super.setAccount(user);
			super.setStatus(AuthenticationStatus.SUCCESS);

			nh.performNavigation("/home");

		} else {
			super.setStatus(AuthenticationStatus.FAILURE);
			throw new FacesException("Usuário ou senha inválidos!");
			// Messages.addError(null, "Usuário ou senha inválidos!");
		}
	}

}
