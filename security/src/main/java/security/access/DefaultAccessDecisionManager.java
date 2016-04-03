package security.access;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

@Component
public class DefaultAccessDecisionManager implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException {

		if (authentication instanceof AnonymousAuthenticationToken) {
			throw new AccessDeniedException("Não Autorizado!");
		}

		String requestUrl = ((FilterInvocation) object).getRequestUrl();

		if (StringUtils.isNotBlank(requestUrl)) {
			// for (ConfigAttribute configAttribute : configAttributes) {
			//
			// }
		}
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
