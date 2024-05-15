package com.api.rest.exception.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.rest.dto.ErrorDetail;
import com.api.rest.dto.ValidationError;
import com.api.rest.exception.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class RestExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest httpServletRequest){
		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
		errorDetail.setTitle("Recurso no encontrado");
		errorDetail.setDetail(exception.getClass().getName());
		errorDetail.setDeveloperMessage(exception.getMessage());
		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest httpServletRequest){
		ErrorDetail errorDetail = new ErrorDetail();
		errorDetail.setTimeStamp(new Date().getTime());
		errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
		
		String requestPath= (String) httpServletRequest.getAttribute("javax.servlet.error.request_uri");
		
		if(requestPath==null) {
			requestPath = httpServletRequest.getRequestURI();
		}
		
		errorDetail.setTitle("Validación fallida");
		errorDetail.setDetail("La validaión de entrada falló");
		errorDetail.setDeveloperMessage(exception.getMessage());
		
		List<FieldError> fieldErrors= exception.getBindingResult().getFieldErrors();
		
		for(FieldError fieldError: fieldErrors) {
			List<ValidationError> validationErrorList= errorDetail.getErrors().get(fieldError.getField());
		
			if(validationErrorList ==null) {
				validationErrorList = new ArrayList<ValidationError>();
				errorDetail.getErrors().put(fieldError.getField(), validationErrorList);
			}
			
			ValidationError valitdationerror= new ValidationError();
			valitdationerror.setCode(fieldError.getCode());
			valitdationerror.setMessage(fieldError.getCode());
			valitdationerror.setMessage(fieldError.getDefaultMessage());
			validationErrorList.add(valitdationerror);
		}		
		
		return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
	}
}
