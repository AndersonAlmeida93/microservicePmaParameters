package br.com.microservice.exception.constants;

public enum ErrorCodes {

	INTERNAL_SERVER_ERROR("Internal server error"), 
	BAD_REQUEST("Bad"), 
	NOT_FOUND("Not found");

	private final String message;

	ErrorCodes(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
