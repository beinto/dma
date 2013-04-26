package eu.europa.ec.taxud.vies.services.checkvat;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.1 in JDK 6
 * Generated source version: 2.1
 *
 */
@WebServiceClient(
	name = "checkVatService",
	targetNamespace = "urn:ec.europa.eu:taxud:vies:services:checkVat",
	wsdlLocation = "http://ec.europa.eu/taxation_customs/vies/checkVatService.wsdl")
public class CheckVatService
	extends Service
{

	private final static URL CHECKVATSERVICE_WSDL_LOCATION;

	static{
		URL url = null;
		try{
			url = new URL("http://ec.europa.eu/taxation_customs/vies/checkVatService.wsdl");
		}catch(MalformedURLException e){
			e.printStackTrace();
		}
		CHECKVATSERVICE_WSDL_LOCATION = url;
	}

	public CheckVatService(URL wsdlLocation, QName serviceName){
		super(wsdlLocation, serviceName);
	}

	public CheckVatService(){
		super(CHECKVATSERVICE_WSDL_LOCATION, new QName("urn:ec.europa.eu:taxud:vies:services:checkVat", "checkVatService"));
	}

	/**
	 *
	 * @return
	 *         returns CheckVatPortType
	 */
	@WebEndpoint(name = "checkVatPort")
	public CheckVatPortType getCheckVatPort() {
		return super.getPort(new QName("urn:ec.europa.eu:taxud:vies:services:checkVat", "checkVatPort"), CheckVatPortType.class);
	}

	/**
	 *
	 * @param features
	 *            A list of {@link javax.xml.ws.WebServiceFeature} to configure
	 *            on the proxy. Supported features not in the
	 *            <code>features</code> parameter will have their default
	 *            values.
	 * @return
	 *         returns CheckVatPortType
	 */
	@WebEndpoint(name = "checkVatPort")
	public CheckVatPortType getCheckVatPort(WebServiceFeature... features) {
		return super.getPort(new QName("urn:ec.europa.eu:taxud:vies:services:checkVat", "checkVatPort"), CheckVatPortType.class, features);
	}

}