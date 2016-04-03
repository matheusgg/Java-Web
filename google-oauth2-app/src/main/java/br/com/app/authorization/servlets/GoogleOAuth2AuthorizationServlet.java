package br.com.app.authorization.servlets;

import br.com.app.authorization.config.AuthorizationCodeFlowWrapper;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.http.GenericUrl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 04/01/2016.
 */
@WebServlet(value = "/*", loadOnStartup = 1)
public class GoogleOAuth2AuthorizationServlet extends AbstractAuthorizationCodeServlet {

	private static final long serialVersionUID = -7131748546278654894L;
	private static final String URL_CALLBACK = "http://localhost:8080/google-oauth2-app/callback";

	@Inject
	private AuthorizationCodeFlowWrapper codeFlowWrapper;

	@Override
	protected AuthorizationCodeFlow initializeFlow() throws ServletException, IOException {
		return this.codeFlowWrapper.getWrapped();
	}

	@Override
	protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		String quryString = req.getQueryString();
		StringBuilder requestedURL = new StringBuilder(url);

		if (quryString != null) {
			requestedURL.append("?").append(quryString);
		}

		req.getSession().setAttribute("google-oauth2-app-url", requestedURL);
		return new GenericUrl(URL_CALLBACK).build();
	}

	@Override
	protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
		String code = req.getParameter("code");
		return code == null ? "" : code;
	}

	@Override
	protected void onAuthorization(HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeRequestUrl authorizationUrl) throws ServletException, IOException {
		String url = authorizationUrl.build();
		req.getSession().setAttribute("google-authorization-url", url);
		resp.sendRedirect(url);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("Seja Bem-Vindo!");
	}
}
