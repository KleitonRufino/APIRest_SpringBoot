
package com.example.consumingwebservice;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.example.consumingwebservice.wsdl.Assinante;
import com.example.consumingwebservice.wsdl.ConsultarAssinanteRequest;
import com.example.consumingwebservice.wsdl.ConsultarAssinanteResponse;

public class ConsultarAssinanteClient extends WebServiceGatewaySupport {

public void getConsultarAssinante() {

		ConsultarAssinanteRequest request = new ConsultarAssinanteRequest();
		Assinante assinante = new Assinante();
		assinante.setMobileAssinanteId(Long.valueOf(1111));
		request.setAssinante(assinante);

		
		ConsultarAssinanteResponse consultarAssinanteResponse = (ConsultarAssinanteResponse) getWebServiceTemplate()
		.marshalSendAndReceive(request);
		System.out.println(consultarAssinanteResponse);
	}

}
