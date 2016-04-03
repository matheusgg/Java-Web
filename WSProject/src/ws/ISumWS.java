package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * É preciso de a interface esteja anotada com @WebService e seus respoectivos @WebMethod
 * para a criacao das definicoes de porta no WSDL.
 * 
 * @author Matheus
 *
 */
@WebService
public interface ISumWS {

	@WebMethod
	int sum(int x, int y);

}
