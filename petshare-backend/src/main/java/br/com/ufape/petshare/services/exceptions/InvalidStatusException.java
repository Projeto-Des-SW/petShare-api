package br.com.ufape.petshare.services.exceptions;


public class InvalidStatusException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidStatusException(String msg) {
		super(msg);
	}

	public InvalidStatusException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
