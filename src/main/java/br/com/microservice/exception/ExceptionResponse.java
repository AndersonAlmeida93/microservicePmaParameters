package br.com.microservice.exception;

import br.com.microservice.exception.constants.ErrorCodes;
import lombok.Data;

@Data
public class ExceptionResponse {

	private final String message;

	private final ErrorCodes errorCodes;
}
