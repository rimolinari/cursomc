package com.rmolinari.cursomc.resources.excepetion;

import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rmolinari.cursomc.services.Excepetions.DataIntegrityException;
import com.rmolinari.cursomc.services.Excepetions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExcepetionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequestWrapper request){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), "teste", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> integrityException(DataIntegrityException e, HttpServletRequestWrapper request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequestWrapper request){ 
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação.", System.currentTimeMillis());
		
		for(FieldError x: e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}
