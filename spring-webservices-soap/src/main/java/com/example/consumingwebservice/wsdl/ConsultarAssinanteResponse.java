package com.example.consumingwebservice.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "assinante" })
@XmlRootElement(name = "ConsultarAssinanteResponse", namespace = "http://www.uri.com.br/v1")
public class ConsultarAssinanteResponse {

	@XmlElement(name = "assinante", required = true, namespace = "http://www.uri.com.br/v1")
	private Assinante assinante;
	
	
	public Assinante getAssinante() {
		return assinante;
	}

	public void setAssinante(Assinante assinante) {
		this.assinante = assinante;
	}
	
}
