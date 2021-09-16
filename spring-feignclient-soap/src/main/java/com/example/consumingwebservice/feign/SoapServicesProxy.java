package com.example.consumingwebservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.consumingwebservice.wsdl.ConsultarAssinanteRequest;
import com.example.consumingwebservice.wsdl.ConsultarAssinanteResponse;

@FeignClient(name = "soap-services", url = "url_base", configuration = SoapClientConfiguration.class)
public interface SoapServicesProxy {

	@PostMapping(value = "uri", consumes = "application/xml", produces = "application/xml")
	ConsultarAssinanteResponse consultarAssinante(@RequestBody ConsultarAssinanteRequest consultarAssinanteRequest,
			@RequestHeader("Authorization") String authorization) throws Throwable;

}
