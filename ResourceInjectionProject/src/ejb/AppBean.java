package ejb;

import javax.ejb.Stateless;

/**
 * EJB de sessao sem estado
 * 
 * @author Matheus
 * 
 */
@Stateless
public class AppBean {

	@Override
	public String toString() {
		return super.getClass().getSimpleName();
	}
}
