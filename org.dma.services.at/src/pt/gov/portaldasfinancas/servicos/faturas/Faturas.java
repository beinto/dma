
package pt.gov.portaldasfinancas.servicos.faturas;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "faturas", targetNamespace = "http://servicos.portaldasfinancas.gov.pt/faturas/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Faturas {


    /**
     * 
     * @param registerInvoiceRequest
     * @return
     *     returns pt.gov.portaldasfinancas.servicos.faturas.RegisterInvoiceResponseType
     */
    @WebMethod(operationName = "RegisterInvoice", action = "http://servicos.portaldasfinancas.gov.pt/faturas/RegisterInvoice")
    @WebResult(name = "RegisterInvoiceResponseElem", targetNamespace = "http://servicos.portaldasfinancas.gov.pt/faturas/", partName = "RegisterInvoiceResponse")
    public RegisterInvoiceResponseType registerInvoice(
        @WebParam(name = "RegisterInvoiceElem", targetNamespace = "http://servicos.portaldasfinancas.gov.pt/faturas/", partName = "RegisterInvoiceRequest")
        RegisterInvoiceType registerInvoiceRequest);

}
