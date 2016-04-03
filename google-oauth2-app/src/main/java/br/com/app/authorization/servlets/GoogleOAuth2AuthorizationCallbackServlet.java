package br.com.app.authorization.servlets;

import br.com.app.authorization.config.AuthorizationCodeFlowWrapper;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeCallbackServlet;
import com.google.api.client.http.GenericUrl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Created on 04/01/2016.
 */
@WebServlet(value = "/callback", loadOnStartup = 1)
public class GoogleOAuth2AuthorizationCallbackServlet extends AbstractAuthorizationCodeCallbackServlet {

	private static final long serialVersionUID = 8494203867240676017L;
	private static final String URL_CALLBACK = "http://localhost:8080/google-oauth2-app/callback";

	@Inject
	private AuthorizationCodeFlowWrapper codeFlowWrapper;

	@Override
	protected AuthorizationCodeFlow initializeFlow() throws ServletException, IOException {
		return this.codeFlowWrapper.getWrapped();
	}

	@Override
	protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
		return new GenericUrl(URL_CALLBACK).build();
	}

	@Override
	protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
		String code = req.getParameter("code");
		return code == null ? "" : code;
	}

	@Override
	protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, Credential credential) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Optional<StringBuilder> requestedURLOptional = Optional.ofNullable((StringBuilder) session.getAttribute("google-oauth2-app-url"));

		if (requestedURLOptional.isPresent()) {
			session.removeAttribute("google-oauth2-app-url");
			StringBuilder requestedURL = requestedURLOptional.get();

			if (requestedURL.indexOf("?") == -1) {
				requestedURL.append("?");
			} else {
				requestedURL.append("&");
			}

			resp.sendRedirect(requestedURL.append("code=").append(this.getUserId(req)).toString());
		}
	}
}
