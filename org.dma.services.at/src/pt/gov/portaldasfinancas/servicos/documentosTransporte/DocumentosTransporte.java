package pt.gov.portaldasfinancas.servicos.documentosTransporte;

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
@WebService(
	name = "documentosTransporte",
	targetNamespace = "https://servicos.portaldasfinancas.gov.pt/sgdtws/documentosTransporte/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
	ObjectFactory.class
})
public interface DocumentosTransporte {

	/**
	 * 
	 * @param stockMovement
	 * @return
	 *         returns https.servicos_portaldasfinancas_gov_pt.sgdtws.
	 *         documentostransporte.StockMovementResponse
	 */
	@WebMethod(
		action = "https://servicos.portaldasfinancas.gov.pt/sgdtws/documentosTransporte/")
	@WebResult(
		name = "envioDocumentoTransporteResponseElem",
		targetNamespace = "https://servicos.portaldasfinancas.gov.pt/sgdtws/documentosTransporte/",
		partName = "StockMovementResponse")
	public StockMovementResponse envioDocumentoTransporte(
			@WebParam(
				name = "envioDocumentoTransporteRequestElem",
				targetNamespace = "https://servicos.portaldasfinancas.gov.pt/sgdtws/documentosTransporte/",
				partName = "StockMovement")
			StockMovement stockMovement);

}