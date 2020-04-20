package br.com.microservice.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.microservice.exception.constants.ErrorCodes;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String LOG_ERROR = "An unexpected error occur";

	// PADRAO
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		logger.error(LOG_ERROR, ex);

		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), ErrorCodes.INTERNAL_SERVER_ERROR);

		request.getDescription(false);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
	}

	// PADRAO
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<String> errors = new ArrayList<>();
		fieldErrors.forEach(f -> {
			errors.add(f.getField() + " : " + f.getDefaultMessage());
		});

		ExceptionResponse exceptionResponse = new ExceptionResponse(errors.toString(), ErrorCodes.BAD_REQUEST);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);

	}

	// PADRAO
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
		logger.error("Invalid Arguments ", ex);

		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), ErrorCodes.INTERNAL_SERVER_ERROR);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);

	}

	@ExceptionHandler(NotFoudException.class)
	public ResponseEntity<Object> handleNotFoundException(NotFoudException ex) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), ErrorCodes.NOT_FOUND);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);

	}

	public ResponseEntity<Object> handlerBadRequestException(BadRequestException ex) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), ErrorCodes.BAD_REQUEST);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
	}

}
