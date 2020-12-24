package br.com.apirest_springboot.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileStorageException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FileStorageException(String exception) {
		super(exception);
	}

	public FileStorageException(String exception, Throwable cause) {
		super(exception, cause);
	}
	
}
