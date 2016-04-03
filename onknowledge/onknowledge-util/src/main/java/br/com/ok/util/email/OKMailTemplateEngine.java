package br.com.ok.util.email;

import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import br.com.ok.util.constants.OKConstants;
import br.com.ok.util.security.OKPasswordHandler;

/**
 * The Class OKMailTemplateEngine.
 *
 * @author Matheus
 * @version 1.0 - 18/10/2014
 */
public final class OKMailTemplateEngine {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(OKPasswordHandler.class.getName());

	/**
	 * Instantiates a new OK mail template engine.
	 */
	private OKMailTemplateEngine() {

	}

	/**
	 * Load message from tamplate.
	 *
	 * @param template
	 *            the template
	 * @param params
	 *            the params
	 * @return the string
	 */
	public static String loadMessageFromTamplate(String template, Map<String, Object> params) {
		String message = null;

		try (StringWriter writer = new StringWriter()) {
			params.put(OKConstants.VELOCITY_TEMPLATE_KEY, template);
			params.put(OKConstants.VELOCITY_SERVER_URL_KEY, OKMailTemplateEngine.getServerURL());

			Properties properties = new Properties();
			properties.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
			properties.put("file.resource.loader.path", OKMailTemplateEngine.getTemplatesResourcePath());
			properties.put("file.resource.loader.cache", "true");
			properties.put("file.resource.loader.modificationCheckInterval", OKConstants.VALOR_DOIS.toString());

			Velocity.init(properties);

			VelocityContext context = new VelocityContext();
			for (Entry<String, Object> param : params.entrySet()) {
				context.put(param.getKey(), param.getValue());
			}

			Velocity.mergeTemplate(OKConstants.VELOCITY_MAIN_TEMPLATE, OKConstants.ISO_8859_1_ENCODING, context, writer);
			message = writer.toString();

		} catch (Exception e) {
			OKMailTemplateEngine.LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}

		return message;
	}

	/**
	 * Gets the resource path.
	 *
	 * @return the resource path
	 */
	private static String getTemplatesResourcePath() {
		ExternalContext ec = OKMailTemplateEngine.getExternalContext();
		return ec.getRealPath(OKConstants.BASE_MAIL_TEMPLATE_PATH);
	}

	/**
	 * Gets the server url.
	 *
	 * @return the server url
	 */
	private static String getServerURL() {
		ExternalContext ec = OKMailTemplateEngine.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();

		StringBuilder resourcesURL = new StringBuilder("http://ec2-54-69-191-248.us-west-2.compute.amazonaws.com:8080");
		// resourcesURL.append(OKConstants.DOIS_PONTOS).append(OKConstants.BARRA_NORMAL).append(OKConstants.BARRA_NORMAL);
		// resourcesURL.append(request.getLocalName()).append(OKConstants.DOIS_PONTOS).append(request.getLocalPort());

		return resourcesURL.toString();
	}

	/**
	 * Gets the external context.
	 *
	 * @return the external context
	 */
	private static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}
}
