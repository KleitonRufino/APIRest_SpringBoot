package br.com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceEmptyException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceEmptyException(String exception) {
		super(exception);
	}
	
	
	
}
