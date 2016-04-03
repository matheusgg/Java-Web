package br.com.app.authorization.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created on 06/01/2016.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationCodeFlowWrapper implements Serializable {

	private static final long serialVersionUID = 3599194142377089870L;
	private GoogleAuthorizationCodeFlow wrapped;

}
