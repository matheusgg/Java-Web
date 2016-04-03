package test;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

import ws.ISumWS;

/**
 * A anotacao @WebServiceClient transforma esta classe em um service client.
 * Deste modo, essa classe deve estender javax.xml.ws.Service. Ela será
 * utilizada na anotacao @WebServiceRef para indicar que esta classe é
 * reponsavel por criar instancias de endpoints anotados com @WebServiceRef.
 * 
 * @author Matheus
 *
 */
@WebServiceClient(name = "SumWSService", targetNamespace = "http://ws/", wsdlLocation = "http://localhost:8080/WSProject/SumWS?wsdl")
public class ServiceProvider extends Service {

	public ServiceProvider(URL wsdlDocumentLocation, QName serviceName) {
		super(wsdlDocumentLocation, serviceName);
	}

	/**
	 * A anotacao @WebEndpoint serve para indicar que este metodo produz
	 * instancias para endpoints do tipo ISumWS anotados com @WebServiceRef.
	 * 
	 * @return
	 */
	@WebEndpoint
	public ISumWS getSumWS() {
		return super.getPort(ISumWS.class);
	}

}
