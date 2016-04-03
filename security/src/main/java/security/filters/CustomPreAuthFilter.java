package security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class CustomPreAuthFilter extends BasicAuthenticationFilter {

	@Autowired
	public CustomPreAuthFilter(@Qualifier("defaultAuthenticationManager") AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			((HttpServletResponse) res).sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		chain.doFilter(req, res);
	}

}
