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
@XmlRootElement(name = "modelo")
public class Modelo {

	@XmlElement(name = "tecnologia", namespace = "http://www.uri.com.br/v11")
	private String tecnologia;
	@XmlElement(name = "geracao-tecnologica", namespace = "http://www.uri.com.br/v11")
	private String geracaoTecnologica;

}
