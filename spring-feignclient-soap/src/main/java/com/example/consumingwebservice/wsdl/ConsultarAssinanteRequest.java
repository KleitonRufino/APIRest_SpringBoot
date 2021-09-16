package com.example.consumingwebservice.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "assinante" })
@XmlRootElement(name = "ConsultarAssinanteRequest", namespace = "http://www.uri.com.br/v1")
public class ConsultarAssinanteRequest {

	@XmlElement(name = "assinante", required = true, namespace = "http://www.uri.com.br/v1")
	private Assinante assinante;

	public Assinante getAssinante() {
		return assinante;
	}

	public void setAssinante(Assinante assinante) {
		this.assinante = assinante;
	}

}
