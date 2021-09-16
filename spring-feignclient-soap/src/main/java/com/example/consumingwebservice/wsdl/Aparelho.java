package com.example.consumingwebservice.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "aparelho")
public class Aparelho {

	@XmlElement(name = "msisdn", namespace = "http://www.uri.com.br/v11")
	private String msisdn;
	@XmlElement(name = "modelo", namespace = "http://www.uri.com.br/v11")
	private Modelo modelo;

}
