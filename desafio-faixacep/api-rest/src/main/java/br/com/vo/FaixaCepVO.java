package br.com.vo;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "codigo_loja", "faixa_inicio", "faixa_fim" })
public class FaixaCepVO extends ResourceSupport implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4585212527599491027L;
	@JsonProperty("id")
	private Long key;
	@JsonProperty("codigo_loja")
	private String codigoLoja;
	@JsonProperty("faixa_inicio")
	private int faixaInicio;
	@JsonProperty("faixa_fim")
	private int faixaFim;

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getCodigoLoja() {
		return codigoLoja;
	}

	public void setCodigoLoja(String codigoLoja) {
		this.codigoLoja = codigoLoja;
	}

	public int getFaixaInicio() {
		return faixaInicio;
	}

	public void setFaixaInicio(int faixaInicio) {
		this.faixaInicio = faixaInicio;
	}

	public int getFaixaFim() {
		return faixaFim;
	}

	public void setFaixaFim(int faixaFim) {
		this.faixaFim = faixaFim;
	}

}
