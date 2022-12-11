package br.com.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "nome", "nascimento" })
public class ClienteVOInsert implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4585212527599491027L;
	@JsonProperty("nome")
	private String nome;
	@JsonProperty("nascimento")
	private LocalDate nascimento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

}
