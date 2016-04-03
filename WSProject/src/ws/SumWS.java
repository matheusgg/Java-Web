package ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class SumWS implements ISumWS {

	@Override
	@WebMethod(action = "sum")
	public int sum(@WebParam(name = "x") int x, @WebParam(name = "y") int y) {
		return x + y;
	}

}
