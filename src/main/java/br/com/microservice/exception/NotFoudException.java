package br.com.microservice.exception;

public class NotFoudException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoudException(String message) {

		super(message);
	}

}
