package br.com.ufape.petshare.services.exceptions;


public class InvalidReceivedItemException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidReceivedItemException(String msg) {
		super(msg);
	}

	public InvalidReceivedItemException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
