package com.example.consumingwebservice.feign;

import org.springframework.stereotype.Component;

import com.example.consumingwebservice.wsdl.Assinante;
import com.example.consumingwebservice.wsdl.ConsultarAssinanteRequest;
import com.example.consumingwebservice.wsdl.ConsultarAssinanteResponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
@Component
public class ManagerProxy {

	private final ManagerAuthorization tokenManager;
	private final SoapServicesProxy soapServicesProxy;

	public ConsultarAssinanteResponse consultarAssinante(Long mobileAssinanteId) throws Throwable {

		log.info("Realizando chamada para servico Consultar Assinante");
		ConsultarAssinanteRequest request = new ConsultarAssinanteRequest();
		Assinante assinante = new Assinante();
		assinante.setMobileAssinanteId(mobileAssinanteId);
		request.setAssinante(assinante);

		String auth = this.tokenManager.getSoapBasicAuthorization();
		return this.soapServicesProxy.consultarAssinante(request, auth);

	}

}
