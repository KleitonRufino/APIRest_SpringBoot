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
//@XmlType(name = "assinante", propOrder = { "mobileAssinanteId", "tipoAssinatura", "dataAtivacao", 
//		"situacao", "dataSituacao", "situacaoRede", "aparelho"})
@XmlRootElement(name = "assinante", namespace = "http://www.uri.com.br/v1")
public class Assinante {

	@XmlElement(name = "mobile-assinante-id", namespace = "http://www.uri.com.br/v11")
	private Long mobileAssinanteId;
	@XmlElement(name = "tipo-assinatura", namespace = "http://www.uri.com.br/v11")
	private String tipoAssinatura;
	@XmlElement(name = "data-ativacao", namespace = "http://www.uri.com.br/v11")
	private String dataAtivacao;
	@XmlElement(name = "situacao", namespace = "http://www.uri.com.br/v11")
	private String situacao;
	@XmlElement(name = "data-situacao", namespace = "http://www.uri.com.br/v11")
	private String dataSituacao;
	@XmlElement(name = "situacao-rede", namespace = "http://www.uri.com.br/v11")
	private String situacaoRede;
	@XmlElement(name = "aparelho", namespace = "http://www.uri.com.br/v11")
	private Aparelho aparelho;
	@XmlElement(name = "plano-preco", namespace = "http://www.uri.com.br/v11")
	private PlanoPreco planoPreco;
	@XmlElement(name = "titularidade-assinante", namespace = "http://www.uri.com.br/v11")
	private TitularidadeAssinante titularidadeAssinante;

}
