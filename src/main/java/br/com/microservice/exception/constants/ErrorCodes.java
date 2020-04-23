package br.com.microservice.exception.constants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCodes {

	INTERNAL_SERVER_ERROR("Internal server error"), BAD_REQUEST("Bad"), NOT_FOUND("Not found");

	private final String message;

}
