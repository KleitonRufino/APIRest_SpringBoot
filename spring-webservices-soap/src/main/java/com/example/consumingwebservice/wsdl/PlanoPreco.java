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
@XmlRootElement(name = "plano-preco")
public class PlanoPreco {

	@XmlElement(name = "qtd-dependentes", namespace = "http://www.uri.com.br/v11")
	private Long qtdDependentes;
	@XmlElement(name = "data-inicio-carencia", namespace = "http://www.uri.com.br/v11")
	private String dataInicioCarencia;
	@XmlElement(name = "prazo-carencia", namespace = "http://www.uri.com.br/v11")
	private Long prazoCarencia;

}
