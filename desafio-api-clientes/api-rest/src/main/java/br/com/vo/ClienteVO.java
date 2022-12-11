package br.com.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "nome", "nascimento", "idade" })
public class ClienteVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4585212527599491027L;
	@JsonProperty("id")
	private Long id;
	@JsonProperty("nome")
	private String nome;
	@JsonProperty("nascimento")
	private LocalDate nascimento;
	@JsonProperty("idade")
	private int idade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

}
