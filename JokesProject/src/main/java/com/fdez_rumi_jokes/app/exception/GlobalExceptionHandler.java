package com.fdez_rumi_jokes.app.exception;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<ErrorResponse> handleDataIntegrityException(DataIntegrityException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Error de integridad de datos: " + ex.getMessage());
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran resultados que coincidan: " + ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    String errorMessage = ex.getBindingResult().getFieldErrors().stream()
	        .map(error -> error.getField() + ": " + error.getDefaultMessage())
	        .collect(Collectors.joining(", "));
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
	    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado: " + ex.getMessage());
	}
}