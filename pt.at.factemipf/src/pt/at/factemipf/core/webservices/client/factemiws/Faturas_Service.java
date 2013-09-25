package pt.at.factemipf.core.webservices.client.factemiws;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.3-b01-
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(
		name = "faturas",
		targetNamespace = "http://servicos.portaldasfinancas.gov.pt/faturas/",
		wsdlLocation = "file:/C:/PROJECTS/AT/FACTEMIPF/04-Implementacao/trunk/factemipf-applet/src/main/resources/wsdl/factemiws/factemiws.wsdl")
public class Faturas_Service
extends Service
{

	private final static URL FATURAS_WSDL_LOCATION;
	private final static WebServiceException FATURAS_EXCEPTION;
	private final static QName FATURAS_QNAME = new QName("http://servicos.portaldasfinancas.gov.pt/faturas/", "faturas");

	static{
		URL url = null;
		WebServiceException e = null;
		//        try {
		//            url = new URL("file:/C:/PROJECTS/AT/FACTEMIPF/04-Implementacao/trunk/factemipf-applet/src/main/resources/wsdl/factemiws/factemiws.wsdl");
		//        } catch (MalformedURLException ex) {
		//            e = new WebServiceException(ex);
		//        }
		FATURAS_WSDL_LOCATION = url;
		FATURAS_EXCEPTION = e;
	}

	public Faturas_Service(){
		super(__getWsdlLocation(), FATURAS_QNAME);
	}

	//    public Faturas_Service(WebServiceFeature... features) {
	//        super(__getWsdlLocation(), FATURAS_QNAME, features);
	//    }

	public Faturas_Service(URL wsdlLocation){
		super(wsdlLocation, FATURAS_QNAME);
	}

	//    public Faturas_Service(URL wsdlLocation, WebServiceFeature... features) {
	//        super(wsdlLocation, FATURAS_QNAME, features);
	//    }

	public Faturas_Service(URL wsdlLocation, QName serviceName){
		super(wsdlLocation, serviceName);
	}

	//    public Faturas_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
	//        super(wsdlLocation, serviceName, features);
	//    }

	/**
	 * 
	 * @return
	 *         returns Faturas
	 */
	@WebEndpoint(name = "faturasSOAP")
	public Faturas getFaturasSOAP() {
		return super.getPort(new QName("http://servicos.portaldasfinancas.gov.pt/faturas/", "faturasSOAP"), Faturas.class);
	}

	/**
	 * 
	 * @param features
	 *            A list of {@link javax.xml.ws.WebServiceFeature} to configure
	 *            on the proxy. Supported features not in the
	 *            <code>features</code> parameter will have their default
	 *            values.
	 * @return
	 *         returns Faturas
	 */
	@WebEndpoint(name = "faturasSOAP")
	public Faturas getFaturasSOAP(WebServiceFeature... features) {
		return super.getPort(new QName("http://servicos.portaldasfinancas.gov.pt/faturas/", "faturasSOAP"), Faturas.class, features);
	}

	private static URL __getWsdlLocation() {
		if(FATURAS_EXCEPTION != null){
			throw FATURAS_EXCEPTION;
		}
		return FATURAS_WSDL_LOCATION;
	}

}
