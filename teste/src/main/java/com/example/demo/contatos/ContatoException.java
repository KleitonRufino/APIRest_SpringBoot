package com.example.demo.contatos;

public class ContatoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4383148346718588512L;

	public ContatoException(Exception e) throws ContatoException {
		super(e);
	}

}
