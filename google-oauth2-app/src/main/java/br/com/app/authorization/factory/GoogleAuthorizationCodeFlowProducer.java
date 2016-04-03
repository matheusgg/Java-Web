package br.com.app.authorization.factory;

import br.com.app.authorization.config.AuthorizationCodeFlowWrapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 06/01/2016.
 */
@ApplicationScoped
public class GoogleAuthorizationCodeFlowProducer {

	private static final String CLIENT_ID = "69222409317-66k1e6u8s3linskm5o9a295uj79uarpg.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "nlQ4ROn33nmPCA_KZDZ5uO8m";
	private static final List<String> SCOPES = Arrays.asList("profile", "email");

	@Produces
	@SessionScoped
	public AuthorizationCodeFlowWrapper produces() {
		try {
			NetHttpTransport transport = new NetHttpTransport();
			JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
			DataStoreFactory dataStoreFactory = new MemoryDataStoreFactory();

			GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(transport, jacksonFactory, CLIENT_ID, CLIENT_SECRET, SCOPES).setDataStoreFactory
					(dataStoreFactory).build();

			return new AuthorizationCodeFlowWrapper(flow);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

}
