package br.com.ufape.petshare.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ufape.petshare.services.exceptions.AuthenticationException;
import br.com.ufape.petshare.services.exceptions.AuthorizationException;
import br.com.ufape.petshare.services.exceptions.InvalidReceivedItemException;
import br.com.ufape.petshare.services.exceptions.InvalidStatusException;
import br.com.ufape.petshare.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<StandardError> RuntimeException(RuntimeException e, HttpServletRequest request) {
		int httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
		StandardError err = new StandardError(httpStatus, "Erro inesperado", e.getMessage(), request.getRequestURI());
		e.printStackTrace();
		return ResponseEntity.status(httpStatus).body(err);
	}

	@ExceptionHandler({ ObjectNotFoundException.class })
	public ResponseEntity<StandardError> ObjectNotFoundException(RuntimeException e, HttpServletRequest request) {
		int httpStatus = HttpStatus.NOT_FOUND.value();
		StandardError err = new StandardError(httpStatus, "Não Encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(httpStatus).body(err);
	}
	
	@ExceptionHandler({ InvalidStatusException.class })
	public ResponseEntity<StandardError> invalidStatusException(RuntimeException e, HttpServletRequest request) {
		int httpStatus = HttpStatus.UNPROCESSABLE_ENTITY.value();
		StandardError err = new StandardError(httpStatus, "Entidade não processável", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(httpStatus).body(err);
	}
	
	@ExceptionHandler({ InvalidReceivedItemException.class })
	public ResponseEntity<StandardError> invalidReceivedItemException(RuntimeException e, HttpServletRequest request) {
		int httpStatus = HttpStatus.UNPROCESSABLE_ENTITY.value();
		StandardError err = new StandardError(httpStatus, "Entidade não processável", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(httpStatus).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		int httpStatus = HttpStatus.UNPROCESSABLE_ENTITY.value();
		ValidationError err = new ValidationError(httpStatus, "Erro de validação",
				"Campos não preenchidos corretamente", request.getRequestURI());
		System.out.println(e.getBindingResult().getFieldErrors());
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(httpStatus).body(err);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<StandardError> badCredentialsException(BadCredentialsException e,
			HttpServletRequest request) {
		int httpStatus = HttpStatus.UNAUTHORIZED.value();
		StandardError err = new StandardError(httpStatus, "Não autorizado", "E-mail e/ou senha incorreto/s ",
				request.getRequestURI());
		return ResponseEntity.status(httpStatus).body(err);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<StandardError> UsernameNotFoundException(UsernameNotFoundException e,
			HttpServletRequest request) {
		int httpStatus = HttpStatus.UNAUTHORIZED.value();
		StandardError err = new StandardError(httpStatus, "Não autorizado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(httpStatus).body(err);
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<StandardError> authenticationException(AuthenticationException e, HttpServletRequest request) {
		int httpStatus = HttpStatus.UNAUTHORIZED.value();
		StandardError err = new StandardError(httpStatus,
				"Não autorizado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(httpStatus).body(err);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorizationException(AuthorizationException e, HttpServletRequest request) {
		int httpStatus = HttpStatus.FORBIDDEN.value();
		StandardError err = new StandardError(httpStatus,
				"Não autorizado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(httpStatus).body(err);
	}

}
