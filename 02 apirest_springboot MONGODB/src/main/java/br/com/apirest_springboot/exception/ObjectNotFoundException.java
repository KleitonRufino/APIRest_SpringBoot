package br.com.apirest_springboot.exception;

public class ObjectNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String message) {
		super(message);
	}

}
