package com.example.consumingwebservice.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "codigoAplicacaoOrigem", "codigoOperacoOrigem", "correlacoIdOrigem",
		"enderecoAplicacaoOrigem", "usuarioIdOrigem", "dataHoraOrigem", "canalIdOrigem" })
@XmlRootElement(name = "meta-informacao", namespace = "http://www.uri.com.br/v1")
public class MetaInformacao {

	@XmlElement(name = "codigo-aplicacao-origem", required = true, namespace = "http://www.uri.com.br/v11")
	private String codigoAplicacaoOrigem;
	@XmlElement(name = "codigo-operacao-origem", required = true, namespace = "http://www.uri.com.br/v11")
	private String codigoOperacoOrigem;
	@XmlElement(name = "correlacao-id-origem", required = true, namespace = "http://www.uri.com.br/v11")
	private Long correlacoIdOrigem;
	@XmlElement(name = "endereco-aplicacao-origem", required = true, namespace = "http://www.uri.com.br/v11")
	private String enderecoAplicacaoOrigem;
	@XmlElement(name = "usuario-id-origem", required = true, namespace = "http://www.uri.com.br/v11")
	private Long usuarioIdOrigem;
	@XmlElement(name = "data-hora-origem", required = true, namespace = "http://www.uri.com.br/v11")
	private String dataHoraOrigem;
	@XmlElement(name = "canal-id-origem", required = true, namespace = "http://www.uri.com.br/v11")
	private String canalIdOrigem;

	public String getCodigoAplicacaoOrigem() {
		return codigoAplicacaoOrigem;
	}

	public void setCodigoAplicacaoOrigem(String codigoAplicacaoOrigem) {
		this.codigoAplicacaoOrigem = codigoAplicacaoOrigem;
	}

	public String getCodigoOperacoOrigem() {
		return codigoOperacoOrigem;
	}

	public void setCodigoOperacoOrigem(String codigoOperacoOrigem) {
		this.codigoOperacoOrigem = codigoOperacoOrigem;
	}

	public Long getCorrelacoIdOrigem() {
		return correlacoIdOrigem;
	}

	public void setCorrelacoIdOrigem(Long correlacoIdOrigem) {
		this.correlacoIdOrigem = correlacoIdOrigem;
	}

	public String getEnderecoAplicacaoOrigem() {
		return enderecoAplicacaoOrigem;
	}

	public void setEnderecoAplicacaoOrigem(String enderecoAplicacaoOrigem) {
		this.enderecoAplicacaoOrigem = enderecoAplicacaoOrigem;
	}

	public Long getUsuarioIdOrigem() {
		return usuarioIdOrigem;
	}

	public void setUsuarioIdOrigem(Long usuarioIdOrigem) {
		this.usuarioIdOrigem = usuarioIdOrigem;
	}

	public String getDataHoraOrigem() {
		return dataHoraOrigem;
	}

	public void setDataHoraOrigem(String dataHoraOrigem) {
		this.dataHoraOrigem = dataHoraOrigem;
	}

	public String getCanalIdOrigem() {
		return canalIdOrigem;
	}

	public void setCanalIdOrigem(String canalIdOrigem) {
		this.canalIdOrigem = canalIdOrigem;
	}

}
