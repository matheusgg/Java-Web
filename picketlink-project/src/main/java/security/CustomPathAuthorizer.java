package security;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.picketlink.config.http.PathConfiguration;
import org.picketlink.http.authorization.PathAuthorizer;

@RequestScoped
public class CustomPathAuthorizer implements PathAuthorizer {

	@Override
	public boolean authorize(PathConfiguration pathConfiguration, HttpServletRequest request, HttpServletResponse response) {
		return false;
	}

}
